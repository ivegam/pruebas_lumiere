/*******************************************************************************
 * Copyright (C) 2017 Yoigo & Masmovil - All Rights Reserved
 *
 * This file is a part of proprietary and confidential software.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *******************************************************************************/
package com.yoigo.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "operators")

public class Operator implements Serializable {

    private static final long serialVersionUID = -193081495580118548L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "line_type")
    private OperatorType lineType;

    @Column(name = "custom_order")
    private Long customOrder;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public OperatorType getType() {
	return lineType;
    }

    public void setType(OperatorType type) {
	this.lineType = type;
    }

    public Long getCustomOrder() {
	return customOrder;
    }

    public void setCustomOrder(Long customOrder) {
	this.customOrder = customOrder;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((Id == null) ? 0 : Id.hashCode());
	result = prime * result + ((code == null) ? 0 : code.hashCode());
	result = prime * result + ((customOrder == null) ? 0 : customOrder.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((lineType == null) ? 0 : lineType.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof Operator)) {
	    return false;
	}
	Operator other = (Operator) obj;
	if (Id == null) {
	    if (other.Id != null) {
		return false;
	    }
	} else if (!Id.equals(other.Id)) {
	    return false;
	}
	if (code == null) {
	    if (other.code != null) {
		return false;
	    }
	} else if (!code.equals(other.code)) {
	    return false;
	}
	if (customOrder == null) {
	    if (other.customOrder != null) {
		return false;
	    }
	} else if (!customOrder.equals(other.customOrder)) {
	    return false;
	}
	if (name == null) {
	    if (other.name != null) {
		return false;
	    }
	} else if (!name.equals(other.name)) {
	    return false;
	}
	if (lineType != other.lineType) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Operator [name=");
	builder.append(name);
	builder.append(", code=");
	builder.append(code);
	builder.append(", type=");
	builder.append(lineType);
	builder.append(", customOrder=");
	builder.append(customOrder);
	builder.append("]");
	return builder.toString();
    }

}
