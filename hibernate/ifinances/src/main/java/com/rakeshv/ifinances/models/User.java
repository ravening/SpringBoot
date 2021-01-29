package com.rakeshv.ifinances.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

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

//    @Formula("YEAR(CURDATE()) - YEAR(BIRTH_DATE)")
    @Formula("lower(datediff(curdate(),BIRTH_DATE)/365)")
    private int age;
}
