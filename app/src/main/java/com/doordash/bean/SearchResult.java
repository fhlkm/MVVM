
package com.doordash.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("is_first_time_user")
    @Expose
    private Boolean isFirstTimeUser;
    @SerializedName("sort_order")
    @Expose
    private String sortOrder;
    @SerializedName("next_offset")
    @Expose
    private Integer nextOffset;
    @SerializedName("show_list_as_pickup")
    @Expose
    private Boolean showListAsPickup;
    @SerializedName("stores")
    @Expose
    private List<Store> stores = null;

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public Boolean getIsFirstTimeUser() {
        return isFirstTimeUser;
    }

    public void setIsFirstTimeUser(Boolean isFirstTimeUser) {
        this.isFirstTimeUser = isFirstTimeUser;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getNextOffset() {
        return nextOffset;
    }

    public void setNextOffset(Integer nextOffset) {
        this.nextOffset = nextOffset;
    }

    public Boolean getShowListAsPickup() {
        return showListAsPickup;
    }

    public void setShowListAsPickup(Boolean showListAsPickup) {
        this.showListAsPickup = showListAsPickup;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

}
