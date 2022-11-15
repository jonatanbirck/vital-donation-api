package com.univates.vitaldonationapi.app.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.univates.vitaldonationapi.app.model.donation.DonationDetail;
import com.univates.vitaldonationapi.domain.common.BloodType;
import com.univates.vitaldonationapi.domain.entity.Role;
import com.univates.vitaldonationapi.helper.ConverterHelper;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class UserDetail {

    private UUID id;

    private String name;

    private String cpf;

    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Length(min = 10, max = 30)
    private String password;

    private String phone;

    private String address;

    private String complement;

    private Integer weight; //g

    private Short height; //cm

    private LocalDate birthdate;

    private BloodType bloodType;

    private Collection<Role> roles;

    private Set<DonationDetail> donations;

    @JsonIgnore
    public Integer getWeightInteger() {
        return weight;
    }

    @JsonIgnore
    public Short getHeightShort() {
        return height;
    }

    public Double getWeight() {
        if (Objects.isNull(weight)) return null;
        return ConverterHelper.toKilos(weight);
    }

    public Double getHeight() {
        if (Objects.isNull(height)) return null;
        return ConverterHelper.toMeters(height);
    }

}
