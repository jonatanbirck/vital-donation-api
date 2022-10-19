package com.univates.vitaldonationapi.app.model.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.univates.vitaldonationapi.domain.entity.Role;
import com.univates.vitaldonationapi.domain.entity.User;
import com.univates.vitaldonationapi.helper.ConverterHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    private User.BloodType bloodType;

    private Collection<Role> roles;

    public Integer getWeightInteger() {
        return weight;
    }

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
