package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 32)
    private String name;

    @Column(name = "password", length = 128)
    private String password;

    @Column(name = "mail", length = 128)
    private String email;

    @Column(name = "age", length = 20)
    private Long age;

    @Column(name = "role", length = 5, nullable = false, columnDefinition = "varchar(5) default 'user'")
    private String role;



    public User() {
    }

    public User(String name, String password, String email, Long age) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = "user";
    }

    public User(String name, String password, String email, Long age, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = role;

    }

    public User(Long id, String name, String password, String email, Long age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = "user";
    }

    public User(Long id, String name, String password, String email, Long age, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                getName().equals(user.getName()) &&
                getPassword().equals(user.getPassword()) &&
                getEmail().equals(user.getEmail()) &&
                getAge().equals(user.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPassword(), getEmail(), getAge());
    }
}
