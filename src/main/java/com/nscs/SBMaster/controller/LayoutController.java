package com.nscs.SBMaster.controller;

import com.nscs.SBMaster.model.MenuItem;
import com.nscs.SBMaster.model.MenuResponse;
import com.nscs.SBMaster.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LayoutController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        MenuResponse menuData = menuService.getUserMenuData(9);
        model.addAttribute("menuResponse", menuData);
        return "layout";
    }
}
