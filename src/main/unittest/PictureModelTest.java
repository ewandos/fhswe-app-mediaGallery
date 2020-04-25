package main.unittest;

import main.model.EXIFModel;
import main.model.IPTCModel;
import main.model.PhotographerModel;
import main.model.PictureModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PictureModelTest {
    @Test
    void getPhotographerNeverReturnsNull() {
        PictureModel picture = new PictureModel("random");
        PhotographerModel photographer = picture.getPhotographer();
        assertNotNull(photographer);
    }

    @Test
    void getEXIFNeverReturnsNull() {
        PictureModel picture = new PictureModel("random");
        EXIFModel exif = picture.getExif();
        assertNotNull(exif);
    }

    @Test
    void getIPTCNeverReturnsNull() {
        PictureModel picture = new PictureModel("random");
        IPTCModel iptc = picture.getIptc();
        assertNotNull(iptc);
    }
}