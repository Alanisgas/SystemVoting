package ar.edu.unaj.login.controller;

import ar.edu.unaj.login.dto.VoteRequest;
import ar.edu.unaj.login.model.PoliticalParty;
import ar.edu.unaj.login.model.User;
import ar.edu.unaj.login.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/Vote")
@Api(value = "Vote")
public class VoteController {
    private final VoteService voteService;
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Successfully"),
            @ApiResponse(code = 401, message = "You are not authenticated")})
    @PostMapping(path = "/vote")
    public ResponseEntity<Void> vote(@RequestBody User user, String voto) throws Exception {

        if (user.isAutenticate()) {
            voteService.vote(user, voto);
            return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @GetMapping
    public ResponseEntity<Map<String, Integer>> getAllVotes() {
        Map<String, Integer> voteCounts = voteService.getAllVotes();
        return ResponseEntity.ok(voteCounts);
    }
}
