package utn.dan2021.proyectodan.Rest;

import java.util.List;
import java.util.Optional;

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




    @Autowired
    ClienteService clienteService;


    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca un cliente por id")
    public ResponseEntity<Cliente> clientePorId(@PathVariable Integer id){


        Cliente cl = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cl);
    }

    @GetMapping
    @ApiOperation(value = "Busca todos los clientes")
    public ResponseEntity<List<Cliente>> todos(){

        return ResponseEntity.ok(clienteService.listarClientes());
    }


    @PostMapping
    @ApiOperation(value = "Alta de un Cliente ")
    public ResponseEntity<String> crear(@RequestBody Cliente nuevo) throws Exception {
        System.out.println(" crear cliente "+nuevo);

        if(nuevo.getObras().isEmpty()) {
            return ResponseEntity.badRequest().body("No se posee informacion de la obra");
        }
        /*if(nuevo.getUser().getUser().isEmpty() || nuevo.getUser().getPassword().isEmpty() ) {
            return ResponseEntity.badRequest().body("EL cliente no tiene usuario y contraseña");
        }*/

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
    public ResponseEntity<String> actualizar(@RequestBody Cliente unCliente,  @PathVariable Integer id) {

        try {
            clienteService.actualizarCliente(unCliente,id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FOUND).body("FAIL");
        }


        return ResponseEntity.status(HttpStatus.ACCEPTED).body("OK");
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Elimina un cliente")
    public ResponseEntity<String> bajaCliente(@PathVariable Integer id) {
        try {

           if (clienteService.bajaCliente(id)){
               String respuesta = "Se borro satisfactoriamente el cliente "+id;
               return ResponseEntity.status(HttpStatus.ACCEPTED).body(respuesta );

                       }else {
                           String respuesta = "No se puedo borrar poque es un cliente activo. Se le asigno fecha de baja "+id;
                           return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(respuesta );}

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //solicitado en guia 1
    @GetMapping(path = "/cuit/{cuit}")
    @ApiOperation(value = "Busca un cliente por cuit")
    public ResponseEntity<Cliente> clientePorCuit(@PathVariable String cuit){

        try {
           return ResponseEntity.ok(clienteService.buscarClientePorCuit(cuit));

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping(path = "qry")
    @ApiOperation(value = "Busca un cliente por Razon Social utilizano qry")
    public ResponseEntity<Cliente> clientePorRazonSocial(@RequestParam(required = false, value = "rz") String rz){

        try {
                return ResponseEntity.ok(clienteService.buscarClientePorRazonSocial(rz));

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }


}
