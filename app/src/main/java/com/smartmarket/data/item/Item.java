package com.smartmarket.data.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smartmarket.data.BaseData;

import java.util.List;

/**
 * Created by Glauco on 19/12/2016.
 */

public class Item extends BaseData {

    private String id;
    private String siteId;
    private String title;
    private Object subtitle;
    private Integer sellerId;
    private String categoryId;
    private Object officialStoreId;
    private Float price;
    private Float basePrice;
    private Object originalPrice;
    private String currencyId;
    private Integer initialQuantity;
    private Integer availableQuantity;
    private Integer soldQuantity;
    private String buyingMode;
    private String listingTypeId;
    private String startTime;
    private String stopTime;
    private String condition;
    private String permalink;
    private String thumbnail;
    private String secureThumbnail;
    private List<Picture> pictures = null;
    private Object videoId;
    private List<Description> descriptions = null;
    private Boolean acceptsMercadopago;
    private List<Object> nonMercadoPagoPaymentMethods = null;
    private Shipping shipping;
    private String internationalDeliveryMode;
    private SellerAddress sellerAddress;
    private Object sellerContact;
    private Location location;
    private Geolocation geolocation;
    private List<Object> coverageAreas = null;
    private List<Attribute> attributes = null;
    private List<Object> warnings = null;
    private String listingSource;
    private List<Object> variations = null;
    private String status;
    private List<Object> subStatus = null;
    private List<String> tags = null;
    private Object warranty;
    private Object catalogProductId;
    private Object domainId;
    private Object parentItemId;
    private Object differentialPricing;
    private List<Object> dealIds = null;
    private Boolean automaticRelist;
    private String dateCreated;
    private String lastUpdated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(Object subtitle) {
        this.subtitle = subtitle;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Object getOfficialStoreId() {
        return officialStoreId;
    }

    public void setOfficialStoreId(Object officialStoreId) {
        this.officialStoreId = officialStoreId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Float basePrice) {
        this.basePrice = basePrice;
    }

    public Object getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Object originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public Integer getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(Integer initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getBuyingMode() {
        return buyingMode;
    }

    public void setBuyingMode(String buyingMode) {
        this.buyingMode = buyingMode;
    }

    public String getListingTypeId() {
        return listingTypeId;
    }

    public void setListingTypeId(String listingTypeId) {
        this.listingTypeId = listingTypeId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSecureThumbnail() {
        return secureThumbnail;
    }

    public void setSecureThumbnail(String secureThumbnail) {
        this.secureThumbnail = secureThumbnail;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Object getVideoId() {
        return videoId;
    }

    public void setVideoId(Object videoId) {
        this.videoId = videoId;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public Boolean getAcceptsMercadopago() {
        return acceptsMercadopago;
    }

    public void setAcceptsMercadopago(Boolean acceptsMercadopago) {
        this.acceptsMercadopago = acceptsMercadopago;
    }

    public List<Object> getNonMercadoPagoPaymentMethods() {
        return nonMercadoPagoPaymentMethods;
    }

    public void setNonMercadoPagoPaymentMethods(List<Object> nonMercadoPagoPaymentMethods) {
        this.nonMercadoPagoPaymentMethods = nonMercadoPagoPaymentMethods;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public String getInternationalDeliveryMode() {
        return internationalDeliveryMode;
    }

    public void setInternationalDeliveryMode(String internationalDeliveryMode) {
        this.internationalDeliveryMode = internationalDeliveryMode;
    }

    public SellerAddress getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(SellerAddress sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public Object getSellerContact() {
        return sellerContact;
    }

    public void setSellerContact(Object sellerContact) {
        this.sellerContact = sellerContact;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public List<Object> getCoverageAreas() {
        return coverageAreas;
    }

    public void setCoverageAreas(List<Object> coverageAreas) {
        this.coverageAreas = coverageAreas;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Object> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<Object> warnings) {
        this.warnings = warnings;
    }

    public String getListingSource() {
        return listingSource;
    }

    public void setListingSource(String listingSource) {
        this.listingSource = listingSource;
    }

    public List<Object> getVariations() {
        return variations;
    }

    public void setVariations(List<Object> variations) {
        this.variations = variations;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Object> getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(List<Object> subStatus) {
        this.subStatus = subStatus;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Object getWarranty() {
        return warranty;
    }

    public void setWarranty(Object warranty) {
        this.warranty = warranty;
    }

    public Object getCatalogProductId() {
        return catalogProductId;
    }

    public void setCatalogProductId(Object catalogProductId) {
        this.catalogProductId = catalogProductId;
    }

    public Object getDomainId() {
        return domainId;
    }

    public void setDomainId(Object domainId) {
        this.domainId = domainId;
    }

    public Object getParentItemId() {
        return parentItemId;
    }

    public void setParentItemId(Object parentItemId) {
        this.parentItemId = parentItemId;
    }

    public Object getDifferentialPricing() {
        return differentialPricing;
    }

    public void setDifferentialPricing(Object differentialPricing) {
        this.differentialPricing = differentialPricing;
    }

    public List<Object> getDealIds() {
        return dealIds;
    }

    public void setDealIds(List<Object> dealIds) {
        this.dealIds = dealIds;
    }

    public Boolean getAutomaticRelist() {
        return automaticRelist;
    }

    public void setAutomaticRelist(Boolean automaticRelist) {
        this.automaticRelist = automaticRelist;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public class Attribute {

        private String id;
        private String name;
        private String valueId;
        private String valueName;
        private String attributeGroupId;
        private String attributeGroupName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValueId() {
            return valueId;
        }

        public void setValueId(String valueId) {
            this.valueId = valueId;
        }

        public String getValueName() {
            return valueName;
        }

        public void setValueName(String valueName) {
            this.valueName = valueName;
        }

        public String getAttributeGroupId() {
            return attributeGroupId;
        }

        public void setAttributeGroupId(String attributeGroupId) {
            this.attributeGroupId = attributeGroupId;
        }

        public String getAttributeGroupName() {
            return attributeGroupName;
        }

        public void setAttributeGroupName(String attributeGroupName) {
            this.attributeGroupName = attributeGroupName;
        }

    }

    public class City {

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class City_ {

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class Country {

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class Description {

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    public class Geolocation {

        private Double latitude;
        private Double longitude;

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

    }

    public class Location {


    }

    public class Neighborhood {

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class Picture {

        private String id;
        private String url;
        private String secureUrl;
        private String size;
        private String maxSize;
        private String quality;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSecureUrl() {
            return secureUrl;
        }

        public void setSecureUrl(String secureUrl) {
            this.secureUrl = secureUrl;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(String maxSize) {
            this.maxSize = maxSize;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

    }

    public class SearchLocation {

        private Neighborhood neighborhood;
        private City_ city;
        private State_ state;

        public Neighborhood getNeighborhood() {
            return neighborhood;
        }

        public void setNeighborhood(Neighborhood neighborhood) {
            this.neighborhood = neighborhood;
        }

        public City_ getCity() {
            return city;
        }

        public void setCity(City_ city) {
            this.city = city;
        }

        public State_ getState() {
            return state;
        }

        public void setState(State_ state) {
            this.state = state;
        }

    }

    public class SellerAddress {

        private Integer id;
        private String comment;
        private String addressLine;
        private String zipCode;
        private City city;
        private State state;
        private Country country;
        private Double latitude;
        private Double longitude;
        private SearchLocation searchLocation;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getAddressLine() {
            return addressLine;
        }

        public void setAddressLine(String addressLine) {
            this.addressLine = addressLine;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public SearchLocation getSearchLocation() {
            return searchLocation;
        }

        public void setSearchLocation(SearchLocation searchLocation) {
            this.searchLocation = searchLocation;
        }

    }

    public class Shipping {

        private String mode;
        private Boolean localPickUp;
        private Boolean freeShipping;
        private Object methods;
        private Object dimensions;
        private List<String> tags = null;

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public Boolean getLocalPickUp() {
            return localPickUp;
        }

        public void setLocalPickUp(Boolean localPickUp) {
            this.localPickUp = localPickUp;
        }

        public Boolean getFreeShipping() {
            return freeShipping;
        }

        public void setFreeShipping(Boolean freeShipping) {
            this.freeShipping = freeShipping;
        }

        public Object getMethods() {
            return methods;
        }

        public void setMethods(Object methods) {
            this.methods = methods;
        }

        public Object getDimensions() {
            return dimensions;
        }

        public void setDimensions(Object dimensions) {
            this.dimensions = dimensions;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

    }

    public class State {

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class State_ {

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
