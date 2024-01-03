package ad.clinic.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DoctorAuthenticationProvider implements AuthenticationProvider {

    private final CustomDoctorDetails customDoctorDetails;

    public DoctorAuthenticationProvider(
            CustomDoctorDetails customDoctorDetails) {

        this.customDoctorDetails = customDoctorDetails;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = null;

        try {
            user = customDoctorDetails.loadUserByUsername(username);
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
