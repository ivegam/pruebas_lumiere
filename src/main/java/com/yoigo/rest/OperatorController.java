/*******************************************************************************
 * Copyright (C) 2017 Yoigo & Masmovil - All Rights Reserved
 *
 * This file is a part of proprietary and confidential software.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *******************************************************************************/
package com.yoigo.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yoigo.domain.dto.OperatorItem;
import com.yoigo.domain.entity.OperatorType;
import com.yoigo.service.OperatorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Operator", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping("/")
public class OperatorController {

    @Autowired
    OperatorService operatorService;

    @RequestMapping(value = "/mobile/{phoneNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({ @ApiResponse(code = 200, message = "Successful call", response = OperatorItem.class),
	    @ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Page not found"),
	    @ApiResponse(code = 500, message = "Internal server error") })
    @ApiOperation(value = "Check Operator for a given phone number", notes = "Returns an operator information", response = OperatorItem.class)
    public OperatorItem findMobileOperator(
	    @ApiParam(value = "Phone number", required = true) @Valid @PathVariable String phoneNumber) {
	return operatorService.findOperator(phoneNumber);

    }

    @RequestMapping(value = "/mobile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({
	    @ApiResponse(code = 200, message = "Successful call", response = OperatorItem.class, responseContainer = "List"),
	    @ApiResponse(code = 404, message = "Page not found"),
	    @ApiResponse(code = 500, message = "Internal server error") })
    @ApiOperation(value = "Returns a list of mobile operators", notes = "Returns a list of mobile operators", response = OperatorItem.class, responseContainer = "List")
    public List<OperatorItem> findAllMobileOperator() {
	return operatorService.findOperatorByLineType(OperatorType.MOBILE_PHONE);

    }

    @RequestMapping(value = "/land", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses({
	    @ApiResponse(code = 200, message = "Successful call", response = OperatorItem.class, responseContainer = "List"),
	    @ApiResponse(code = 404, message = "Page not found"),
	    @ApiResponse(code = 500, message = "Internal server error") })
    @ApiOperation(value = "Returns a list of land operators", notes = "Returns a list of land operators", response = OperatorItem.class, responseContainer = "List")
    public List<OperatorItem> findAllLandOperator() {
	return operatorService.findOperatorByLineType(OperatorType.LAND_LINE);

    }

    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
	response.sendError(HttpStatus.BAD_REQUEST.value());
    }

}
