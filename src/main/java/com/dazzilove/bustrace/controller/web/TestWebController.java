package com.dazzilove.bustrace.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestWebController {
    @RequestMapping("/welcome")
    public ModelAndView welcome() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("welcome");
        mav.addObject("id", "dazzilove");
        return mav;
    }
}
