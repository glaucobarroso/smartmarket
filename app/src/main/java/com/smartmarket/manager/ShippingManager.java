package com.smartmarket.manager;

import android.os.Handler;
import android.os.Message;
import com.mercadolibre.android.sdk.Identity;
import com.smartmarket.activity.DefaultListActivity;
import com.smartmarket.data.question.Shipping;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glauco on 02/06/2018.
 */

public class ShippingManager extends Manager {

    private static final String GET_SHIPMENT_FORMAT_STR = "/shipments/%s?access_token=%s";

    public ShippingManager(Identity identity, Handler handler) {
        super(identity, handler);
    }

    private String getShipment(String shipmentId) {
        return get(GET_SHIPMENT_FORMAT_STR, shipmentId, mIdentity.getAccessToken().getAccessTokenValue());
    }

    private List<Shipping> getShipments(List<String> shipmentIdList) {
        List<Shipping> shipments = new ArrayList<Shipping>();
        if (shipmentIdList != null && shipmentIdList.size() > 0) {
            for (String shipmentId : shipmentIdList) {
                String shipment = getShipment(shipmentId);
                if (shipment != null) {
                    //shipments.add(shipment);
                }
            }
        }
        return shipments;
    }

    public class GetShipments extends Thread {

        List<String> mShipmentIdList;

        public GetShipments(List<String> shipmentIdList) {
            mShipmentIdList = shipmentIdList;
        }

        @Override
        public void run() {
            List<Shipping> shipments = getShipments(mShipmentIdList);
            Message msg = mHandler.obtainMessage();
            msg.what = DefaultListActivity.GET_PENDING_MESSAGE;
            msg.obj = shipments;
            mHandler.sendMessage(msg);
        }
    }

}
