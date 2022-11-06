package com.univates.vitaldonationapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.univates.vitaldonationapi.helper.ConverterHelper;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private String complement;

    @Column
    private Integer weight; //g

    @Column
    private Short height; //cm

    @Column
    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @Column(nullable = false)
    protected LocalDateTime createAt;

    @Column
    protected LocalDateTime updateAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "donor", targetEntity = Donation.class)
    private Collection<Donation> donations = new ArrayList<>();

    @Getter
    @AllArgsConstructor
    public enum BloodType {
        @JsonProperty("A+")
        AP,

        @JsonProperty("B+")
        BP,

        @JsonProperty("AB+")
        ABP,

        @JsonProperty("O+")
        OP,

        @JsonProperty("A-")
        AN,

        @JsonProperty("B-")
        BN,

        @JsonProperty("AB-")
        ABN,

        @JsonProperty("O-")
        ON;
    }

    public org.springframework.security.core.userdetails.User toUserAuth() {
        return new org.springframework.security.core.userdetails.User(
            this.cpf, this.password, mapRolesToSimpleGrantedAuthority()
        );
    }

    public Collection<SimpleGrantedAuthority> mapRolesToSimpleGrantedAuthority() {
        return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();
    }

    public void setCpf(String cpf) {
        this.cpf = ConverterHelper.maskCPF(cpf);
    }

}
