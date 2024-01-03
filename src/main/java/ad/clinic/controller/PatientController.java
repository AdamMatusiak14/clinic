package ad.clinic.controller;

import java.util.List;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ad.clinic.dto.PatientDto;
import ad.clinic.dto.PatientNoteDto;
import ad.clinic.model.ERecept;
import ad.clinic.model.Patient;
import ad.clinic.model.Visit;
import ad.clinic.service.PatientService;

@Controller
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/patientCardDoc")
    String patientCard(Model model, @RequestParam Long id) {
        Patient patient = patientService.findPatient(id);
        model.addAttribute("patient", patient);

        return "/patientCardDoc";
    }

    @PostMapping("/newNote")
    String newNote(Model model, @RequestParam Long id) {
        Patient patient = patientService.findPatient(id);
        model.addAttribute("patient", patient);
        model.addAttribute("patientNoteDto", new PatientNoteDto());
        return "newNote";
    }

    @PutMapping("/saveNote")
    String patientCard(PatientNoteDto patientNoteDto) {

        patientService.saveNote(patientNoteDto);
        return "redirect:confirmationSaveNote";
    }

    @GetMapping("/confirmationSaveNote")
    String getPatientCart() {
        return "/confirmationSaveNote";
    }

    @PostMapping("/eRecept")
    String setERecept(Model model, @RequestParam Long id) {
        Patient patient = patientService.findPatient(id);
        ERecept eRecept = new ERecept();
        eRecept.setPatient(patient);
        model.addAttribute("erecept", eRecept);
        return "eRecept";
    }

    // to do wyrzucenia
    // @GetMapping("/kardPatient")
    // String choosePatient(Model model) {
    // model.addAttribute("patientDto", new PatientDto());
    // return "kardPatient";
    // }

    // przejscie z Indexu bezpośrednio do tego
    @GetMapping("/listPatient")
    String patientKard(@CurrentSecurityContext SecurityContext securityContext, Model model) {
        String patientName = securityContext.getAuthentication().getName();

        System.out.println("Imię użytkownika to: " + patientName);

        Patient patient = patientService.findByName(patientName);

        List<ERecept> eRecepts = patient.getERecepts();
        List<Visit> visits = patient.getVisits();
        model.addAttribute("eRecepts", eRecepts);
        model.addAttribute("visits", visits);
        model.addAttribute("patient", patient);
        return "listPatient";
    }

}
