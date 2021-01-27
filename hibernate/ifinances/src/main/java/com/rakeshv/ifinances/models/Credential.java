package com.rakeshv.ifinances.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "credential")
public class Credential {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="CREDENTIAL_ID")
    public Long credentialId;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="USER_ID")
    public User user;

    @Column(name="USERNAME")
    private String username;

    @Column(name="PASSWORD")
    private String password;
}
