package com.salonBelleza.salon.controller;

import com.salonBelleza.salon.service.ServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final ServicioService servicioService;

    public IndexController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        var servicios = servicioService.getServicios();
        model.addAttribute("servicios", servicios);
        return "/index";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "/contacto";
    }
}
