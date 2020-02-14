package com.telenor.application.controller;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = GreetingControllerTest.Conf.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class GreetingControllerTest {

	private final String validPersonalRequest = "{\"id\": 123}";
	private final String invalidPersonalRequest = "{\"id\": -123}";

	private final String validBusinessRequest = "{\"type\": \"big\"}";
	private final String validBusinessRequestButNotValid = "{\"type\": \"small\"}";
	private final String invalidBusinessRequest = "{\"type\": \"zzxxx\"}";

	private final String BUSINESS = "business";
	private final String PERSONAL = "personal";

	private static String input;
	private static String account;

	private static ResponseEntity<?> response;

	@Autowired
	private GreetingController greetingController;
	
	
	

	@Test
	public void given_Invalid_Personal_Greeting_When_Read_Input_Data_Then_Response_Failure() {

		given_Invalid_Personal_Input_Data();

		when_Process_Requet();

		then_Failure();
	}
	
	@Test
	public void given_Valid_Business_Greeting_When_Read_Input_Data_Then_Response_Success() {

		given_Valid_Business_Input_Data();

		when_Process_Requet();

		then_Success();
	}
	
	@Test
	public void given_Invalid_Business_Greeting_When_Read_Input_Data_Then_Response_Failure() {

		given_Invalid_Business_Input_Data();

		when_Process_Requet();

		then_Failure();
	}
	
	
	@Test
	public void given_Invalid_But_Not_Business_Greeting_When_Read_Input_Data_Then_Response_Failure() {

		given_Invalid_But_Almost_Valid_Business_Input_Data();

		when_Process_Requet();

		then_Failure_Special();
	}
	
	
	@Test
	public void given_Valid_Personal_Greeting_When_Read_Input_Data_Then_Response_Success() {

		given_Valid_Personal_Input_Data();

		when_Process_Requet();

		then_Success();
	}
	

	private void then_Failure_Special() {
		Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		Assert.assertNotNull(response.getBody());
		Assert.assertEquals(response.getBody(), "Error: that the path is not yet implemented.");
		
	}

	private void given_Invalid_But_Almost_Valid_Business_Input_Data() {
		account = BUSINESS;
		input = validBusinessRequestButNotValid;
		
	}

	private void given_Valid_Business_Input_Data() {
		account = BUSINESS;
		input = validBusinessRequest;
		
	}
	
	private void given_Invalid_Business_Input_Data() {
		account = BUSINESS;
		input = invalidBusinessRequest;
		
	}


	private void then_Failure() {
		Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

	}

	private void given_Invalid_Personal_Input_Data() {
		account = PERSONAL;
		input = invalidPersonalRequest;

	}

	private void then_Success() {

		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	private void when_Process_Requet() {

		response = greetingController.greeting(input, account);

	}

	private void given_Valid_Personal_Input_Data() {
		account = PERSONAL;
		input = validPersonalRequest;

	}
	


	@Configuration
	@EnableAutoConfiguration
	@ComponentScan(basePackages = { "com.telenor.application" })
	static class Conf {

	}

}
