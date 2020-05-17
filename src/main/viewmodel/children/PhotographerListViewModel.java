package main.viewmodel.children;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import main.model.PhotographerModel;


import java.util.List;
import java.util.logging.Logger;

public class PhotographerListViewModel {
    private final Logger logger = Logger.getLogger("PhotographerListViewModel");
    private ObservableList<Text> photographerDisplayNames =  FXCollections.observableArrayList();

    public PhotographerListViewModel(List<PhotographerModel> photographerModels) {
        refresh(photographerModels);
    }

    public void refresh(List<PhotographerModel> photographerModels) {
        for (PhotographerModel photographer : photographerModels) {
            String displayName = photographer.getFirstName() + " " + photographer.getLastName();
            photographerDisplayNames.add(new Text(displayName));
        }
    }

    public ObservableList<Text> getPhotographerList() {
        return photographerDisplayNames;
    }
}
