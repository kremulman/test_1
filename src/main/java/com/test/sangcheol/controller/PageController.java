package com.test.sangcheol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class PageController {
    @RequestMapping("/")
    public String viewHomePage(Model model) {
//        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", new ArrayList<>());

        return "index";
    }
}
