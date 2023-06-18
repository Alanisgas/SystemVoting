package ar.edu.unaj.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "politicalPartydto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliticalPartyDto {
    private String namePP;
    private String numberIDPP;
}
