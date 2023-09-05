package validate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.w3c.dom.Document;

import soap.SoapMessage;
import soap.parser.SoapParser;
import soap.parser.XmlUtil;

import org.junit.gen5.api.Assertions;
import org.junit.gen5.api.DisplayName;
import org.junit.gen5.api.Test;

public class Validators {
  private StringReader readSoapBodyFromXmlFile(String filePath) throws Exception {
    String currentDirectory = FileSystems.getDefault().getPath(".").toString();
    String absoluteFilePath = Paths.get(currentDirectory, filePath).toString();
    System.out.println(absoluteFilePath);
    String xml = Files.lines(Paths.get(absoluteFilePath)).collect(Collectors.joining("\n"));
    System.out.println(xml);
    Document doc = XmlUtil.fromXML(xml);
    SoapParser parser = new SoapParser(doc);
    SoapMessage soap = parser.parse();
    System.out.println(soap.toString());
    String soapBody = XmlUtil.toXML(soap.getDocument(), false);
    System.out.println(soapBody);
    StringReader reader = new StringReader(soapBody);
    return reader;
  }

  @Test
  @DisplayName("Test CancelOrderRequest")
  public void validateCancelOrderRequest(String filePath) throws Exception {
    StringReader soapBody = readSoapBodyFromXmlFile(filePath);
    JAXBContext context = JAXBContext.newInstance(CancelOrderRequest.class);
    Unmarshaller unmarshaller = context.createUnmarshaller();
    CancelOrderRequest cancelOrderRequest = (CancelOrderRequest) unmarshaller.unmarshal(soapBody);
    System.out.println(cancelOrderRequest.receiver);
    System.out.println(cancelOrderRequest.originator);
    System.out.println(cancelOrderRequest.order.getOrderOMSId());
    System.out.println(cancelOrderRequest.order.orderReasonCode);
    System.out.println(cancelOrderRequest.order.orderReasonText);
    Assertions.assertNotNull(cancelOrderRequest.receiver);
    Assertions.assertNotNull(cancelOrderRequest.originator);
  }
}
