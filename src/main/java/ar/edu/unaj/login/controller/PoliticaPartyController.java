package ar.edu.unaj.login.controller;

import ar.edu.unaj.login.model.PoliticalParty;
import ar.edu.unaj.login.service.PoliticalPartyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/politicals")
@Api(value = "political")
public class PoliticaPartyController {
    @Autowired
    private PoliticalPartyService politicalPartyService;
    @CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation(value = "create political",response = Iterable.class)
    @PostMapping(path = "create-politicalParty")
    public ResponseEntity<Void> createPolitical(PoliticalParty politicalParty){
        politicalPartyService.addPolitical(politicalParty);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<PoliticalParty> getPolitical(){
        return  politicalPartyService.findAllParty();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("political/{politicalId}")
    public Optional<PoliticalParty> getPoliticalId(@PathVariable String politicalId){
        return  politicalPartyService.getPolitical(politicalId);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("political/{politicalIds }")
    public String deletePolitical(@PathVariable String politicalIds){
        return  politicalPartyService.deleteParty(politicalIds);
    }


}
