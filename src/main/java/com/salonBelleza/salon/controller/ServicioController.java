package com.salonBelleza.salon.controller;

import com.salonBelleza.salon.domain.Servicio;
import com.salonBelleza.salon.service.CategoriaService;
import com.salonBelleza.salon.service.ServicioService;
import jakarta.validation.Valid;
import java.util.Locale;
import java.util.Optional;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

    private final ServicioService servicioService;
    private final CategoriaService categoriaService;
    private final MessageSource messageSource;

    public ServicioController(ServicioService servicioService, CategoriaService categoriaService,
                              MessageSource messageSource) {
        this.servicioService = servicioService;
        this.categoriaService = categoriaService;
        this.messageSource = messageSource;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var servicios = servicioService.getServicios();
        model.addAttribute("servicios", servicios);
        var categorias = categoriaService.getCategorias();
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalServicios", servicios.size());
        model.addAttribute("servicio", new Servicio());
        return "/servicio/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Servicio servicio, RedirectAttributes redirectAttributes) {
        servicioService.save(servicio);
        redirectAttributes.addFlashAttribute("todoOk",
                messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));
        return "redirect:/servicio/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idServicio, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";
        try {
            servicioService.delete(idServicio);
        } catch (IllegalArgumentException e) {
            titulo = "error";
            detalle = "servicio.error01";
        } catch (DataIntegrityViolationException | IllegalStateException e) {
            titulo = "error";
            detalle = "servicio.error02";
        } catch (Exception e) {
            titulo = "error";
            detalle = "servicio.error03";
        }
        redirectAttributes.addFlashAttribute(titulo,
                messageSource.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/servicio/listado";
    }

    @GetMapping("/modificar/{idServicio}")
    public String modificar(@PathVariable("idServicio") Integer idServicio, Model model,
                            RedirectAttributes redirectAttributes) {
        Optional<Servicio> servicioOpt = servicioService.getServicio(idServicio);
        if (servicioOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error",
                    messageSource.getMessage("servicio.error01", null, Locale.getDefault()));
            return "redirect:/servicio/listado";
        }
        model.addAttribute("servicio", servicioOpt.get());
        var categorias = categoriaService.getCategorias();
        model.addAttribute("categorias", categorias);
        return "/servicio/modifica";
    }
}
