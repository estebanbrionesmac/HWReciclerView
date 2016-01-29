package com.example.admin ;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MyActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("EJBM ", "starting..." ) ;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("EJBM ", "" + mRecyclerView) ;

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        Log.d("EJBM ", "" +  mRecyclerView ) ;

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList <String> al = new ArrayList <String> () ;
            al.add( "1");
            al.add( "2");
            al.add( "3");
            al.add( "4");
            al.add( "5");
            al.add( "6");

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter( /* al */ null  );
        mRecyclerView.setAdapter(mAdapter);
    }

}