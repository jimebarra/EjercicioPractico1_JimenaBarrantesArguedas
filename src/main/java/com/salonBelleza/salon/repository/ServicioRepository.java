package com.salonBelleza.salon.repository;

import com.salonBelleza.salon.domain.Servicio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
    List<Servicio> findByCategoriaId(Integer categoriaId);
}
