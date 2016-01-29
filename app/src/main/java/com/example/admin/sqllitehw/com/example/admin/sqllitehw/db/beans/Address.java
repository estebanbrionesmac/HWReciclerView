package com.example.admin.sqllitehw.db.beans;

/**
 * Created by admin on 1/16/2016.
 */
public class Address {
    
    private int _id;
    private String _street ; 
    private String _street2 ; 
    private String _city ;
    private String _state ;
    private int _zip ;

    public Address(String _street, String _street2, String _city, String _state, int _zip) {
        this._street = _street;
        this._street2 = _street2;
        this._city = _city;
        this._state = _state;
        this._zip = _zip;
    }

    public Address(int id , String _street, String _street2, String _city, String _state, int _zip) {
        this._id = id ;
        this._street = _street;
        this._street2 = _street2;
        this._city = _city;
        this._state = _state;
        this._zip = _zip;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getID () { return getId(); }

    public String getStreet() {
        return _street;
    }

    public void setStreet(String _street) {
        this._street = _street;
    }

    public String getStreet2() {
        return _street2;
    }

    public void setstreet2(String _street2) {
        this._street2 = _street2;
    }

    public String getCity() {
        return _city;
    }

    public void setCity(String _city) {
        this._city = _city;
    }

    public String getState() {
        return _state;
    }

    public void setState(String _state) {
        this._state = _state;
    }

    public int getZip() {
        return _zip;
    }

    public void setZip(int _zip) {
        this._zip = _zip;
    }

    public String toString () {
        return this._street + ", " + this._street2 + ", " + this._city + ", " + this._state + ", " + this._zip ;

    }
}
