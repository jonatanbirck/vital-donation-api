package com.univates.vitaldonationapi.helper;

import com.univates.vitaldonationapi.domain.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuthHelper {

        public static UserDetails map(User user) {
            return new org.springframework.security.core.userdetails.User(
                    user.getCpf(),
                    user.getPassword(),
                    user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList()
            );
        }

}
