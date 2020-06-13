package main.service;

/*
    IT NEEDS TO CONTAIN A LIST OF PICTUREMODELS that updates by every command
    the MainWindowViewModel depends that it always get the same pictureModel
 */

import main.model.EXIFModel;
import main.model.IPTCModel;
import main.model.PhotographerModel;
import main.model.PictureModel;
import main.service.interfaces.IPictureService;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PictureService implements IPictureService {
    private static PictureService instance = new PictureService();
    private final Logger logger = Logger.getLogger("PictureService");
    private Connection con;

    private PictureService() {
        String DB_URL = "jdbc:h2:./database/database.mv.db";
        String USER = "";
        String PASS = "";

        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            logger.info("Connected...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static PictureService getInstance() {
        return instance;
    }

    @Override
    public int getPictureCount() {
        try {
            PreparedStatement stmt = con.prepareStatement("select count(*) as COUNT from picture;");
            ResultSet res = stmt.executeQuery();
            res.next();
            return res.getInt("COUNT");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<PictureModel> getAllPictures() {
        List<PictureModel> retList = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from PICTURE left join PHOTOGRAPHER P on PICTURE.PHOTOGRAPHER_ID = P.ID;");
            ResultSet res = stmt.executeQuery();

            while(res.next()){
                retList.add(getPictureModelFromRow(res));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retList;
    }

    @Override
    public List<PictureModel> searchPictures(String keyword) {
        List<PictureModel> retList = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from PICTURE join PHOTOGRAPHER P on PICTURE.PHOTOGRAPHER_ID = P.ID where lower(FILENAME) like ? or lower(IPTC_DESCRIPTION) like ? or lower(P.NAME) like ? or lower(P.FORENAME) like ?;");
            String pattern = "%" + keyword + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            stmt.setString(3, pattern);
            stmt.setString(4, pattern);
            ResultSet res = stmt.executeQuery();

            while(res.next()){
                retList.add(getPictureModelFromRow(res));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retList;
    }

    @Override
    public PictureModel getPictureOfSearchResult(String keyword, int index) {
        List<PictureModel> retList = searchPictures(keyword);
        return retList.get(index);
    }

    @Override
    public PictureModel getPicture(String filename) {
        try {
            PreparedStatement stmt = con.prepareStatement("select * from PICTURE join PHOTOGRAPHER P on PICTURE.PHOTOGRAPHER_ID = P.ID where PICTURE.FILENAME = ?");
            stmt.setString(1, filename);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                return getPictureModelFromRow(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PictureModel getPicture(int index) {
        try {
            PreparedStatement stmt = con.prepareStatement("select * from PICTURE join PHOTOGRAPHER P on PICTURE.PHOTOGRAPHER_ID = P.ID where PICTURE.ID = ?");
            stmt.setInt(1, ++index);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                return getPictureModelFromRow(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addPicture(String filename) {

    }

    @Override
    public PictureModel updatePicture(PictureModel pic) {
        EXIFModel ex = pic.getExif();
        IPTCModel ip = pic.getIptc();
        PhotographerModel pg = pic.getPhotographer();

        try {
            // Update EXIF
            PreparedStatement stmt = con.prepareStatement("update picture set EXIF_CAMERA = ?, EXIF_DATE = ?, EXIF_ISO = ?, EXIF_APERTURE = ? where ID = ?");
            stmt.setString(1, ex.getCamera());
            stmt.setDate(2, Date.valueOf(ex.getDate()));
            stmt.setInt(3, ex.getIso());
            stmt.setFloat(4, ex.getAperture());
            stmt.setString(5, pic.getFilename());
            stmt.executeUpdate();

            // Update IPTC
            stmt = con.prepareStatement("update picture set IPTC_DESCRIPTION = ?, IPTC_RATING = ? where FILENAME = ?");
            stmt.setString(1, ip.getDescription());
            stmt.setInt(2, ip.getRating());
            stmt.setString(3, pic.getFilename());
            stmt.executeUpdate();

            // Update PHOTOGRAPHER

            stmt = con.prepareStatement("update PHOTOGRAPHER set FORENAME = ?, NAME = ?, BIRTH = ?, NOTE = ? where FILENAME = ?");
            stmt.setString(1, pg.getFirstName());
            stmt.setString(2, pg.getLastName());
            stmt.setDate(3, Date.valueOf(pg.getBirthday()));
            stmt.setString(4, pg.getNotes());
            stmt.setString(5, pic.getFilename());
            stmt.executeUpdate();

            // TODO: Tags wont update!
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return pic;
    }

    @Override
    public List<PhotographerModel> getAllPhotographers() {
        List<PhotographerModel> retList = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from PHOTOGRAPHER");
            ResultSet res = stmt.executeQuery();

            while(res.next()){
                retList.add(getPhotographerModelFromRow(res));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retList;
    }

    @Override
    public PhotographerModel getPhotographer(int i) {
        try {
            PreparedStatement stmt = con.prepareStatement("select * from PHOTOGRAPHER where ID = ?");
            stmt.setInt(1, ++i);
            ResultSet res = stmt.executeQuery();

            if(res.next()){
                return getPhotographerModelFromRow(res);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PhotographerModel updatePhotographer(PhotographerModel photographerModel, int index) {
        try {
            PreparedStatement stmt = con.prepareStatement("update PHOTOGRAPHER set FORENAME = ?, NAME = ?, BIRTH = ?, NOTE = ? where ID = ?");
            stmt.setString(1, photographerModel.getFirstName());
            stmt.setString(2, photographerModel.getLastName());
            stmt.setDate(3, Date.valueOf(photographerModel.getBirthday()));
            stmt.setString(4, photographerModel.getNotes());
            stmt.setInt(5, index);
            stmt.executeUpdate();
            return photographerModel;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PictureModel getPictureModelFromRow(ResultSet row) throws SQLException {
        PictureModel pic = new PictureModel(row.getString("FILENAME"));

        // BUILD PHOTOGRAPHER
        PhotographerModel pg = new PhotographerModel();
        pg.setFirstName(row.getString("FORENAME"));
        pg.setLastName(row.getString("NAME"));

        Date birthday = row.getDate("BIRTH");
        if (birthday != null) {
            LocalDate localDate = DateUtils.asLocalDate(birthday);
            pg.setBirthday(localDate);
        } else {
            pg.setBirthday(null);
        }
        pg.setNotes(row.getString("NOTE"));

        // BUILD EXIF
        EXIFModel ex = new EXIFModel();
        ex.setCamera(row.getString("EXIF_CAMERA"));

        Date todayDate = row.getDate("EXIF_DATE");
        if (todayDate != null) {
            LocalDate localDate = DateUtils.asLocalDate(todayDate);
            ex.setDate(localDate);
        } else {
            ex.setDate(null);
        }

        ex.setAperture(row.getFloat("EXIF_APERTURE"));

        // BUILD IPTC
        IPTCModel ip = new IPTCModel();
        ip.setDescription(row.getString("IPTC_DESCRIPTION"));
        ip.setRating(row.getInt("IPTC_RATING"));
        // TODO: TAGS FEHLEN

        pic.setPhotographer(pg);
        pic.setExif(ex);
        pic.setIptc(ip);

        return pic;
    }

    private PhotographerModel getPhotographerModelFromRow(ResultSet row) throws SQLException {
        PhotographerModel pg = new PhotographerModel();
        pg.setFirstName(row.getString("FORENAME"));
        pg.setLastName(row.getString("NAME"));

        Date birthday = row.getDate("BIRTH");
        if (birthday != null) {
            LocalDate localDate = DateUtils.asLocalDate(birthday);
            pg.setBirthday(localDate);
        } else {
            pg.setBirthday(null);
        }
        pg.setNotes(row.getString("NOTE"));

        return pg;
    }
}
