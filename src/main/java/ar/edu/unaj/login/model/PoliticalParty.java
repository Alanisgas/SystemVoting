package ar.edu.unaj.login.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document (collection = "politicalParty")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliticalParty {
    @Id
    private String idPP;
    private String namePP;
    private String numberIDPP;
    private List<String> personal;
}
