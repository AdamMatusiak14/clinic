package ad.clinic.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ad.clinic.dto.doctorDto.DoctorCredentialDto;
import ad.clinic.service.DoctorService;

@Service
public class CustomDoctorDetails implements UserDetailsService {

    private final DoctorService doctorService;

    public CustomDoctorDetails(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return doctorService.findDoctorByfirstName(username).map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Doctor with firstName %s not found", username)));
    }

    private UserDetails createUserDetails(DoctorCredentialDto doctorDto) {
        return User.builder()
                .username(doctorDto.getFirstName())
                .password(doctorDto.getPassword())
                .roles(doctorDto.getRole())
                .build();
    }

}
