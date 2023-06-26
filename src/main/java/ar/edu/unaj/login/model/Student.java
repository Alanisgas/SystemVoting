package ar.edu.unaj.login.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private String studentId;
    private String name;
    private String email;
    private String instituto;
    private String type;
    private String phone;
    private String password;
    private Long createdAt;
    private Long setUpdatedAt;
}
