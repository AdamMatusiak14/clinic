package ad.clinic.dto;

public class DoctorDto {

    private Long id;
    private String date;

    public DoctorDto() {
    };

    public Long getId() {
        return this.id;
    }

    public void setId(
            Long id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
