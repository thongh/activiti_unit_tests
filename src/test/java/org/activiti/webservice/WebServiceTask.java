package org.activiti.webservice;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.apache.log4j.Logger;

public class WebServiceTask implements JavaDelegate {

	static Logger logger = Logger.getLogger(WebServiceTask.class);

	public void execute(DelegateExecution execution) throws Exception {

		logger.info("Init service task to call web service execution : "
				+ execution.getId());

		String wsdlurl = "http://www.webservicex.net/currencyconvertor.asmx?wsdl";
		callSOAP(wsdlurl);
	}

	private void callSOAP(String wsdlurl) throws Exception {
		// Create SOAP Connection
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory
				.createConnection();

		// Send SOAP Message to SOAP Server
		SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(),
				wsdlurl);
		logger.info("Soap response");
//		logger.info(soapResponse.getSOAPBody().getElementName());
		printSOAPResponse(soapResponse);
	}

	private static SOAPMessage createSOAPRequest() throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		String serverURI = "http://www.webserviceX.NET/";

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("tns", "http://www.webserviceX.NET/");

		/*
		 * Constructed SOAP Request Message: <SOAP-ENV:Envelope
		 * xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
		 * xmlns:example="http://ws.cdyne.com/"> <SOAP-ENV:Header/>
		 * <SOAP-ENV:Body> <ConversionRate xmlns="http://www.webserviceX.NET/">
		 * <FromCurrency>USD</FromCurrency>
		 * <ToCurrency>AUD</ToCurrency></ConversionRate> </SOAP-ENV:Body>
		 * </SOAP-ENV:Envelope>
		 */

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement("ConversionRate");
		soapBodyElem.setAttribute("xmlns", "http://www.webserviceX.NET/");
		SOAPElement soapBodyElem1 = soapBodyElem
				.addChildElement("FromCurrency");
		soapBodyElem1.addTextNode("AUD");
		SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("ToCurrency");
		soapBodyElem2.addTextNode("USD");

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", serverURI + "ConversionRate");

		soapMessage.saveChanges();

		/* Print the request message */
//		logger.info("Request SOAP Message = ");
//		logger.info(soapMessage);

		return soapMessage;
	}

	/**
	 * Method used to print the SOAP Response
	 */
	private static void printSOAPResponse(SOAPMessage soapResponse)
			throws Exception {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		System.out.print("\nResponse SOAP Message = ");
		StreamResult result = new StreamResult(System.out);
		transformer.transform(sourceContent, result);
	}

}
