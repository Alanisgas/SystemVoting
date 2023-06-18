package ar.edu.unaj.login.service;

import ar.edu.unaj.login.model.PoliticalParty;
import ar.edu.unaj.login.repository.PoliticalPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PoliticalPartyService {
    @Autowired
    private PoliticalPartyRepository politicalPartyRepository;
    public PoliticalParty addPolitical(PoliticalParty politicalParty){
        politicalParty.setIdPP(UUID.randomUUID().toString().split("-")[0]);
        return politicalPartyRepository.save(politicalParty);

    }
    public Optional<PoliticalParty> getPolitical(String intId){
        return  politicalPartyRepository.findById(intId);
    }
    public List<PoliticalParty> findAllParty(){return politicalPartyRepository.findAll();}

    public  String deleteParty(String partyId){
        politicalPartyRepository.deleteById(partyId);
        return  partyId+ "Political Party is delete";
    }

}
