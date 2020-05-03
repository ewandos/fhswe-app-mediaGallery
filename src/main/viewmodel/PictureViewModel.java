package main.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import main.model.PictureModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PictureViewModel extends ViewModel {
    private EXIFViewModel exif;
    private IPTCViewModel iptc;
    private PhotographerViewModel photographer;
    private ObjectProperty<Image> image = new SimpleObjectProperty<>();

    public PictureViewModel(PictureModel pic) {
        refresh(pic);
    }

    public void refresh(PictureModel pic) {
        exif = new EXIFViewModel(pic.getExif());
        iptc = new IPTCViewModel(pic.getIptc());
        photographer = new PhotographerViewModel(pic.getPhotographer());
        try {
            String path = "./images/" + pic.getFilename();
            Image image = new Image(new FileInputStream(path));
            this.image.set(image);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public EXIFViewModel getExif() {
        return exif;
    }

    public IPTCViewModel getIptc() {
        return iptc;
    }

    public PhotographerViewModel getPhotographer() {
        return photographer;
    }

    public ObjectProperty<Image> imageProperty() {
        return image;
    }
}
