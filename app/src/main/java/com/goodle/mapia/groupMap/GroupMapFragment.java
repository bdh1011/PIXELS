package com.goodle.mapia.groupMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.goodle.mapia.R;

/**
 * Created by JahyunKim on 15. 8. 16..
 */
public class GroupMapFragment extends Fragment {
    static View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_group_map, container, false);
        Toast.makeText(getActivity(), "GroupMapFragment", Toast.LENGTH_SHORT).show();
        return v;
    }
}
