package utn.dan2021.proyectodan.Service;

import org.springframework.http.ResponseEntity;
import utn.dan2021.proyectodan.Domain.Cliente;
import utn.dan2021.proyectodan.Domain.Empleado;

import java.util.List;

public interface ClienteService {
    public Cliente guardarCliente(Cliente c) throws Exception;

    public Boolean bajaCliente(Integer id) throws Exception;

    public void actualizarCliente(Cliente cliente, Integer Id);

    public List<Cliente> listarClientes();

    public Cliente buscarClientePorId(Integer id);

    public Cliente buscarClientePorCuit(String  cuit);

    public Cliente buscarClientePorRazonSocial(String rz);
}
