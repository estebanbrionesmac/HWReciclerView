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
public class AddressDBH  extends SQLiteOpenHelper {

    // All Static variables
    // Database Version

    private static final int DATABASE_VERSION = 1;

    // Database Name

    private static final String DATABASE_NAME = "SQLLiteHW01" ;

    // addresses table name

    private static final String TABLE_ADDRESSES = "addresses";

    // addresses Table Columns names

    private static final String KEY_ID = "id";

    private static final String KEY_STREET = "street";

    private static final String KEY_STREET2 = "street2";

    private static final String KEY_CITY = "city";

    private static final String KEY_STATE = "state";

    private static final String KEY_ZIP = "zip";



    public AddressDBH (Context context) {
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




    public static void onCreateSupport (SQLiteDatabase db) {
        String CREATE_addresses_TABLE = "CREATE TABLE " + TABLE_ADDRESSES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_STREET + " TEXT,"
                + KEY_STREET2 + " TEXT ,"
                + KEY_CITY + " TEXT ,"
                + KEY_STATE + " TEXT ,"
                + KEY_ZIP + " INTEGER"
                + ")";

        try {
            db.execSQL(CREATE_addresses_TABLE);

            Log.d("EJBM DB", "Table created: " + TABLE_ADDRESSES);
        } catch ( Exception ex ) {
            Log.d("EJBM DB E", ex.getMessage() );
        }
    }


    // Upgrading database


    public static void onUpgradeSupport(SQLiteDatabase db, int oldVersion, int newVersion) {
/*        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESSES);

        // Create tables again
        onCreateSupport(db);*/
    }

    // Adding new Address
    public void addAddress(Address address) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put ( KEY_STREET, address.getStreet() );
            values.put ( KEY_STREET2, address.getStreet2());
            values.put ( KEY_CITY, address.getCity() );
            values.put ( KEY_STATE, address.getState() );
            values.put ( KEY_ZIP, address.getZip() );

        // Inserting Row
        db.insert(TABLE_ADDRESSES, null, values);
        db.close(); // Closing database connection
    }

    // Getting single Address
    public Address getAddress(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ADDRESSES, new String[] { KEY_ID, KEY_STREET, KEY_STREET2, KEY_CITY, KEY_STATE, KEY_ZIP }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return loadAddress (cursor);
    }

    private Address loadAddress ( Cursor cursor  ) {

        Address Address = new Address( cursor.getString(0)
                , cursor.getString(1)
                , cursor.getString(2)
                , cursor.getString(3)
                , Integer.parseInt(cursor.getString(4)) );

        return Address ;
    }

    // Getting All addresses
    public List<Address> getAllAddresses() {
        List<Address> AddressList = new ArrayList<Address>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ADDRESSES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Address Address = null ;

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Address = loadAddress( cursor ) ;

                // Adding Address to list
                AddressList.add(Address);

            } while (cursor.moveToNext());
        }

        // return Address list
        return AddressList;
    }

    // Getting addresses Count
    public int getAddressesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ADDRESSES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    // Updating single Address
    public int updateAddress(Address address) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
            values.put ( KEY_STREET, address.getStreet() );
            values.put ( KEY_STREET2, address.getStreet2());
            values.put ( KEY_CITY, address.getCity() );
            values.put ( KEY_STATE, address.getState() );
            values.put ( KEY_ZIP, address.getZip() );

        // updating row
        return db.update(TABLE_ADDRESSES, values, KEY_ID + " = ?", new String[] { String.valueOf(address.getID()) });
    }

    // Deleting single Address
    public void deleteAddress(Address Address) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ADDRESSES, KEY_ID + " = ?", new String[] { String.valueOf(Address.getID()) });
        db.close();
    }
}