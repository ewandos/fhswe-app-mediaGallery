package main.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EXIFModel {
    private String camera = null;
    private Date date = null;
    private Integer iso = null;
    private Float aperture = null;

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String dateString) throws ParseException {
        this.date = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
    }

    public int getIso() {
        return iso;
    }

    public void setIso(int iso) {
        this.iso = iso;
    }

    public float getAperture() {
        return aperture;
    }

    public void setAperture(float aperture) {
        this.aperture = aperture;
    }
}
