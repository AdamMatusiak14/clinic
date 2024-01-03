package ad.clinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Objects;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 2, max = 100)
    private String firstName;
    @NotNull
    @Size(min = 2, max = 100)
    private String lastName;
    @NotNull
    @Size(min = 2, max = 200)
    private String password;
    private String role;
    private String note; // jako
                         // obiekt
    @OneToMany(mappedBy = "patient")
    private List<Visit> visits = new ArrayList<>();
    @OneToMany(mappedBy = "patient")
    private List<ERecept> eRecepts = new ArrayList<>();

    public Patient() {
    }

    // public Patient(Long id) {
    // this.id = id;
    // }

    public Patient(Long id, String firstName, String lastName, String password, String role, String note,
            List<Visit> visits,
            List<ERecept> eRecepts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.note = note;
        this.visits = visits;
        this.eRecepts = eRecepts;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Visit> getVisits() {
        return this.visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public List<ERecept> getERecepts() {
        return this.eRecepts;
    }

    public void setERecepts(List<ERecept> eRecepts) {
        this.eRecepts = eRecepts;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
