package main.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.model.PictureModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PictureViewModel extends ViewModel {
    private String filename;
    private EXIFViewModel exif;
    private IPTCViewModel iptc;
    private PhotographerViewModel photographer;
    private ObjectProperty<Image> image = new SimpleObjectProperty<>();
    private ImageView imageView;
    private StringProperty selector = new SimpleStringProperty();

    public PictureViewModel(PictureModel pic) {
        filename = pic.getFilename();
        exif = new EXIFViewModel(pic.getExif());
        iptc = new IPTCViewModel(pic.getIptc());
        photographer = new PhotographerViewModel(pic.getPhotographer());
        refreshImage(pic);
    }

    public void refresh(PictureModel pic) {
        exif.refresh(pic.getExif());
        iptc.refresh(pic.getIptc());
        photographer.refresh(pic.getPhotographer());
        refreshImage(pic);
    }

    private void refreshImage(PictureModel pic) {
        try {
            String path = "./" + pic.getFilename();
            Image image = new Image(new FileInputStream(path));
            this.image.set(image);
            this.imageView = new ImageView(image);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);
            imageView.setOnMouseClicked(this::clickedPreview);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clickedPreview(MouseEvent mouseEvent) {
        System.out.println(this.filename);
        selector.setValue(this.filename);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public StringProperty getSelector() {
        return selector;
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

    public ObjectProperty<Image> getImage() {
        return image;
    }
}
