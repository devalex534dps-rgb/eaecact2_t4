package com.fst.eaecact2_t4.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fst.eaecact2_t4.models.dto.MascotaDTO;

@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    @Value("${config.veterinaria.nombre}")
    private String nombreVeterinaria;

    @Value("${config.veterinaria.eslogan}")
    private String esloganVeterinaria;

    @Value("${config.veterinaria.horario}")
    private String horarioVeterinaria;

    private static final List<MascotaDTO> listaMascotas = new ArrayList<>(Arrays.asList(
            new MascotaDTO(1, "Bobby", "Perro", "Golden Retriever", 3, "Edwin Alexis"),
            new MascotaDTO(2, "Michi", "Gato", "Siames", 2, "Ana Lopez"),
            new MascotaDTO(3, "Rocky", "Perro", "Pastor Aleman", 5, "Carlos Perez"),
            new MascotaDTO(4, "Luna", "Gato", "Persa", 1, "Maria Garcia")
    ));

    public static List<MascotaDTO> obtenerMascotasEnMemoria() {
        return listaMascotas;
    }

    @ModelAttribute("mascotas")
    public List<MascotaDTO> listaMascotas() {
        return listaMascotas;
    }

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Mascotas Registradas");
        model.addAttribute("nombreVeterinaria", nombreVeterinaria);
        model.addAttribute("eslogan", esloganVeterinaria);
        model.addAttribute("horario", horarioVeterinaria);
        model.addAttribute("nuevaMascota", new MascotaDTO());
        return "mascotas";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("titulo", "Registrar Nueva Mascota");
        model.addAttribute("nombreVeterinaria", nombreVeterinaria);
        model.addAttribute("eslogan", esloganVeterinaria);
        model.addAttribute("horario", horarioVeterinaria);
        model.addAttribute("nuevaMascota", new MascotaDTO());
        return "formulario_mascota";
    }

    @PostMapping("/guardar")
    public String guardarMascota(@ModelAttribute("nuevaMascota") MascotaDTO mascota, Model model) {
        if (mascota.getId() == 0) {
            mascota.setId(listaMascotas.size() + 1);
        }
        listaMascotas.add(mascota);

        model.addAttribute("titulo", "Mascota Registrada Exitosamente");
        model.addAttribute("nombreVeterinaria", nombreVeterinaria);
        model.addAttribute("eslogan", esloganVeterinaria);
        model.addAttribute("horario", horarioVeterinaria);
        model.addAttribute("mensajeExito", "La mascota '" + mascota.getNombre() + "' fue registrada en memoria.");
        model.addAttribute("nuevaMascota", new MascotaDTO());
        return "mascotas";
    }

}

