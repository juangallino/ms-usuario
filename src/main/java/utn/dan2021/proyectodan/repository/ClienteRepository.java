package utn.dan2021.proyectodan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.dan2021.proyectodan.Domain.Cliente;

import java.time.LocalDate;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

        Cliente findClienteByCuit (String cuit);
        Cliente findClienteByRazonSocial (String razonSocial);

        Cliente findClienteByCuitAndFechaBaja (String cuit, LocalDate fechaBaja);
        Cliente findClienteByRazonSocialAndFechaBaja (String razonSocial, LocalDate fechaBaja);

        Cliente findFirstByCuitAndFechaBaja (String cuit, LocalDate fechaBaja);
        Cliente findFirstByRazonSocialAndFechaBaja (String razonSocial, LocalDate fechaBaja);
}
