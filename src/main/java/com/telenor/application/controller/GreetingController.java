package com.telenor.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telenor.application.exception.GreetingValidationException;
import com.telenor.application.service.GreetignService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class GreetingController {

	@Autowired
	private GreetignService service;

	@ApiOperation(value = "Telenor Greeting", notes = "", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses(value = {

			@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	@RequestMapping(value = "/greeting/{account}/", method = RequestMethod.GET)
	public final ResponseEntity<?> greeting(
			@ApiParam(value = "{ \"id\": 18}", example = "{\"id\": 18}") @RequestBody final String input,
			@ApiParam(allowableValues = "personal,business", defaultValue = "personal", required = true) @PathVariable final String account ) {

		try {

			String response = service.processRequest(account, input);

			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (GreetingValidationException e) {

			return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
