package ar.edu.unaj.login.repository;

import ar.edu.unaj.login.model.Roles;
import ar.edu.unaj.login.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("rolesRepository")
public interface RolesRepository extends MongoRepository<User,String> {
    Roles findByRoles(String roles);

}
