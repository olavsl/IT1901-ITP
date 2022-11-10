package personal_finance.restserver;

import com.fasterxml.jackson.databind.Module;
import java.util.EnumSet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import personal_finance.json.PersonalFinancePersistence;
import personal_finance.json.PersonalFinancePersistence.PersonalFinanceModelParts;

@SpringBootApplication
public class PersonalFinanceModelApplication {
    
    @Bean
    public Module objectMapperModule() {
        return PersonalFinancePersistence.createJacksonModule(EnumSet.of(PersonalFinanceModelParts.USERS));
    }

    public static void main(String[] args) {
        SpringApplication.run(PersonalFinanceModelApplication.class, args);
    }

}
