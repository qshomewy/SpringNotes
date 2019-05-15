package com.qs.springboot.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @author : qshomewy
 * @description : 
 */
@Data
public class User implements Serializable {

    private long userId;
    private String username;
    private String password;

}
