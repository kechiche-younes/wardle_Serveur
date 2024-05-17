package application;

public class PageManager {
    private static loubiControler loubiControllerInstance;

    public static void setLoubiController(loubiControler controller) {
        loubiControllerInstance = controller;
    }

    public static loubiControler getLoubiController() {
        return loubiControllerInstance;
    }
}
