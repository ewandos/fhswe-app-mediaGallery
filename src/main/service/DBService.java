package main.service;
import java.sql.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.Logger;

public class DBService {
    private static DBService instance = new DBService();

    private Connection con = null;

    private static final String DB_URL = "jdbc:h2:/Users/philipewert/repository/fhswe-java-app-mediaGallery/database.mv.db";
    private static final String USER = "";
    private static final String PASS = "";
    private final Logger logger = Logger.getLogger("DBService");

    Dictionary<String, PreparedStatement> preparedStatements = new Hashtable<>();

    private DBService() {
        try {
            con = DriverManager.getConnection(DB_URL,USER,PASS);
            logger.info("Connected...");

            preparedStatements.put("getAllPictures", con.prepareStatement("select * from PICTURE join PHOTOGRAPHER P on PICTURE.PHOTOGRAPHER_ID = P.ID"));
            preparedStatements.put("getPicture", con.prepareStatement("select * from PICTURE join PHOTOGRAPHER P on PICTURE.PHOTOGRAPHER_ID = P.ID where PICTURE.ID = ?"));
            preparedStatements.put("getEXIF", con.prepareStatement("select EXIF_CAMERA, EXIF_DATE, EXIF_ISO, EXIF_APERTURE from PICTURE where ID = ?"));
            preparedStatements.put("getIPTC", con.prepareStatement("select IPTC_DESCRIPTION, IPTC_RATING from PICTURE where ID = ?"));

            preparedStatements.put("searchPicture", con.prepareStatement("select * from PICTURE join PHOTOGRAPHER P on PICTURE.PHOTOGRAPHER_ID = P.ID where lower(FILENAME) like ? or lower(IPTC_DESCRIPTION) like ? or lower(P.NAME) like ? or lower(P.FORENAME) like ?"));
            preparedStatements.put("searchPhotographer", con.prepareStatement("select * from PHOTOGRAPHER where FORENAME = ? or NAME = ?"));
            preparedStatements.put("searchTag", con.prepareStatement("select * from TAG where DESCRIPTION = ?"));

            preparedStatements.put("insertPicture", con.prepareStatement("insert into PICTURE (FILENAME, PHOTOGRAPHER_ID, EXIF_CAMERA, EXIF_DATE, EXIF_ISO, EXIF_APERTURE, IPTC_DESCRIPTION, IPTC_RATING) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)"));
            preparedStatements.put("insertPhotographer", con.prepareStatement("insert into PHOTOGRAPHER (FORENAME, NAME, BIRTH, NOTE) VALUES ( ?, ?, ?, ?)"));
            preparedStatements.put("insertTag", con.prepareStatement("insert into TAG (DESCRIPTION) VALUE (?)"));

            preparedStatements.put("tagPicture", con.prepareStatement("insert into PICTURE_TAG VALUES ( ?, ?)"));

            preparedStatements.put("updateEXIF", con.prepareStatement("update picture set EXIF_CAMERA = ?, EXIF_DATE = ?, EXIF_ISO = ?, EXIF_APERTURE = ? where ID = ?"));
            preparedStatements.put("updateIPTC", con.prepareStatement("update picture set IPTC_DESCRIPTION = ?, IPTC_RATING = ? where ID = ?"));
            preparedStatements.put("updatePhotographer", con.prepareStatement("update PHOTOGRAPHER set FORENAME = ?, NAME = ?, BIRTH = ?, NOTE = ? where ID = ?"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBService getInstance() {
        return instance;
    }

    PreparedStatement getStatement(String sql) {
        try {
            return con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    ResultSet executeQuery(PreparedStatement ps) {
        try {
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void executeUpdate(PreparedStatement ps) {
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
