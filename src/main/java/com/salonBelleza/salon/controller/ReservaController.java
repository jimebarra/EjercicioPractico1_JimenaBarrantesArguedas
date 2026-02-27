package com.salonBelleza.salon.controller;

import com.salonBelleza.salon.domain.Reserva;
import com.salonBelleza.salon.service.ReservaService;
import com.salonBelleza.salon.service.ServicioService;
import jakarta.validation.Valid;
import java.util.Locale;
import java.util.Optional;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    private final ReservaService reservaService;
    private final ServicioService servicioService;
    private final MessageSource messageSource;

    public ReservaController(ReservaService reservaService, ServicioService servicioService,
                             MessageSource messageSource) {
        this.reservaService = reservaService;
        this.servicioService = servicioService;
        this.messageSource = messageSource;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var reservas = reservaService.getReservas();
        model.addAttribute("reservas", reservas);
        var servicios = servicioService.getServicios();
        model.addAttribute("servicios", servicios);
        model.addAttribute("totalReservas", reservas.size());
        model.addAttribute("reserva", new Reserva());
        return "/reserva/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Reserva reserva, RedirectAttributes redirectAttributes) {
        reservaService.save(reserva);
        redirectAttributes.addFlashAttribute("todoOk",
                messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));
        return "redirect:/reserva/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idReserva, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";
        try {
            reservaService.delete(idReserva);
        } catch (IllegalArgumentException e) {
            titulo = "error";
            detalle = "reserva.error01";
        } catch (Exception e) {
            titulo = "error";
            detalle = "reserva.error03";
        }
        redirectAttributes.addFlashAttribute(titulo,
                messageSource.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/reserva/listado";
    }

    @GetMapping("/modificar/{idReserva}")
    public String modificar(@PathVariable("idReserva") Integer idReserva, Model model,
                            RedirectAttributes redirectAttributes) {
        Optional<Reserva> reservaOpt = reservaService.getReserva(idReserva);
        if (reservaOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("reserva.error01", null, Locale.getDefault()));
            return "redirect:/reserva/listado";
        }
        model.addAttribute("reserva", reservaOpt.get());
        var servicios = servicioService.getServicios();
        model.addAttribute("servicios", servicios);
        return "/reserva/modifica";
    }
}
