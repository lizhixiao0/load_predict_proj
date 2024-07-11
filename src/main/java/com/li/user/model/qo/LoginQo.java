package com.li.user.model.qo;

import lombok.Data;

@Data
public class LoginQo {
    private String username;
    private String password;
    private String role;
}
