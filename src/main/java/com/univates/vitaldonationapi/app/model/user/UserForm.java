package com.univates.vitaldonationapi.app.model.user;

import com.univates.vitaldonationapi.domain.entity.User;
import com.univates.vitaldonationapi.helper.ConverterHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UserForm {

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    @Length(min = 10, max = 30)
    private String password;

    @NotBlank
    private String name;

    @Email
    private String email;

    private String phone;

    private String address;

    private String complement;

    private Integer weight; //g

    private Short height; //cm

    private LocalDate birthdate;

    private User.BloodType bloodType;

    public void setCpf(String cpf) {
        this.cpf = ConverterHelper.maskCPF(cpf);
    }

}
