package validate;

import javax.xml.bind.annotation.*;

@XmlRootElement(namespace = "http://oms.rt.ru/")
public class CancelOrderRequest {
  @XmlElement(required = true)
  public String originator;

  @XmlElement(required = true)
  public String receiver;

  @XmlElement(required = true)
  public Order order;
}
