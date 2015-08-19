package com.goodle.mapia.volley;

/**
 * Created by daehyun on 15. 6. 13..
 */

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.NoCache;

import org.apache.http.util.TextUtils;


public class MapiaVolley
{
    private static RequestQueue requestQueue;
    public static final String TAG = "MAPIA Volley";

    public static RequestQueue getRequestQueue() {
        if (MapiaVolley.requestQueue == null) {
            throw new IllegalStateException("RequestQueue is not initialized.");
        }
        return MapiaVolley.requestQueue;
    }

    public static void init(final Context context) {
        if (context == null) {
            throw new NullPointerException("context must not be null.");
        }
        (MapiaVolley.requestQueue = new RequestQueue(new NoCache(), new BasicNetwork(new HurlStack()))).start();
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        VolleyLog.d("Adding request to queue: %s", req.getUrl());

        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);

        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (MapiaVolley.requestQueue != null) {
            MapiaVolley.requestQueue.cancelAll(tag);
        }
    }

}