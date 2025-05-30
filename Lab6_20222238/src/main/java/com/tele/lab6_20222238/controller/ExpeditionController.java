package com.tele.lab6_20222238.controller;

import com.tele.lab6_20222238.entity.CrewMember;
import com.tele.lab6_20222238.entity.Expedition;
import com.tele.lab6_20222238.repository.CrewMemberRepository;
import com.tele.lab6_20222238.repository.ExpeditionRepository;
import com.tele.lab6_20222238.repository.PlanetRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/expeditions")
public class ExpeditionController {

    @Autowired
    private ExpeditionRepository expeditionRepository;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private CrewMemberRepository crewRepository;

    @GetMapping
    public String listarExpediciones(Model model) {
        model.addAttribute("expeditions", expeditionRepository.findAll());
        return "expedition/list";
    }

    @GetMapping("/new")
    public String nuevaExpedicion(Model model) {
        model.addAttribute("expedition", new Expedition());
        model.addAttribute("planets", planetRepository.findAll());
        model.addAttribute("crewList", crewRepository.findAll());
        return "expedition/form";
    }

    @PostMapping("/save")
    public String guardarExpedicion(@Valid Expedition expedition, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("planets", planetRepository.findAll());
            model.addAttribute("crewList", crewRepository.findAll());
            return "expedition/form";
        }

        // Validación: no puede tener miembros duplicados activos
        for (CrewMember cm : expedition.getTripulacion()) {
            List<Expedition> activas = expeditionRepository.findActiveExpeditionsByCrewMemberId(cm.getId());
            if (!activas.isEmpty()) {
                result.rejectValue("tripulacion", "error.tripulacion", "Tripulante ya asignado a otra expedición activa.");
                model.addAttribute("planets", planetRepository.findAll());
                model.addAttribute("crewList", crewRepository.findAll());
                return "expedition/form";
            }
        }

        expedition.setEstado("Planificada");
        expeditionRepository.save(expedition);
        return "redirect:/expeditions";
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        Expedition expedition = expeditionRepository.findById(id).orElseThrow();
        model.addAttribute("expedition", expedition);
        return "expedition/view";
    }

    @PostMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable Long id, @RequestParam String nuevoEstado, @RequestParam(required = false) String resultados) {
        Expedition expedition = expeditionRepository.findById(id).orElseThrow();

        if ("En Curso".equals(nuevoEstado)) {
            boolean tienePiloto = expedition.getTripulacion().stream().anyMatch(c -> c.getEspecialidad().equalsIgnoreCase("Piloto"));
            boolean tieneCientifico = expedition.getTripulacion().stream().anyMatch(c -> c.getEspecialidad().equalsIgnoreCase("Científico"));

            if (!(tienePiloto && tieneCientifico)) {
                return "redirect:/expeditions/view/" + id + "?error=tripulacion";
            }
        }

        if ("Completada".equals(nuevoEstado)) {
            expedition.setResultados(resultados);
        }

        if ("Cancelada".equals(nuevoEstado)) {
            expedition.setTripulacion(new ArrayList<>());
        }

        expedition.setEstado(nuevoEstado);
        expeditionRepository.save(expedition);
        return "redirect:/expeditions/view/" + id;
    }
}

