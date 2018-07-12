package com.smartmarket.data.order;

import com.smartmarket.data.BaseData;

import java.util.List;

/**
 * Created by Glauco on 31/05/2018.
 */

public class Order extends BaseData {
    public Long id;
    public String status;
    public Object statusDetail;
    public String dateCreated;
    public String dateClosed;
    public List<OrderItem> orderItems = null;
    public Integer totalAmount;
    public String currencyId;
    public Buyer buyer;
    public Seller seller;
    public List<Payment> payments = null;
    public Feedback feedback;
    public Shipping shipping;
    public List<String> tags = null;

    public class BillingInfo {

        public String docType;
        public String docNumber;

    }

    public class Buyer {

        public String id;
        public String nickname;
        public String email;
        public Phone phone;
        public String firstName;
        public String lastName;
        public BillingInfo billingInfo;

    }

    public class City {

        public String id;
        public String name;

    }

    public class Country {

        public String id;
        public String name;

    }

    public class Feedback {

        public Object purchase;
        public Object sale;

    }

    public class Item {

        public String id;
        public String title;
        public Object variationId;
        public List<Object> variationAttributes = null;

    }

    public class OrderItem {

        public Item item;
        public Integer quantity;
        public Integer unitPrice;
        public String currencyId;

    }

    public class Payment {

        public String id;
        public Integer transactionAmount;
        public String currencyId;
        public String status;
        public Object dateCreated;
        public Object dateLastModified;

    }

    public class Phone {

        public String areaCode;
        public String number;
        public Object extension;

    }

    public class ReceiverAddress {

        public Long id;
        public String addressLine;
        public String zipCode;
        public City city;
        public State state;
        public Country country;
        public Object latitude;
        public Object longitude;
        public Object comment;

    }

    public class Seller {

        public String id;
        public String nickname;
        public String email;
        public Phone phone;
        public String firstName;
        public String lastName;

    }

    public class Shipping {

        public Long id;
        public String shipmentType;
        public String status;
        public String dateCreated;
        public ReceiverAddress receiverAddress;
        public String currencyId;
        public Float cost;

    }

    public class State {

        public String id;
        public String name;

    }

}

