package ad.clinic.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ad.clinic.dto.VisitDto;
import ad.clinic.model.Doctor;
import ad.clinic.model.Patient;
import ad.clinic.model.Visit;
import ad.clinic.service.VisitService;

@Controller
public class VisitController {

    private VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;

    }

    @PostMapping("/savevisit")
    public String addVisit(VisitDto visitDto) {

        Doctor doc = visitService.findDoc(visitDto);
        Patient patient = visitService.savePatient(visitDto);

        LocalTime time = visitService.roundTime(visitDto.getTime());
        LocalDate date = LocalDate.parse(visitDto.getDate());

        Visit visit = new Visit(time, date, doc, patient);

        visitService.saveVisit(visit);

        return "/confirmation";
    }

    @PostMapping("/visit")
    public String seeVisit(Model model, Long id) {
        Visit visit = visitService.seeVisit(id);
        model.addAttribute("visit", visit);
        return "visit";
    }
}
