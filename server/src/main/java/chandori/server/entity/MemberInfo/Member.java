package chandori.server.entity.MemberInfo;


import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;


@Entity
@Table(name = "member_info")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_email", unique = true)
    private String email;

    @Column(name = "member_password", nullable = false)
    private String password;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Column(name = "member_nickname", nullable = false)
    private String nickname;

    @Column(name = "member_job", nullable = false)
    private String job;

    @Column(name = "member_income", nullable = false)
    private Long income;

    @Column(name = "member_roles", nullable = false)
    private String roles;

    public Member(Long id, String email, String password, String name, String nickname, String job, Long income, String roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.job = job;
        this.income = income;
        this.roles = roles;
    }

    public Member() {}

    public static Member createMember(String email, String password, String name, String nickname, String job, Long income, PasswordEncoder passwordEncoder){
        Member newUser = new Member(null, email, passwordEncoder.encode(password), name, nickname, job, income, "USER");
        return newUser;
    }

    public Long getId() {
        return id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String toString(Member user){
        return "User{" +
                "id=" + id +
                " email=" + email +
                ", password=" + password +
                ", name=" + name +
                ", nickname=" + nickname +
                ", job=" + job +
                ", income=" + income +
                ", roles=" + roles +
                "}";
    }
}