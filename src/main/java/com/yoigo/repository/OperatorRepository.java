/*******************************************************************************
 * Copyright (C) 2017 Yoigo & Masmovil - All Rights Reserved
 *
 * This file is a part of proprietary and confidential software.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *******************************************************************************/
package com.yoigo.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.yoigo.domain.entity.Operator;
import com.yoigo.domain.entity.OperatorType;

@Repository
public interface OperatorRepository extends PagingAndSortingRepository<Operator, Long> {

    List<Operator> findByLineTypeOrderByCustomOrderAsc(OperatorType lineType);

}
