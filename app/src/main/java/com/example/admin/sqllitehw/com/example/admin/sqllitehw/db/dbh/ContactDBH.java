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
public class ContactDBH  extends SQLiteOpenHelper {

        // All Static variables
        // Database Version

        private static final int DATABASE_VERSION = 6 ;

        // Database Name

        private static final String DATABASE_NAME = "SQLLiteHW02" ;

        // Contacts table name

        private static final String TABLE_CONTACTS = "contacts";

        // Contacts Table Columns names

        private static final String KEY_ID = "id";

        private static final String KEY_NAME = "name";

        private static final String KEY_PH_NO = "phone_number";

        private static final String KEY_EMAIL = "email";

        private static final String KEY_COMPANY_ID = "companyId";



        public ContactDBH (Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            //super();

        }


        // Creating Tables

        @Override
        public void onCreate(SQLiteDatabase db) {

/*            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

            db.execSQL("DROP TABLE IF EXISTS Companies " );

            db.execSQL("DROP TABLE IF EXISTS ADDRESSES" );*/


            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                    + KEY_ID + " INTEGER PRIMARY KEY,"
                    + KEY_NAME + " TEXT,"
                    + KEY_PH_NO + " TEXT ,"
                    + KEY_EMAIL + " TEXT ,"
                    + KEY_COMPANY_ID + " INTEGER"
                    + ")";
            db.execSQL(CREATE_CONTACTS_TABLE);

            Log.d("EJBM DB", "\tTable created: " + TABLE_CONTACTS);

/*            String CREATE_CompanyS_TABLE = "CREATE TABLE Companies ("
                    + KEY_ID + " INTEGER PRIMARY KEY,"
                    + KEY_NAME + " TEXT,"
                    + "url  TEXT ,"
                    + "EMPLOYEES  INTEGER"
                    + ")";
            db.execSQL(CREATE_CompanyS_TABLE);

            Log.d("EJBM DB", "\tTable created: Companies" );

            String CREATE_addresses_TABLE = "CREATE TABLE ADDRESSES ("
                    + KEY_ID + " INTEGER PRIMARY KEY,"
                    + " STREET  TEXT,"
                    + " STREET2  TEXT ,"
                    + " CITY  TEXT ,"
                    + " STATE  TEXT ,"
                    + " ZIP INTEGER"
                    + ")";
            db.execSQL(CREATE_addresses_TABLE);

            Log.d("EJBM DB", "\tTable created: ADDRESSES " );
*/
            //CompanyDBH.onCreateSupport(db);

            // AddressDBH.onCreateSupport ( db );


        }


        // Upgrading database

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

            // Create tables again
            onCreate(db);

            CompanyDBH.onUpgradeSupport(db, oldVersion, newVersion ) ;

            AddressDBH.onUpgradeSupport(db, oldVersion, newVersion ) ;
        }

        // Adding new contact
        public void addContact(Contact contact) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
                values.put ( KEY_NAME, contact.getName() ); // Contact Name
                values.put ( KEY_PH_NO, contact.getPhoneNumber( )); // Contact Phone Number
                values.put ( KEY_EMAIL, contact.getEmail() );
                values.put(KEY_COMPANY_ID, contact.getCompanyID());

            // Inserting Row
            db.insert(TABLE_CONTACTS, null, values);
            db.close(); // Closing database connection
        }

        // Getting single contact
        public Contact getContact(int id) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_NAME, KEY_PH_NO, KEY_EMAIL, KEY_COMPANY_ID }, KEY_ID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();

            return loadContact (cursor);
        }

        private Contact loadContact ( Cursor cursor  ) {

            Contact contact = new Contact(Integer.parseInt(cursor.getString(0))
                    , cursor.getString(1)
                    , cursor.getString(2)
                    , cursor.getString(3)
                    , Integer.parseInt(cursor.getString(4)) );

            return contact ;
        }

        // Getting All Contacts
        public ArrayList <Contact> getAllContacts() {
            ArrayList <Contact> contactList = new ArrayList<Contact>();
            // Select All Query
            String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            Contact contact = null ;

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    contact = loadContact( cursor ) ;

                    // Adding contact to list
                    contactList.add(contact);

                } while (cursor.moveToNext());
            }

            // return contact list
            return contactList;
        }

        // Getting contacts Count
        public int getContactsCount() {
            String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();

            // return count
            return cursor.getCount();
        }


        // Updating single contact
        public int updateContact(Contact contact) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put ( KEY_NAME, contact.getName());
            values.put ( KEY_PH_NO, contact.getPhoneNumber());
            values.put ( KEY_EMAIL, contact.getEmail() );
            values.put(KEY_COMPANY_ID, contact.getCompanyID());

            // updating row
            return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?", new String[] { String.valueOf(contact.getID()) });
        }

        // Deleting single contact
        public void deleteContact(Contact contact) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_CONTACTS, KEY_ID + " = ?", new String[] { String.valueOf(contact.getID()) });
            db.close();
        }
    }