package com.dazzilove.bustrace.controller.web;

import com.dazzilove.bustrace.domain.Bus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BusMngController {

    @RequestMapping("/busMng")
    public ModelAndView viewBusMng() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("busMng");

        List<Bus> busList = new Bus().getBusList();

        mav.addObject("busList", busList);

        return mav;
    }

    @RequestMapping("/busMngInfo")
    public ModelAndView viewBusMngInfo() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("busMngInfo");
        return mav;
    }

}
