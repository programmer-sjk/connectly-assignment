package connectly.assignment.user.domain;

import connectly.assignment.common.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    protected User() {}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
