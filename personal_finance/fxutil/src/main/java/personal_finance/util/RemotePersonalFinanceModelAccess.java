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
    }

    public RemotePersonalFinanceModelAccess() {
        setEndpointBaseUri();
        objectMapper = PersonalFinancePersistence.createObjectMapper();
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
        if (model == null) {
            HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .GET()
                .build();
            try {
                final HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
                this.model = objectMapper.readValue(response.body(), PersonalFinanceModel.class);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return model;
    }

    @Override
    public User getUser(String username) {
        User oldUser = this.model.getUser(username);
        
        if (oldUser == null || (!(oldUser instanceof User))) {
            HttpRequest request = HttpRequest.newBuilder(userUri(username))
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .GET()
                .build();
            try {
                final HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
                String responseString = response.body();
                User user = objectMapper.readValue(responseString, User.class);
                this.model.putUser(user);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return oldUser;
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
            Boolean added = objectMapper.readValue(responseString, Boolean.class);

            if (added != null) {
                model.putUser(user);
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
                model.deleteUser(user.getUsername());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
}
