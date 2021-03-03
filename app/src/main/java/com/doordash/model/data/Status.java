
package com.doordash.model.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("unavailable_reason")
    @Expose
    private Object unavailableReason;
    @SerializedName("pickup_available")
    @Expose
    private Boolean pickupAvailable;
    @SerializedName("asap_available")
    @Expose
    private Boolean asapAvailable;
    @SerializedName("scheduled_available")
    @Expose
    private Boolean scheduledAvailable;
    @SerializedName("asap_minutes_range")
    @Expose
    private List<Integer> asapMinutesRange = null;

    public Object getUnavailableReason() {
        return unavailableReason;
    }

    public void setUnavailableReason(Object unavailableReason) {
        this.unavailableReason = unavailableReason;
    }

    public Boolean getPickupAvailable() {
        return pickupAvailable;
    }

    public void setPickupAvailable(Boolean pickupAvailable) {
        this.pickupAvailable = pickupAvailable;
    }

    public Boolean getAsapAvailable() {
        return asapAvailable;
    }

    public void setAsapAvailable(Boolean asapAvailable) {
        this.asapAvailable = asapAvailable;
    }

    public Boolean getScheduledAvailable() {
        return scheduledAvailable;
    }

    public void setScheduledAvailable(Boolean scheduledAvailable) {
        this.scheduledAvailable = scheduledAvailable;
    }

    public List<Integer> getAsapMinutesRange() {
        return asapMinutesRange;
    }

    public void setAsapMinutesRange(List<Integer> asapMinutesRange) {
        this.asapMinutesRange = asapMinutesRange;
    }

}
