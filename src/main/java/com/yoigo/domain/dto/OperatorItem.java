package com.yoigo.domain.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperatorItem implements Serializable {

    private static final long serialVersionUID = -1027761904474436855L;

    /**
     * Operator name
     * 
     */

    private String operator;

    /**
     * Operator identifier
     * 
     */
    private String idOperator;

    @JsonProperty("operator")
    @ApiModelProperty(example = "YOIGO", value = "Operator name")
    public String getOperator() {
	return operator;
    }

    @JsonProperty("operador")
    public void setOperador(String operador) {
	this.operator = operador;
    }

    @JsonProperty("idOperator")
    @ApiModelProperty(example = "005", value = "Operator code")
    public String getIdOperator() {
	return idOperator;
    }

    @JsonProperty("idOperador")
    public void setIdOperador(String idOperador) {
	this.idOperator = idOperador;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((idOperator == null) ? 0 : idOperator.hashCode());
	result = prime * result + ((operator == null) ? 0 : operator.hashCode());
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
	if (!(obj instanceof OperatorItem)) {
	    return false;
	}
	OperatorItem other = (OperatorItem) obj;
	if (idOperator == null) {
	    if (other.idOperator != null) {
		return false;
	    }
	} else if (!idOperator.equals(other.idOperator)) {
	    return false;
	}
	if (operator == null) {
	    if (other.operator != null) {
		return false;
	    }
	} else if (!operator.equals(other.operator)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Operator [operator=");
	builder.append(operator);
	builder.append(", idOperator=");
	builder.append(idOperator);
	builder.append("]");
	return builder.toString();
    }

}
