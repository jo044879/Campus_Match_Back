package com.pigs.holiday.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/club")
@Controller
public class ClubController {
    @RequestMapping("/{page}")
    public String page(@PathVariable String page) { return "club/" + page; }
    @RequestMapping("/{page}/{id}")
    public String page(@PathVariable String page, @PathVariable String id) { return "club/" + page; }

}
