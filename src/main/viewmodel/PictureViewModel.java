package main.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.model.PictureModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PictureViewModel extends ViewModel {
    private EXIFViewModel exif;
    private IPTCViewModel iptc;
    private PhotographerViewModel photographer;
    private ObjectProperty<Image> image = new SimpleObjectProperty<>();
    private MainWindowViewModel mwvm;
    private PictureModel pictureModel;

    public PictureViewModel(PictureModel pic, MainWindowViewModel mwvm) {
        this.mwvm = mwvm;
        this.pictureModel = pic;

        exif = new EXIFViewModel(pic.getExif());
        iptc = new IPTCViewModel(pic.getIptc());
        photographer = new PhotographerViewModel(pic.getPhotographer());
        loadImage(pic);
    }

    public void refresh(PictureModel pic) {
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

    private void selectImage(MouseEvent mouseEvent) {
        mwvm.setSelectedPicture(pictureModel);
        System.out.println(this);
    }

    public EXIFViewModel getExif() {
        return exif;
    }

    public IPTCViewModel getIptc() {
        return iptc;
    }

    public ImageView getThumbnail() {
        ImageView imageView = new ImageView(image.get());
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);
        imageView.setOnMouseClicked(this::selectImage);
        return imageView;
    }

    public PictureModel getPictureModel() {
        return pictureModel;
    }

    public PhotographerViewModel getPhotographer() {
        return photographer;
    }

    public ObjectProperty<Image> imageProperty() {
        return image;
    }
}
