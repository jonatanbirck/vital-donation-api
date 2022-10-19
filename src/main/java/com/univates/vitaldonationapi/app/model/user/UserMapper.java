package com.univates.vitaldonationapi.app.model.user;

import com.univates.vitaldonationapi.domain.entity.User;
import org.mapstruct.*;

@Mapper(componentModel="spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User map(UserForm form);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "weight", source = "detail.weightInteger")
    @Mapping(target = "height", source = "detail.heightShort")
    User map(UserDetail detail, String id);


    @Mapping(target = "password", ignore = true)
    UserDetail map(User user);

}
