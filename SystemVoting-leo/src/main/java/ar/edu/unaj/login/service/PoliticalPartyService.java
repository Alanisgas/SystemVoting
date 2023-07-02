package ar.edu.unaj.login.service;

import ar.edu.unaj.login.dto.CreatePPartyRequest;
import ar.edu.unaj.login.model.PoliticalParty;
import ar.edu.unaj.login.model.User;
import ar.edu.unaj.login.repository.PoliticalPartyRepository;
import ar.edu.unaj.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticalPartyService {
    @Autowired
    private UserRepository userRepository;
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

    public void addPoliticalParty(CreatePPartyRequest politicalPartyRequest,String username) {
        User user =userRepository.findByName(username);
        if (user == null || !"administrador".equalsIgnoreCase(String.valueOf(user.getRoles()))) {
            PoliticalParty politicalParty = new PoliticalParty();
            politicalParty.setNamePP(politicalPartyRequest.getNamePP());
            politicalParty.setIdPP(politicalPartyRequest.getIdPP());
            politicalParty.setPersonal(politicalPartyRequest.getPersonal());
            politicalPartyRepository.addPoliticalParty(politicalParty);
        } else{
            throw new IllegalStateException("No tiene la autoridad para agregar un partido político.");




        }
    }
    public List<PoliticalParty> getAllPoliticalParties() {
        return politicalPartyRepository.getAllPoliticalParties();
    }

}
