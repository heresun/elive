package com.sundehui.controller;

import com.sundehui.domain.Transaction;
import com.sundehui.domain.User;
import com.sundehui.domain.help.TransactionHelper;
import com.sundehui.service.TransactionService;
import com.sundehui.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @GetMapping("/getTransaction")
    public List<TransactionHelper> getTransaction(HttpServletRequest request) {
        String paramType = request.getParameter("type");
        HttpSession session = request.getSession(false);
        if (paramType == null || session == null) {
            return null;
        }

        User user = (User) session.getAttribute(Constants.USER_SESSION);
        int type = Integer.parseInt(paramType);
        int uId = user.getId();
        List<TransactionHelper> transaction = service.getTransaction(uId, type);

        return transaction;


    }
}
