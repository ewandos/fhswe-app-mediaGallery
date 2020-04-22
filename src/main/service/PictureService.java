package main.service;

import main.model.PictureModel;

public class PictureService {
    DBService dbservice;

    public PictureModel[] getAllPictures() {
        // send query to DBService
        // get a huge ResultSet
        // iterate through ResultSet rows
        // create new PictureModels with PictureFactory(ResultSet)
        // add Models to new Array
        // return array
        return null;
    }

    public PictureModel searchPicture() {
        return null;
    }

    public PictureModel getPictureById(int id) {
        return null;
    }

    public void updatePicture() {}
}
