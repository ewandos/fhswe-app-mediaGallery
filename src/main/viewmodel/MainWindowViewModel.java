package main.viewmodel;
import main.model.PictureModel;
import main.service.PictureServiceMock;
import java.util.logging.Logger;

public class MainWindowViewModel {
    private PictureServiceMock ps = PictureServiceMock.getInstance();
    private final Logger logger = Logger.getLogger("MainWindowViewModel");

    // Children View Models
    private PictureListViewModel pictureListViewModel;
    private SearchViewModel searchViewModel;
    private PictureViewModel selectedPicture;
    private int selectedIndex = 0;

    public MainWindowViewModel() {
        selectedPicture = new PictureViewModel(ps.getPicture(selectedIndex));
        pictureListViewModel = new PictureListViewModel(ps.getAllPictures());
        searchViewModel = new SearchViewModel();
    }

    public PictureViewModel getPictureViewModel() {
        return selectedPicture;
    }

    public PictureListViewModel getPictureListViewModel() {
        return pictureListViewModel;
    }

    public SearchViewModel getSearchViewModel() {
        return searchViewModel;
    }

    public void selectPicture(int selectedIndex) {
        if (this.selectedIndex != selectedIndex) {
            this.selectedIndex = selectedIndex;
            selectedPicture.refresh(ps.getPicture(selectedIndex));
            logger.info("Selected image with index: " + selectedIndex);
        } else {
            logger.info("Image " + selectedIndex + " is already selected.");
        }
    }

    public void updateDatabase() {
        PictureModel pic = selectedPicture.getUpdatedModel();

        // TODO: Business Layer validates Data
        /*
            PictureViewModel liefert eine Kopie des PictureModels mit den neuen Daten
            Das neue PictureModel wird an den BL gesendet und validiert
            Der BL gibt zurück, ob es einen Fehler gab oder nicht
         */

    }
}
