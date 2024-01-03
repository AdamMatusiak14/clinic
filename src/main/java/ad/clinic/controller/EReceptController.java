package ad.clinic.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ad.clinic.model.ERecept;
import ad.clinic.service.EReceptService;

@Controller
public class EReceptController {

    private final EReceptService eReceptService;

    public EReceptController(EReceptService eReceptSercive) {
        this.eReceptService = eReceptSercive;
    }

    @PostMapping("saveRecept")
    String saveRecept(ERecept erecept) {
        eReceptService.saveRecept(erecept);
        return "saveRecept";
    }

    @PostMapping("recept")
    String viewRecept(@RequestParam Long id, Model model) {
        ERecept eRecept = eReceptService.findRecept(id);
        model.addAttribute("eRecept", eRecept);
        return "recept";

    }

}
