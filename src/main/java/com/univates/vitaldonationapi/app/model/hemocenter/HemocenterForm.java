package com.univates.vitaldonationapi.app.model.hemocenter;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class HemocenterForm {

    @NotNull
    private String name;
    private String address;
    private String website;
    private String email;
    private String phone;

}
