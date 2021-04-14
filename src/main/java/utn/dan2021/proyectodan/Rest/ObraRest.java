package utn.dan2021.proyectodan.Rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.dan2021.proyectodan.Domain.Empleado;
import utn.dan2021.proyectodan.Domain.Obra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;


@RestController
@RequestMapping("/api/obra")
@Api(value = "EmpleadoRest", description = "Permite gestionar los empelados de la empresa")
public class ObraRest {

    private static final List<Obra> listaObras = new ArrayList<>();
    private static Integer ID_GEN = 1;


    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca un cliente por id")
    public ResponseEntity<Obra> ObraPorId(@PathVariable Integer id){

        Optional<Obra> c =  listaObras
                .stream()
                .filter(Obra -> Obra.getId().equals(id))
                .findFirst();
        return ResponseEntity.of(c);
    }

    @GetMapping
    @ApiOperation(value = "Busca todos las Obras")
    public ResponseEntity<List<Obra>> todos(){
        return ResponseEntity.ok(listaObras);
    }


    @PostMapping
    @ApiOperation(value = "Alta de una Obra ")
    public ResponseEntity<Obra> crear(@RequestBody Obra obra){
        System.out.println(" crear Empleado "+obra);
        obra.setId(ID_GEN++);
        listaObras.add(obra);
        return ResponseEntity.ok(obra);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza una Obra")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Actualizado correctamente"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "El ID no existe")})
    public ResponseEntity<Obra> actualizar(@RequestBody Obra obra,  @PathVariable Integer id){
        OptionalInt indexOpt =   IntStream.range(0, listaObras.size())
                .filter(i -> listaObras.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            listaObras.set(indexOpt.getAsInt(), obra);
            return ResponseEntity.ok(obra);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Obra> borrar(@PathVariable Integer id){
        OptionalInt indexOpt =   IntStream.range(0, listaObras.size())
                .filter(i -> listaObras.get(i).getId().equals(id))
                .findFirst();

        if(indexOpt.isPresent()){
            listaObras.remove(indexOpt.getAsInt());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /*@GetMapping(path = "qry")
    @ApiOperation(value = "Busca un Obra por nombre utilizano qry")
    public ResponseEntity<Empleado> clientePorRazonSocial(@RequestParam(required = false, value = "nombre") String nombre){

        Optional<Empleado> c =  listaObras
                .stream()
                .filter(Obra -> Obra.getNombre(). equals(nombre))
                .findFirst();
        //  return ResponseEntity.of(c);
        if(c.isPresent()){

            return ResponseEntity.of(c);
        } else {
            return ResponseEntity.notFound().build();
        }*/

    }



