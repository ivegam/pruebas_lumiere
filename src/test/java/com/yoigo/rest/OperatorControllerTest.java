/*******************************************************************************
 * Copyright (C) 2017 Yoigo & Masmovil - All Rights Reserved
 *
 * This file is a part of proprietary and confidential software.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *******************************************************************************/
package com.yoigo.rest;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoigo.domain.dto.OperatorItem;
import com.yoigo.domain.entity.OperatorType;
import com.yoigo.service.OperatorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperatorControllerTest {

    @InjectMocks
    private OperatorController operatorController;

    @Mock
    private OperatorService operatorService;

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    private static final String mobileOperatorListPath = "/mobile";
    private static final String mobileOperatorPath = "/mobile/{phoneNumber}";
    private static final String landOperatorListPath = "/land";

    @Before
    public void setup() {
	initMocks(this);
	this.mockMvc = MockMvcBuilders.standaloneSetup(operatorController).build();
	this.mapper = new ObjectMapper();

	when(operatorService.findOperator("612345678")).thenReturn(getOperator());
	when(operatorService.findOperator("957957957")).thenThrow(new IllegalArgumentException());
	when(operatorService.findOperator("6123456789")).thenThrow(new IllegalArgumentException());
	when(operatorService.findOperator("61234567")).thenThrow(new IllegalArgumentException());
	when(operatorService.findOperatorByLineType(OperatorType.MOBILE_PHONE)).thenReturn(getOperatorItemList());
	when(operatorService.findOperatorByLineType(OperatorType.LAND_LINE)).thenReturn(getOperatorItemList());

    }

    @Test
    public void shouldFindMobileOperator() throws Exception {

	mockMvc.perform(get(mobileOperatorPath, "612345678")).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(content().json(mapper.writeValueAsString(getOperator())));

    }

    @Test
    public void shouldGetBadRequest() throws Exception {
	mockMvc.perform(get(mobileOperatorPath, "957957957")).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldGetBadRequest3() throws Exception {
	mockMvc.perform(get(mobileOperatorPath, "6123456789")).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldGetBadRequest4() throws Exception {
	mockMvc.perform(get(mobileOperatorPath, "61234567")).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldFindMobileListOperator() throws Exception {

	mockMvc.perform(get(mobileOperatorListPath)).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(content().json(mapper.writeValueAsString(getOperatorItemList())));

    }

    @Test
    public void shouldFindLandListOperator() throws Exception {

	mockMvc.perform(get(landOperatorListPath)).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(content().json(mapper.writeValueAsString(getOperatorItemList())));

    }

    public OperatorItem getOperator() {

	OperatorItem operator = new OperatorItem();
	operator.setIdOperador("005");
	operator.setOperador("YOIGO");

	return operator;
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
