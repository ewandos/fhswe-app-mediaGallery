package main.viewmodel;
import main.model.PictureModel;
import main.service.PictureServiceMock;
import main.viewmodel.subViewModel.PictureListViewModel;
import main.viewmodel.subViewModel.PictureViewModel;
import main.viewmodel.subViewModel.SearchViewModel;

import java.util.logging.Logger;

public class MainWindowViewModel {
    private PictureServiceMock ps = PictureServiceMock.getInstance();
    private final Logger logger = Logger.getLogger("MainWindowViewModel");
    private int selectedIndex = 0;

    // Children View Models
    private PictureListViewModel pictureListViewModel = new PictureListViewModel(ps.getAllPictures());
    private SearchViewModel searchViewModel = new SearchViewModel();
    private PictureViewModel pictureViewModel = new PictureViewModel(ps.getPicture(selectedIndex));

    public PictureViewModel getPictureViewModel() {
        return pictureViewModel;
    }

    public PictureListViewModel getPictureListViewModel() {
        return pictureListViewModel;
    }

    public SearchViewModel getSearchViewModel() {
        return searchViewModel;
    }

    public void selectPicture(int selectedIndex) {
        if(selectedIndex != -1) {
            this.selectedIndex = selectedIndex;
            pictureViewModel.refresh(ps.getPicture(selectedIndex));
            logger.info("Selected image with index: " + selectedIndex);
        }
    }

    public String updateDatabase() {
        PictureModel pic = pictureViewModel.getUpdatedModel();

        // TODO: Business Layer validates Data
        /*
            PictureViewModel liefert eine Kopie des PictureModels mit den neuen Daten
            Das neue PictureModel wird an den BL gesendet und validiert
            Der BL gibt zurück, ob es einen Fehler gab oder nicht
         */

        return "Changes saved!";
    }

    public void loadAllPictures() {
        pictureViewModel.refresh(ps.getPicture(selectedIndex));
        pictureListViewModel.refresh(ps.getAllPictures());
        logger.info("Reloaded all images");
    }
}
