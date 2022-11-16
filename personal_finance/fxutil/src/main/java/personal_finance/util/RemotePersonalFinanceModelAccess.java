package personal_finance.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

import personal_finance.core.PersonalFinanceModel;
import personal_finance.core.User;
import personal_finance.json.PersonalFinancePersistence;

public class RemotePersonalFinanceModelAccess implements PersonalFinanceModelAccess {

    private URI endpointBaseUri;
    private static final String APPLICATION_JSON = "application/json";
    private static final String ACCEPT_HEADER = "Accept";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private ObjectMapper objectMapper;
    private PersonalFinanceModel model;

    public RemotePersonalFinanceModelAccess(URI endpointBaseUri) {
        this.endpointBaseUri = endpointBaseUri;
        objectMapper = PersonalFinancePersistence.createObjectMapper();
        this.model = getPersonalFinanceModel();
    }

    public RemotePersonalFinanceModelAccess() {
        setEndpointBaseUri();
        objectMapper = PersonalFinancePersistence.createObjectMapper();
        this.model = getPersonalFinanceModel();
    }

    private String uriParam(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    private URI userUri(String username) {
        return endpointBaseUri.resolve("users/").resolve(uriParam(username));
    }

    private void setEndpointBaseUri() {
        try {
            this.endpointBaseUri = new URI("http://localhost:8080/personal_finance/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PersonalFinanceModel getPersonalFinanceModel() {
        if (this.model == null) {
            HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .GET()
                .build();
            try {
                final HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
                String responseString = response.body();
                this.model = objectMapper.readValue(responseString, PersonalFinanceModel.class);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return this.model;
    }

    @Override
    public User getUser(String username) {
        return this.model.getUser(username);
    }

    @Override
    public void putUser(User user) {
        try {
            String json = objectMapper.writeValueAsString(user);
            HttpRequest request = HttpRequest.newBuilder(userUri(user.getUsername()))
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .PUT(BodyPublishers.ofString(json))
                .build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            Boolean updated = objectMapper.readValue(responseString, Boolean.class);
            
            if (updated != null) {
                this.model.putUser(user);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void postUser(User user) {
        System.out.println("postUser(User user) : " + userUri(user.getUsername()));

        try {
            String json = objectMapper.writeValueAsString(user);
            HttpRequest request = HttpRequest.newBuilder(userUri(user.getUsername()))
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .POST(BodyPublishers.ofString(json))
                .build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            Boolean created = objectMapper.readValue(responseString, Boolean.class);

            if (created != null) {
                this.model.addUser(user);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            HttpRequest request = HttpRequest.newBuilder(userUri(user.getUsername()))
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .DELETE()
                .build();
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request, BodyHandlers.ofString());
            String responseString = response.body();
            Boolean removed = objectMapper.readValue(responseString, Boolean.class);   

            if (removed != null) {
                this.model.deleteUser(user.getUsername());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
}
