package com.dev.dormotory.Utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import java.io.UnsupportedEncodingException;


public class Utils {

    private Context context;
    String STORAGE = "";

    public Utils(Context context) {
        this.context = context;
    }
    public void saveText(String name, String text) {
        SharedPreferences prefs = context.getSharedPreferences(STORAGE,Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString(name, text);
        ed.commit();
    }
    public  String loadText(String name) {
        SharedPreferences prefs = context.getSharedPreferences(STORAGE,Context.MODE_PRIVATE);
        String savedText = prefs.getString(name,"");
        return savedText;
    }
    public boolean isMyServiceRunning(Class<?> serviceClass,Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService( Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    public String getScore(){
        String t = loadText("score");
        if(!t.equals("")) {
            try {
                byte[] data = Base64.decode(t, Base64.DEFAULT);
                t = new String(data, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{
            return t;
        }

        return t;
    }
    public void SaveScore(int score){
        try {
            byte[] data = new byte[0];
            data = String.valueOf(score).getBytes("UTF-8");
            String base64 = Base64.encodeToString(data, Base64.DEFAULT);
            saveText("score",base64);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
    public float dpToPixels(int dp) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public Drawable UpScale(Drawable src, float scale){
        Bitmap s = drawableToBitmap(src);
        Bitmap bitmapRe = Bitmap.createScaledBitmap(s, (int) (s.getWidth()*scale), (int) (s.getHeight()*scale), false);
        return new BitmapDrawable(context.getResources(), bitmapRe);
    }
}