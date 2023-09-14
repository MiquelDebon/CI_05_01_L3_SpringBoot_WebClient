package cat.itacademy.barcelonactiva.DebonVillagrasa.Miquel.s05.t01.n03.S05T01N03DebonVillagrasaMiquel.exception.customExceptions;

public class NoSuchElementException extends RuntimeException{
    public NoSuchElementException() {
        super("Any element with this ID");
    }

    public NoSuchElementException(String message) {
        super(message);
    }
}
