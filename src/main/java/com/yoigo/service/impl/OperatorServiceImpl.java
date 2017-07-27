/*******************************************************************************
 * Copyright (C) 2017 Yoigo & Masmovil - All Rights Reserved
 *
 * This file is a part of proprietary and confidential software.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *******************************************************************************/
package com.yoigo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.yoigo.domain.dto.OperatorItem;
import com.yoigo.domain.entity.Operator;
import com.yoigo.domain.entity.OperatorType;
import com.yoigo.exception.OperatorException;
import com.yoigo.repository.OperatorRepository;
import com.yoigo.service.OperatorService;
import com.yoigo.util.PhoneUtils;

@Service
public class OperatorServiceImpl implements OperatorService {

    private final Logger logger = LogManager.getLogger(OperatorServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OperatorRepository operatorRepository;

    @Value("${operator.endpoint}")
    private String operatorEndpoint;

    @Value("${operator.path}")
    private String operatorPath;

    @Override
    public OperatorItem findOperator(String phoneNumber) {

	if (!PhoneUtils.isValidMobilePhoneNumber(phoneNumber)) {
	    throw new IllegalArgumentException(
		    "The 'phoneNumber' parameter must be a valid mobile phone number. It must start with number 6 or 7 and It must have 9 characters");
	}

	OperatorItem operator = new OperatorItem();

	UriComponentsBuilder uri = UriComponentsBuilder.fromUriString(operatorEndpoint).path(operatorPath);
	String uriString = uri.buildAndExpand(phoneNumber).toUriString();

	logger.debug(String.format("Sended request: [HTTP METHOD: %s] - URL: %s", HttpMethod.GET, uriString));

	try {
	    ResponseEntity<OperatorItem> response = restTemplate.getForEntity(uriString, OperatorItem.class);
	    logger.debug(String.format("Received response: [HTTP %d] %s", response.getStatusCodeValue(), response));

	    if (response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
		operator = response.getBody();
	    }

	    return operator;

	} catch (RestClientException e) {

	    String errorMsg = String.format("ERROR in request: [HTTP METHOD: %s] - URL: %s - ExceptionMessage: %s",
		    HttpMethod.GET, uriString, e.getMessage());

	    logger.error(errorMsg);
	    throw new OperatorException(errorMsg);

	}

    }

    @Override
    public List<OperatorItem> findOperatorByLineType(OperatorType lineType) {

	return operatorRepository.findByLineTypeOrderByCustomOrderAsc(lineType).stream().map(this::getOperatorItem)
		.collect(Collectors.toList());

    }

    private OperatorItem getOperatorItem(Operator operator) {

	OperatorItem op = new OperatorItem();
	op.setIdOperador(operator.getCode());
	op.setOperador(operator.getName());

	return op;
    }

}