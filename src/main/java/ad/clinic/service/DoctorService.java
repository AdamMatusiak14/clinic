package ad.clinic.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ad.clinic.data.DoctorRepository;
import ad.clinic.data.VisitRepository;
import ad.clinic.dto.doctorDto.DoctorCredentialDto;
import ad.clinic.dto.doctorDto.DoctorCredentialDtoMapper;
import ad.clinic.model.Doctor;
import ad.clinic.model.Visit;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final VisitRepository visitRepository;

    public DoctorService(DoctorRepository doctorRepository, VisitRepository visitRepository) {
        this.doctorRepository = doctorRepository;
        this.visitRepository = visitRepository;
    }

    public List<Doctor> getDoctorList() {
        return doctorRepository.findAll();
    }

    public Doctor findDoctor(String FirstName, String LastName) {
        Doctor doc = doctorRepository.findByFirstNameAndLastName(FirstName, LastName);
        return doc;

    }

    // ZNAJDZ WIZYTE NA PODSTAWIE DATY I IDLEKARZA
    public List<Visit> findDocAndDate(Long id, LocalDate date) {
        List<Visit> grafik = visitRepository.findByDoctorIdAndDate(id, date);
        return grafik;
    }

    public Optional<DoctorCredentialDto> findDoctorByfirstName(String firstName) {
        System.out.println("Tu Service Doctora");
        return doctorRepository.findByfirstName(firstName).map(DoctorCredentialDtoMapper::map);
    }

    public Doctor findByfirstName(String firstName) {
        Doctor doc = doctorRepository.findDoctorByfirstName(firstName);
        return doc;
    }
}
