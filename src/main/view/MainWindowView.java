package main.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import main.model.PictureModel;
import main.resources.Binding;
import main.service.PictureServiceMock;
import main.viewmodel.*;

import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowView extends AbstractController {
    // Mocks
    private PictureServiceMock mock = PictureServiceMock.getInstance();
    private List<PictureModel> picList = mock.getAllPictures();
    private List<PictureViewModel> pictureViewModelList = new ArrayList<>();

    // FX:Container
    public AnchorPane exifView;
    public AnchorPane iptcView;
    public AnchorPane photographerView;
    public HBox pictureScrollView;
    public AnchorPane imageViewContainer;

    // FX:Controls
    public ImageView imageView;

    // ViewModels that needs to be bind explicitly
    private List<ImageView> imageViewList = new ArrayList<>();
    private StringProperty selectedPicture = new SimpleStringProperty();

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        // Initialization
        super.initialize(url, resources);

        for (PictureModel pic: picList) {
            PictureViewModel picVM = new PictureViewModel(pic);
            pictureViewModelList.add(picVM);
            selectedPicture.bindBidirectional(picVM.getSelector());
        }

        // ViewModels that are bind automatically
        PictureViewModel pictureViewModel = pictureViewModelList.get(0);

        for (PictureViewModel pic : pictureViewModelList) {
            imageViewList.add(pic.getImageView());
        }

        ObservableList pictureListObs = pictureScrollView.getChildren();
        pictureListObs.addAll(imageViewList);

        // Bindings
        imageView.fitWidthProperty().bind(imageViewContainer.widthProperty());
        imageView.imageProperty().bind(pictureViewModel.getImage());

        Binding.applyBinding(exifView, pictureViewModel.getExif());
        Binding.applyBinding(iptcView, pictureViewModel.getIptc());
        Binding.applyBinding(photographerView, pictureViewModel.getPhotographer());
    }
}
