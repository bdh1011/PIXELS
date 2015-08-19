package com.goodle.mapia.alert;

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
public class AlertFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_alert, container, false);
        Toast.makeText(getActivity(), "AlertFragment", Toast.LENGTH_SHORT).show();
        return v;
    }
}
