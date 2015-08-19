package com.goodle.mapia.myMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.goodle.mapia.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

/**
 * Created by JahyunKim on 15. 8. 16..
 */
public class MyMapFragment extends Fragment {
           // GoogleMap gMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_map, container, false);
        Toast.makeText(getActivity(), "MyMapFragment", Toast.LENGTH_SHORT).show();

        //SupportMapFragment supportMapFragment = (SupportMapFragment)getFragmentManager().findFragmentById(R.id.map);
        //gMap = supportMapFragment.getMap();
        //drawColorBlock();
        return v;
    }
}
