package main.viewmodel;
import main.model.PictureModel;
import main.service.PictureServiceMock;
import java.util.logging.Logger;

public class MainWindowViewModel {
    private PictureServiceMock ps = PictureServiceMock.getInstance();
    private final Logger logger = Logger.getLogger("MainWindowViewModel");
    private int selectedIndex = 0;

    // Children View Models
    private PictureListViewModel pictureListViewModel = new PictureListViewModel(ps.getAllPictures());
    private SearchViewModel searchViewModel = new SearchViewModel();
    private PictureViewModel selectedPicture = new PictureViewModel(ps.getPicture(selectedIndex));

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

    public String updateDatabase() {
        PictureModel pic = selectedPicture.getUpdatedModel();

        // TODO: Business Layer validates Data
        /*
            PictureViewModel liefert eine Kopie des PictureModels mit den neuen Daten
            Das neue PictureModel wird an den BL gesendet und validiert
            Der BL gibt zurück, ob es einen Fehler gab oder nicht
         */

        return "Changes saved!";
    }

    public void loadAllPictures() {
        selectPicture(0);
    }
}
