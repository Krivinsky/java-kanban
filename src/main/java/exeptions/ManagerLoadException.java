package exeptions;

public class ManagerLoadException extends Exception {
    public ManagerLoadException(String massage) {
        super(massage);
    }

    public ManagerLoadException(Exception e) {

    }
}
