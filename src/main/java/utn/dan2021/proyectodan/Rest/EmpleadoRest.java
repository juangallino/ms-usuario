package utn.dan2021.proyectodan.Rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.dan2021.proyectodan.Domain.Cliente;
import utn.dan2021.proyectodan.Domain.Empleado;
import utn.dan2021.proyectodan.Service.ClienteService;
import utn.dan2021.proyectodan.Service.EmpleadoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;



@CrossOrigin(maxAge = 86400)
@RestController
@RequestMapping("/api/empleado")
@Api(value = "EmpleadoRest", description = "Permite gestionar los empelados de la empresa")
public class EmpleadoRest {

    @Autowired
    EmpleadoService empleadoService;

    @CrossOrigin(maxAge = 86400)
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca un cliente por id")
    public ResponseEntity<Empleado> clientePorId(@PathVariable Integer id){


        try{
            Empleado emp = empleadoService.buscarEmpleadoPorId(id);
            return ResponseEntity.ok(emp);

        }catch (Exception e){return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
    }
    @CrossOrigin(maxAge = 86400)
    @GetMapping
    @ApiOperation(value = "Busta todos los clientes")
    public ResponseEntity<List<Empleado>> todos(){

    return ResponseEntity.ok(empleadoService.listarEmpleados());

    }

    @CrossOrigin(maxAge = 86400)
    @PostMapping
    @ApiOperation(value = "Alta de un Cliente ")
    public ResponseEntity<String> crear(@RequestBody Empleado nuevo)  {
        System.out.println(" crear Empleado "+nuevo);
        try{
        empleadoService.guardarEmpleado(nuevo);                                                                                    // si quiero retorna la entidad al crearla ResponseEntity.ok(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }catch (Exception e){return ResponseEntity.status(HttpStatus.CONFLICT).build();}
    }

    @CrossOrigin(maxAge = 86400)
    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza un cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Actualizado correctamente"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "El ID no existe")})
    public ResponseEntity<Empleado> actualizar(@RequestBody Empleado nuevo,  @PathVariable Integer id) {
        try {
            empleadoService.actualizarEmpleado(nuevo, id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }
    }
    @CrossOrigin(maxAge = 86400)
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Elimina una Obra")
    public ResponseEntity<String> borrar(@PathVariable Integer id){
        try {
            empleadoService.bajaEmpleado(id);
            String respuesta = "ok "+id;
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta );
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(maxAge = 86400)
    @GetMapping(path = "qry")
    @ApiOperation(value = "Busca un empleado por nombre utilizano qry")
    public ResponseEntity<Empleado> empleadoPorNombre(@RequestParam(required = false, value = "name") String name){
                empleadoService.buscarEmpleadoPorNombre(name);
                try{
              return ResponseEntity.ok(empleadoService.buscarEmpleadoPorNombre(name));
        }catch (Exception e){
                    return ResponseEntity.notFound().build();}
    }

    }



