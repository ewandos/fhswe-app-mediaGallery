package main.viewmodel;

import main.model.PictureModel;
import main.service.PictureServiceMock;
import java.util.List;

public class MainWindowViewModel {
    // Source
    private PictureServiceMock pictureService = PictureServiceMock.getInstance();

    // Children
    private PictureViewModel pictureViewModel;
    private SearchViewModel searchViewModel;
    private PictureListViewModel pictureListViewModel;

    public MainWindowViewModel() {
        List<PictureModel> pictureList = pictureService.getAllPictures();

        pictureListViewModel = new PictureListViewModel(pictureList);

        pictureViewModel = new PictureViewModel(pictureList.get(1));
    }

    public PictureViewModel getPictureViewModel() {
        return pictureViewModel;
    }

    public PictureListViewModel getPictureListViewModel() {
        return pictureListViewModel;
    }
}
