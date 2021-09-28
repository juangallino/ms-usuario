package utn.dan2021.proyectodan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.dan2021.proyectodan.Domain.Obra;

import java.util.List;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Integer> {


    public List<Obra> findObrasByCliente_Id(Integer id_cliente);
}
