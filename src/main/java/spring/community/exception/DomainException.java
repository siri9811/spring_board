package spring.community.exception;


  public class DomainException extends RuntimeException {

    private final String errorCode;

    public DomainException(String errorCode, String message) {
      super(message);
      this.errorCode = errorCode;
    }

    public String getErrorCode() {
      return errorCode;
    }


    public static class NotFoundException extends DomainException {

      public NotFoundException(String message) {
        super("NOT_FOUND", message);
      }
    }

    public static class ForbiddenException extends DomainException {

      public ForbiddenException(String message) {
        super("FORBIDDEN", message);
      }
    }

    public static class ConflictException extends DomainException {

      public ConflictException(String message) {
        super("CONFLICT", message);
      }
    }

    public static class BadRequestException extends DomainException {

      public BadRequestException(String message) {
        super("BAD_REQUEST", message);
      }
    }

    public static class InvalidTokenException extends DomainException {

      public InvalidTokenException(String message) {
        super("INVALID_TOKEN", message);
      }
    }

  }

