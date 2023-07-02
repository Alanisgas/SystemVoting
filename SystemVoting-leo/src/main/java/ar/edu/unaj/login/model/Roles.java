package ar.edu.unaj.login.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document(collection = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    @Id
    private int id;

    private String roles;
}
