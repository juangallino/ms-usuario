package utn.dan2021.proyectodan.repository;

import frsf.isi.dan.InMemoryRepository;
import org.springframework.stereotype.Repository;
import utn.dan2021.proyectodan.Domain.Obra;

@Repository
public class ObraRepository extends InMemoryRepository<Obra> {
    @Override
    public Integer getId(Obra obra) {

         return obra.getId();
    }

    @Override
    public void setId(Obra obra, Integer integer) {
            obra.setId(integer);
    }
}
