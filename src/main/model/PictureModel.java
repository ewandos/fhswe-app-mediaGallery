package main.model;

public class PictureModel {
    public enum FileType { JPG, PNG, BMP, GIF }
    private String filename;
    private FileType filetype;
    private PhotographerModel photographer = new PhotographerModel();
    private IPTCModel iptc = new IPTCModel();
    private EXIFModel exif = new EXIFModel();

    public PictureModel(String filename, FileType filetype) {
        this.filename = filename;
        this.filetype = filetype;
    }

    public String getFilename() {
        return filename;
    }

    public FileType getFiletype() {
        return filetype;
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
