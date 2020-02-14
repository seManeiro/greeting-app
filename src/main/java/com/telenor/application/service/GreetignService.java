package com.telenor.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.telenor.application.exception.GreetingValidationException;
import com.telenor.application.validation.GreetingRequestValidation;

@Service
public class GreetignService {


	@Autowired
	private GreetingRequestValidation validate;

	private Integer id;
	private String type;

	public String processRequest( final String account, final String input) throws GreetingValidationException {

		
		type = null;
		id = null;

		parseRequestInput(input);

		if (!Strings.isNullOrEmpty(type)) {

			validate.validateBussinesGreetingRequest(type, account);
			return "Welcome, business user!";

		} else {

			validate.validatePersonalGreetingRequest(id, account);

			return "Hi, userId " + id;

		}

	}
	
	
	

	private void parseRequestInput(final String input) throws GreetingValidationException {
	
		try {

			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readValue(input, JsonNode.class);

			if (node.has("id")) {

				id = node.get("id").asInt();

			} else if (node.has("type")) {

				type = node.get("type").asText();

			} else {
				
				throw new GreetingValidationException("The content in the request is invalid: " + input);
			}

		} catch (Exception e) {

			throw new GreetingValidationException("Something when wrong pasring the input data: " + input);

		}

	}

}
