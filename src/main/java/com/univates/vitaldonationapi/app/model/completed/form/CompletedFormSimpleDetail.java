package com.univates.vitaldonationapi.app.model.completed.form;

import com.univates.vitaldonationapi.app.model.form.FormSimpleDetail;
import com.univates.vitaldonationapi.app.model.user.UserSimpleDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CompletedFormSimpleDetail {

    private UUID id;
    private FormSimpleDetail form;
    private UserSimpleDetail user;

}
