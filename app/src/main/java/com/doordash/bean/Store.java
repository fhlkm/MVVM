
package com.doordash.bean;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store {

    @SerializedName("is_consumer_subscription_eligible")
    @Expose
    private Boolean isConsumerSubscriptionEligible;
    @SerializedName("promotion_delivery_fee")
    @Expose
    private Integer promotionDeliveryFee;
    @SerializedName("delivery_fee")
    @Expose
    private Integer deliveryFee;
    @SerializedName("delivery_fee_monetary_fields")
    @Expose
    private DeliveryFeeMonetaryFields deliveryFeeMonetaryFields;
    @SerializedName("num_ratings")
    @Expose
    private Integer numRatings;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("extra_sos_delivery_fee_monetary_fields")
    @Expose
    private ExtraSosDeliveryFeeMonetaryFields extraSosDeliveryFeeMonetaryFields;
    @SerializedName("menus")
    @Expose
    private List<Menu> menus = null;
    @SerializedName("average_rating")
    @Expose
    private Double averageRating;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("display_delivery_fee")
    @Expose
    private String displayDeliveryFee;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("business_id")
    @Expose
    private Integer businessId;
    @SerializedName("extra_sos_delivery_fee")
    @Expose
    private Integer extraSosDeliveryFee;
    @SerializedName("cover_img_url")
    @Expose
    private String coverImgUrl;
    @SerializedName("header_img_url")
    @Expose
    private String headerImgUrl;
    @SerializedName("price_range")
    @Expose
    private Integer priceRange;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("is_newly_added")
    @Expose
    private Boolean isNewlyAdded;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("next_close_time")
    @Expose
    private String nextCloseTime;
    @SerializedName("next_open_time")
    @Expose
    private String nextOpenTime;
    @SerializedName("service_rate")
    @Expose
    private Object serviceRate;
    @SerializedName("distance_from_consumer")
    @Expose
    private Double distanceFromConsumer;
    @SerializedName("merchant_promotions")
    @Expose
    private List<MerchantPromotion> merchantPromotions = null;

    public Boolean getIsConsumerSubscriptionEligible() {
        return isConsumerSubscriptionEligible;
    }

    public void setIsConsumerSubscriptionEligible(Boolean isConsumerSubscriptionEligible) {
        this.isConsumerSubscriptionEligible = isConsumerSubscriptionEligible;
    }

    public Integer getPromotionDeliveryFee() {
        return promotionDeliveryFee;
    }

    public void setPromotionDeliveryFee(Integer promotionDeliveryFee) {
        this.promotionDeliveryFee = promotionDeliveryFee;
    }

    public Integer getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public DeliveryFeeMonetaryFields getDeliveryFeeMonetaryFields() {
        return deliveryFeeMonetaryFields;
    }

    public void setDeliveryFeeMonetaryFields(DeliveryFeeMonetaryFields deliveryFeeMonetaryFields) {
        this.deliveryFeeMonetaryFields = deliveryFeeMonetaryFields;
    }

    public Integer getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(Integer numRatings) {
        this.numRatings = numRatings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ExtraSosDeliveryFeeMonetaryFields getExtraSosDeliveryFeeMonetaryFields() {
        return extraSosDeliveryFeeMonetaryFields;
    }

    public void setExtraSosDeliveryFeeMonetaryFields(ExtraSosDeliveryFeeMonetaryFields extraSosDeliveryFeeMonetaryFields) {
        this.extraSosDeliveryFeeMonetaryFields = extraSosDeliveryFeeMonetaryFields;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDisplayDeliveryFee() {
        return displayDeliveryFee;
    }

    public void setDisplayDeliveryFee(String displayDeliveryFee) {
        this.displayDeliveryFee = displayDeliveryFee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getExtraSosDeliveryFee() {
        return extraSosDeliveryFee;
    }

    public void setExtraSosDeliveryFee(Integer extraSosDeliveryFee) {
        this.extraSosDeliveryFee = extraSosDeliveryFee;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getHeaderImgUrl() {
        return headerImgUrl;
    }

    public void setHeaderImgUrl(String headerImgUrl) {
        this.headerImgUrl = headerImgUrl;
    }

    public Integer getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(Integer priceRange) {
        this.priceRange = priceRange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsNewlyAdded() {
        return isNewlyAdded;
    }

    public void setIsNewlyAdded(Boolean isNewlyAdded) {
        this.isNewlyAdded = isNewlyAdded;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNextCloseTime() {
        return nextCloseTime;
    }

    public void setNextCloseTime(String nextCloseTime) {
        this.nextCloseTime = nextCloseTime;
    }

    public String getNextOpenTime() {
        return nextOpenTime;
    }

    public void setNextOpenTime(String nextOpenTime) {
        this.nextOpenTime = nextOpenTime;
    }

    public Object getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(Object serviceRate) {
        this.serviceRate = serviceRate;
    }

    public Double getDistanceFromConsumer() {
        return distanceFromConsumer;
    }

    public void setDistanceFromConsumer(Double distanceFromConsumer) {
        this.distanceFromConsumer = distanceFromConsumer;
    }

    public List<MerchantPromotion> getMerchantPromotions() {
        return merchantPromotions;
    }

    public void setMerchantPromotions(List<MerchantPromotion> merchantPromotions) {
        this.merchantPromotions = merchantPromotions;
    }

}
