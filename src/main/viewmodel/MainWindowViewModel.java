package main.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import main.model.PictureModel;
import main.service.PictureServiceMock;

import java.util.ArrayList;
import java.util.List;

public class MainWindowViewModel {
    // Source
    private PictureServiceMock pictureService = PictureServiceMock.getInstance();

    // Children
    private PictureListViewModel pictureListViewModel;
    private PictureViewModel selectedPicture;

    public MainWindowViewModel() {
        List<PictureViewModel> pictureList = new ArrayList<>();
        for (PictureModel pic : pictureService.getAllPictures())
            pictureList.add(new PictureViewModel(pic, this));

        pictureListViewModel = new PictureListViewModel(pictureList);
        PictureModel pic = pictureList.get(0).getPictureModel();
        selectedPicture = new PictureViewModel(pic, this);
    }

    public void setSelectedPicture(PictureModel pic) {
        this.selectedPicture.refresh(pic);
    }

    public PictureViewModel getPictureViewModel() {
        return selectedPicture;
    }

    public PictureListViewModel getPictureListViewModel() {
        return pictureListViewModel;
    }
}
