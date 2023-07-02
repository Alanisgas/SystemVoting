package ar.edu.unaj.login.repository;

import ar.edu.unaj.login.model.PoliticalParty;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PoliticalPartyRepository {
    private final List<PoliticalParty> politicalParties;

    public PoliticalPartyRepository() {
        politicalParties = new ArrayList<>();
    }

    public List<PoliticalParty> getAllPoliticalParties() {
        return politicalParties;
    }
    public PoliticalParty getPoliticalPartyById(String id) {
        return politicalParties.stream()
                .filter(party -> party.getIdPP().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addPoliticalParty(PoliticalParty politicalParty) {
        politicalParties.add(politicalParty);
    }
}
