package ad.clinic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity

public class Doctor {

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
    @Min(2)
    private int age;
    private String specialist;
    private String experience;
    @OneToMany(mappedBy = "doctor")
    private List<Visit> visits = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Doctor(Long id, String firstName, String lastName, String password, String role, int age, String specialist,
            String experience,
            List<Visit> visits) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.age = age;
        this.specialist = specialist;
        this.experience = experience;
        this.visits = visits;
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

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecialist() {
        return this.specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getExperience() {
        return this.experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public List<Visit> getVisits() {
        return this.visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Doctor)) {
            return false;
        }
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(firstName, doctor.firstName)
                && Objects.equals(lastName, doctor.lastName) && age == doctor.age
                && Objects.equals(specialist, doctor.specialist) && Objects.equals(experience, doctor.experience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, specialist, experience);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", age='" + getAge() + "'" +
                ", specialist='" + getSpecialist() + "'" +
                ", experience='" + getExperience() + "'" +
                "}";
    }

}
