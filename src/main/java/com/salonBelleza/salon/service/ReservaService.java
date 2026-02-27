package com.salonBelleza.salon.service;

import com.salonBelleza.salon.domain.Reserva;
import java.util.List;
import java.util.Optional;

public interface ReservaService {
    List<Reserva> getReservas();
    Optional<Reserva> getReserva(Integer id);
    void save(Reserva reserva);
    void delete(Integer id);
}
