package main.unitTests;

import main.model.EXIFModel;
import main.model.IPTCModel;
import main.model.PhotographerModel;
import main.model.PictureModel;
import main.model.PictureModel.FileType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PictureModelTest {
    @Test
    void getPhotographerNeverReturnsNull() {
        PictureModel picture = new PictureModel("random", FileType.GIF);
        PhotographerModel photographer = picture.getPhotographer();
        assertNotNull(photographer);
    }

    @Test
    void getEXIFNeverReturnsNull() {
        PictureModel picture = new PictureModel("random", FileType.GIF);
        EXIFModel exif = picture.getExif();
        assertNotNull(exif);
    }

    @Test
    void getIPTCNeverReturnsNull() {
        PictureModel picture = new PictureModel("random", FileType.GIF);
        IPTCModel iptc = picture.getIptc();
        assertNotNull(iptc);
    }
}