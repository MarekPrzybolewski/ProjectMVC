package projekt.controller;

import projekt.view.ProjektFrame;

/**
 * Created by miQ333 on 28.12.2016.
 */
public class ProjektAppController {

    private ProjektFrame appFrame;
    private ProjektDataController dataController;



    public ProjektAppController() {
        dataController = new ProjektDataController(this);
    }

    public ProjektFrame getAppFrame() {
        return appFrame;
    }

    public ProjektDataController getDataController() {
        return dataController;
    }

    public void start(){
        appFrame = new ProjektFrame(this);
    }
}
