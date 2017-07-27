/*******************************************************************************
 * Copyright (C) 2017 Yoigo & Masmovil - All Rights Reserved
 *
 * This file is a part of proprietary and confidential software.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *******************************************************************************/
package com.yoigo.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneUtilsTest {

    @Test
    public void isValidMobilePhoneNumberShouldReturnFalseWithEmptyString() {
	assertFalse(PhoneUtils.isValidMobilePhoneNumber(""));
    }

    @Test
    public void isValidMobilePhoneNumberShouldReturnFalseWithNull() {
	assertFalse(PhoneUtils.isValidMobilePhoneNumber(null));
    }

    @Test
    public void isValidMobilePhoneNumberShouldReturnFalseWithLandNumber() {
	assertFalse(PhoneUtils.isValidMobilePhoneNumber("957957957"));
    }

    @Test
    public void isValidMobilePhoneNumberShouldReturnFalseWithLandNumber2() {
	assertFalse(PhoneUtils.isValidMobilePhoneNumber("857957957"));
    }

    @Test
    public void isValidMobilePhoneNumberShouldReturnFalseWithShortNumber() {
	assertFalse(PhoneUtils.isValidMobilePhoneNumber("657"));
    }

    @Test
    public void isValidMobilePhoneNumberShouldReturnFalseWithShortNumber2() {
	assertFalse(PhoneUtils.isValidMobilePhoneNumber("65765765"));
    }

    @Test
    public void isValidMobilePhoneNumberShouldReturnFalseWithLongNumber() {
	assertFalse(PhoneUtils.isValidMobilePhoneNumber("6576576576"));
    }

    @Test
    public void isValidMobilePhoneNumberShouldReturnTrue() {
	assertTrue(PhoneUtils.isValidMobilePhoneNumber("657657656"));
    }

    @Test
    public void isValidMobilePhoneNumberShouldReturnTrue2() {
	assertTrue(PhoneUtils.isValidMobilePhoneNumber("757657657"));
    }

}
