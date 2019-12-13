package com.dazzilove.bustrace.controller.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestWebController {
    @Value("${spring.application.name}")
    String appName;

    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @RequestMapping("/welcome")
    public ModelAndView welcome() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("welcome");
        mav.addObject("id", "dazzilove");
        return mav;
    }
}
