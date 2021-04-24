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
import utn.dan2021.proyectodan.Domain.Obra;
import utn.dan2021.proyectodan.Service.ObraService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;


@RestController
@RequestMapping("/api/obra")
@Api(value = "EmpleadoRest", description = "Permite gestionar los empelados de la empresa")
public class ObraRest {


    @Autowired
    ObraService obraService;

                                    // GETS
    //-------------------------------------------------------------------------------------------------------------------------------------------------

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca una Obra por id")
    public ResponseEntity<Obra> ObraPorId(@PathVariable Integer id){

        try{
        return ResponseEntity.ok(obraService.buscarObraPorId(id));
    }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @ApiOperation(value = "Busca todos las Obras")
    public ResponseEntity<List<Obra>> todos(){

        return ResponseEntity.ok(obraService.listarObras());
    }




    @GetMapping(path = "/cliente/{idObra}")
    @ApiOperation(value = "Busca un Cliente por id de la Obra")
    public ResponseEntity<Cliente> clientePorIdObra(@PathVariable Integer idObra) {

        /*Optional<Obra> c =  obraService.listarObras()
                .stream()
                .filter(Obra -> Obra.getId().equals(idObra))
                .findFirst();*/
        try {
            Obra obra = obraService.buscarObraPorId(idObra);
            return ResponseEntity.ok(obra.getCliente());


        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

//todo ulitmo get de la guia 1   Busca un Obra por cliente o tipo de obra()ambos param"
  /*  @GetMapping(path = "qry")
    @ApiOperation(value = "Busca un Obra por cliente o tipo de obra()ambos param")
    public ResponseEntity<Empleado> obrita(@RequestParam(required = false, value = "nombre") String nombre) {

        Optional<Empleado> c = listaObras
                .stream()
                .filter(Obra -> Obra.getNombre().equals(nombre))
                .findFirst();
        //  return ResponseEntity.of(c);
        if (c.isPresent()) {

            return ResponseEntity.of(c);
        } else {
            return ResponseEntity.notFound().build();
        }

    }*/

                                // POST
    //-------------------------------------------------------------------------------------------------------------------------------------------------

    @PostMapping
    @ApiOperation(value = "Alta de una Obra ")
    public ResponseEntity<String> crear(@RequestBody Obra obra) throws Exception {
        System.out.println(" crear Empleado "+obra);

        try{
            obraService.guardarObra(obra);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("OK");
        }catch (Exception e){
            return ResponseEntity.notFound().build();}
    }
                            //PUT
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Actualiza una Obra")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Actualizado correctamente"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 403, message = "Prohibido"),
            @ApiResponse(code = 404, message = "El ID no existe")})
    public ResponseEntity<String> actualizar(@RequestBody Obra obra,  @PathVariable Integer id)  {

        try{
            obraService.actualizarObra(obra, id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("OK");
       }catch (Exception e){
            return ResponseEntity.notFound().build();}

    }


                                //DELETE
    //-------------------------------------------------------------------------------------------------------------------------------------------------
    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Elimina una Obra")
    public ResponseEntity<String> borrar(@PathVariable Integer id){
        try {
            obraService.bajaObra(id);
            String respuesta = "ok "+id;
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta );
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    }



