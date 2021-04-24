package utn.dan2021.proyectodan.Service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utn.dan2021.proyectodan.Domain.Cliente;
import utn.dan2021.proyectodan.Domain.Empleado;
import utn.dan2021.proyectodan.Service.EmpleadoService;
import utn.dan2021.proyectodan.repository.EmpleadoRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;


    @Override
    public Empleado guardarEmpleado(Empleado empleado) throws Exception {

        try{
            empleadoRepository.save(empleado);
            return empleado;
        }catch (Exception e){
            throw new Exception("not found");}
    }

    @Override
    public void actualizarEmpleado(Empleado empleado, Integer Id) throws Exception {

        try{
            empleado.setId(Id);
            empleadoRepository.save(empleado);
        }catch (Exception e){
            throw new Exception("not found");}


    }

    @Override
    public void bajaEmpleado(Integer id) throws Exception {
        try{
            empleadoRepository.deleteById(id);
        }catch (Exception e){
            throw new Exception("not found");}

    }

    @Override
    public List<Empleado> listarEmpleados() {
        List<Empleado> result = new ArrayList<>();
        empleadoRepository.findAll().forEach(empleado -> result.add(empleado));
        return result;
    }

    @Override
    public Empleado buscarEmpleadoPorId(Integer id) throws Exception {



        if (empleadoRepository.findById(id).isPresent()){
            return empleadoRepository.findById(id).get();
        }
        throw new Exception("not found");

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
