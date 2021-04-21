package utn.dan2021.proyectodan.Service;

import utn.dan2021.proyectodan.Domain.Empleado;

import java.util.List;

public interface EmpleadoService {

    public Empleado guardarEmpleado(Empleado empleado) throws Exception;
    public void bajaEmpleado(Integer id) throws Exception;
    public List<Empleado> listarEmpleados();
    public Empleado buscarEmpleadoPorId(Integer id);
}
