package com.example.admin.sqllitehw.db.beans;

/**
 * Created by admin on 1/16/2016.
 */
public class Contact {

    //private variables
    int _id;
    String _name;
    String _phone_number;
    String _email ;
    int _companyID ;

    // Empty constructor
    public Contact(){

    }
    // constructor
    public Contact(int id, String name, String _phone_number, String email, int coID ){
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
        this._email = email ;
        this._companyID = coID ;
    }

    // constructor
    public Contact(String name, String _phone_number){
        this._name = name;
        this._phone_number = _phone_number;
    }

    public Contact( String name, String _phone_number, String email, int coID ){

        this._name = name;
        this._phone_number = _phone_number;
        this._email = email ;
        this._companyID = coID ;
    }

    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting phone number
    public String getPhoneNumber(){
        return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }

    public int getCompanyID() {
        return _companyID;
    }

    public void setCompanyID(int _companyID) {
        this._companyID = _companyID;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public String toString () {
        return this.getID() + " >> " + this.getName() + "  [ " + this.getPhoneNumber() + " ]" ;

    }
}