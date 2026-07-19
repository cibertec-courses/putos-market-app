package edu.pe.cibertec.puntosmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirigirASwagger(){
        return  "redirect:/swagger-ui/index.html";

    }}
