package com.qs.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 
 * @author : qshomewy
 * @description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Programmer {

    private String name;

    private int age;

    private float salary;

    private Date birthday;
}
