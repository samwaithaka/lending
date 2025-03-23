package com.lending.services;

import org.springframework.beans.factory.annotation.Value;

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
	@Value("${app.constants.ws.url.tran-data}")
	private String tranDataEndpoint;
	
	private CustomerRepo customerRepo;
	private HttpClient httpClient;

	public static void main(String[] args) {
		System.out.println(CBSService.getIsoCode("KEN"));
	}

	public Customer getKYC(String customerNumber) {
		String message = SoapMessage.getKYCRequestMessage(customerNumber);
		try {
			XmlMapper xmlMapper = new XmlMapper();
			String response = httpClient.sendPostRequest(KYCEndpoint, message, Format.SOAP, null);
			//System.out.println(response);
			Customer customer = xmlMapper.readValue(response, Customer.class);
			customerRepo.save(customer);
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			return new Customer();
		}
	}

	public Customer getTransactionData(String customerNumber) {
		String message = SoapMessage.getTranDataRequestMessage(customerNumber);
		try {
			XmlMapper xmlMapper = new XmlMapper();
			String response = httpClient.sendPostRequest(tranDataEndpoint, message, Format.SOAP, null);
			//System.out.println(response);
			Customer customer = xmlMapper.readValue(response, Customer.class);
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			return new Customer();
		}
	}

	public Customer findExistingCustomer(String customerNumber) {
		Customer customer = customerRepo.findByCustomerNumber(customerNumber);
		return customer;
	}

	public static Customer getIsoCode(String customerNumber) {
		String message = SoapMessage.gesCountryISOCode(customerNumber);
		try {
			String tranDataEndpoint = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";
			XmlMapper xmlMapper = new XmlMapper();
			String response = HttpClient.sendPostRequest(tranDataEndpoint, message, Format.SOAP, null);
			System.out.println(response);
			Customer customer = xmlMapper.readValue(response, Customer.class);
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			return new Customer();
		}
	}
}
