package com.univates.vitaldonationapi.domain.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
