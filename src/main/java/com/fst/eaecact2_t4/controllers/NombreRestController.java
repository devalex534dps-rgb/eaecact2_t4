package com.fst.eaecact2_t4.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NombreRestController {

    @GetMapping("/nombre")

    public Map<String, Object> nombre() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("Nombres", "Edwin Alexis");
        respuesta.put("Apellidos", "Esperanza Chavez");
        return respuesta;
    }

    @GetMapping("/datos_personales")
    public Map<String, Object> datos_personales() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("Edad", 21);
        respuesta.put("fecha-nacimiento", "03-03-2005");
        respuesta.put("Sexo", "hombre");
        return respuesta;
    }

}
