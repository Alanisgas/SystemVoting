package ar.edu.unaj.login.repository;

import ar.edu.unaj.login.model.PoliticalParty;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PoliticalPartyRepository extends MongoRepository<PoliticalParty,String> {

}
