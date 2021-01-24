package com.test.sangcheol.controller;

import com.test.sangcheol.domain.RejectedFile;
import com.test.sangcheol.service.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PageController {

    private final MainService mainService;

    public PageController(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("/")
    public String viewHomePage(Model model) {
//        List<Product> listProducts = service.listAll();
        List<RejectedFile> customTypeList = mainService.getAllCustomTypeList();
        List<RejectedFile> fixedTypeList = mainService.getAllFixedTypeList();
        model.addAttribute("customTypeList", customTypeList);
        model.addAttribute("fixedTypeList", fixedTypeList);
        return "index";
    }
}
