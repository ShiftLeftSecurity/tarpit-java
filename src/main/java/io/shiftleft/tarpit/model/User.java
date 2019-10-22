package io.shiftleft.tarpit.model;

import java.io.Serializable;
import io.shiftleft.tarpit.annotation.*;

public class User extends BaseModel implements Serializable {

  @SensitiveBeacon
  private String userName;
  @SensitiveBeacon
  private String firstName;
  @SensitiveBeacon
  private String lastName;

  @SensitiveRedact
  private String passportNumber;

  private String address1;
  private String address2;

  @SensitiveRedact
  private String zipCode;

  public User(String userName, String firstName, String lastName, String passportNumber,
      String address1, String address2, String zipCode) {
    this.userName = userName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.passportNumber = passportNumber;
    this.address1 = address1;
    this.address2 = address2;
    this.zipCode = zipCode;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassportNumber() {
    return passportNumber;
  }

  public void setPassportNumber(String passportNumber) {
    this.passportNumber = passportNumber;
  }

  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }


}
