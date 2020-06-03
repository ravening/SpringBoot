package com.rakeshv.paymentprocessor.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/api/payments/home")
    public String getIndex() {
        return "index";
    }
}
