package com.univates.vitaldonationapi.app.model.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
public class InFormDetail {

    private UUID id;

    private String name;

    @NotEmpty
    private Set<UUID> questionIds;

}
