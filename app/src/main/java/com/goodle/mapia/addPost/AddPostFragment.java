package com.goodle.mapia.addPost;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.goodle.mapia.R;
import com.goodle.mapia.custom.HighlightEditText;
import com.goodle.mapia.home.HomeActivity;
import com.goodle.mapia.volley.MapiaRequest;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JahyunKim on 15. 8. 16..
 */
public class AddPostFragment extends Fragment implements View.OnClickListener{
    TextView txt_center_loc, txt_rect_size;
    private RelativeLayout mBtnUpload;
    private HighlightEditText mEdtPost;

    private LinearLayout btn_place_picker;
    private View mRootView;
    //private static int LAUNCH_PlacePicker = 1;
    private static int REQUEST_LOCATION_PICKER = 1;
    private View.OnClickListener mBtnUploadClickListener;
    private LatLng location;


    public AddPostFragment(){
        super();



//
//                if (mainActivity.mainApplication.getPostActivity().mFileUriList.size() > 0){
//                    for(Uri eachUri : mainActivity.mainApplication.getPostActivity().mFileUriList) {
//                        TransferController.upload(getActivity().getApplicationContext(), eachUri);
//
//                        String uriString = eachUri.toString();
//                        mainActivity.mainApplication.getPostActivity().mFileNameList.add(Util.getFileName(uriString));
//                        Log.i("file", uriString);
//                        Log.i("file", mainActivity.mainApplication.getPostActivity().mFileNameList.toString());
//                    }
//                    requestHelper.posts(
//                            mapType, postComment, postLatlng, mainActivity.mainApplication.getPostActivity().mFileNameList, new Callback<JsonObject>() {
//                                @Override
//                                public void success(JsonObject jsonObject, retrofit.client.Response response) {
//                                    Toast.makeText(getActivity(), "Post 등록 성공".toString(), Toast.LENGTH_LONG).show();
////                                    ((MainApplication) getActivity().getApplication()).getMapPublicFragment();
//                                    getActivity().finish();
//                                }
//
//                                @Override
//                                public void failure(RetrofitError error) {
//                                    Log.i("Post 등록 실패", error.getMessage().toString());
//                                    Toast.makeText(getActivity(), "Post 등록 실패".toString(), Toast.LENGTH_LONG).show();
//
//                                }
//                            });
//
//                }
//                else {
//                    requestHelper.posts(
//                            mapType, postComment, postLatlng, new Callback<JsonObject>() {
//                                @Override
//                                public void success(JsonObject jsonObject, retrofit.client.Response response) {
//                                    Toast.makeText(getActivity(), "Post 등록 성공".toString(), Toast.LENGTH_LONG).show();
////                                    ((MainApplication) getActivity().getApplication()).getMapPublicFragment();
//                                    getActivity().finish();
//                                }
//
//                                @Override
//                                public void failure(RetrofitError error) {
//                                    Log.i("Post 등록 실패", error.getMessage().toString());
//                                    Toast.makeText(getActivity(), "Post 등록 실패".toString(), Toast.LENGTH_LONG).show();
//
//                                }
//                            });
//                }
//            }
//        };

    }
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
    }

    public void updateLocation(LatLng location){
        this.location = location;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_add_post, container, false);
        Toast.makeText(getActivity(), "AddPostFragment", Toast.LENGTH_SHORT).show();
        initialize();
        return mRootView;
    }

    private void initialize(){
        initView();
        setEventListener();
    }

    private void initView() {
        btn_place_picker = (LinearLayout)mRootView.findViewById(R.id.btn_place_picker);

        txt_center_loc = (TextView)mRootView.findViewById(R.id.txt_center_loc);
        txt_rect_size = (TextView)mRootView.findViewById(R.id.txt_rect_size);
        mEdtPost = (HighlightEditText)mRootView.findViewById(R.id.edit_post);
        mBtnUpload = (RelativeLayout)mRootView.findViewById(R.id.btnPostSubmit);

    }
    private void setEventListener() {
        btn_place_picker.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_LOCATION_PICKER){
            if(resultCode==Activity.RESULT_OK){
                Bundle bundle = data.getParcelableExtra("bundle");
                location = bundle.getParcelable("location");
                float zoom = bundle.getFloat("zoom");
                int color = bundle.getInt("color");
                Toast.makeText(this.getActivity(), "location pick success", Toast.LENGTH_LONG).show();
                txt_center_loc.setText(location.latitude + "/" + location.longitude + "/" + zoom);
                txt_rect_size.setText(color + "");

                double len = 315/Math.pow(2,zoom+5);
                PolygonOptions options = new PolygonOptions();
                options.addAll(createRectangle(new LatLng(location.latitude, location.longitude),len,len));
                options.fillColor(color);
                options.strokeWidth(0);
                HomeActivity.addMyBlocks(options);
            }
        }
    }

    private List<LatLng> createRectangle (LatLng center, Double halfWidth, Double halfHeight) {
        return Arrays.asList(new LatLng(center.latitude - halfHeight, center.longitude - halfWidth),
                new LatLng (center.latitude - halfHeight, center.longitude + halfWidth),
                new LatLng (center.latitude + halfHeight, center.longitude + halfWidth),
                new LatLng (center.latitude + halfHeight, center.longitude - halfWidth),
                new LatLng (center.latitude - halfHeight, center.longitude - halfWidth));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_place_picker:
                Intent intent = new Intent(this.getActivity(), LocationPickerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("current_location", HomeActivity.getCurrentLatlng());
                bundle.putFloat("current_zoom",16);
                intent.putExtra("bundle",bundle);
                startActivityForResult(intent,REQUEST_LOCATION_PICKER);
                break;

            case R.id.btnPostSubmit:
                SharedPreferences prefs = getActivity().getSharedPreferences("Location", getActivity().MODE_PRIVATE);
                Float lat = prefs.getFloat("latitude", 0.1f);
                Float lng = prefs.getFloat("longitude", 0.1f);

                final LatLng postLatLng = new LatLng(lat, lng);
                final String postComment = mEdtPost.getText().toString();
                String mapType = "public";
                String postContent = mEdtPost.getText().toString();
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("mapType", mapType);
                params.put("postContent", postContent);
                params.put("postLatLng", postLatLng.toString());

                MapiaRequest mapiaRequest = new MapiaRequest("/post", new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                });

        }
    }
}
