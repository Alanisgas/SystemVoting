package ar.edu.unaj.login.dto;

import lombok.Data;
@Data
public class VoteRequest {
    private String userId;
    private String politicalPartyId;
}
