package com.qs.springbootredis.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author : qshomewy
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Programmer implements Serializable {

    private String name;

    private int age;

    private float salary;

    private Date birthday;
}