package com.silvermedal.fraudetectcentral.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class BatchController {

        @Value("${my.rest: Restcontroller returns JSON}")
        private String greetingMessage;

        @GetMapping("/rest")
        public String rest() { return ("<p>Here is my message:" + greetingMessage + "</p>");
        }
    }
