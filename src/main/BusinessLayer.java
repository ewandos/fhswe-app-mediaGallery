package main;

import main.service.PictureServiceM;
import main.service.interfaces.IPictureService;

public class BusinessLayer {
    private IPictureService ps = PictureServiceM.getInstance();

    public BusinessLayer() {

    }
}
