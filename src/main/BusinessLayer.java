package main;

import main.service.PictureServiceMock;
import main.service.interfaces.IPictureService;

public class BusinessLayer {
    private IPictureService ps = PictureServiceMock.getInstance();

    public BusinessLayer() {

    }
}
