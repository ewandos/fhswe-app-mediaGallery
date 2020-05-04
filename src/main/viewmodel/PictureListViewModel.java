package main.viewmodel;

import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;

public class PictureListViewModel {

    private List<ImageView> imageViewList = new ArrayList<>();

    public PictureListViewModel(List<PictureViewModel> pictureList) {
        refresh(pictureList);
    }

    public void refresh(List<PictureViewModel> pictureList) {
        for (PictureViewModel pic : pictureList) {
            imageViewList.add(pic.getThumbnail());
        }
    }

    public List<ImageView> getImageViewList() {
        return imageViewList;
    }
}
