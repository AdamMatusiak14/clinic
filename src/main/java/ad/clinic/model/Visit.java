package ad.clinic.model;

import java.time.*;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;

import ad.clinic.validator.TimeConstraint;

@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // wlasny walidator
    @TimeConstraint
    private LocalTime time;
    @FutureOrPresent
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Visit() {
    }

    public Visit(LocalTime time, LocalDate date, Doctor doctor, Patient patient) {
        this.time = time;
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Visit)) {
            return false;
        }
        Visit visit = (Visit) o;
        return Objects.equals(id, visit.id) && Objects.equals(time, visit.time) && Objects.equals(date, visit.date)
                && Objects.equals(doctor, visit.doctor) && Objects.equals(patient, visit.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, date, doctor, patient);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", time='" + getTime() + "'" +
                ", date='" + getDate() + "'" +
                "}";
    }

}
