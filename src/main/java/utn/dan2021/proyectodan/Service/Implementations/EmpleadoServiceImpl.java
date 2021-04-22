package utn.dan2021.proyectodan.Service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.dan2021.proyectodan.Domain.Cliente;
import utn.dan2021.proyectodan.Domain.Empleado;
import utn.dan2021.proyectodan.Service.EmpleadoService;
import utn.dan2021.proyectodan.repository.EmpleadoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;


    @Override
    public Empleado guardarEmpleado(Empleado empleado) throws Exception {
        empleadoRepository.save(empleado);
        return empleado;
    }

    @Override
    public void actualizarEmpleado(Empleado empleado, Integer Id) {
        empleado.setId(Id);
        empleadoRepository.save(empleado);


    }

    @Override
    public void bajaEmpleado(Integer id) throws Exception {
        empleadoRepository.deleteById(id);

    }

    @Override
    public List<Empleado> listarEmpleados() {
        List<Empleado> result = new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> result.add(empleado));
        return result;
    }

    @Override
    public Empleado buscarEmpleadoPorId(Integer id) {

       return empleadoRepository.findById(id).get();
    }

    @Override
    public Empleado buscarEmpleadoPorNombre(String nombre) {
        List<Empleado> result = new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> result.add(empleado));
       return result.stream()
                .filter(e->e.getNombre().equals(nombre))
               .findFirst()
               .get();

    }
}
