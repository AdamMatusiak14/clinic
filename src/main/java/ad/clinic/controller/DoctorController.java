package ad.clinic.controller;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ad.clinic.dto.DoctorDto;
import ad.clinic.dto.VisitDto;
import ad.clinic.model.Doctor;
import ad.clinic.model.Visit;
import ad.clinic.service.DoctorService;
import ad.clinic.service.VisitService;

@Controller
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/registration") // pobiera imiona i nazwiska lekarzy
    String doctorName(Model model) {
        List<Doctor> doctors = doctorService.getDoctorList();
        model.addAttribute("visitDto", new VisitDto());
        model.addAttribute("doctors", doctors);
        return "/registration";
    }

    @GetMapping("/doctor")
    String doctorList(@CurrentSecurityContext SecurityContext securityContext, Model model) {
        String doctorName = securityContext.getAuthentication().getName();
        Doctor doctor = doctorService.findByfirstName(doctorName);
        model.addAttribute("doctor", doctor);
        model.addAttribute("doctorDto", new DoctorDto());
        return "/doctor";
    }

    @PostMapping("/grafik")
    String doctorGrafik(DoctorDto doctorDto, Model model) {
        // Zapytanie o grafiik lekarza do wizyt
        LocalDate date = LocalDate.parse(doctorDto.getDate());
        List<Visit> docVisit = doctorService.findDocAndDate(doctorDto.getId(), date);

        // sortowanie po time
        model.addAttribute("docVisit", docVisit);

        return "/grafik";
    }

    @GetMapping("/doctorInf")
    String doctorLabel(Model model) {
        List<Doctor> doctors = doctorService.getDoctorList();
        model.addAttribute("doctors", doctors);
        return "/labelDoc";
    }
}
