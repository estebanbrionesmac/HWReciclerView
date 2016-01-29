package com.example.admin.sqllitehw.db.beans;

/**
 * Created by admin on 1/16/2016.
 */
public class Company {

    private int _id ;
    private String _name ;
    private String _url  ;
    private int _employees ; 
    
   
    public Company () {

    }

    public Company(int _id, String _name) {
        this._id = _id;
        this._name = _name;
    }

    public Company(int _id, String _name, String _url, int _employees) {
        this._id = _id;
        this._name = _name;
        this._url = _url;
        this._employees = _employees;
    }

    public Company( String _name, String _url, int _employees) {
        this._id = _id;
        this._name = _name;
        this._url = _url;
        this._employees = _employees;
    }

    public int getId() {
        return _id;
    }

    public int getID () { return getId(); }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String _url) {
        this._url = _url;
    }

    public int getEmployees() {
        return _employees;
    }

    public void setEmployees(int _employees) {
        this._employees = _employees;
    }

    public String toString () {
        return "[" + this._id + "] " + this._name ;
    }
}
