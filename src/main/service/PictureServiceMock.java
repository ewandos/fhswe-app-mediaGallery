package main.service;

import main.model.EXIFModel;
import main.model.IPTCModel;
import main.model.PhotographerModel;
import main.model.PictureModel;
import main.service.interfaces.IPictureService;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is responsible for mocking the DAL for the application.
 * It does NOT contain business logic, like validation or a request,
 * if a picture already exists or not.
 *
 * In the real DAL the PictureService is responsible for converting ResultSets
 * to Model-Objects and simulating EXIF and IPTC Data for new Picture-Entries
 */
public class PictureServiceMock implements IPictureService {
    private List<PictureModel> pictureShop = new ArrayList<>();

    private PictureServiceMock() {
        // PICTURE 1
        PhotographerModel pg1 = new PhotographerModel();
        pg1.setFirstName("Peter");
        pg1.setLastName("Lustig");
        pg1.setNotes("Ein sehr talentierter Fotograf!");

        EXIFModel ex1 = new EXIFModel();
        ex1.setCamera("iPhone 11 Pro");
        ex1.setIso(1400);
        ex1.setAperture(2.5f);

        IPTCModel ip1 = new IPTCModel();
        ip1.setDescription("Eine Maus die sitzt.");
        ip1.setRating(3);
        ip1.addTag("Tier");
        ip1.addTag("Nager");
        ip1.addTag("Klein");

        pg1.setBirthday(LocalDate.of(1995, 2, 15));
        ex1.setDate(LocalDate.of(2020, 1, 15));

        PictureModel pi1 = new PictureModel("maus.jpg");
        pi1.setPhotographer(pg1);
        pi1.setExif(ex1);
        pi1.setIptc(ip1);

        pictureShop.add(pi1);

        // PICTURE 2
        EXIFModel ex2 = new EXIFModel();
        ex2.setCamera("Sony Alpha");
        ex2.setIso(800);
        ex2.setAperture(2.5f);

        IPTCModel ip2 = new IPTCModel();
        ip2.setDescription("Ein unglaublich niedlicher Hund.");
        ip2.setRating(4);
        ip2.addTag("Tier");
        ip2.addTag("Hund");
        ip2.addTag("Groß");
        ip2.addTag("Doggo");

        ex2.setDate(LocalDate.of(2020, 1, 2));

        PictureModel pi2 = new PictureModel("hund.jpg");
        pi2.setExif(ex2);
        pi2.setIptc(ip2);

        pictureShop.add(pi2);

        // PICTURE 3
        PhotographerModel pg3 = pg1;

        EXIFModel ex3 = new EXIFModel();
        ex3.setCamera("Canon EOS 550D");
        ex3.setIso(1200);
        ex3.setAperture(1.5f);

        ex3.setDate(LocalDate.of(2020, 1, 12));

        PictureModel pi3 = new PictureModel("katze.jpg");
        pi3.setPhotographer(pg3);
        pi3.setExif(ex3);

        pictureShop.add(pi3);

        // PICTURE 4
        PhotographerModel pg4 = new PhotographerModel();
        pg1.setFirstName("Hans");
        pg1.setLastName("Hirte");
        pg1.setNotes("Der Dude kann den Auslöser drücken!");

        IPTCModel ip4 = new IPTCModel();
        ip4.setDescription("Ein großes Haus");
        ip4.setRating(2);
        ip4.addTag("Gebäude");
        ip4.addTag("Groß");
        ip4.addTag("Holz");

        PictureModel pi4 = new PictureModel("haus.jpg");
        pi4.setPhotographer(pg4);
        pi4.setIptc(ip4);

        pictureShop.add(pi4);

        // PICTURE 5
        PictureModel pi5 = new PictureModel("haus2.jpg");
        pictureShop.add(pi5);
    }

    public static PictureServiceMock getInstance() {
        return new PictureServiceMock();
    }

    @Override
    public int getPictureCount() {
        return this.pictureShop.size();
    }

    @Override
    public List<PictureModel> getAllPictures() {
        return this.pictureShop;
    }

    @Override
    public List<PictureModel> searchPictures(String keyword) {
        // TODO: Can't search by anything than filename!
        List<PictureModel> result = new ArrayList<>();
        String pattern = keyword.toLowerCase();
        for (PictureModel pic : pictureShop) {
            if (pic.getFilename().contains(pattern))
                result.add(pic);
        }
        return result;
    }

    @Override
    public PictureModel getPicture(String filename) {
        for (PictureModel pic : pictureShop) {
            if (pic.getFilename().equalsIgnoreCase(filename))
                return pic;
        }
        return null;
    }

    @Override
    public void addPicture(String filename){
        PictureModel pic = new PictureModel(filename);
        pictureShop.add(pic);
    }

    @Override
    public void updatePicture(PictureModel pic) {
    }
}
