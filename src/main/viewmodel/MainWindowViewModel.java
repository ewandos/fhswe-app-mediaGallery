package main.viewmodel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import main.model.PictureModel;
import main.service.PictureServiceMock;
import main.viewmodel.children.PictureListViewModel;
import main.viewmodel.children.PictureViewModel;
import main.viewmodel.children.SearchViewModel;

import java.util.List;
import java.util.logging.Logger;

public class MainWindowViewModel {
    private PictureServiceMock ps = PictureServiceMock.getInstance();
    private final Logger logger = Logger.getLogger("MainWindowViewModel");
    private int selectedIndex = 0;

    // Children View Models
    private PictureListViewModel pictureListViewModel = new PictureListViewModel(ps.getAllPictures());
    private SearchViewModel searchViewModel = new SearchViewModel();
    private PictureViewModel pictureViewModel = new PictureViewModel(ps.getPicture(selectedIndex));

    private BooleanProperty activeSearch = new SimpleBooleanProperty();

    public MainWindowViewModel() {
        activeSearch.bind(searchViewModel.activeSearchProperty());
    }

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
            if (activeSearch.get()) {
                String searchText = searchViewModel.searchTextProperty().get();
                PictureModel pictureModel = ps.getPictureOfSearchResult(searchText, selectedIndex);
                pictureViewModel.refresh(pictureModel);
                logger.info("Selected image in search results with index: " + selectedIndex);
            } else {
                pictureViewModel.refresh(ps.getPicture(selectedIndex));
                logger.info("Selected image with index: " + selectedIndex);
            }
            this.selectedIndex = selectedIndex;
        }
    }

    public String updateModels() {
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

    public void loadSearchedPictures() {
        String searchText = searchViewModel.searchTextProperty().get();
        List<PictureModel> searchResult = ps.searchPictures(searchText);

        logger.info("Searched for: " + searchText + "\nFound: " + searchResult);

        pictureViewModel.refresh(searchResult.get(0));
        pictureListViewModel.refresh(searchResult);
    }
}
