package ad.clinic.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ad.clinic.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByFirstNameAndLastName(String firsttName, String lastName);

    Doctor findDoctorById(Long id);

    Optional<Doctor> findByfirstName(String firstName);

    Doctor findDoctorByfirstName(String firstName);
}
