package main.view;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import javafx.scene.image.ImageView;
import main.resources.Binding;
import main.viewmodel.MainWindowViewModel;

import java.net.URL;
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
        // GUI Related Bindings
        image.fitWidthProperty().bind(imageView.widthProperty());
        image.fitHeightProperty().bind(imageView.heightProperty());

        // Initialization
        super.initialize(url, resources);
        pictureListView.getChildren().addAll(mwvm.getPictureListViewModel().getImageViewList());
        Binding.applyBinding(imageView, mwvm.getPictureViewModel());
        Binding.applyBinding(exifView, mwvm.getPictureViewModel().getExif());
        Binding.applyBinding(iptcView, mwvm.getPictureViewModel().getIptc());
        Binding.applyBinding(photographerView, mwvm.getPictureViewModel().getPhotographer());
        Binding.applyBinding(searchView, mwvm.getSearchViewModel());
    }
}
