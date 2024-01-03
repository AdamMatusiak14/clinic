package ad.clinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import ad.clinic.data.PatientRepository;
import ad.clinic.dto.PatientNoteDto;
import ad.clinic.dto.patientDto.PatientCredentialDto;
import ad.clinic.dto.patientDto.PatientCredentialDtoMapper;
import ad.clinic.model.ERecept;
import ad.clinic.model.Patient;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient findPatient(Long id) {
        Patient patient = patientRepository.findPatientById(id);
        return patient;
    }

    public void saveNote(PatientNoteDto patientDto) {
        Patient pat = patientRepository.findPatientById(patientDto.getId());
        String allNote = patientDto.getNote();
        pat.setNote(allNote);
        patientRepository.save(pat);
    }

    public Patient findPatientName(String FirstName, String LastName) {
        Patient patient = patientRepository.findByFirstNameAndLastName(FirstName,
                LastName);
        return patient;
    }

    public void saveRecept(ERecept erecept) {

    }

    public Optional<PatientCredentialDto> findPatientByfirstName(String firstName) {
        System.out.println("Tu Service Patienta");

        return patientRepository.findByfirstName(firstName).map(PatientCredentialDtoMapper::map);
    }

    // Uwierzytelnienie po logowaniu i ścieżka pacjenta
    public Patient findByName(String patientName) {
        Patient patient = patientRepository.findPatientByfirstName(patientName);
        return patient;

    }
}
