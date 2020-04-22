package main.factory;

import main.model.PictureModel;

import java.sql.ResultSet;

public class PictureFactory {
    public static PictureModel build(ResultSet set) {
        // create new PictureModel
        // give data to PhotographerFactory to create Model
        // give data to IPTCFactory to create Model
        // give data to EXIFFactory to create Model
        // return Model
        return null;
    }
}
