import validate.Validators;

public class App {
    public static void main(String[] args) throws Exception {
        Validators validators = new Validators();
        validators.validateCancelOrderRequest("/src/main/xmlToValidate/CancelOrderRequest.xml");
    }
}
