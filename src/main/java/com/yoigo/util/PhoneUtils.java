/*******************************************************************************
 * Copyright (C) 2017 Yoigo & Masmovil - All Rights Reserved
 *
 * This file is a part of proprietary and confidential software.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *******************************************************************************/
package com.yoigo.util;

import com.google.common.base.Optional;

public class PhoneUtils {

    private PhoneUtils() {

    }

    /**
     * This function validates if phoneNumber input param is a valid mobile
     * phone number in spain
     * 
     * @param phoneNumber
     * @return
     */
    public static Boolean isValidMobilePhoneNumber(String phoneNumber) {

	return Optional.fromNullable(phoneNumber).or("").matches("^(6|7)\\d{8}$") ? true : false;

    }

}
