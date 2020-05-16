package main.view;

import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import main.resources.Binding;
import main.viewmodel.PhotographerManagerViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class PhotographerManagerView extends AbstractController {
    public ListView photographerListView;
    public VBox photographerView;

    private PhotographerManagerViewModel photographerManagerViewModel = new PhotographerManagerViewModel();

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        super.initialize(url, resources);
        photographerListView.getItems().addAll(photographerManagerViewModel.getPhotographerListViewModel().getPhotographerList());
        Binding.applyBinding(photographerView, photographerManagerViewModel.getPhotographerViewModel());
    }

    public void selectPhotographer(MouseEvent mouseEvent) {
        // TODO: only selects photographer if clicked explicitly on the text
        int selectedIndex = photographerListView.getItems().indexOf(mouseEvent.getTarget());
        photographerManagerViewModel.selectPhotographer(selectedIndex);
    }

    public void saveChanges() {
        String message = photographerManagerViewModel.updateDatabase();
        PopUpView.display(message);
    }
}
