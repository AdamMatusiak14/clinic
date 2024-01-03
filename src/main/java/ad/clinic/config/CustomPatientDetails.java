package ad.clinic.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ad.clinic.dto.patientDto.PatientCredentialDto;
import ad.clinic.service.PatientService;

@Service
public class CustomPatientDetails implements UserDetailsService {

    private final PatientService patientService;

    public CustomPatientDetails(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return patientService.findPatientByfirstName(username).map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with firstName %s not found", username)));
    }

    private UserDetails createUserDetails(PatientCredentialDto patientDto) {
        return User.builder()
                .username(patientDto.getFirstName())
                .password(patientDto.getPassword())
                .roles(patientDto.getRole())
                .build();
    }

}
