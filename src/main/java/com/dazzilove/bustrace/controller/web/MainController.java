package com.dazzilove.bustrace.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping("/main")
    public ModelAndView goMain() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        return mav;
    }
}
