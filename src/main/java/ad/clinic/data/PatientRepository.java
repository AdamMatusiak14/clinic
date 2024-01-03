package ad.clinic.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ad.clinic.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findPatientById(Long id);

    Patient findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Patient> findByfirstName(String firstName);

    Patient findPatientByfirstName(String firstName);
}
