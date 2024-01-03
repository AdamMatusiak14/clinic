package ad.clinic.dto.doctorDto;

import org.springframework.stereotype.Service;

import ad.clinic.model.Doctor;

@Service
public class DoctorCredentialDtoMapper {

    public static DoctorCredentialDto map(Doctor doctor) {
        String firstName = doctor.getFirstName();
        String password = doctor.getPassword();
        String role = doctor.getRole();

        return new DoctorCredentialDto(firstName, password, role);

    }
}
