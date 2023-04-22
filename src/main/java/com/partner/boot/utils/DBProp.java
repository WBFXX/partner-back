package com.partner.boot.utils;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DBProp {
    private String url;
    private String username;
    private String password;

}
