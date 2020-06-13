package main.service.test;
import main.model.PictureModel;
import main.service.PictureServiceM;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PictureServiceMockTest {
    @Test
    void returns_all_pictures() {
        PictureServiceM pservice = PictureServiceM.getInstance();
        List<PictureModel> pictures = pservice.getAllPictures();
        assertNotNull(pictures);
        assertEquals(5, pictures.size());
    }

    @Test
    void can_search_by_text_in_filename() {
        PictureServiceM pservice = PictureServiceM.getInstance();
        List<PictureModel> pictures = pservice.searchPictures("katze");
        assertNotNull(pictures);
        assertEquals(1, pictures.size());
    }

    @Test
    void can_search_by_text_in_filename_not_case_sensitive() {
        PictureServiceM pservice = PictureServiceM.getInstance();
        List<PictureModel> pictures = pservice.searchPictures("kAtZe");
        assertNotNull(pictures);
        assertEquals(1, pictures.size());
    }

    @Test
    void can_search_by_text_in_filename_pattern_recognition() {
        PictureServiceM pservice = PictureServiceM.getInstance();
        List<PictureModel> pictures = pservice.searchPictures("haus");
        assertNotNull(pictures);
        assertEquals(2, pictures.size());
    }

    @Test
    void can_get_picture_by_filename() {
        PictureServiceM pservice = PictureServiceM.getInstance();
        String filename = "maus.jpg";
        PictureModel pic = pservice.getPicture(filename);
        assertNotNull(pic);
        assertEquals(filename, pic.getFilename());
    }

    @Test
    void can_get_picture_by_filename_not_cases_sensitive() {
        PictureServiceM pservice = PictureServiceM.getInstance();
        String searchedFilename = "mAuS.jPg";
        String expectedFilename = "maus.jpg";
        PictureModel pic = pservice.getPicture(searchedFilename);
        assertNotNull(pic);
        assertEquals(expectedFilename, pic.getFilename());
    }

    @Test
    void can_insert_picture() {
        PictureServiceM pservice = PictureServiceM.getInstance();
        int oldSize = pservice.getPictureCount();
        pservice.addPicture("garten.jpg");
        assertNotEquals(oldSize, pservice.getPictureCount());
    }

    @Test
    void can_update_picture_iso() {
        PictureServiceM pservice = PictureServiceM.getInstance();
        int newIso = 400;
        int oldIso = -1;
        String filename = "katze.jpg";
        PictureModel picOld = pservice.getPicture(filename);
        assertNotNull(picOld);

        oldIso = picOld.getExif().getIso();
        assertNotEquals(oldIso, newIso);

        picOld.getExif().setIso(newIso);
        pservice.updatePicture(picOld);
        PictureModel picNew = pservice.getPicture(filename);
        assertEquals(newIso, picNew.getExif().getIso());
    }
}