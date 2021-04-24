package utn.dan2021.proyectodan.repository;

import frsf.isi.dan.InMemoryRepository;
import org.springframework.stereotype.Repository;
import utn.dan2021.proyectodan.Domain.Cliente;

@Repository
public class ClienteRepository extends InMemoryRepository<Cliente> {
    @Override
    public Integer getId(Cliente cliente) {
        return cliente.getId();
    }

    @Override
    public void setId(Cliente cliente, Integer integer) {
        cliente.setId(integer);

    }
}
