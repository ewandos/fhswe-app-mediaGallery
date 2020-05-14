package main.viewmodel;

import javafx.beans.property.*;
import main.model.EXIFModel;

import java.time.LocalDate;

public class EXIFViewModel {
    private StringProperty camera = new SimpleStringProperty();
    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private StringProperty iso = new SimpleStringProperty();
    private StringProperty aperture = new SimpleStringProperty();

    public EXIFViewModel(EXIFModel exif) {
        refresh(exif);
    }

    public void refresh(EXIFModel exif) {
        camera.setValue(exif.getCamera());
        date.setValue(exif.getDate());

        if (exif.getIso() != null)
            iso.setValue(Integer.toString(exif.getIso()));
        else
            iso.setValue("");

        if (exif.getIso() != null)
            aperture.setValue(Float.toString(exif.getAperture()));
        else
            aperture.setValue("");
    }

    public StringProperty cameraProperty() {
        return camera;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public StringProperty isoProperty() {
        return iso;
    }

    public StringProperty apertureProperty() {
        return aperture;
    }

    public void saveChanges(EXIFModel exif) {
        exif.setCamera(camera.get());
        exif.setDate(date.get());

        int newIso = Integer.parseInt(this.iso.get());
        exif.setIso(newIso);

        float newAperture = Float.parseFloat(this.aperture.get());
        exif.setAperture(newAperture);
    }
}
