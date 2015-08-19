package com.goodle.mapia.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.goodle.mapia.addPost.AddPostFragment;
import com.goodle.mapia.alert.AlertFragment;
import com.goodle.mapia.groupMap.GroupMapFragment;
import com.goodle.mapia.myMap.MyMapFragment;
import com.goodle.mapia.newsFeed.NewsFeedFragment;
import com.goodle.mapia.R;


public class HomeActivity extends FragmentActivity implements View.OnClickListener{

    int currentFragmentIndex = 0;
    public final static int FragmentAddPost = 0;
    public final static int FragmentAlert = 1;
    public final static int FragmentGroupMap = 2;
    public final static int FragmentMyMap = 3;
    public final static int FragmentNewsFeed = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn_add_post = (Button)findViewById(R.id.btn_add_post);
        btn_add_post.setOnClickListener(this);
        Button btn_alert = (Button)findViewById(R.id.btn_alert);
        btn_alert.setOnClickListener(this);
        Button btn_group_map = (Button)findViewById(R.id.btn_group_map);
        btn_group_map.setOnClickListener(this);
        Button btn_my_map = (Button)findViewById(R.id.btn_my_map);
        btn_my_map.setOnClickListener(this);
        Button btn_news_feed = (Button)findViewById(R.id.btn_news_feed);
        btn_news_feed.setOnClickListener(this);

        fragmentReplace(currentFragmentIndex);
    }

    private void fragmentReplace(int newFragmentIndex){
        Fragment newFragment = getFragment(newFragmentIndex);
        final FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.ll_fragment, newFragment);
        transaction.commit();
    }

    private Fragment getFragment(int newFragmentIndex){
        Fragment newFragment = null;
        switch(newFragmentIndex){
            case FragmentAddPost:
                newFragment = new AddPostFragment();
                break;
            case FragmentAlert:
                newFragment = new AlertFragment();
                break;
            case FragmentGroupMap:
                newFragment = new GroupMapFragment();
                break;
            case FragmentMyMap:
                newFragment = new MyMapFragment();
                break;
            case FragmentNewsFeed:
                newFragment = new NewsFeedFragment();
                break;
        }
        return newFragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_post:
                currentFragmentIndex = FragmentAddPost;
                fragmentReplace(currentFragmentIndex);
                break;
            case R.id.btn_alert:
                currentFragmentIndex = FragmentAlert;
                fragmentReplace(currentFragmentIndex);
                break;
            case R.id.btn_group_map:
                currentFragmentIndex = FragmentGroupMap;
                fragmentReplace(currentFragmentIndex);
                break;
            case R.id.btn_my_map:
                currentFragmentIndex = FragmentMyMap;
                fragmentReplace(currentFragmentIndex);
                break;
            case R.id.btn_news_feed:
                currentFragmentIndex = FragmentNewsFeed;
                fragmentReplace(currentFragmentIndex);
                break;
        }
    }
}
