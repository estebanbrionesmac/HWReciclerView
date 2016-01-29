package com.example.admin ;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import com.example.admin.sqllitehw.db.dbh.* ;
import com.example.admin.sqllitehw.db.beans.* ;
import com.example.admin.* ;


public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("EJBM ", "starting...") ;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("EJBM ", "" + mRecyclerView) ;

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        Log.d("EJBM ", "" + mRecyclerView) ;

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation (  LinearLayoutManager.VERTICAL );
        mRecyclerView.setLayoutManager(mLayoutManager);



        ArrayList<String> al = new ArrayList <String> () ;
            al.add( "1" );
            al.add( "2" );
            al.add( "3" );
            al.add("4");
            al.add( "5" );
            al.add("6");
            al.add( "7" );
            al.add( "8" );
            al.add( "9" );
            al.add( "10" );

        ContactDBH cdbh = new ContactDBH( this ) ;
        cdbh.addContact(new Contact("Esteban Briones", "+52 1 55 4453 9832", "esteban.brionesmac@gmail.com", 1));
        cdbh.addContact( new Contact ( "Jose Luis", "123456789", "a@b.c", 0 ));
        cdbh.addContact( new Contact ( "Miguel", "756695789", "a@b.c", 0 ));
        cdbh.addContact( new Contact ( "Antonio", "123898249", "a@b.c", 0 ));
        cdbh.addContact( new Contact ( "Jorge", "984156789", "a@b.c", 0 ));
        cdbh.addContact( new Contact ( "Taral" , "415879636", "a@b.c", 0 ));
        cdbh.addContact( new Contact ( "Nitin" , "124785199", "a@b.c", 0 ));



        // specify an adapter (see also next example)
        mAdapter = new MyAdapter( /*al*/ cdbh.getAllContacts ()  );
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addContact ( View view ) {
        Intent i = new Intent(this, com.example.admin.ContactsAddActivity.class);
        startActivity(i);
    }

}