package ar.edu.unaj.login.controller;

import ar.edu.unaj.login.dto.CreatePPartyRequest;

import ar.edu.unaj.login.model.PoliticalParty;
import ar.edu.unaj.login.service.PoliticalPartyService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/political-party")
public class PoliticalPartyController {
    private final PoliticalPartyService politicalPartyService;

    public PoliticalPartyController(PoliticalPartyService politicalPartyService) {
        this.politicalPartyService = politicalPartyService;
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path = "/political-parties")
    public ResponseEntity<List<PoliticalParty>> getAllPoliticalParties() {
        List<PoliticalParty> politicalParties = politicalPartyService.getAllPoliticalParties();
        return ResponseEntity.ok(politicalParties);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(path="/political-party")
    public ResponseEntity<Void> addPoliticalParty(@RequestBody CreatePPartyRequest politicalPartyRequest) {
        politicalPartyService.addPoliticalParty(politicalPartyRequest);
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
