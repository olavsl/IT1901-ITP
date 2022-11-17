package personal_finance.json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.Set;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import personal_finance.core.PersonalFinanceModel;

public class PersonalFinancePersistence {

    private File storageFile;
    private ObjectMapper mapper;

    public PersonalFinancePersistence() {
        mapper = createObjectMapper();
    }

    public static SimpleModule createJacksonModule(Set<PersonalFinanceModelParts> parts) {
        return new PersonalFinanceModule(parts);
    }

    public enum PersonalFinanceModelParts {
        USERS, TRANSACTIONS, BUDGET, CATEGORY
    }

    public void setStorageFile(final String saveFile) throws IOException {
        Path path = Paths.get(System.getProperty("user.home"), saveFile);
        this.storageFile = new File(path.toUri());
        this.storageFile.createNewFile();       
    }

    public File getStorageFile() {
        return this.storageFile;
    }

    public static ObjectMapper createObjectMapper() {
        return new ObjectMapper().registerModule(new PersonalFinanceModule(EnumSet.allOf(PersonalFinanceModelParts.class)));
    }

    public PersonalFinanceModel readPersonalFinanceModel(Reader reader) throws StreamReadException, DatabindException, IOException {
        return mapper.readValue(reader, PersonalFinanceModel.class);
    }

    public void writePersonalFinanceModel(PersonalFinanceModel personalFinanceModel, Writer writer) throws StreamWriteException, DatabindException, IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, personalFinanceModel);
    }

    /**
     * @return PersonalFinanceModel - which carries the information of users in the database.
     * @throws IOException
     */
    public PersonalFinanceModel loadPersonalFinanceModel() throws IOException {
        Reader reader = new FileReader(getStorageFile(), StandardCharsets.UTF_8);
        return readPersonalFinanceModel(reader);
    }

    /**
     * Writes the User objects of the parsed PersonalFinanceModel to the database.
     * 
     * @param personalFinanceModel
     * @throws IOException
     */
    public void savePersonalFinanceModel(PersonalFinanceModel personalFinanceModel) throws IOException {
        Writer writer = new FileWriter(getStorageFile(), StandardCharsets.UTF_8);
        writePersonalFinanceModel(personalFinanceModel, writer);
    }

}
