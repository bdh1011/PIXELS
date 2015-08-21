package com.goodle.mapia.myMap;

import com.goodle.mapia.common.BaseMapFragment;
import com.goodle.mapia.home.HomeActivity;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

/**
 * Created by JahyunKim on 15. 8. 16..
 */
public class MyMapFragment extends BaseMapFragment {

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_my_map, container, false);
//        Toast.makeText(getActivity(), "MyMapFragment", Toast.LENGTH_SHORT).show();
//
//        SupportMapFragment supportMapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
//        gMap = supportMapFragment.getMap();
//        drawColorBlock();
//
//        return v;
//    }

    private void drawColorBlock(){
        ArrayList<PolygonOptions> blocks = HomeActivity.getMyBlocks();
        for(int i=0;i<blocks.size();i++){
            gMap.addPolygon(blocks.get(i));
        }
    }


}


/* 675/2^(zoom+5) -> width*/