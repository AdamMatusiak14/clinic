package ad.clinic.service;

import java.time.LocalTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ad.clinic.data.DoctorRepository;
import ad.clinic.data.PatientRepository;
import ad.clinic.data.VisitRepository;
import ad.clinic.dto.VisitDto;
import ad.clinic.model.Doctor;
import ad.clinic.model.Patient;
import ad.clinic.model.Visit;

@Service
public class VisitService {
    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    public VisitService(VisitRepository visitRepository, DoctorRepository doctorRepository,
            PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.visitRepository = visitRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ZNAJDZ LEKARZA
    public Doctor findDoc(VisitDto visit) {
        Doctor doc = doctorRepository.findDoctorById(visit.getId());
        System.out.println("Znalazł lekarza");
        return doc;
    }

    // ZAPISZ PACJENTA

    public Patient savePatient(VisitDto visit) {
        Patient patient = new Patient();
        patient.setFirstName(visit.getFirstName());
        patient.setLastName(visit.getLastName());
        String passwordHash = passwordEncoder.encode(visit.getPassword());
        patient.setPassword(passwordHash);
        patient.setRole("PATIENT");
        patient.setNote(null);
        patient.setERecepts(null);
        patientRepository.save(patient);
        return patient;
    }

    // ZAOKRAGLIJ CZAS

    public LocalTime roundTime(LocalTime time) {
        int minute = time.getMinute();
        int hours = time.getHour();

        System.out.println("Minuty: " + minute);

        if (0 < minute && minute <= 20)
            minute = 0;
        if (20 < minute && minute <= 40)
            minute = 30;
        if (40 < minute && minute <= 59) {
            hours++;
            minute = 0;
        }

        System.out.println("Godzina i minuty: " + hours + ":" + minute);

        return LocalTime.of(hours, minute);

    }

    // ZAPISZ WIZYTE
    public void saveVisit(Visit visit) {
        visitRepository.save(visit);
    }

    // WYSWIETL WIYTE
    public Visit seeVisit(Long id) {
        Visit visit = visitRepository.findVisitById(id);
        return visit;
    }
}