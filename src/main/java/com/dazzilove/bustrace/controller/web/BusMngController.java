package com.dazzilove.bustrace.controller.web;

import com.dazzilove.bustrace.domain.Bus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BusMngController {

    @RequestMapping("/busMng/busMngList")
    public ModelAndView viewBusMng() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("busMng/busMngList");

        List<Bus> busList = new Bus().getBusList();

        mav.addObject("busList", busList);

        return mav;
    }

    @RequestMapping("/busMng/busMngInfo")
    public ModelAndView viewBusMngInfo() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("busMng/busMngInfo");
        return mav;
    }

}
