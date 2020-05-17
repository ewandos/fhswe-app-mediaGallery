package main.view;

import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
        photographerListView.setItems(photographerManagerViewModel.getPhotographerListViewModel().getPhotographerList());
        Binding.applyBinding(photographerView, photographerManagerViewModel.getPhotographerViewModel());
    }

    public void selectPhotographer() {
        Text selectedName = (Text) photographerListView.getSelectionModel().getSelectedItem();
        int selectedIndex = photographerListView.getItems().indexOf(selectedName);
        photographerManagerViewModel.selectPhotographer(selectedIndex);
    }

    public void saveChanges() {
        String message = photographerManagerViewModel.updateDatabase();
        PopUpView.display(message);
    }
}
