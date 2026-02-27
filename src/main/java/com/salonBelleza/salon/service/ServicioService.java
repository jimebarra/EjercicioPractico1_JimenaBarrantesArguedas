package com.salonBelleza.salon.service;

import com.salonBelleza.salon.domain.Servicio;
import java.util.List;
import java.util.Optional;

public interface ServicioService {
    List<Servicio> getServicios();
    Optional<Servicio> getServicio(Integer id);
    void save(Servicio servicio);
    void delete(Integer id);
}
