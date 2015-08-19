package com.goodle.mapia;

import android.app.Application;
import android.content.Context;

import com.goodle.mapia.addPost.LocationPickerActivity;
import com.goodle.mapia.home.HomeActivity;

/**
 * Created by daehyun on 15. 8. 19..
 */
public class MainApplication extends Application {
    private static Context context;
    private static MainApplication mainApplication;
    private HomeActivity homeActivity;

    private LocationPickerActivity locationPickerAcitivty;

    public static MainApplication getInstance(){
        synchronized (MainApplication.class) {

            return mainApplication;
        }
    }

    public static Context getContext(){
        return context;
    }

}
