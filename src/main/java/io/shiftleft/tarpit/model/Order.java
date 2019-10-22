package io.shiftleft.tarpit.model;

import io.shiftleft.tarpit.annotation.SensitiveBeacon;
import io.shiftleft.tarpit.annotation.SensitiveRedact;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {

  private String orderId;
  private String custId;
  private Date orderDate;
  private String orderStatus;
  private Date shipDate;

  @SensitiveRedact
  private String creditCardNumber;

  @SensitiveBeacon
  private String street;
  @SensitiveBeacon
  private String city;
  @SensitiveBeacon
  private String state;
  @SensitiveBeacon
  private String zipCode;
  @SensitiveBeacon
  private String emailAddress;

  static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

  public Order(String orderId, String custId, Date orderDate, String orderStatus,
      Date shipDate, String creditCardNumber, String street, String city, String state,
      String zipCode, String emailAddress) {
    this.orderId = orderId;
    this.custId = custId;
    this.orderDate = orderDate;
    this.orderStatus = orderStatus;
    this.shipDate = shipDate;
    this.creditCardNumber = creditCardNumber;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.emailAddress = emailAddress;
  }

  public static Order getDefaultOrder() throws ParseException {
    return new Order("1234","5678", formatter.parse("04/10/2019"), "PENDING", formatter.parse(""), "344472003047574", "Lakeside Drive", "Santa Clara", "CA", "95054", "mike@waltz.com");
  }

  public static Order createOrder() throws ParseException {
    return new Order("1234","5678", formatter.parse("04/10/2019"), "PENDING", formatter.parse(""), "344472003047574", "Lakeside Drive", "Santa Clara", "CA", "95054", "mike@waltz.com");
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getCustId() {
    return custId;
  }

  public void setCustId(String custId) {
    this.custId = custId;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public String getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(String orderStatus) {
    this.orderStatus = orderStatus;
  }

  public Date getShipDate() {
    return shipDate;
  }

  public void setShipDate(Date shipDate) {
    this.shipDate = shipDate;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

  public String getCreditCardNumber() {
    return creditCardNumber;
  }

  public void setCreditCardNumber(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }

  @Override
  public String toString() {
    return "Order{" +
        "orderId='" + orderId + '\'' +
        ", custId='" + custId + '\'' +
        ", orderDate=" + orderDate +
        ", orderStatus='" + orderStatus + '\'' +
        ", shipDate=" + shipDate +
        ", creditCardNumber='" + creditCardNumber + '\'' +
        ", street='" + street + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", zipCode='" + zipCode + '\'' +
        ", emailAddress='" + emailAddress + '\'' +
        '}';
  }
}
