package com.fst.eaecact2_t4.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fst.eaecact2_t4.models.dto.MascotaDTO;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaRestController {

    @Value("${config.veterinaria.nombre}")
    private String nombreVeterinaria;

    @Value("${config.veterinaria.eslogan}")
    private String esloganVeterinaria;

    @Value("${config.veterinaria.horario}")
    private String horarioVeterinaria;

    // 1. Listar todas las mascotas en memoria
    @GetMapping("/listar")
    public List<MascotaDTO> listarMascotas() {
        return MascotaController.obtenerMascotasEnMemoria();
    }

    // 2. Endpoint con @RequestParam
    @GetMapping("/buscar")
    public Map<String, Object> buscarPorParametro(
            @RequestParam(defaultValue = "Sin especificar") String nombre,
            @RequestParam(required = false, defaultValue = "General") String especie) {
        Map<String, Object> json = new HashMap<>();

        MascotaDTO encontrada = null;
        for (MascotaDTO m : MascotaController.obtenerMascotasEnMemoria()) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                encontrada = m;
                break;
            }
        }

        json.put("nombreBuscado", nombre);
        json.put("especieFiltro", especie);
        if (encontrada != null) {
            json.put("mensaje", "Mascota encontrada con @RequestParam");
            json.put("mascota", encontrada);
        } else {
            json.put("mensaje", "No se encontro una mascota con ese nombre en memoria");
        }
        return json;
    }

    // 3. Endpoint con @PathVariable
    @GetMapping("/detalle/{especie}/{id}")
    public Map<String, Object> detallePorRuta(
            @PathVariable String especie,
            @PathVariable int id) {
        Map<String, Object> json = new HashMap<>();

        MascotaDTO encontrada = null;
        for (MascotaDTO m : MascotaController.obtenerMascotasEnMemoria()) {
            if (m.getId() == id) {
                encontrada = m;
                break;
            }
        }

        json.put("especieRuta", especie);
        json.put("idBuscado", id);
        if (encontrada != null) {
            json.put("mensaje", "Mascota encontrada en memoria");
            json.put("mascota", encontrada);
        } else {
            MascotaDTO temporal = new MascotaDTO(id, "Mascota #" + id, especie, "Mestizo", 1, "Pendiente");
            json.put("mensaje", "Mascota generada con @PathVariable");
            json.put("mascota", temporal);
        }
        return json;
    }

    // 4. Endpoint con @Value mostrando propiedades de application.properties
    @GetMapping("/configuracion")
    public Map<String, Object> configuracionVeterinaria() {
        Map<String, Object> json = new HashMap<>();
        json.put("veterinaria", nombreVeterinaria);
        json.put("eslogan", esloganVeterinaria);
        json.put("horario", horarioVeterinaria);
        return json;
    }

    // 5. Endpoint REST POST que recibe MascotaDTO mediante JSON (@RequestBody)
    @PostMapping("/crear")
    public MascotaDTO crearMascota(@RequestBody MascotaDTO mascota) {
        List<MascotaDTO> lista = MascotaController.obtenerMascotasEnMemoria();
        if (mascota.getId() == 0) {
            mascota.setId(lista.size() + 1);
        }
        lista.add(mascota);
        return mascota;
    }

}
