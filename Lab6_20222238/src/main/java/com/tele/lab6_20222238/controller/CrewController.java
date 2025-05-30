package com.tele.lab6_20222238.controller;

import com.tele.lab6_20222238.entity.CrewMember;
import com.tele.lab6_20222238.repository.CrewMemberRepository;
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
@RequestMapping("/crew")
public class CrewController {

    @Autowired
    private CrewMemberRepository crewRepository;

    @GetMapping
    public String listarTripulacion(Model model) {
        model.addAttribute("crewList", crewRepository.findAll());
        return "crew/list";
    }

    @GetMapping("/new")
    public String nuevoMiembro(Model model) {
        model.addAttribute("crewMember", new CrewMember());
        return "crew/form";
    }

    @PostMapping("/save")
    public String guardarMiembro(@Valid CrewMember crewMember, BindingResult result) {
        if (result.hasErrors()) return "crew/form";
        crewRepository.save(crewMember);
        return "redirect:/crew";
    }

    @GetMapping("/edit/{id}")
    public String editarMiembro(@PathVariable Long id, Model model) {
        model.addAttribute("crewMember", crewRepository.findById(id).orElseThrow());
        return "crew/form";
    }

    @GetMapping("/delete/{id}")
    public String eliminarMiembro(@PathVariable Long id) {
        crewRepository.deleteById(id);
        return "redirect:/crew";
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        model.addAttribute("crewMember", crewRepository.findById(id).orElseThrow());
        return "crew/view";
    }
}

