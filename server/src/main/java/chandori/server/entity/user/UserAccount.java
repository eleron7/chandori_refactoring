package chandori.server.entity.user;


import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;


@Entity
@Table(name = "member_info")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "user_email", unique = false)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "user_nickname", nullable = false)
    private String nickname;

    @Column(name = "user_job", nullable = false)
    private String job;

    @Column(name = "user_income", nullable = false)
    private Long income;

    @Column(name = "user_roles", nullable = false)
    private String roles;

    public UserAccount(Long id, String userId, String email, String password, String username, String nickname, String job, Long income, String roles) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.job = job;
        this.income = income;
        this.roles = roles;
    }

    public UserAccount() {}

    public static UserAccount createUser(String userId, String email, String password, String name, String nickname, String job, Long income, PasswordEncoder passwordEncoder){
        UserAccount newUser = new UserAccount(null, userId, email, passwordEncoder.encode(password), name, nickname, job, income, "USER");
        return newUser;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}