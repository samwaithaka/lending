package com.lending.soap;

public class SoapMessage {
	public static String getKYCRequestMessage(String customerNumber) {
		String soap = "<soapenv:Envelope \n"
				+ "    xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\n"
				+ "    xmlns:cus=\"http://credable.io/cbs/customer/\">\n"
				+ "    <soapenv:Header/>\n"
				+ "    <soapenv:Body>\n"
				+ "      <cus:CustomerRequest>\n"
				+ "        <cus:customerNumber>" + customerNumber + "</cus:customerNumber>\n"
				+ "      </cus:CustomerRequest>\n"
				+ "    </soapenv:Body>\n"
				+ "  </soapenv:Envelope>";
		return soap;
	}
	//http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso
	public static String getTranDataRequestMessage(String customerNumber) {
		String soap = "<soapenv:Envelope \n"
				+ "    xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\n"
				+ "    xmlns:cus=\"http://credable.io/cbs/customer/\">\n"
				+ "    <soapenv:Header/>\n"
				+ "    <soapenv:Body>\n"
				+ "      <cus:CustomerRequest>\n"
				+ "        <cus:customerNumber>" + customerNumber + "</cus:customerNumber>\n"
				+ "      </cus:CustomerRequest>\n"
				+ "    </soapenv:Body>\n"
				+ "  </soapenv:Envelope>";
		return soap;
	}
	
	
		public static String gesCountryISOCode(String isocode) {
			String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
					+ "<soap12:Envelope xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n"
					+ "xmlns:cus=\"http://www.oorsprong.org/websamples.countryinfo\">\n"
					+ "  <soap12:Body>\n"
					+ "    <cus:CountryIntPhoneCode xmlns=\"http://www.oorsprong.org/websamples.countryinfo\">\n"
					+ "      <cus:sCountryISOCode>" + isocode + "</cus:sCountryISOCode>\n"
					+ "    </cus:CountryIntPhoneCode>\n"
					+ "  </soap12:Body>\n"
					+ "</soap12:Envelope>";
			System.out.println(soap);
			return soap;
		}
}
