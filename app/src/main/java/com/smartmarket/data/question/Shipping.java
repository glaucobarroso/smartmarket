package com.smartmarket.data.question;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Shipping {

    @SerializedName("destination")
    @Expose
    private Destination destination;
    @SerializedName("options")
    @Expose
    private List<Option> options = null;
    @SerializedName("custom_message")
    @Expose
    private CustomMessage customMessage;

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public CustomMessage getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(CustomMessage customMessage) {
        this.customMessage = customMessage;
    }

    public class City {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
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

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
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

    public class CustomMessage {

        @SerializedName("reason")
        @Expose
        private String reason;
        @SerializedName("display_mode")
        @Expose
        private String displayMode;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getDisplayMode() {
            return displayMode;
        }

        public void setDisplayMode(String displayMode) {
            this.displayMode = displayMode;
        }

    }

    public class Destination {

        @SerializedName("zip_code")
        @Expose
        private String zipCode;
        @SerializedName("city")
        @Expose
        private City city;
        @SerializedName("state")
        @Expose
        private State state;
        @SerializedName("country")
        @Expose
        private Country country;
        @SerializedName("extended_attributes")
        @Expose
        private ExtendedAttributes extendedAttributes;

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

        public ExtendedAttributes getExtendedAttributes() {
            return extendedAttributes;
        }

        public void setExtendedAttributes(ExtendedAttributes extendedAttributes) {
            this.extendedAttributes = extendedAttributes;
        }

    }

    public class Discount {

        @SerializedName("rate")
        @Expose
        private Integer rate;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("promoted_amount")
        @Expose
        private Integer promotedAmount;

        public Integer getRate() {
            return rate;
        }

        public void setRate(Integer rate) {
            this.rate = rate;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getPromotedAmount() {
            return promotedAmount;
        }

        public void setPromotedAmount(Integer promotedAmount) {
            this.promotedAmount = promotedAmount;
        }

    }

    public class EstimatedDeliveryTime {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("date")
        @Expose
        private Object date;
        @SerializedName("shipping")
        @Expose
        private Integer shipping;
        @SerializedName("handling")
        @Expose
        private Object handling;
        @SerializedName("unit")
        @Expose
        private String unit;
        @SerializedName("offset")
        @Expose
        private Offset offset;
        @SerializedName("time_frame")
        @Expose
        private TimeFrame timeFrame;
        @SerializedName("pay_before")
        @Expose
        private Object payBefore;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getDate() {
            return date;
        }

        public void setDate(Object date) {
            this.date = date;
        }

        public Integer getShipping() {
            return shipping;
        }

        public void setShipping(Integer shipping) {
            this.shipping = shipping;
        }

        public Object getHandling() {
            return handling;
        }

        public void setHandling(Object handling) {
            this.handling = handling;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Offset getOffset() {
            return offset;
        }

        public void setOffset(Offset offset) {
            this.offset = offset;
        }

        public TimeFrame getTimeFrame() {
            return timeFrame;
        }

        public void setTimeFrame(TimeFrame timeFrame) {
            this.timeFrame = timeFrame;
        }

        public Object getPayBefore() {
            return payBefore;
        }

        public void setPayBefore(Object payBefore) {
            this.payBefore = payBefore;
        }

    }

    public class ExtendedAttributes {

        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("owner_name")
        @Expose
        private Object ownerName;
        @SerializedName("zip_code_type")
        @Expose
        private ZipCodeType zipCodeType;
        @SerializedName("city_type")
        @Expose
        private String cityType;
        @SerializedName("city_name")
        @Expose
        private String cityName;
        @SerializedName("neighborhood")
        @Expose
        private String neighborhood;
        @SerializedName("status")
        @Expose
        private String status;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(Object ownerName) {
            this.ownerName = ownerName;
        }

        public ZipCodeType getZipCodeType() {
            return zipCodeType;
        }

        public void setZipCodeType(ZipCodeType zipCodeType) {
            this.zipCodeType = zipCodeType;
        }

        public String getCityType() {
            return cityType;
        }

        public void setCityType(String cityType) {
            this.cityType = cityType;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getNeighborhood() {
            return neighborhood;
        }

        public void setNeighborhood(String neighborhood) {
            this.neighborhood = neighborhood;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }

    public class Offset {

        @SerializedName("date")
        @Expose
        private Object date;
        @SerializedName("shipping")
        @Expose
        private Integer shipping;

        public Object getDate() {
            return date;
        }

        public void setDate(Object date) {
            this.date = date;
        }

        public Integer getShipping() {
            return shipping;
        }

        public void setShipping(Integer shipping) {
            this.shipping = shipping;
        }

    }


    public class Option {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("shipping_method_id")
        @Expose
        private Integer shippingMethodId;
        @SerializedName("currency_id")
        @Expose
        private String currencyId;
        @SerializedName("list_cost")
        @Expose
        private Double listCost;
        @SerializedName("cost")
        @Expose
        private Double cost;
        @SerializedName("tracks_shipments_status")
        @Expose
        private String tracksShipmentsStatus;
        @SerializedName("display")
        @Expose
        private String display;
        @SerializedName("estimated_delivery_time")
        @Expose
        private EstimatedDeliveryTime estimatedDeliveryTime;
        @SerializedName("discount")
        @Expose
        private Discount discount;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getShippingMethodId() {
            return shippingMethodId;
        }

        public void setShippingMethodId(Integer shippingMethodId) {
            this.shippingMethodId = shippingMethodId;
        }

        public String getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(String currencyId) {
            this.currencyId = currencyId;
        }

        public Double getListCost() {
            return listCost;
        }

        public void setListCost(Double listCost) {
            this.listCost = listCost;
        }

        public Double getCost() {
            return cost;
        }

        public void setCost(Double cost) {
            this.cost = cost;
        }

        public String getTracksShipmentsStatus() {
            return tracksShipmentsStatus;
        }

        public void setTracksShipmentsStatus(String tracksShipmentsStatus) {
            this.tracksShipmentsStatus = tracksShipmentsStatus;
        }

        public String getDisplay() {
            return display;
        }

        public void setDisplay(String display) {
            this.display = display;
        }

        public EstimatedDeliveryTime getEstimatedDeliveryTime() {
            return estimatedDeliveryTime;
        }

        public void setEstimatedDeliveryTime(EstimatedDeliveryTime estimatedDeliveryTime) {
            this.estimatedDeliveryTime = estimatedDeliveryTime;
        }

        public Discount getDiscount() {
            return discount;
        }

        public void setDiscount(Discount discount) {
            this.discount = discount;
        }

    }

    public class State {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
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


    public class TimeFrame {

        @SerializedName("from")
        @Expose
        private Object from;
        @SerializedName("to")
        @Expose
        private Object to;

        public Object getFrom() {
            return from;
        }

        public void setFrom(Object from) {
            this.from = from;
        }

        public Object getTo() {
            return to;
        }

        public void setTo(Object to) {
            this.to = to;
        }

    }

    public class ZipCodeType {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("description")
        @Expose
        private String description;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }
}

