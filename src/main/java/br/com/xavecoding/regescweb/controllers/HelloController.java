package br.com.xavecoding.regescweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("**************************");
        return "hello"; // o Spring vai renderizar o arquivo templates/hello.html
    }
}
