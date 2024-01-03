package ad.clinic.model;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class ERecept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private LocalDate issueDate;
    private String doctor; // wpisywany z palca, nie jako obiekt
    private String code; // wpisywany z palca
    private String medicine; // wpisywane z palca LEKI

    public ERecept() {
    }

    public ERecept(Long id) {
        this.id = id;
    }

    public ERecept(Long id, Patient patient, LocalDate issueDate, String doctor, String code, String medicine) {
        this.id = id;
        this.patient = patient;
        this.issueDate = issueDate;
        this.doctor = doctor;
        this.code = code;
        this.medicine = medicine;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getDoctor() {
        return this.doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMedicine() {
        return this.medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

}
