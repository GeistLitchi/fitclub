package comp3350.fitclub.persistence.sql;

public class PersistenceException extends RuntimeException {
    public PersistenceException (Exception e) {
        super(e);
    }
}
