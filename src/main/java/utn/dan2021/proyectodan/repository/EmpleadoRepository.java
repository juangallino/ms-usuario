package utn.dan2021.proyectodan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.dan2021.proyectodan.Domain.Empleado;


@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

}
