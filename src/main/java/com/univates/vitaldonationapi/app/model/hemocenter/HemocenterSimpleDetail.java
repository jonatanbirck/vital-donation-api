package com.univates.vitaldonationapi.app.model.hemocenter;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class HemocenterSimpleDetail {

    private UUID id;
    private String name;

}
