package com.salonBelleza.salon.service;

import com.salonBelleza.salon.domain.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> getCategorias();
    Optional<Categoria> getCategoria(Integer id);
    void save(Categoria categoria);
    void delete(Integer id);
}
