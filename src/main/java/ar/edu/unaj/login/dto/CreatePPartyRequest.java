package ar.edu.unaj.login.dto;

import java.util.List;
import lombok.Data;
@Data
public class CreatePPartyRequest {
    private String idPP;
    private String namePP;
    private String numberIDPP;
    private List<String> personal;

    public CreatePPartyRequest(String idPP, String namePP, String numberIDPP, List<String> personal) {
        this.idPP = idPP;
        this.namePP = namePP;
        this.numberIDPP = numberIDPP;
        this.personal = personal;
    }
}
