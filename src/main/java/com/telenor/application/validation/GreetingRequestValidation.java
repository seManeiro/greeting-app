package com.telenor.application.validation;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.telenor.application.exception.GreetingValidationException;

@Component
public class GreetingRequestValidation {

	private final String BUSINESS = "business";
	private final String PERSONAL = "personal";

	private final String SMALL = "small";
	private final String BIG = "big";

	public void validatePersonalGreetingRequest(final Integer id, final String account) throws GreetingValidationException {

		if (null == id || String.valueOf(id).startsWith("-")) {
			throw new GreetingValidationException("The given id can not be null and should always be positive number. "
					+ (id == null ? "" : "The given one is invalid: " + id));
		}

		if (Strings.isNullOrEmpty(account) || !account.equals(BUSINESS) && !account.equals(PERSONAL)) {
			throw new GreetingValidationException("Account value can only be : " + BUSINESS + " or " + PERSONAL
					+ ". The give input is invalid: " + account);
		}

	}

	public void validateBussinesGreetingRequest( final String type, final String account) throws GreetingValidationException {

		if (Strings.isNullOrEmpty(account) || !account.equals(BUSINESS) && !account.equals(PERSONAL)) {
			throw new GreetingValidationException("Account value can only be : " + BUSINESS + " or " + PERSONAL
					+ ". The give input is invalid: " + account);
		}

		if (Strings.isNullOrEmpty(type) || type.equals(SMALL)) {
			throw new GreetingValidationException("Error: that the path is not yet implemented.");
		}

		if (Strings.isNullOrEmpty(type) || !type.equals(BIG)) {
			throw new GreetingValidationException(
					"Type value can only be : " + BIG + " for the moment. The give input is invalid: " + type);
		}

	}

}
