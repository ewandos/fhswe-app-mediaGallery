package main.viewmodel;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.model.PictureModel;

import javafx.scene.input.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PictureListViewModel {

    List<ImageView> imageViewList = new ArrayList<>();

    public PictureListViewModel(List<PictureModel> pictureList) {
        for (PictureModel pic : pictureList) {
            String path = "./images/" + pic.getFilename();
            Image image = null;

            try {
                image = new Image(new FileInputStream(path));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(120);
            imageView.setPreserveRatio(true);
            imageView.setOnMouseClicked(this::mouseClicked);
            imageViewList.add(imageView);
        }
    }

    public List<ImageView> getImageViewList() {
        return imageViewList;
    }

    public void mouseClicked(MouseEvent event) {

        System.out.println("Clicked an image!");
    }
}
