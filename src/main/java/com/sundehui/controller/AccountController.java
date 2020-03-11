package com.sundehui.controller;

import com.sundehui.domain.Account;
import com.sundehui.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class AccountController {

    @Autowired
    private  AccountService accountService;


    @RequestMapping("/account/findAll")
    public String findAll(Model model){
        List<Account> all = accountService.findAll();
        model.addAttribute("list",all);
        System.out.println(all);
        return "list";
    }
}
