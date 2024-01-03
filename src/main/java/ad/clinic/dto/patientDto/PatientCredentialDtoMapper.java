package ad.clinic.dto.patientDto;

import org.springframework.stereotype.Service;

import ad.clinic.model.Patient;

@Service
public class PatientCredentialDtoMapper {
    // przy metodzie w Service będzie problem bo tu przed static trzeba dodać public
    public static PatientCredentialDto map(Patient patient) {
        String firstName = patient.getFirstName();
        String password = patient.getPassword();
        String role = patient.getRole();

        return new PatientCredentialDto(firstName, password, role);
    }

}
