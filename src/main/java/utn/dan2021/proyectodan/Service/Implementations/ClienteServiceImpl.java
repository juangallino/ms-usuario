package utn.dan2021.proyectodan.Service.Implementations;

import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import utn.dan2021.proyectodan.Domain.Cliente;

import utn.dan2021.proyectodan.Domain.Obra;
import utn.dan2021.proyectodan.Domain.Usuario;
import utn.dan2021.proyectodan.Service.ClienteService;
import utn.dan2021.proyectodan.Service.ObraService;
import utn.dan2021.proyectodan.Service.RiesgoCrediticioService;
import utn.dan2021.proyectodan.repository.ClienteRepository;

import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {


    @Autowired
    RiesgoCrediticioService riesgoCrediticioService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ObraService obraService;


    private static final String PEDIDOS_REST_API_URL = "http://localhost:9000";
    private static final String Pedido_ENDPOINT = "/api/pedido/";
    //api rest pagos
    private static final String PAGOS_REST_API_URL = "http://localhost:9003";
    private static final String OBRA_ENDPOINnT = "/api/obra/";
    RestTemplate rest = new RestTemplate();




    @Override
    public Cliente guardarCliente(Cliente c) throws Exception {
        //si no existe el cliente los creamos POST

        if (!(c.getId() != null && c.getId() > 0)) {
            if (!riesgoCrediticioService.reporteAFIPPositivo(c.getCuit())) {throw new Exception("AFIP");}
            if (!riesgoCrediticioService.reporteBCRAPositivo(c.getCuit())) {throw new Exception("BCRA");}
            if (!riesgoCrediticioService.reporteVerazPositivo(c.getCuit())) {throw new Exception("VERAZ");}
           //validacion cliente debe tener obra
            if (c.getObras().isEmpty()) {  throw new Exception("SIN OBRAS"); }


                //CREAR Y SETEAR USUSARIO A CLIENTE
                Usuario usr = new Usuario();
                usr.setUser(c.getMail());
                usr.setPassword("1234");
                c.setUser(usr);

                clienteRepository.save(c);

            }
            //si ya existe el cliente lo editamos PUT
            else {
                Cliente cl = buscarClientePorId(c.getId());
                if (cl != null) {
                    //actualizamos su informacion

                    clienteRepository.save(c);
                } else {
                    throw new Exception("Clente no encontrado");

                }
            }
            return c;
        }




    @Override
    public void bajaCliente(Integer id_cliente) throws Exception {

        //TODO VERIFICAR QUE NO TIENE UN PEDIDO PENDIENTE ANTES DE ELIMINAR



        Optional<Cliente> cl = clienteRepository.findById(id_cliente);

        // si obtuvimos el cliente
        if (cl.isPresent()) {
            //si el cliente esta activo
            if (checkClienteActivo(id_cliente)) {
                clienteRepository.deleteById(id_cliente);
            } else {         //SI NO ESTA ACTIVO LE ASIGNAMOS FECHA de BAJA
                                 Cliente aux=cl.get();
                                 aux.setFechaBaja(LocalDate.now().plusDays(20));
                                 actualizarCliente(aux, id_cliente);

                    }
        }else throw new Exception("cliente not found");




    }

    public boolean checkClienteActivo(Integer id_cliente) {
        //todo cheackear si existe algun pedido donde la obra asociada tenga idcliente X
        //obetner la obras de un cliente, e iterar con esos id en los pedidos, si al menos un matchea devolver true


        List<String> auxIdObras= obraService.listarObrasDeCliente(id_cliente).stream()
                .map(o->o.getId().toString())
                .collect(Collectors.toList());

        /*//******************************************************
            // Create a character list
            List<Character> ch = Arrays.asList('G', 'e', 'e', 'k', 's',
                    'f', 'o', 'r',
                    'G', 'e', 'e', 'k', 's');

            // Convert the character list into String
            // using Collectors.joining() method
            // with, as the delimiter
            String chString = ch.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));

            // Print the concatenated String
            System.out.println(chString);

            //******************************************************
*/

        String listaIdObra= auxIdObras.stream()
                .collect(Collectors.joining(","));


        //1er opcion   nuevo metodo http en pedidos rest. devuelve boolean al enviarle una lista de id de obras. Mayor eficiencia de recursos

        String url = PEDIDOS_REST_API_URL + Pedido_ENDPOINT +"obra/clienteactivo/?listaId_Obras="+ listaIdObra;
       return rest.exchange(url, HttpMethod.GET,null, Boolean.class).getBody();


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
