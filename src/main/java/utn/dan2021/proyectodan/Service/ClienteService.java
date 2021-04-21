package utn.dan2021.proyectodan.Service;

import utn.dan2021.proyectodan.Domain.Cliente;

public interface ClienteService {
    public Cliente guardarCliente(Cliente c);
    public Cliente bajaCliente(Integer id);
    public Cliente listarClientes();
    public Cliente buscarClientePorId(Integer id);
}
