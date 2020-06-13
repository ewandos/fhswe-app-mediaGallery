package main.viewmodel;

import main.model.PhotographerModel;
import main.service.PictureServiceM;
import main.service.interfaces.IPictureService;
import main.viewmodel.children.PhotographerListViewModel;
import main.viewmodel.children.PhotographerViewModel;

public class PhotographerManagerViewModel {
    private int selectedIndex = 0;
    private IPictureService ps = PictureServiceM.getInstance();
    private PhotographerListViewModel photographerListViewModel = new PhotographerListViewModel(ps.getAllPhotographers());
    private PhotographerViewModel photographerViewModel = new PhotographerViewModel(ps.getPhotographer(selectedIndex));


    public PhotographerListViewModel getPhotographerListViewModel() {
        return photographerListViewModel;
    }
    public PhotographerViewModel getPhotographerViewModel() {
        return photographerViewModel;
    }

    public void selectPhotographer(int newIndex) {
        selectedIndex = newIndex;
        if(selectedIndex != -1)
            photographerViewModel.refresh(ps.getPhotographer(selectedIndex));
    }

    public String updateDatabase() {
        photographerViewModel.updateModel();
        PhotographerModel updatedModel = photographerViewModel.getPhotographerModel();

        // TODO: Business Layer validates Data
        ps.updatePhotographer(updatedModel, selectedIndex);
        /*
            PictureViewModel liefert eine Kopie des PictureModels mit den neuen Daten
            Das neue PictureModel wird an den BL gesendet und validiert
            Der BL gibt zurück, ob es einen Fehler gab oder nicht
         */

        photographerListViewModel.refresh(ps.getAllPhotographers());

        return "Changes saved!";
    }
}
