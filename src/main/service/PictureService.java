package main.service;

import main.model.PictureModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PictureService {
    private DBService dbservice;
    private final Logger logger = Logger.getLogger("PictureService");

    public PictureModel[] getAllPictures() {
        PreparedStatement ps = dbservice.preparedStatements.get("getAllPictures");
        ResultSet rs = dbservice.executeQuery(ps);

        // aus ResultSet PictureModels bauen

        return null;
    }

    public PictureModel getPicture(String searchtext) {
        PreparedStatement ps = dbservice.preparedStatements.get("searchPicture");
        ps.setString(1, searchtext);
        ResultSet rs = dbservice.executeQuery(ps);

        return null;
    }

    public PictureModel getPicture(int id) {
        return null;
    }

    public void updatePicture(int id, PictureModel picture) {

    }

    public void insertPicture(PictureModel picture) {

    }
}
