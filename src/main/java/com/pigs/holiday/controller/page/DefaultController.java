package com.pigs.holiday.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DefaultController {
    @RequestMapping({"/home", "", "/"})
    public String page() { return "home"; }
}