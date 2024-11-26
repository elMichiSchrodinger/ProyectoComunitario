package uni.edu.pe.modulo_crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.edu.pe.modulo_crm.dto.DetallePostulacion;
import uni.edu.pe.modulo_crm.dto.Postulacion;
import uni.edu.pe.modulo_crm.dto.Postulaciones;
import uni.edu.pe.modulo_crm.service.PostulacionesService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PostulacionesController {
    @Autowired
    private PostulacionesService postulacionesService;
    @GetMapping("/postulaciones")
    public List<Postulaciones> getPostulaciones(@RequestParam(required = false) String area, @RequestParam(required = false) String titulo) {
        return postulacionesService.listaPostulaciones(area, titulo);
    }
    @GetMapping("/postulaciones/{id_postulacion}")
    public DetallePostulacion buscarPostulacion(@PathVariable String id_postulacion) {
        return postulacionesService.buscarPostulacion(id_postulacion);
    }
    @PostMapping("/postulaciones/crear")
    public ResponseEntity<Postulacion> crearPostulacion(@RequestBody Postulacion postulacion){
        postulacion = postulacionesService.insertarPostulacion(postulacion);
        return ResponseEntity.ok(postulacion);
    }
    @PutMapping("/postulaciones/aprobar")
    public ResponseEntity<?> aprobarPostulacion(@RequestBody Postulacion postulacion){
        try{
            postulacion = postulacionesService.aprobarPostulacion(postulacion);
            return ResponseEntity.ok().body(postulacion);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error en la actualización del cliente", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/postulaciones/desaprobar")
    public ResponseEntity<?> desaprobarPostulacion(@RequestBody Postulacion postulacion){
        try{
            postulacion = postulacionesService.desaprobarPostulacion(postulacion);
            return ResponseEntity.ok().body(postulacion);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error en la actualización del cliente", HttpStatus.BAD_REQUEST);
        }
    }
}
