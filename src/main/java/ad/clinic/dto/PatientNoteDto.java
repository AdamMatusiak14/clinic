package ad.clinic.dto;

public class PatientNoteDto {

    private Long id;
    private String note;

    public PatientNoteDto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}