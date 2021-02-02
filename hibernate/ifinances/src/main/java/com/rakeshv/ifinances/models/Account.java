package com.rakeshv.ifinances.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class Account {
    @Id
    // AUTO is the default generation strategy
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "account_table_generator")
    @TableGenerator(name = "account_table_generator", table = "ifinances_keys",
            pkColumnName = "PK_NAME", valueColumnName = "PK_VALUE")
    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Column(name = "ACCOUNT_TYPE")
    @Enumerated(value = EnumType.STRING)
    private AccountTypeEnum accountTypeEnum;

    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name="USER_ACCOUNT", joinColumns=@JoinColumn(name="ACCOUNT_ID"),
            inverseJoinColumns=@JoinColumn(name="USER_ID"))
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="BANK_ID")
    private Bank bank;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    List<Transaction> transactions = new ArrayList<>();

    @Column(name = "NAME")
    private String name;

    @Column(name = "INITIAL_BALANCE")
    private BigDecimal initialBalance;

    @Column(name = "CURRENT_BALANCE")
    private BigDecimal currentBalance;

    @Column(name = "OPEN_DATE")
    private Date openDate;

    @Column(name = "CLOSE_DATE")
    private Date closeDate;

    @Column(name = "LAST_UPDATED_DATE")
    private Date lastUpdatedDate;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    public List<Transaction> getTransactions() {
        if (transactions == null)
            transactions = new ArrayList<>();
        return transactions;
    }


    public Set<User> getUsers() {
        if (users == null) {
            users = new HashSet<>();
        }
        return users;
    }
}
