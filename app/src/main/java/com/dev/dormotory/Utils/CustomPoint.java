package com.dev.dormotory.Utils;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.dev.dormotory.R;

import org.osmdroid.api.IMapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class CustomPoint extends ItemizedOverlay<OverlayItem> {
    Context context;
    private ArrayList<OverlayItem> overlayItemList = new ArrayList<OverlayItem>();
    private OnClickListener onClickListener;
    private MapView mapView;
    private MapController mapController;
    public CustomPoint(Drawable pDefaultMarker, Context context, MapController mapController, MapView mapView) {
        super(context,pDefaultMarker);
        this.context = context;
        this.mapView = mapView;
        this.mapController = mapController;

        // TODO Auto-generated constructor stub
    }

    public OverlayItem addItem(GeoPoint p, String title, String snippet,Drawable marker) {
        OverlayItem newItem = new OverlayItem(title, snippet, p);
        newItem.setMarker(marker);
        overlayItemList.add(newItem);

        populate();
        return newItem;
    }

    @Override
    public boolean onSnapToItem(int arg0, int arg1, Point arg2, IMapView arg3) {
        // TODO Auto-generated method stub

        return false;
    }

    @Override
    protected boolean onTap(int index) {
        OverlayItem item = overlayItemList.get(index);
        mapController.animateTo(overlayItemList.get(index).getPoint());
        onClickListener.OnClick(index,item);
        return super.onTap(index);
    }

    @Override
    protected OverlayItem createItem(int arg0) {
        // TODO Auto-generated method stub
        return overlayItemList.get(arg0);
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return overlayItemList.size();
    }

    public void clear() {
       overlayItemList.clear();
       mapView.invalidate();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void OnClick(int index,OverlayItem item);
    }
}