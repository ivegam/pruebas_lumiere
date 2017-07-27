/*******************************************************************************
 * Copyright (C) 2017 Yoigo & Masmovil - All Rights Reserved
 *
 * This file is a part of proprietary and confidential software.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *******************************************************************************/
package com.yoigo.domain.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OperatorConverter implements AttributeConverter<OperatorType, String> {

    @Override
    public String convertToDatabaseColumn(OperatorType attribute) {
	switch (attribute) {
	case MOBILE_PHONE:
	    return "mobile";
	case LAND_LINE:
	    return "land";
	default:
	    throw new IllegalArgumentException("Unknown" + attribute);
	}
    }

    @Override
    public OperatorType convertToEntityAttribute(String dbData) {
	switch (dbData) {
	case "mobile":
	    return OperatorType.MOBILE_PHONE;
	case "land":
	    return OperatorType.LAND_LINE;

	default:
	    throw new IllegalArgumentException("Unknown" + dbData);
	}
    }

}
