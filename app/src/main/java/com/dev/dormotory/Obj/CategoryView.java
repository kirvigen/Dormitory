package com.dev.dormotory.Obj;

import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryView {
    private CircleImageView circleColor;
    private ImageView icon;
    private TextView name;

    public CategoryView(CircleImageView circleColor, ImageView icon, TextView name) {
        this.circleColor = circleColor;
        this.icon = icon;
        this.name = name;
    }

    public CircleImageView getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(CircleImageView circleColor) {
        this.circleColor = circleColor;
    }

    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }
}
