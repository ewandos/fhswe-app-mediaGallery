package main.model;
import java.time.LocalDate;

public class EXIFModel {
    private String camera = null;
    private LocalDate date = null;
    private Integer iso = null;
    private Float aperture = null;

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getIso() {
        return iso;
    }

    public void setIso(int iso) {
        this.iso = iso;
    }

    public Float getAperture() {
        return aperture;
    }

    public void setAperture(float aperture) {
        this.aperture = aperture;
    }
}
