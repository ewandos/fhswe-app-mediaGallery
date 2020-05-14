package main.viewmodel;

import javafx.scene.image.Image;
import main.model.PictureModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PictureListViewModel {

    private List<Image> imageList = new ArrayList<>();

    public PictureListViewModel(List<PictureModel> pictureList) {
        for (PictureModel pic : pictureList) {
            try {
                String path = "./images/" + pic.getFilename();
                Image image = new Image(new FileInputStream(path));
                imageList.add(image);
            } catch(FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Image> getImages() {
        return imageList;
    }
}
