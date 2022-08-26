package exeptions;

public class ManagerSaveException extends Exception {
    public ManagerSaveException (final String massage) {
        super(massage);
    }

    public ManagerSaveException(Exception e) {

    }
}
