package com.tele.lab6_20222238.controller;

import com.tele.lab6_20222238.repository.ExpeditionRepository;
import com.tele.lab6_20222238.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    ExpeditionRepository expeditionRepository;
    @Autowired
    PlanetRepository planetRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("expeditions", expeditionRepository.findAll());
        model.addAttribute("planets", planetRepository.findAll());
        return "index";
    }
}

