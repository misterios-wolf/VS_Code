package validate;

import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;

public class Order {
  @XmlElement(required = true)
  public String orderOMSId;

  public UUID getOrderOMSId() {
    try {
      UUID uuid = UUID.fromString(orderOMSId);
      return uuid;
    } catch (IllegalArgumentException exception) {
      // Log?
      throw exception;
    }
  }

  @XmlElement(required = true)
  public Integer affiliate;

  @XmlElement(required = true)
  public Integer orderReasonCode;

  @XmlElement(required = true)
  public String orderReasonText;
}
