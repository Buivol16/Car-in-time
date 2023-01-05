package ua.denis.project.CarInTime.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String username;
    private String imgLink;
    private String fullName;
    private int rating;
    private String dateWhenConnected;

    public Profile(User userId, String username, String imgLink, String fullName, int rating, String dateWhenConnected) {
        this.user = userId;
        this.username = username;
        this.imgLink = imgLink;
        this.fullName = fullName;
        this.rating = rating;
        this.dateWhenConnected = dateWhenConnected;
    }
}
