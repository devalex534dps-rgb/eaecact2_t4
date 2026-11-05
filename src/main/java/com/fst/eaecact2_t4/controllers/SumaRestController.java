package com.fst.eaecact2_t4.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SumaRestController {

    @GetMapping("/sumar")

    public Map<String, Object> suma(@RequestParam double num1, @RequestParam double num2) {
        Map<String, Object> respuesta = new HashMap<>();
        double resultado = num1 + num2;
        respuesta.put("num1", num1);
        respuesta.put("num2", num2);
        respuesta.put("resultado", resultado);
        return respuesta;
    }
}
