# order-service
This is the microservice created using Spring boot and deployed on PCF using Jenkins pipeline.
Below are the endpoints which are implemented here. Here Data-Rest Api is also used which provides many default 
endpoints but below are the endpoints which it doesn't provide.

1) Add Order
Post: /orderses
Request Body: 
            {
              "orderDate":"2018-06-30T20:00:00",
              "account_order_id":23,
              "address_order_id":24
            }
2) Add new OrderlineItem for specific orderId
Post: /orders/{orderId}/orderLineItems
Request Body:
            {
              "quantity":"2",
              "productId":5
            }
3) Get All orders by AccountId
Get: /orders/accounts/accountId/{accountId}
Body: [
          {
              "orderNumber": 2,
              "orderDate": "2018-06-30T20:00:00",
              "account_order_id": 1,
              "address_order_id": 2
          }
      ]
4) Get OrderLineItems by OrderId
Get: /orders/2/orderLineItems
Body: [
          {
              "orderItemsId": 3,
              "productId": 58,
              "quantity": 2,
              "price": 10,
              "totalPrice": 20,
              "orders": {
                  "orderNumber": 2,
                  "orderDate": "2018-06-30T20:00:00",
                  "account_order_id": 1,
                  "address_order_id": 2
              }
          }
      ]
      
5) Update orderline item by order id and orderline item id
Put: /orders/{orderId}/orderLineItems/{orderLineId}
Request Body:{
              "quantity":"2",
              "productId":5
             }
             
6) Delete OrderLine item by order id and orderline item id
Delete: /orders/{orderId}/orderLineItems/{orderLineId}

7) Get order details by account id
Get: 
Body: 
    [
          {
              "orderNumber": 31,
              "address": {
                  "addressId": 26,
                  "street": "sanskriti",
                  "apt": "park",
                  "city": "unioncity",
                  "state": "CA",
                  "zipcode": 90815,
                  "country": "India"
              },
              "product": {
                  "productId": 5,
                  "productName": "Apple",
                  "price": 0
              },
              "quantity": 2,
              "price": 10,
              "totalPrice": 20,
              "shipment": {
                  "shippedDate": "2018-07-10T00:00:00.000+0000",
                  "deliveryDate": "2018-07-11T00:00:00.000+0000"
              }
          }
     ]
