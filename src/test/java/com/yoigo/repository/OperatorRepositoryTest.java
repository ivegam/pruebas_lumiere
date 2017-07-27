/*******************************************************************************
 * Copyright (C) 2017 Yoigo & Masmovil - All Rights Reserved
 *
 * This file is a part of proprietary and confidential software.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *******************************************************************************/
package com.yoigo.repository;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yoigo.domain.entity.Operator;
import com.yoigo.domain.entity.OperatorType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperatorRepositoryTest {

    @Autowired
    private OperatorRepository operatorRepository;

    @Test
    public void shouldFindMobileOperator() {

	List<Operator> operators = operatorRepository.findByLineTypeOrderByCustomOrderAsc(OperatorType.MOBILE_PHONE);
	Assert.assertThat(operators.size(), Matchers.greaterThan(0));

    }

    @Test
    public void shouldFindLandOperator() {

	List<Operator> operators = operatorRepository.findByLineTypeOrderByCustomOrderAsc(OperatorType.LAND_LINE);
	Assert.assertThat(operators.size(), Matchers.greaterThan(0));

    }

}
