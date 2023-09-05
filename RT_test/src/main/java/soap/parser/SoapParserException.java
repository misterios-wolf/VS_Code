package soap.parser;


public class SoapParserException extends Exception {
  public SoapParserException(ErrorCode errorCode, String message) {
    super(message);
  }

  public SoapParserException(ErrorCode errorCode, String message, Throwable cause) {
    super(message, cause);
  }
}
