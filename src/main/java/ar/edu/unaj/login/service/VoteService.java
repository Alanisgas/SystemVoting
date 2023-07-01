package ar.edu.unaj.login.service;

import ar.edu.unaj.login.dto.VoteRequest;
import ar.edu.unaj.login.model.PoliticalParty;
import ar.edu.unaj.login.model.User;
import ar.edu.unaj.login.repository.PoliticalPartyRepository;
import ar.edu.unaj.login.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@AllArgsConstructor
@NoArgsConstructor
@Service
public class VoteService {
    private UserRepository userRepository;
    private PoliticalPartyRepository politicalPartyRepository;

    private Map<String, Integer> voteList = new HashMap<>();
    private Map<String, Boolean> votedUsers = new HashMap<>();
    public Map<String, Integer> getList() {
        return voteList;
    }
    public VoteService(PoliticalPartyRepository politicalPartyRepository) {
        this.politicalPartyRepository = politicalPartyRepository;
        this.voteList = new HashMap<>();
        initializeVoteCounts();
    }
    public Map<String, Integer> getAllVotes() {
        return voteList;
    }
    private void initializeVoteCounts() {
        List<PoliticalParty> politicalParties = politicalPartyRepository.getAllPoliticalParties();
        for (PoliticalParty party : politicalParties) {
            voteList.put(party.getIdPP(), 0);
        }
    }
    public void vote(User user, String voto) {
        VoteRequest votoRequest = new VoteRequest();
        votoRequest.setUserId(user.getUserId());
        votoRequest.setPoliticalPartyId(voto);
        if(hasUserVoted(votoRequest.getUserId())){
            throw new IllegalStateException("You have already voted");
        }
        else{
            String clave = votoRequest.getPoliticalPartyId();
            if (voteList.containsKey(clave)) {
                int valor = voteList.get(clave);
                voteList.put(clave, valor + 1);
                votedUsers.put(votoRequest.getUserId(), true);
            } else {
                throw new IllegalArgumentException("It is not a valid vote, please try again.");
            }
        }
    }

    private boolean hasUserVoted(String userId){
        if (votedUsers != null && votedUsers.containsKey(userId))
            return true;
        else{
            return false;
        }
    }
    public void addPoliticalPartyToVoteList(String politicalPartyId) {
        voteList.put(politicalPartyId, 0);
    }
    public boolean politicalPartyExists(String id){
        if (voteList.containsKey(id)){
            return true;
        }else {
            return false;
        }
    }
}
