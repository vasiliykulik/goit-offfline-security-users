package ua.goit.offline.test_security.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by andreymi on 2/1/2017.
 */
@Controller
public class LandingController {
    @GetMapping
    public ModelAndView startPage(Authentication authentication) {
        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.addObject("username", authentication.getName());
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
