package ad.clinic.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class VisitDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private LocalTime time;
    private String date;

    public VisitDto() {
    }

    public VisitDto(Long id, String firstName, String lastName, String password, LocalTime time, String date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.time = time;
        this.date = date;
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public String getDate() {
        return this.date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
