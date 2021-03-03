
package com.doordash.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MinimumSubtotalMonetaryFields {

    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("display_string")
    @Expose
    private String displayString;
    @SerializedName("decimal_places")
    @Expose
    private Integer decimalPlaces;
    @SerializedName("unit_amount")
    @Expose
    private Object unitAmount;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    public Integer getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(Integer decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public Object getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(Object unitAmount) {
        this.unitAmount = unitAmount;
    }

}
