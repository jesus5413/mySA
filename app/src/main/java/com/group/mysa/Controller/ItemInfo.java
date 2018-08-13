package com.group.mysa.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toolbar;

import com.group.mysa.Model.FeedInfo;
import com.group.mysa.R;

public class ItemInfo extends AppCompatActivity {
    android.support.v7.widget.Toolbar mToolBar;
    TextView item_info_decription;
    TextView item_info_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        mToolBar = (android.support.v7.widget.Toolbar) findViewById(R.id.item_info_app_bar);
        setSupportActionBar(mToolBar);



        Intent i = getIntent();
        FeedInfo postInfo = (FeedInfo) i.getSerializableExtra("FeedInfo");
        System.out.println(postInfo.getTitle());
        item_info_decription = (TextView) findViewById(R.id.item_info_description);
        item_info_decription.setText("Description: " + postInfo.getDescription());

        item_info_title = (TextView) findViewById(R.id.item_info_title);
        item_info_title.setText(postInfo.getTitle());

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu2, menu);
        return true;
    }


}
