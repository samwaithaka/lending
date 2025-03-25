package com.lending.services;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.lending.entities.Customer;
import com.lending.enums.Format;
import com.lending.http.HttpClient;
import com.lending.repo.CustomerRepo;
import com.lending.soap.SoapMessage;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CBSService {

	@Value("${app.constants.ws.url.customer}")
	private String KYCEndpoint;
	
	private CustomerRepo customerRepo;
	private HttpClient httpClient;
	
	public Customer getKYC(String customerNumber) {
		String message = SoapMessage.getKYCRequestMessage(customerNumber);
		Customer customer = null;
		try {
			XmlMapper xmlMapper = new XmlMapper();
			ObjectMapper jsonMapper = new ObjectMapper();
			jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			//String xmlRes = httpClient.sendPostRequest(KYCEndpoint, message, Format.SOAP, null);
			String xmlRes = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"                   xmlns:tns=\"http://credable.io/cbs/customer\">    <soapenv:Header/>    <soapenv:Body>       <tns:CustomerResponse>          <customer>             <id>20</id>             <accountNumber>9876543210</accountNumber>             <createdAt>2025-03-24T10:15:30Z</createdAt>             <createdDate>2025-03-24T10:15:30Z</createdDate>             <dob>2025-03-24</dob>             <email>samuel@gmail.com</email>             <firstName>John</firstName>             <middleName>Smith</middleName>             <lastName>Doe</lastName>             <gender>MALE</gender>             <idNumber>203030303</idNumber>             <idType>NATIONAL_ID</idType>             <mobile>0703393928</mobile>             <monthlyIncome>4</monthlyIncome>             <status>2500.00</status>             <updatedAt>2025-03-24T10:15:30Z</updatedAt>           </customer>       </tns:CustomerResponse>    </soapenv:Body> </soapenv:Envelope>";
			JsonNode jsonFromSoap = xmlMapper.readTree(xmlRes.getBytes());
			JsonNode responseObject = jsonFromSoap.get("Body").get("CustomerResponse").get("customer");
			customer = jsonMapper.readValue(responseObject.toString(), new TypeReference<Customer>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	public Customer findExistingCustomer(String customerNumber) {
		Customer customer = customerRepo.findByCustomerNumber(customerNumber);
		return customer;
	}
}
