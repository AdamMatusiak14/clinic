package ad.clinic.dto.patientDto;

public class PatientCredentialDto {

    private final String firstName;
    private final String password;
    private final String role;

    public PatientCredentialDto(String firstName, String password, String role) {
        this.firstName = firstName;
        this.password = password;
        this.role = role;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }

}
