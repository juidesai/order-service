package com.solstice.orderservice.com.solstice.orderservice.Domain;

import javax.persistence.*;
import java.util.Date;

public class Shipment {
    private Date shippedDate;
    private Date deliveryDate;
    public Shipment(){}

    public Shipment(long shipmentId, long shipment_account_id, long shipment_address_id, long shipment_orderlineitem_id, Date shippedDate, Date deliveryDate) {
        this.shippedDate = shippedDate;
        this.deliveryDate = deliveryDate;
    }
    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
