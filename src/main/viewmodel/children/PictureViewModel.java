package main.viewmodel.children;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import main.model.PictureModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class PictureViewModel {
    private EXIFViewModel exif;
    private IPTCViewModel iptc;
    private PhotographerViewModel photographer;
    private ObjectProperty<Image> image = new SimpleObjectProperty<>();
    private PictureModel pictureModel;
    private final Logger logger = Logger.getLogger("PictureViewModel");

    public PictureViewModel(PictureModel pic) {
        this.pictureModel = pic;
        exif = new EXIFViewModel(pic.getExif());
        iptc = new IPTCViewModel(pic.getIptc());
        photographer = new PhotographerViewModel(pic.getPhotographer());
        loadImage(pic);
    }

    public void refresh(PictureModel pic) {
        this.pictureModel = pic;
        exif.refresh(pic.getExif());
        iptc.refresh(pic.getIptc());
        photographer.refresh(pic.getPhotographer());
        loadImage(pic);
    }

    private void loadImage(PictureModel pic) {
        try {
            String path = "./images/" + pic.getFilename();
            Image image = new Image(new FileInputStream(path));
            this.image.set(image);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PictureModel getUpdatedModel() {
        // TODO: Photographer doesn't update!
        exif.saveChanges(pictureModel.getExif());
        iptc.saveChanges(pictureModel.getIptc());
        photographer.updateModel();

        logger.info("Updated Model " + this.pictureModel);
        refresh(pictureModel);

        return pictureModel;
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
