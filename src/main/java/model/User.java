package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "mail")
    private String email;

    @Column(name = "age")
    private Long age;

    @Column(name = "role")
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
