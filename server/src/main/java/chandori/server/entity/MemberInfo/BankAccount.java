package chandori.server.entity.MemberInfo;

import jakarta.persistence.*;

@Table(name = "bank_account")
@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_bank_account_info_num;
}
