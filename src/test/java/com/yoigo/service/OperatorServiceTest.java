/*******************************************************************************
 * Copyright (C) 2017 Yoigo & Masmovil - All Rights Reserved
 *
 * This file is a part of proprietary and confidential software.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *******************************************************************************/
package com.yoigo.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoigo.domain.dto.OperatorItem;
import com.yoigo.domain.entity.Operator;
import com.yoigo.domain.entity.OperatorType;
import com.yoigo.exception.OperatorException;
import com.yoigo.repository.OperatorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperatorServiceTest {

    @Value("${operator.endpoint}")
    private String operatorEndpoint;

    @Value("${operator.path}")
    private String operatorPath;

    @MockBean
    private OperatorRepository operatorRepository;

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private UriComponentsBuilder uri;
    private ObjectMapper mapper;

    @Before
    public void setUp() {
	mockServer = MockRestServiceServer.createServer(restTemplate);
	uri = UriComponentsBuilder.fromUriString(operatorEndpoint).path(operatorPath);
	mapper = new ObjectMapper();

    }

    @Test
    public void shouldFindOperatorForAPhoneNumber() {
	try {
	    String phoneNumber = "612345678";
	    String uriString = uri.buildAndExpand(phoneNumber).toUriString();

	    OperatorItem operator = getOperator();

	    String json;

	    json = mapper.writeValueAsString(operator);

	    mockServer.expect(requestTo(uriString)).andExpect(method(HttpMethod.GET))
		    .andRespond(withSuccess(json, MediaType.APPLICATION_JSON_UTF8));

	    OperatorItem operatorRes = operatorService.findOperator(phoneNumber);

	    mockServer.verify();
	    assertEquals(operator, operatorRes);

	} catch (JsonProcessingException e) {
	    e.printStackTrace();
	}

    }

    @Test
    public void shouldNotFindOperatorForAPhoneNumber() {

	String phoneNumber = "612345678";
	String uriString = uri.buildAndExpand(phoneNumber).toUriString();

	OperatorItem operator = new OperatorItem();

	String json = "{}";

	mockServer.expect(requestTo(uriString)).andExpect(method(HttpMethod.GET))
		.andRespond(withSuccess(json, MediaType.APPLICATION_JSON_UTF8));

	OperatorItem operatorRes = operatorService.findOperator(phoneNumber);

	mockServer.verify();
	assertEquals(operator, operatorRes);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWithLandNumber() {

	String phoneNumber = "957957445";
	operatorService.findOperator(phoneNumber);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWithLandNumber2() {

	String phoneNumber = "857957445";
	operatorService.findOperator(phoneNumber);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWithEmptyString() {

	String phoneNumber = "";
	operatorService.findOperator(phoneNumber);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWithNull() {

	String phoneNumber = null;
	operatorService.findOperator(phoneNumber);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWithShortPhone() {

	String phoneNumber = "61234567";
	operatorService.findOperator(phoneNumber);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWithLongPhone() {

	String phoneNumber = "6123456789";
	operatorService.findOperator(phoneNumber);

    }

    @Test(expected = OperatorException.class)
    public void shouldThrowOperatorException() {

	String phoneNumber = "612345678";
	String uriString = uri.buildAndExpand(phoneNumber).toUriString();

	mockServer.expect(requestTo(uriString)).andExpect(method(HttpMethod.GET)).andRespond(withBadRequest());

	operatorService.findOperator(phoneNumber);

	mockServer.verify();

    }

    @Test
    public void shouldFindMobileListOperator() {

	List<Operator> operators = getOperatorList(OperatorType.MOBILE_PHONE);
	when(operatorRepository.findByLineTypeOrderByCustomOrderAsc(OperatorType.MOBILE_PHONE)).thenReturn(operators);

	List<OperatorItem> result = operatorService.findOperatorByLineType(OperatorType.MOBILE_PHONE);
	List<OperatorItem> target = getOperatorItemList();

	assertEquals(result, target);
	assertEquals(result.size(), target.size());
	assertArrayEquals(result.toArray(), target.toArray());

    }

    @Test
    public void shouldNotFindMobileListOperator() {

	List<Operator> operators = getOperatorList(OperatorType.LAND_LINE);
	when(operatorRepository.findByLineTypeOrderByCustomOrderAsc(OperatorType.LAND_LINE)).thenReturn(operators);

	List<OperatorItem> result = operatorService.findOperatorByLineType(OperatorType.MOBILE_PHONE);

	assertEquals(result.size(), 0);

    }

    @Test
    public void shouldFindLandListOperator() {
	List<Operator> operators = getOperatorList(OperatorType.LAND_LINE);
	when(operatorRepository.findByLineTypeOrderByCustomOrderAsc(OperatorType.LAND_LINE)).thenReturn(operators);

	List<OperatorItem> result = operatorService.findOperatorByLineType(OperatorType.LAND_LINE);
	List<OperatorItem> target = getOperatorItemList();

	assertEquals(result, target);
	assertEquals(result.size(), target.size());
	assertArrayEquals(result.toArray(), target.toArray());
    }

    @Test
    public void shouldNotFindLandListOperator() {

	List<Operator> operators = getOperatorList(OperatorType.MOBILE_PHONE);
	when(operatorRepository.findByLineTypeOrderByCustomOrderAsc(OperatorType.MOBILE_PHONE)).thenReturn(operators);

	List<OperatorItem> result = operatorService.findOperatorByLineType(OperatorType.LAND_LINE);

	assertEquals(result.size(), 0);

    }

    private OperatorItem getOperator() {
	OperatorItem operator = new OperatorItem();
	operator.setIdOperador("005");
	operator.setOperador("YOIGO");
	return operator;
    }

    private List<Operator> getOperatorList(OperatorType type) {
	List<Operator> operators = new ArrayList<>();

	for (int i = 0; i < 10; i++) {
	    Operator operator = new Operator();
	    operator.setCode("00" + i);
	    operator.setName("OPERATOR" + i);
	    operator.setType(type);
	    operators.add(operator);

	}
	return operators;
    }

    private List<OperatorItem> getOperatorItemList() {
	List<OperatorItem> operators = new ArrayList<>();

	for (int i = 0; i < 10; i++) {
	    OperatorItem operator = new OperatorItem();
	    operator.setIdOperador("00" + i);
	    operator.setOperador("OPERATOR" + i);

	    operators.add(operator);

	}
	return operators;
    }

}
