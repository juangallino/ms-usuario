package utn.dan2021.proyectodan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.dan2021.proyectodan.Domain.TipoObra;

public interface TipoObraRepository extends JpaRepository<TipoObra,Integer> {
}
