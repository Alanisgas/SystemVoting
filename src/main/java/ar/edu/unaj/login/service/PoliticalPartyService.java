package ar.edu.unaj.login.service;

import ar.edu.unaj.login.dto.CreatePPartyRequest;
import ar.edu.unaj.login.model.PoliticalParty;
import ar.edu.unaj.login.repository.PoliticalPartyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticalPartyService {
    private final PoliticalPartyRepository politicalPartyRepository;

    public PoliticalPartyService(PoliticalPartyRepository politicalPartyRepository) {
        this.politicalPartyRepository = politicalPartyRepository;
    }

    public PoliticalParty getPoliticalPartyById(String id) {
        return politicalPartyRepository.getAllPoliticalParties().stream()
                .filter(party -> party.getIdPP().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addPoliticalParty(CreatePPartyRequest politicalPartyRequest) {
        PoliticalParty politicalParty = new PoliticalParty();
        politicalParty.setNamePP(politicalPartyRequest.getNamePP());
        politicalParty.setIdPP(politicalPartyRequest.getIdPP());
        politicalParty.setPersonal(politicalPartyRequest.getPersonal());
        politicalPartyRepository.addPoliticalParty(politicalParty);
    }
    public List<PoliticalParty> getAllPoliticalParties() {
        return politicalPartyRepository.getAllPoliticalParties();
    }

}
