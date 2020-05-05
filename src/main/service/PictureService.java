package main.service;

import main.model.PhotographerModel;
import main.model.PictureModel;
import main.service.interfaces.IPictureService;

import java.util.List;

public class PictureService implements IPictureService {
    private DBService dbservice;

    @Override
    public int getPictureCount() {
        return 0;
    }

    @Override
    public List<PictureModel> getAllPictures() {
        return null;
    }

    @Override
    public List<PictureModel> searchPictures(String keyword) {
        return null;
    }

    @Override
    public PictureModel getPicture(String filename) {
        return null;
    }

    @Override
    public void addPicture(String filename) {

    }

    @Override
    public void updatePicture(PictureModel pic) {

    }

    @Override
    public List<PhotographerModel> getAllPhotographers() {
        return null;
    }
}
