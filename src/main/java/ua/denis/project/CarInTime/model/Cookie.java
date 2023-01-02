package ua.denis.project.CarInTime.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cookies")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Cookie {
    @Id
    private long id;
    @Column(name = "cookieValue")
    private String cookieValue;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    public Cookie(String cookieValue, User userId) {
        this.cookieValue = cookieValue;
        this.userId = userId;
    }
}
