package elcom.ex1.LibraryBooks.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="user")

public class User implements Serializable {

    private static final long serialVersionUid = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    public User() {
    }

    public User(String userName, String password, String fullName, String roleName) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.roleName = roleName;
    }

    @PrePersist
    void preInsert() {
        if (this.getCreatedAt() == null)
            this.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    }

    @PreUpdate
    void preUpdate() {
        if (this.getCreatedAt() == null)
            this.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName(){ return userName; }

    public void setUserName(String userName){ this.userName = userName; }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName(){ return fullName; }

    public void setFullName(String fullName){ this.fullName = fullName; }

    public String getRoleName(){ return roleName; }

    public void setRoleName(String roleName){ this.roleName = roleName; }

    public Timestamp getCreatedAt(){ return createdAt; }

    public void setCreatedAt(Timestamp createdAt){ this.createdAt =createdAt; }


}
