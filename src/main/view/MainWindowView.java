package main.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import main.resources.Binding;
import main.viewmodel.MainWindowViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

// responsible for managing the communication of view models and the actual UI
// this class does NOT contain any logic or data manipulation
// e.g. the MainWindowView binds a textfield for the aperture value to a property in its corresponding view model
// it does NOT decides what happens if there is no aperture value available
public class MainWindowView extends AbstractController {

    // the Logger instance can be used to log text output in this class
    // the given class name makes possible that the log does know in which
    // class the entry was printed
    private final Logger logger = Logger.getLogger("MainWindowView");

    // javafx containers that contain several controls
    // the controls will be bound to properties of corresponding view models
    public AnchorPane exifView;
    public AnchorPane iptcView;
    public AnchorPane photographerView;
    public ListView pictureListView;
    public HBox searchView;

    // container that have no corresponding view model
    public AnchorPane previewImageContainer;
    public ImageView previewImage;

    // used to access every view model
    private MainWindowViewModel main = new MainWindowViewModel();

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        super.initialize(url, resources);

        // bind width & height of the preview image to width & height of its container
        // to make the preview image responsive to its container
        previewImage.fitWidthProperty().bind(previewImageContainer.widthProperty());
        previewImage.fitHeightProperty().bind(previewImageContainer.heightProperty());

        // add an observable list to the hBox, so if the content of the list changes
        // the content in the GUI changes
        pictureListView.setItems(main.getPictureListViewModel().getThumbnails());

        // these bindings are NOT done with native javafx functions
        // instead two helper classes main.resources.Binding & main.resources.ReflectionHelper are used
        // to get an idea of how they are work together with the controller and the fxml
        // visit: https://git-inf.technikum-wien.at/BIF-SWE2/JavaFX-Bindings
        // it is a simple example containing only one view and one view model
        Binding.applyBinding(searchView, main.getSearchViewModel());
        Binding.applyBinding(previewImage, main.getPictureViewModel());
        Binding.applyBinding(exifView, main.getPictureViewModel().getExif());
        Binding.applyBinding(iptcView, main.getPictureViewModel().getIptc());
        Binding.applyBinding(photographerView, main.getPictureViewModel().getPhotographer());
    }

    // EVENT FUNCTIONS
    // the following functions are called by clicking on buttons, images etc.

    // called by clicking on an image in the picture list
    // gets the index of the image that was clicked
    // and forwards it to the main view model to update the selected picture
    public void selectImage() {
        ImageView selectedImage = (ImageView) pictureListView.getSelectionModel().getSelectedItem();
        int selectedIndex = pictureListView.getItems().indexOf(selectedImage);
        main.selectPicture(selectedIndex);
    }

    // called by: a save changes button
    // get the response text if the save was successful or not
    // and displays it in a simple pop up window
    public void saveChanges() {
        String response = main.updateModels();
        PopUpView.display(response);
    }

    // called by: reload pictures button
    public void reloadPictures() {
        main.loadAllPictures();
    }

    // called by: manage photographers button
    // displays a new window to edit the data of every photographer
    public void showPhotographerManagerView() {
        Stage window = new Stage();
        Parent root = null;

        // don't know if this counts as logic
        // TODO: maybe needs a refactoring
        try {
            root = FXMLLoader.load(getClass().getResource("../fxml/photographerManager.fxml"));
        } catch (IOException e) {
            logger.warning("Something went wrong loading the FXML file! Check the path and the syntax.");
        }

        window.setTitle("Photographer Manager");
        window.setScene(new Scene(root, 400, 400));
        window.show();
    }


    public void searchPictures() {
        main.loadSearchedPictures();
    }
}
