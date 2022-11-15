package com.univates.vitaldonationapi.app.model.hemocenter;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class InHemocenterDetail {

    private UUID id;
    private String name;
    private String address;
    private String website;
    private String email;
    private String phone;

}
