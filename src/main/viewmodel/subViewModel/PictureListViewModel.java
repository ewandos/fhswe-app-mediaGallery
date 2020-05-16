package main.viewmodel.subViewModel;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.model.PictureModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PictureListViewModel {

    private List<ImageView> thumbnails = new ArrayList<>();
    private final Logger logger = Logger.getLogger("PictureListViewModel");

    public PictureListViewModel(List<PictureModel> pictureList) {
        refresh(pictureList);
    }

    public void refresh(List<PictureModel> pictureList) {
        thumbnails.clear();
        for (PictureModel pic : pictureList) {
            try {
                String path = "./images/" + pic.getFilename();
                Image image = new Image(new FileInputStream(path));
                ImageView imView = new ImageView(image);
                imView.setFitHeight(120);
                imView.setPreserveRatio(true);
                thumbnails.add(imView);
            } catch(FileNotFoundException e) {
                logger.warning("File not found!");
            }
        }
    }

    public List<ImageView> getThumbnails() {
        return thumbnails;
    }
}
