package main.viewmodel.children;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.model.PictureModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Logger;

public class PictureListViewModel {

    private ObservableList<ImageView> thumbs = FXCollections.observableArrayList();
    private final Logger logger = Logger.getLogger("PictureListViewModel");

    public PictureListViewModel(List<PictureModel> pictureList) {
        refresh(pictureList);
    }

    public void refresh(List<PictureModel> pictureList) {
        thumbs.clear();
        for (PictureModel pic : pictureList) {
            try {
                String path = "./images/" + pic.getFilename();
                Image image = new Image(new FileInputStream(path));
                ImageView imView = new ImageView(image);
                imView.setFitHeight(120);
                imView.setPreserveRatio(true);
                thumbs.add(imView);
            } catch(FileNotFoundException e) {
                logger.warning("File not found!");
            }
        }
    }

    public ObservableList<ImageView> getThumbs() {
        return thumbs;
    }
}
