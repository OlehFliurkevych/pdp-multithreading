package task5.exception;

/**
 * @author Oleh Fliurkevych
 */
public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
    super(message);
  }
}
