package com.dev.dormotory.Utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.dev.dormotory.Obj.Point;
import com.dev.dormotory.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class BottomSheetInfo {
    Activity activity;
    BottomSheetBehavior bottomSheetBehavior;
    RelativeLayout view;
    TextView adress,name_point,description;
    CardView button_route;
    public BottomSheetInfo(Activity activity){
        this.activity = activity;
        view = activity.findViewById(R.id.bottom_sheet_info);
        bottomSheetBehavior = BottomSheetBehavior.from(view);

        adress = view.findViewById(R.id.adress);
        name_point = view.findViewById(R.id.name_point);
        description = view.findViewById(R.id.description);
        button_route = view.findViewById(R.id.button_route);
    }
    public void Open(Point point,String color){
        adress.setText(point.getAdress());
        name_point.setText(point.getName());
        if(!point.getComment().equals("")){
            description.setText("Описание: "+point.getComment());
            description.setVisibility(View.VISIBLE);
        }else{
            description.setVisibility(View.GONE);
        }
        button_route.setCardBackgroundColor(Color.parseColor(color));
        button_route.setOnClickListener(v->{
            setRouteIntent(point);
        });
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
    public void Close(){
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
    private void setRouteIntent(Point point){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=" + point.getLat()+ ","
                        + point.getLng()));
        activity.startActivity(intent);
    }


}
