package utn.dan2021.proyectodan.repository;

import frsf.isi.dan.InMemoryRepository;
import org.springframework.stereotype.Repository;
import utn.dan2021.proyectodan.Domain.Empleado;


@Repository
public class EmpleadoRepository extends InMemoryRepository<Empleado> {
    @Override
    public Integer getId(Empleado empleado) {

       return empleado.getId();
    }

    @Override
    public void setId(Empleado empleado, Integer integer) {
            empleado.setId(integer);
    }
}
