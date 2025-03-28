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
		Customer customer = findExistingCustomer(customerNumber);
		if(customer != null) {
			return customer;
		} else {
			String message = SoapMessage.getKYCRequestMessage(customerNumber);
			try {
				XmlMapper xmlMapper = new XmlMapper();
				String xmlRes = httpClient.sendPostRequest(KYCEndpoint, message, Format.SOAP, null);
				JsonNode jsonFromSoap = xmlMapper.readTree(xmlRes.getBytes());
				JsonNode responseObject = jsonFromSoap.get("Body").get("transactionData");
				customer = new ObjectMapper().readValue(responseObject.toString(), Customer.class);
				customerRepo.save(customer);
				return customer;
			} catch (Exception e) {
				e.printStackTrace();
				return new Customer();
			}
		}
	}

	public Customer findExistingCustomer(String customerNumber) {
		Customer customer = customerRepo.findByCustomerNumber(customerNumber);
		return customer;
	}
}
