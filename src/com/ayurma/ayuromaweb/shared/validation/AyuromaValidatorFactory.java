package com.ayurma.ayuromaweb.shared.validation;

import javax.validation.Validator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

public final class AyuromaValidatorFactory extends AbstractGwtValidatorFactory{
	@GwtValidation(value = {ProductEnquiryRequest.class, ContactUsEnquiry.class,
					SenderData.class,ProductReportsData.class,
					EnquiryData.class})
	public interface AyuromaWebValidator extends Validator{
		
	}
	@Override
	public AbstractGwtValidator createValidator() {
		return GWT.create(AyuromaWebValidator.class);
	}

}
