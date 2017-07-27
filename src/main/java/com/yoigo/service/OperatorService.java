/*******************************************************************************
 * Copyright (C) 2017 Yoigo & Masmovil - All Rights Reserved
 *
 * This file is a part of proprietary and confidential software.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *******************************************************************************/
package com.yoigo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yoigo.domain.dto.OperatorItem;
import com.yoigo.domain.entity.OperatorType;

@Service
public interface OperatorService {

    /**
     * Finds phone number operator
     *
     * @param phoneNumber
     *            that has to be checked
     * @return String with information about phone operator
     */
    OperatorItem findOperator(String phoneNumber);

    /**
     * Finds all operators for a type (mobile or land)
     *
     * @param lineType
     *            to find operators
     * 
     * @return List with all operators for a given lineType
     */
    List<OperatorItem> findOperatorByLineType(OperatorType lineType);

}
