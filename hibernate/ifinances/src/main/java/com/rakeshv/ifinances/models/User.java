package com.rakeshv.ifinances.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Table(name = "finances_user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 100)
    @Column(name="USER_ID")
    private Long userId;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="BIRTH_DATE", nullable=false)
    private Date birthDate;

    @Column(name="EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name="LAST_UPDATED_DATE")
    private Date lastUpdatedDate;

    @Column(name="LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Column(name="CREATED_DATE", updatable=false)
    private Date createdDate;

    @Column(name="CREATED_BY", updatable=false)
    private String createdBy;

    @Transient
    private boolean valid;

//    @ElementCollection
//    @CollectionTable(name = "user_address", joinColumns = @JoinColumn(name = "USER_ID"))
//    @AttributeOverrides({@AttributeOverride(name = "addressLine1", column = @Column(name = "USER_ADDRESS_LINE_1")),
//        @AttributeOverride(name = "addressLine2", column = @Column(name = "USER_ADDRESS_LINE_2"))
//    })
    @Embedded
    private Address address;
//    @Formula("YEAR(CURDATE()) - YEAR(BIRTH_DATE)")
    @Formula("lower(datediff(curdate(),BIRTH_DATE)/365)")
    private int age;

////    @OneToOne(mappedBy = "user")
//    private Credential credential;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Account> accounts = new HashSet<>();

    public Set<Account> getAccounts() {
        if (accounts == null) {
            accounts = new HashSet<>();
        }
        return accounts;
    }
}
