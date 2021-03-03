
package com.doordash.model.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MerchantPromotion {

    @SerializedName("category_new_store_customers_only")
    @Expose
    private Boolean categoryNewStoreCustomersOnly;
    @SerializedName("minimum_subtotal_monetary_fields")
    @Expose
    private MinimumSubtotalMonetaryFields minimumSubtotalMonetaryFields;
    @SerializedName("daypart_constraints")
    @Expose
    private List<Object> daypartConstraints = null;
    @SerializedName("delivery_fee")
    @Expose
    private Object deliveryFee;
    @SerializedName("delivery_fee_monetary_fields")
    @Expose
    private DeliveryFeeMonetaryFields_ deliveryFeeMonetaryFields;
    @SerializedName("minimum_subtotal")
    @Expose
    private Object minimumSubtotal;
    @SerializedName("id")
    @Expose
    private Integer id;

    public Boolean getCategoryNewStoreCustomersOnly() {
        return categoryNewStoreCustomersOnly;
    }

    public void setCategoryNewStoreCustomersOnly(Boolean categoryNewStoreCustomersOnly) {
        this.categoryNewStoreCustomersOnly = categoryNewStoreCustomersOnly;
    }

    public MinimumSubtotalMonetaryFields getMinimumSubtotalMonetaryFields() {
        return minimumSubtotalMonetaryFields;
    }

    public void setMinimumSubtotalMonetaryFields(MinimumSubtotalMonetaryFields minimumSubtotalMonetaryFields) {
        this.minimumSubtotalMonetaryFields = minimumSubtotalMonetaryFields;
    }

    public List<Object> getDaypartConstraints() {
        return daypartConstraints;
    }

    public void setDaypartConstraints(List<Object> daypartConstraints) {
        this.daypartConstraints = daypartConstraints;
    }

    public Object getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Object deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public DeliveryFeeMonetaryFields_ getDeliveryFeeMonetaryFields() {
        return deliveryFeeMonetaryFields;
    }

    public void setDeliveryFeeMonetaryFields(DeliveryFeeMonetaryFields_ deliveryFeeMonetaryFields) {
        this.deliveryFeeMonetaryFields = deliveryFeeMonetaryFields;
    }

    public Object getMinimumSubtotal() {
        return minimumSubtotal;
    }

    public void setMinimumSubtotal(Object minimumSubtotal) {
        this.minimumSubtotal = minimumSubtotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
