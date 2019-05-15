package com.qs.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qs.springboot.bean.Programmer;

import java.time.LocalDate;

/**
 * 
 * @author : qshomewy
 * @description :
 */
@Controller
@RequestMapping("index")
public class JspController {

    @RequestMapping
    public String jsp(Model model){
        Programmer programmer = new Programmer("heibai", 21, 1298.31f, LocalDate.now());
        model.addAttribute("programmer",programmer);
        return "show";
    }
}
