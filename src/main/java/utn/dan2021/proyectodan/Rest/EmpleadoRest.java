package utn.dan2021.proyectodan.Rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.dan2021.proyectodan.Domain.Empleado;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;




@RestController
@RequestMapping("/api/empleado")
@Api(value = "EmpleadoRest", description = "Permite gestionar los empelados de la empresa")
public class EmpleadoRest {

    private static final List<Empleado> listaEmpleados = new ArrayList<>();
    private static Integer ID_GEN = 1;


    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca un cliente por id")
    public ResponseEntity<Empleado> clientePorId(@PathVariable Integer id){

        Optional<Empleado> c =  listaEmpleados
                .stream()
                .filter(empleado -> empleado.getId().equals(id))
                .findFirst();
        return ResponseEntity.of(c);
    }

    @GetMapping
    @ApiOperation(value = "Busta todos los clientes")
    public ResponseEntity<List<Empleado>> todos(){
        return ResponseEntity.ok(listaEmpleados);
    }


    @PostMapping
    @ApiOperation(value = "Alta de un Cliente ")
    public ResponseEntity<Empleado> crear(@RequestBody Empleado nuevo){
        System.out.println(" crear Empleado "+nuevo);
        nuevo.setId(ID_GEN++);
        listaEmpleados.add(nuevo);
        return ResponseEntity.ok(nuevo);
    }


    //TODO EL UPDATE PISA EL ID Y LO DEJA NULL
    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza un cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Actualizado correctamente"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "El ID no existe")})
    public ResponseEntity<Empleado> actualizar(@RequestBody Empleado nuevo,  @PathVariable Integer id){
        OptionalInt indexOpt =   IntStream.range(0, listaEmpleados.size())
                .filter(i -> listaEmpleados.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            listaEmpleados.set(indexOpt.getAsInt(), nuevo);
            return ResponseEntity.ok(nuevo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Empleado> borrar(@PathVariable Integer id){
        OptionalInt indexOpt =   IntStream.range(0, listaEmpleados.size())
                .filter(i -> listaEmpleados.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            listaEmpleados.remove(indexOpt.getAsInt());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping(path = "qry")
    @ApiOperation(value = "Busca un empleado por nombre utilizano qry")
    public ResponseEntity<Empleado> empleadoPorNombre(@RequestParam(required = false, value = "name") String name){

        Optional<Empleado> c =  listaEmpleados
                .stream()
                .filter(empleado -> empleado.getNombre(). equals(name))
                .findFirst();
        //  return ResponseEntity.of(c);
        if(c.isPresent()){

            return ResponseEntity.of(c);
        } else {
            return ResponseEntity.notFound().build();
        }

    }


}
