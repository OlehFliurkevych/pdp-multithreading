package task5.exception;

/**
 * @author Oleh Fliurkevych
 */
public class ValidationException extends RuntimeException{

  public ValidationException(String message) {
    super(message);
  }
}
