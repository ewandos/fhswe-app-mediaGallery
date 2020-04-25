package main.service;
import java.sql.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.Logger;

public class DBService {
    private static DBService instance = new DBService();
    private final Logger logger = Logger.getLogger("DBService");
    private Dictionary<String, PreparedStatement> preparedStatements = new Hashtable<>();

    public static DBService getInstance() {
        return instance;
    }

    private DBService() {
        String DB_URL = "jdbc:h2:/Users/philipewert/repository/fhswe-java-app-mediaGallery/database.mv.db";
        String USER = "";
        String PASS = "";

        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            logger.info("Connected...");
            preparedStatements.put("getAllPictures", con.prepareStatement("select * from PICTURE join PHOTOGRAPHER P on PICTURE.PHOTOGRAPHER_ID = P.ID"));
            preparedStatements.put("getPicture", con.prepareStatement("select * from PICTURE join PHOTOGRAPHER P on PICTURE.PHOTOGRAPHER_ID = P.ID where PICTURE.ID = ?"));
            preparedStatements.put("getEXIF", con.prepareStatement("select EXIF_CAMERA, EXIF_DATE, EXIF_ISO, EXIF_APERTURE from PICTURE where ID = ?"));
            preparedStatements.put("getIPTC", con.prepareStatement("select IPTC_DESCRIPTION, IPTC_RATING from PICTURE where ID = ?"));
            preparedStatements.put("getPhotographer", con.prepareStatement("select * from PHOTOGRAPHER where FORENAME = ? and NAME = ?"));
            preparedStatements.put("getTag", con.prepareStatement("select * from TAG where DESCRIPTION = ?"));

            preparedStatements.put("searchPicture", con.prepareStatement("select * from PICTURE join PHOTOGRAPHER P on PICTURE.PHOTOGRAPHER_ID = P.ID where lower(FILENAME) like ? or lower(IPTC_DESCRIPTION) like ? or lower(P.NAME) like ? or lower(P.FORENAME) like ?"));

            preparedStatements.put("insertPicture", con.prepareStatement("insert into PICTURE (FILENAME, PHOTOGRAPHER_ID, EXIF_CAMERA, EXIF_DATE, EXIF_ISO, EXIF_APERTURE, IPTC_DESCRIPTION, IPTC_RATING) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)"));
            preparedStatements.put("insertPhotographer", con.prepareStatement("insert into PHOTOGRAPHER (FORENAME, NAME, BIRTH, NOTE) VALUES ( ?, ?, ?, ?)"));
            preparedStatements.put("insertTag", con.prepareStatement("insert into TAG (DESCRIPTION) VALUES (?)"));

            preparedStatements.put("tagPicture", con.prepareStatement("insert into PICTURE_TAG VALUES ( ?, ?)"));

            preparedStatements.put("updateEXIF", con.prepareStatement("update picture set EXIF_CAMERA = ?, EXIF_DATE = ?, EXIF_ISO = ?, EXIF_APERTURE = ? where ID = ?"));
            preparedStatements.put("updateIPTC", con.prepareStatement("update picture set IPTC_DESCRIPTION = ?, IPTC_RATING = ? where ID = ?"));
            preparedStatements.put("updatePhotographer", con.prepareStatement("update PHOTOGRAPHER set FORENAME = ?, NAME = ?, BIRTH = ?, NOTE = ? where ID = ?"));

            // private Statements
            preparedStatements.put("getPhotographerId", con.prepareStatement("select ID from PHOTOGRAPHER where FORENAME = ? and NAME = ?"));
            preparedStatements.put("getPictureId", con.prepareStatement("select ID from PICTURE where FILENAME = ?"));
            preparedStatements.put("getTagId", con.prepareStatement("select ID from TAG where DESCRIPTION = ?"));
            logger.info("Created Prepared Statements...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getPictureIdByFilename(String filename) {
        try {
            PreparedStatement stmt = preparedStatements.get("getPictureId");
            stmt.setString(1, filename);
            ResultSet res = stmt.executeQuery();
            res.next();
            return res.getInt("ID");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int getPhotographerIdByName(String forename, String name) {
        try {
            PreparedStatement stmt = preparedStatements.get("getPhotographerId");
            stmt.setString(1, forename);
            stmt.setString(2, name);
            ResultSet res = stmt.executeQuery();
            res.next();
            return res.getInt("ID");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int getTagIdByDescription(String description) {
        try {
            PreparedStatement stmt = preparedStatements.get("getTagId");
            stmt.setString(1, description);
            ResultSet res = stmt.executeQuery();
            res.next();
            return res.getInt("ID");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    ResultSet getAllPictures() {
        try {
            PreparedStatement stmt = preparedStatements.get("getAllPictures");
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    ResultSet getPictureById(int id) {
        try {
            PreparedStatement stmt = preparedStatements.get("getPicture");
            stmt.setInt(1, id);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    ResultSet getEXIFByPictureId(int id) {
        try {
            PreparedStatement stmt = preparedStatements.get("getEXIF");
            stmt.setInt(1, id);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    ResultSet getIPTCByPictureId(int id) {
        try {
            PreparedStatement stmt = preparedStatements.get("getIPTC");
            stmt.setInt(1, id);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    ResultSet searchPictureByString(String searchtext) {
        try {
            PreparedStatement stmt = preparedStatements.get("searchPicture");
            String pattern = "%" + searchtext + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            stmt.setString(3, pattern);
            stmt.setString(4, pattern);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    ResultSet getPhotographerByName(String forename, String name) {
        try {
            PreparedStatement stmt = preparedStatements.get("getPhotographer");
            stmt.setString(1, forename);
            stmt.setString(2, name);

            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    ResultSet getTagByDescription(String description) {
        try {
            PreparedStatement stmt = preparedStatements.get("getTag");
            stmt.setString(1, description);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    int insertPicture(String filename, int photographerId, String exif_camera, Date exif_date, int exif_iso, float exif_aperture, String iptc_description, int iptc_rating) {
        try {
            PreparedStatement stmt = preparedStatements.get("insertPicture");
            stmt.setString(1, filename);
            stmt.setInt(2, photographerId);
            stmt.setString(3, exif_camera);
            stmt.setDate(4, exif_date);
            stmt.setInt(5, exif_iso);
            stmt.setFloat(6, exif_aperture);
            stmt.setString(7, iptc_description);
            stmt.setInt(8, iptc_rating);
            stmt.executeUpdate();
            return getPictureIdByFilename(filename);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    int insertPhotographer(String forename, String name, Date birth, String note) {
        try {
            PreparedStatement stmt = preparedStatements.get("insertPhotographer");
            stmt.setString(1, forename);
            stmt.setString(2, name);
            stmt.setDate(3, birth);
            stmt.setString(4, note);
            stmt.executeUpdate();
            return getPhotographerIdByName(forename, name);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    int insertTag(String description) {
        try {
            PreparedStatement stmt = preparedStatements.get("insertTag");
            stmt.setString(1, description);
            stmt.executeUpdate();
            return getTagIdByDescription(description);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    boolean updateEXIFByPictureId(int id, String exif_camera, Date exif_date, int exif_iso, float exif_aperture) {
        try {
            PreparedStatement stmt = preparedStatements.get("updateExif");
            stmt.setString(1, exif_camera);
            stmt.setDate(2, exif_date);
            stmt.setInt(3, exif_iso);
            stmt.setFloat(4, exif_aperture);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    boolean updateIPTCByPictureId(int id, String iptc_description, int iptc_rating) {
        try {
            PreparedStatement stmt = preparedStatements.get("updateIPTC");
            stmt.setString(1, iptc_description);
            stmt.setInt(2, iptc_rating);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    boolean updatePhotographerByName(String forename_old, String name_old, String forename, String name, Date birth, String note) {
        try {
            int id = getPhotographerIdByName(forename_old, name_old);
            PreparedStatement stmt = preparedStatements.get("updatePhotographer");
            stmt.setString(1, forename);
            stmt.setString(2, name);
            stmt.setDate(3, birth);
            stmt.setString(4, note);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
