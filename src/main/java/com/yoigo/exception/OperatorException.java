/*******************************************************************************
 * Copyright (C) 2017 Yoigo & Masmovil - All Rights Reserved
 *
 * This file is a part of proprietary and confidential software.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *******************************************************************************/
package com.yoigo.exception;

public class OperatorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OperatorException() {
	super();
    }

    public OperatorException(String message) {
	super(message);
    }
}
