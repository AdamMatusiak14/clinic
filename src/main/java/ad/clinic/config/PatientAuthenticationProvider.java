package ad.clinic.config;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ad.clinic.dto.doctorDto.DoctorCredentialDto;
import ad.clinic.service.DoctorService;

@Component
public class PatientAuthenticationProvider implements AuthenticationProvider {

    private final CustomPatientDetails customPatientDetails;

    public PatientAuthenticationProvider(
            CustomPatientDetails customPatientDetails) {

        this.customPatientDetails = customPatientDetails;
    }
    // wstrzel doctor service, albo go pomiń i jedź do UserDetails

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = null;

        try {
            user = customPatientDetails.loadUserByUsername(username);
            System.out.println("Zawartość Usera " + user.getUsername().toString());

        } catch (UsernameNotFoundException exception) {
            throw new BadCredentialsException("Invalid Login details");
        }

        return createSuccesfulAuthentication(authentication, user);
    }

    private Authentication createSuccesfulAuthentication(Authentication authentication, UserDetails user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),
                authentication.getCredentials(), user.getAuthorities());
        token.setDetails(authentication.getDetails());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
