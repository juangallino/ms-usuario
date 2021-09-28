package utn.dan2021.proyectodan.Service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utn.dan2021.proyectodan.Domain.Obra;
import utn.dan2021.proyectodan.Service.ObraService;
import utn.dan2021.proyectodan.repository.ClienteRepository;
import utn.dan2021.proyectodan.repository.ObraRepository;
import utn.dan2021.proyectodan.repository.TipoObraRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ObraServiceImpl implements ObraService {

    @Autowired
    ObraRepository obraRepository;
    @Autowired
    TipoObraRepository tipoObraRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public Obra guardarObra(Obra obra) throws Exception {

        try{
                obra.setCliente(clienteRepository.getOne(obra.getCliente().getId()));
                 obra.setTipo(tipoObraRepository.getOne(obra.getTipo().getId()));
            obraRepository.save(obra);
            return  obra;
        }
        catch (Exception e){
            throw new Exception("not found");
        }

    }

    @Override
    public void bajaObra(Integer id) throws Exception {
        try{obraRepository.deleteById(id);}
        catch (Exception e){
            throw new Exception("not found");
        }

    }

    @Override
    public void actualizarObra(Obra obra, Integer id) {
        obra.setId(id);
        obraRepository.save(obra);

    }

    @Override
    public List<Obra> listarObras() {
        List<Obra> result = new ArrayList<>();
        obraRepository.findAll().forEach(Obra -> result.add(Obra));
        return result;
    }

    @Override
    public Obra buscarObraPorId(Integer id) throws Exception {



        if (obraRepository.findById(id).isPresent()){
            return obraRepository.findById(id).get();
        }
        throw new Exception("not found");

    }

    @Override
    public List<Obra> listarObrasDeCliente(Integer id_cliente) {

     return   obraRepository.findObrasByCliente_Id(id_cliente);

    }


}
