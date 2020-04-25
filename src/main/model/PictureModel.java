package main.model;

public class PictureModel {
    private String filename;
    private PhotographerModel photographer = new PhotographerModel();
    private IPTCModel iptc = new IPTCModel();
    private EXIFModel exif = new EXIFModel();

    public PictureModel(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public PhotographerModel getPhotographer() {
        return photographer;
    }

    public IPTCModel getIptc() {
        return iptc;
    }

    public EXIFModel getExif() {
        return exif;
    }
}
