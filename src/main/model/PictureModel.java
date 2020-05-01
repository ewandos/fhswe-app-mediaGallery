package main.model;

public class PictureModel {
    private final String filename; // identifies the picture (primary key)
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

    public void setPhotographer(PhotographerModel photographer) {
        this.photographer = photographer;
    }

    public IPTCModel getIptc() {
        return iptc;
    }

    public void setIptc(IPTCModel iptc) {
        this.iptc = iptc;
    }

    public EXIFModel getExif() {
        return exif;
    }

    public void setExif(EXIFModel exif) {
        this.exif = exif;
    }
}
