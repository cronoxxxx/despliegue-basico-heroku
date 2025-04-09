package org.example.demo.common.config;

import org.example.demo.common.Constantes;
import org.example.demo.domain.model.Country;
import org.example.demo.ui.request.FindCountryRequest;
import org.example.demo.ui.request.GetStudentRequest;
import org.example.demo.ui.response.FindCountryResponse;
import org.example.demo.ui.response.GetStudentResponse;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class WebServiceEndpoint {
	@PayloadRoot(namespace = Constantes.NAMESPACE_URI, localPart = "getStudentRequest")
	@ResponsePayload
	public GetStudentResponse findStudent(@RequestPayload GetStudentRequest request) {
		GetStudentResponse ret = new GetStudentResponse();
		ret.setId(request.getId());
		ret.setName("Pepito");
		ret.setAge(32);
		return ret;
	}

	@PayloadRoot(namespace = Constantes.NAMESPACE_URI, localPart = "findCountryRequest")
	@ResponsePayload
	public FindCountryResponse getCountry(@RequestPayload FindCountryRequest findCountryRequest) {
		FindCountryResponse ret = new FindCountryResponse();
		Country value = new Country();
		value.setCode("101");
		value.setName(findCountryRequest.getName());
		value.setCurrency("INR");
		ret.setCountry(value);

		return ret;
	}
}

