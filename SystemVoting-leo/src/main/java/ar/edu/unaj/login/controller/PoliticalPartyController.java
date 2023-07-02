package ar.edu.unaj.login.controller;

import ar.edu.unaj.login.dto.CreatePPartyRequest;

import ar.edu.unaj.login.model.PoliticalParty;
import ar.edu.unaj.login.model.User;
import ar.edu.unaj.login.service.PoliticalPartyService;
import ar.edu.unaj.login.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/political-party")
public class PoliticalPartyController {
    @Autowired
    UserService userService;
    private final PoliticalPartyService politicalPartyService;

    public PoliticalPartyController(PoliticalPartyService politicalPartyService) {
        this.politicalPartyService = politicalPartyService;
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public ResponseEntity<List<PoliticalParty>> getAllPoliticalParties() {
        List<PoliticalParty> politicalParties = politicalPartyService.getAllPoliticalParties();
        return ResponseEntity.ok(politicalParties);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public ResponseEntity<Void> addPoliticalParty(@RequestBody CreatePPartyRequest politicalPartyRequest,
                                                  @RequestHeader(value = "name") String name) {
        Optional<User> userOptional = userService.getUserByName(name);

        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        politicalPartyService.addPoliticalParty(politicalPartyRequest,name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 404, message = "Political Party not found")})
    public ResponseEntity<PoliticalParty> getPoliticalPartyById(@PathVariable String id) {
        PoliticalParty politicalParty = politicalPartyService.getPoliticalPartyById(id);
        if (politicalParty != null) {
            return ResponseEntity.ok(politicalParty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
