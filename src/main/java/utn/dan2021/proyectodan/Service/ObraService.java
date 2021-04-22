package utn.dan2021.proyectodan.Service;


import utn.dan2021.proyectodan.Domain.Obra;

import java.util.List;

public interface ObraService {
    public Obra guardarObra(Obra obra) throws Exception;
    public void bajaObra(Integer id) throws Exception;
    public void actualizarObra(Obra obra, Integer id);
    public List<Obra> listarObras();
    public Obra buscarObraPorId(Integer id);

}
