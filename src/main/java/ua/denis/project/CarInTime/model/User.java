package ua.denis.project.CarInTime.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Entity
public class User {
    @Id
    private long id;

    private String email;

    private String password;

    private String username;

}
