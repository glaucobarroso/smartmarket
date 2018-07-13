package com.smartmarket.data.shipping;

import java.util.List;

/**
 * Created by Glauco on 02/06/2018.
 */

public class Shipping {

    public Long id;
    public String mode;
    public String createdBy;
    public Long orderId;
    public Double orderCost;
    public String siteId;
    public String status;
    public String substatus;
    public StatusHistory statusHistory;
    public String dateCreated;
    public String lastUpdated;
    public String trackingNumber;
    public String trackingMethod;
    public Long serviceId;
    public Object carrierInfo;
    public Long senderId;
    public SenderAddress senderAddress;
    public Long receiverId;
    public ReceiverAddress receiverAddress;
    public List<ShippingItem> shippingItems = null;
    public ShippingOption shippingOption;
    public Object comments;
    public Object dateFirstPrinted;
    public String marketPlace;
    public Object returnDetails;
    public List<Object> tags = null;
    public String type;
    public Object applicationId;
    public Object returnTrackingNumber;
    public CostComponents costComponents;
    public String logisticType;
    public List<Object> substatusHistory = null;
    public List<Object> delay = null;

    public class City {
        public String id;
        public String name;
    }

    public class CostComponents {
        public Long specialDiscount;
        public Long loyalDiscount;
        public Long compensation;
    }

    public class Country {
        public String id;
        public String name;
    }

    public class EstimatedDeliveryExtended {
        public String date;
        public Long offset;
    }

    public class EstimatedDeliveryFinal {
        public String date;
        public Long offset;
    }

    public class EstimatedDeliveryLimit {
        public String date;
        public Long offset;
    }

    public class EstimatedDeliveryTime {
        public String type;
        public String date;
        public String unit;
        public Offset offset;
        public TimeFrame timeFrame;
        public Object payBefore;
        public Long shipping;
        public Object handling;
        public Object schedule;
    }

    public class EstimatedHandlingLimit {
        public String date;
    }

    public class EstimatedScheduleLimit {
        public Object date;
    }

    public class Municipality {
        public Object id;
        public Object name;
    }


    public class Neighborhood {
        public Object id;
        public String name;
    }

    public class Offset {
        public String date;
        public Long shipping;
    }

    public class ReceiverAddress {
        public Long id;
        public String addressLine;
        public String streetName;
        public String streetNumber;
        public Object comment;
        public String zipCode;
        public City city;
        public State state;
        public Country country;
        public Neighborhood neighborhood;
        public Municipality municipality;
        public Object agency;
        public List<String> types = null;
        public Double latitude;
        public Double longitude;
        public String geolocationType;
        public String receiverName;
        public String receiverPhone;
    }

    public class SenderAddress {
        public Long id;
        public String addressLine;
        public String streetName;
        public String streetNumber;
        public Object comment;
        public String zipCode;
        public City city;
        public State state;
        public Country country;
        public Neighborhood neighborhood;
        public Municipality municipality;
        public Object agency;
        public List<String> types = null;
        public Double latitude;
        public Double longitude;
        public String geolocationType;
    }

    public class ShippingItem {
        public String id;
        public String description;
        public Long quantity;
        public String dimensions;
    }

    public class ShippingOption {
        public Long id;
        public Long shippingMethodId;
        public String name;
        public String currencyId;
        public Double cost;
        public String deliveryType;
        public Double listCost;
        public EstimatedScheduleLimit estimatedScheduleLimit;
        public EstimatedHandlingLimit estimatedHandlingLimit;
        public EstimatedDeliveryFinal estimatedDeliveryFinal;
        public EstimatedDeliveryLimit estimatedDeliveryLimit;
        public EstimatedDeliveryExtended estimatedDeliveryExtended;
        public EstimatedDeliveryTime estimatedDeliveryTime;
    }

    public class State {
        public String id;
        public String name;
    }

    public class StatusHistory {
        public Object dateCancelled;
        public Object dateDelivered;
        public Object dateFirstVisit;
        public String dateHandling;
        public Object dateNotDelivered;
        public String dateReadyToShip;
        public Object dateShipped;
        public Object dateReturned;
    }

    public class TimeFrame {
        public Object from;
        public Object to;
    }
}
