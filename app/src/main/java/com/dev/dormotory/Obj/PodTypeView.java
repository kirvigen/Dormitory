package com.dev.dormotory.Obj;

import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class PodTypeView {

    private CardView mainView;
    private TextView name_podtype;

    public CardView getMainView() {
        return mainView;
    }

    public void setMainView(CardView mainView) {
        this.mainView = mainView;
    }

    public TextView getName_podtype() {
        return name_podtype;
    }

    public void setName_podtype(TextView name_podtype) {
        this.name_podtype = name_podtype;
    }
}
