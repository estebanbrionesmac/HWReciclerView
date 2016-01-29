package com.example.admin.sqllitehw.db.dbh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.admin.sqllitehw.db.beans.* ;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 1/16/2016.
 */
public class CompanyDBH extends SQLiteOpenHelper {

    // All Static variables
    // Database Version

    private static final int DATABASE_VERSION = 1;

    // Database Name

    private static final String DATABASE_NAME = "SQLLiteHW01" ;

    // Companys table name

    private static final String TABLE_COMPANIES = "Companies";

    // Companys Table Columns names

    private static final String KEY_ID = "id";

    private static final String KEY_NAME = "name";

    private static final String KEY_URL = "url";

    private static final String KEY_EMPLOYEES = "employees";


    public CompanyDBH (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //super();
    }



    @Override
    public  void onCreate (SQLiteDatabase db) {
        onCreateSupport(db);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgradeSupport(db, oldVersion, newVersion);

    }


    public static void onCreateSupport(SQLiteDatabase db) {
        String CREATE_CompanyS_TABLE = "CREATE TABLE " + TABLE_COMPANIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_URL + " TEXT ,"
                + KEY_EMPLOYEES + " INTEGER"
                + ")";

        try {
            db.execSQL(CREATE_CompanyS_TABLE);

            Log.d("EJBM DB", "Table created: " + TABLE_COMPANIES);
        } catch ( Exception ex ) {
            Log.d("EJBM DB E", ex.getMessage() );
        }
    }

    // Upgrading database


    public static void onUpgradeSupport(SQLiteDatabase db, int oldVersion, int newVersion) {
/*        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANIES);

        // Create tables again
        onCreateSupport(db);*/
    }

    // Adding new company
    public void addCompany(Company company) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put ( KEY_NAME, company.getName() ); // Company Name
            values.put ( KEY_URL, company.getUrl() );
            values.put ( KEY_EMPLOYEES, company.getEmployees() );

        // Inserting Row
        db.insert(TABLE_COMPANIES, null, values);
        db.close(); // Closing database connection
    }

    // Getting single company
    public Company getCompany(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_COMPANIES, new String[] { KEY_ID, KEY_NAME, KEY_URL, KEY_EMPLOYEES}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return loadCompany(cursor);
    }

    private Company loadCompany ( Cursor cursor  ) {

        Company company = new Company(Integer.parseInt(cursor.getString(0))
                , cursor.getString(1)
                , cursor.getString(2)
                , Integer.parseInt(cursor.getString(3)) );

        return company ;
    }

    // Getting All Companys
    public List<Company> getAllCompanies() {
        List<Company> companyList = new ArrayList<Company>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_COMPANIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Company company = null ;

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                company = loadCompany(cursor) ;

                // Adding company to list
                companyList.add(company);

            } while (cursor.moveToNext());
        }

        // return company list
        return companyList;
    }

    // Getting companys Count
    public int getCompanysCount() {
        String countQuery = "SELECT * FROM " + TABLE_COMPANIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    // Updating single company
    public int updateCompany(Company company) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put ( KEY_NAME, company.getName());
        values.put ( KEY_URL, company.getUrl());
        values.put ( KEY_EMPLOYEES, company.getEmployees() );

        // updating row
        return db.update(TABLE_COMPANIES, values, KEY_ID + " = ?", new String[] { String.valueOf(company.getID()) });
    }

    // Deleting single company
    public void deleteCompany(Company company) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COMPANIES, KEY_ID + " = ?", new String[] { String.valueOf(company.getID()) });
        db.close();
    }
}