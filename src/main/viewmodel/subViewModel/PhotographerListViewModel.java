package main.viewmodel.subViewModel;

import javafx.beans.binding.StringBinding;
import javafx.scene.text.Text;
import main.model.PhotographerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PhotographerListViewModel {
    private final Logger logger = Logger.getLogger("PhotographerListViewModel");
    private List<Text> photographerDisplayNames = new ArrayList<>();

    public PhotographerListViewModel(List<PhotographerModel> photographerModels) {
        refresh(photographerModels);
    }

    public void refresh(List<PhotographerModel> photographerModels) {
        for (PhotographerModel photographer : photographerModels) {
            String displayName = photographer.getFirstName() + " " + photographer.getLastName();
            photographerDisplayNames.add(new Text(displayName));
        }
    }

    public List<Text> getPhotographerList() {
        return photographerDisplayNames;
    }
}
