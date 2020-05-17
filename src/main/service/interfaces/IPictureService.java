package main.service.interfaces;

import main.model.PhotographerModel;
import main.model.PictureModel;

import java.util.List;


/**
 * This interface is responsible for giving structure to the highest
 * class of the DAL and guarantee the BL can easily swap between a
 * mocked version of the PictureService and the real one.
 */
public interface IPictureService {
    static IPictureService getInstance(){
        return null;
    }

    /**
     * @return number of pictures
     */
    int getPictureCount();

    /**
     * @return List of PictureModels or Null
     */
    List<PictureModel> getAllPictures();


    /**
     * Searches in every attribute of a picture for the given keyword
     * or value.
     * @param keyword value to search for
     * @return List of PictureModels or Null
     */
    List<PictureModel> searchPictures(String keyword);

    PictureModel getPictureOfSearchResult(String keyword, int index);

    /**
     * @param filename The Filename of the picture
     * @return Searched Picture or Null
     */
    PictureModel getPicture(String filename);

    PictureModel getPicture(int index);

    /**
     * Creates a new PictureModel and simulates the Photographer, IPTC & EXIF attributes.
     * Adds the new Object to the storage.
     * @param filename Name of the image file
     */
    void addPicture(String filename);

    /**
     * Search for the filename of the given PictureModel, compares the objects and updates attributes that differ
     * @param pic Updated picture
     */
    PictureModel updatePicture(PictureModel pic);

    /**
     * Get all Photographers
     * @return List of PhotographerModels
     */
    List<PhotographerModel> getAllPhotographers();

    PhotographerModel getPhotographer(int i);

    PhotographerModel updatePhotographer(PhotographerModel photographerModel);
}
