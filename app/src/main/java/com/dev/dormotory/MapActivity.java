package com.dev.dormotory;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.dormotory.Obj.Category;
import com.dev.dormotory.Obj.Point;
import com.dev.dormotory.Utils.BottomSheet;
import com.dev.dormotory.Utils.BottomSheetInfo;
import com.dev.dormotory.Utils.CustomPoint;
import com.dev.dormotory.Utils.Utils;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

public class MapActivity extends AppCompatActivity {

    BottomSheet bottomSheet;
    BottomSheetInfo bottomSheetInfo;
    GeoPoint point_Home;
    View openBottom,body_name,home;
    MapView map;
    TextView name_select;
    CustomPoint customPoint;
    Category active_category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_map);
        findViewById(R.id.back).setOnClickListener(v->{
            onBackPressed();
            finish();
        });
        openBottom = findViewById(R.id.selectCategory);
        body_name = findViewById(R.id.names_select_body);
        name_select = findViewById(R.id.names_select);
        home = findViewById(R.id.home);
        bottomSheetInfo = new BottomSheetInfo(this);

        bottomSheet = new BottomSheet(this);

        home.setOnClickListener(v->{
            map.getController().animateTo(point_Home);
        });
        bottomSheet.setOnClickResult((category) -> {
            active_category = category;
            name_select.setText(category.getName());
            initPoints(category);
            animateView(1,null,body_name,home);
            map.getController().zoomTo(14);
            map.getController().animateTo(point_Home);
        });
        openBottom.setOnClickListener(v->{
            bottomSheet.Open();
            animateView(0,null,body_name,home);
        });
        bottomSheet.setOnClosed(()->{
            if (active_category != null)
            animateView(1,null,body_name,home);
        });
        bottomSheet.Open();
        content();


    }
    private void animateView(float alpha, BottomSheet.OnEventListener onEventListener, View... view) {
        int  i = 0;

        for (View v:view) {
            int finalI = i;
            v.animate().alpha(alpha).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    if(finalI == view.length-1 && onEventListener != null)
                        onEventListener.OnEnd();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            }).start();
            i+=1;
        }

    }
    private void initPoints(Category category){
        customPoint.clear();
        map.invalidate();
        customPoint.addItem(point_Home, "You",
                "G",getCustomDrawable(R.drawable.ic_home_run, "#ffd700"));
        if(category.getPoints().size() > 1) {
            for (Point p : category.getPoints()) {
                Log.e("KKK", "initPoints: " + p.getLat() + " " + p.getLng());
                customPoint.addItem(new GeoPoint(p.getLat(), p.getLng()), "Point",
                        "G", getCustomDrawable(category.getDrawable_id(), category.getColor_id()));
            }
        }else{
            Point p = category.getPoints().get(0);
            customPoint.addItem(new GeoPoint(p.getLat(), p.getLng()), "Point",
                    "G", getCustomDrawable(category.getDrawable_id(), category.getColor_id()));
            map.getController().animateTo(new GeoPoint(p.getLat(),p.getLng()));
        }
        map.invalidate();
    }
    private void content() {
        map = findViewById(R.id.map);
        point_Home = new GeoPoint(59.94134181, 30.46782053);
        map.setTileSource(new XYTileSource("MapQuest" , 0 , 20 , 256 ,
                ".png" , new String[] {"http://a.tiles.maps.sputnik.ru/","http://b.tiles.maps.sputnik.ru/",
                "http://c.tiles.maps.sputnik.ru/"}));
        map.setBuiltInZoomControls(false);
        map.setMultiTouchControls(true);
        MapController mapController = (MapController) map.getController();
        mapController.setZoom(14);
        map.setMaxZoomLevel(20d);
        map.setMinZoomLevel(10d);
        mapController.setCenter(point_Home);
        customPoint = new CustomPoint(getCustomDrawable(R.drawable.ic_home_run, "#000000"), this,mapController, map);
        customPoint.addItem(point_Home, "You",
                "G",getCustomDrawable(R.drawable.ic_home_run, "#ffd700"));
        customPoint.setOnClickListener((index, item) -> {
           if(index == 0){
              Toast.makeText(this, "Общежитие", Toast.LENGTH_SHORT).show();
              bottomSheetInfo.Close();
           }else{
               if(!bottomSheet.isOpen())
               bottomSheetInfo.Open(active_category.getPoints().get(index-1),active_category.getColor_id());
           }

        });

        map.getOverlays().add(customPoint);
    }
    private Drawable getCustomDrawable(int id_drawable, String color_id){
        Utils utils = new Utils(this);
        int color = Color.parseColor(color_id);
        GradientDrawable gD = new GradientDrawable();
        gD.setColor(color);
        gD.setShape(GradientDrawable.OVAL);
        Drawable drawable = getResources().getDrawable(id_drawable);
        Drawable[] layers = new Drawable[2];
        layers[0] = gD;
        layers[1] = drawable;
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(1, (int)utils.dpToPixels(5), (int)utils.dpToPixels(5), (int)utils.dpToPixels(5), (int)utils.dpToPixels(5));
        return utils.UpScale(layerDrawable,0.7f);
    }
}
