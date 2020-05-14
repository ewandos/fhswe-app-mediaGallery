package main.view;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import javafx.scene.image.ImageView;
import main.resources.Binding;
import main.viewmodel.MainWindowViewModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowView extends AbstractController {
    // FX:Container
    public AnchorPane exifView;
    public AnchorPane iptcView;
    public AnchorPane photographerView;
    public HBox pictureListView;
    public AnchorPane imageView;
    public ImageView image;
    public HBox searchView;

    // ViewModel
    private MainWindowViewModel mwvm = new MainWindowViewModel();

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        super.initialize(url, resources);

        // GUI Related Bindings
        image.fitWidthProperty().bind(imageView.widthProperty());
        image.fitHeightProperty().bind(imageView.heightProperty());

        List<ImageView> thumbnails = new ArrayList<>();
        for (Image im : mwvm.getPictureListViewModel().getImages()) {
            ImageView imView = new ImageView(im);
            imView.setFitHeight(120);
            imView.setPreserveRatio(true);
            thumbnails.add(imView);
        }

        pictureListView.getChildren().addAll(thumbnails);

        Binding.applyBinding(searchView, mwvm.getSearchViewModel());
        Binding.applyBinding(imageView, mwvm.getPictureViewModel());
        Binding.applyBinding(exifView, mwvm.getPictureViewModel().getExif());
        Binding.applyBinding(iptcView, mwvm.getPictureViewModel().getIptc());
        Binding.applyBinding(photographerView, mwvm.getPictureViewModel().getPhotographer());
    }

    public void selectImage(MouseEvent mouseEvent) {
        int selectedIndex = pictureListView.getChildren().indexOf(mouseEvent.getTarget());
        mwvm.selectPicture(selectedIndex);
    }

    public void saveChanges() {
        mwvm.updateDatabase();
    }
}
