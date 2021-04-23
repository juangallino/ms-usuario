package utn.dan2021.proyectodan.Service.Implementations;

import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utn.dan2021.proyectodan.Domain.Cliente;
import utn.dan2021.proyectodan.Service.ClienteService;
import utn.dan2021.proyectodan.Service.RiesgoCrediticioService;
import utn.dan2021.proyectodan.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {


    @Autowired
    RiesgoCrediticioService riesgoCrediticioService;

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public Cliente guardarCliente(Cliente c) throws Exception {
        //si no existe el cliente los creamos POST
        if(!(c.getId()!=null && c.getId()>0)){
                if (!riesgoCrediticioService.reporteAFIPPositivo(c.getCuit())){throw new Exception("AFIP");}
                if (!riesgoCrediticioService.reporteBCRAPositivo(c.getCuit())){ throw new Exception("BCRA");}
                if (!riesgoCrediticioService.reporteVerazPositivo(c.getCuit())){ throw new Exception("VERAZ");  }

            clienteRepository.save(c);

        }
        //si ya existe el cliente lo editamos PUT
        else{
            Cliente cl = buscarClientePorId(c.getId());
            if(cl!=null){
                //actualizamos su informacion

                clienteRepository.save(c);
            }
            else{ throw new Exception("Clente no encontrado");

            }
        }
        return c;
    }



    @Override
    public void bajaCliente(Integer id) throws Exception {

        /*
        try {
            clienteRepository.deleteById(id);
            }catch (Exception e){throw new Exception("no funco");}
*/
        if (clienteRepository.findById(id).isPresent()){
            clienteRepository.deleteById(id);
        }else throw new Exception("no funco");


    }

    @Override
    public void actualizarCliente(Cliente cliente, Integer id) {
        cliente.setId(id);
        clienteRepository.save(cliente);

    }

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> result = new ArrayList<>();
        clienteRepository.findAll().forEach(cliente -> result.add(cliente));
        return result;
    }

    @Override
    public Cliente buscarClientePorId(Integer id) {

        return clienteRepository.findById(id).get();


    }

    @Override
    public Cliente buscarClientePorCuit(String cuit) {

        List<Cliente> aux = new ArrayList<>();
        clienteRepository.findAll().forEach(cliente -> aux.add(cliente));				//CONVERT ITERABLE TO lIST

        return aux.stream()
                .filter(cliente->cliente.getCuit().equals(cuit))
                .findFirst()
                .get();
    }

    @Override
    public Cliente buscarClientePorRazonSocial(String rz) {
        List<Cliente> aux = new ArrayList<>();
        clienteRepository.findAll().forEach(cliente -> aux.add(cliente));				//CONVERT ITERABLE TO lIST

        return aux.stream()
                .filter(cliente->cliente.getRazonSocial().equals(rz))
                .findFirst()
                .get();

    }
}
