package utn.dan2021.proyectodan.Rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import utn.dan2021.proyectodan.Domain.Cliente;
import utn.dan2021.proyectodan.Service.ClienteService;

@RestController
@RequestMapping("/api/cliente")
@Api(value = "ClienteRest", description = "Permite gestionar los clientes de la empresa")
public class ClienteRest {

    private static final List<Cliente> listaClientes = new ArrayList<>();
    private static Integer ID_GEN = 1;


    @Autowired
    ClienteService clienteService;


    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca un cliente por id")
    public ResponseEntity<Cliente> clientePorId(@PathVariable Integer id){

        Optional<Cliente> c =  listaClientes
                .stream()
                .filter(unCli -> unCli.getId().equals(id))
                .findFirst();

        return ResponseEntity.of(c);
        //Cliente cl = clienteService.buscarClientePorId(id);
        //return ResponseEntity.ok(cl);
    }

    @GetMapping
    @ApiOperation(value = "Busta todos los clientes")
    public ResponseEntity<List<Cliente>> todos(){

        return ResponseEntity.ok(listaClientes);
    }


    @PostMapping
    @ApiOperation(value = "Alta de un Cliente ")
    public ResponseEntity<String> crear(@RequestBody Cliente nuevo){
        System.out.println(" crear cliente "+nuevo);
        nuevo.setId(ID_GEN++);
        listaClientes.add(nuevo);


        if(nuevo.getObras().isEmpty()) {
            return ResponseEntity.badRequest().body("No se posee informacion de la obra");
        }
        if(nuevo.getUser().getUser().isEmpty() || nuevo.getUser().getPassword().isEmpty() ) {
            return ResponseEntity.badRequest().body("EL cliente no tiene usuario y contraseña");
        }

        clienteService.guardarCliente(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");

    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza un cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Actualizado correctamente"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "El ID no existe")})
    public ResponseEntity<Cliente> actualizar(@RequestBody Cliente nuevo,  @PathVariable Integer id){

        OptionalInt indexOpt =   IntStream.range(0, listaClientes.size())
                .filter(i -> listaClientes.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            listaClientes.set(indexOpt.getAsInt(), nuevo);
            return ResponseEntity.ok(nuevo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza un cliente")
    public ResponseEntity<Cliente> borrar(@PathVariable Integer id){
        OptionalInt indexOpt =   IntStream.range(0, listaClientes.size())
                .filter(i -> listaClientes.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            listaClientes.remove(indexOpt.getAsInt());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //solicitado en guia 1
    @GetMapping(path = "/cuit/{cuit}")
    @ApiOperation(value = "Busca un cliente por cuit")
    public ResponseEntity<Cliente> clientePorCuit(@PathVariable String cuit){

        Optional<Cliente> c =  listaClientes
                .stream()
                .filter(unCli -> unCli.getCuit().equals(cuit))
                .findFirst();
      //  return ResponseEntity.of(c);
        if(c.isPresent()){

            return ResponseEntity.of(c);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping(path = "qry")
    @ApiOperation(value = "Busca un cliente por Razon Social utilizano qry")
    public ResponseEntity<Cliente> clientePorRazonSocial(@RequestParam(required = false, value = "rz") String rz){

        Optional<Cliente> c =  listaClientes
                .stream()
                .filter(unCli -> unCli.getRazonSocial(). equals(rz))
                .findFirst();
        if(c.isPresent()){

            return ResponseEntity.of(c);
        } else {
            return ResponseEntity.notFound().build();
        }

    }


}