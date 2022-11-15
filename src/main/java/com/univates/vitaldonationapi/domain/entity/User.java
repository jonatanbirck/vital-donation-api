package com.univates.vitaldonationapi.domain.entity;

import com.univates.vitaldonationapi.domain.common.BloodType;
import com.univates.vitaldonationapi.helper.ConverterHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "donor", targetEntity = Donation.class)
    private Set<Donation> donations = new HashSet<>();

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
