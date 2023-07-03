package ar.edu.unaj.login.service;

import ar.edu.unaj.login.dto.CreatePPartyRequest;
import ar.edu.unaj.login.model.PoliticalParty;
import ar.edu.unaj.login.repository.PoliticalPartyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticalPartyService {
    private final PoliticalPartyRepository politicalPartyRepository;
    private final VoteService voteService;

 //   public PoliticalPartyService() {

  //  }
    public PoliticalPartyService(PoliticalPartyRepository politicalPartyRepository, VoteService voteService) {
        this.politicalPartyRepository = politicalPartyRepository;
        this.voteService = voteService;
    }

    public PoliticalParty getPoliticalPartyById(String id) {
        return politicalPartyRepository.getAllPoliticalParties().stream()
                .filter(party -> party.getIdPP().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addPoliticalParty(CreatePPartyRequest politicalPartyRequest) {
        String politicalPartyId = politicalPartyRequest.getIdPP();
        if (voteService.politicalPartyExists(politicalPartyId)) {
            throw new IllegalStateException("\n" + "The PoliticalParty key already exists");
        }
        else {
            PoliticalParty politicalParty = new PoliticalParty();
            politicalParty.setNamePP(politicalPartyRequest.getNamePP());
            politicalParty.setIdPP(politicalPartyRequest.getIdPP());
            politicalParty.setPersonal(politicalPartyRequest.getPersonal());
            politicalPartyRepository.addPoliticalParty(politicalParty);
            voteService.addPoliticalPartyToVoteList(politicalParty.getIdPP());
        }
    }
    public List<PoliticalParty> getAllPoliticalParties() {
        return politicalPartyRepository.getAllPoliticalParties();
    }

}
