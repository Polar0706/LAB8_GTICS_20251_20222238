package com.tele.lab6_20222238.controller;

import com.tele.lab6_20222238.entity.Planet;
import com.tele.lab6_20222238.repository.PlanetRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/planets")
public class PlanetController {

    @Autowired
    private PlanetRepository planetRepository;

    @GetMapping
    public String listarPlanetas(Model model) {
        model.addAttribute("planets", planetRepository.findAll());
        return "planet/list";
    }

    @GetMapping("/new")
    public String nuevoPlaneta(Model model) {
        model.addAttribute("planet", new Planet());
        return "planet/form";
    }

    @PostMapping("/save")
    public String guardarPlaneta(@Valid Planet planet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "planet/form";
        }
        planetRepository.save(planet);
        return "redirect:/planets";
    }

    @GetMapping("/edit/{id}")
    public String editarPlaneta(@PathVariable Long id, Model model) {
        model.addAttribute("planet", planetRepository.findById(id).orElseThrow());
        return "planet/form";
    }

    @GetMapping("/delete/{id}")
    public String eliminarPlaneta(@PathVariable Long id) {
        planetRepository.deleteById(id);
        return "redirect:/planets";
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        model.addAttribute("planet", planetRepository.findById(id).orElseThrow());
        return "planet/view";
    }
}

