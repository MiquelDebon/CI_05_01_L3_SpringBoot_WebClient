package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.exception.customExceptions;

public class EmptyDatabaseException extends RuntimeException{
    public EmptyDatabaseException() {
        super("Empty Database");
    }

    public EmptyDatabaseException(String message) {
        super(message);
    }
}
