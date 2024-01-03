package ad.clinic.data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ad.clinic.model.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    // doctor i data
    List<Visit> findByDoctorIdAndDate(Long id, LocalDate date);

    Visit findVisitById(Long id);
}
