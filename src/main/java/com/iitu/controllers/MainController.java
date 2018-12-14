package com.iitu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Assylkhan
 * on 12.12.2018
 * @project qapp
 */
@Controller
public class MainController {

    @GetMapping(path = {"" , "/"})
    public ModelAndView sayHello(){
        ModelAndView mw = new ModelAndView("index.html");
        return mw;
    }

    @PostMapping(path = {"" , "/"})
    public ModelAndView sayHello2(){
        ModelAndView mw = new ModelAndView("index.html");
        return mw;
    }


}
