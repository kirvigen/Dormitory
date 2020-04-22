package com.dev.dormotory.Utils;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.dev.dormotory.Obj.Category;
import com.dev.dormotory.Obj.CategoryView;
import com.dev.dormotory.Obj.PodTypeView;
import com.dev.dormotory.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BottomSheet {
    private BottomSheetBehavior bottomSheetBehavior;
    private RelativeLayout view;
    private List<PodTypeView> podTypeViews;
    private LinearLayout categoryView,podtypeView;
    private List<CategoryView> categoryViews;
    private List<Category> categories;
    private CardView bg;
    private Activity activity;
    private MyDBManager myDBManager;
    private OnClickResult onResult;
    private ImageView back,icon_podtype;
    private TextView name_podtype;
    int Page = 1;
    private OnEventListener onClosed;
    public BottomSheet(Activity activity){
        this.activity = activity;
        view = activity.findViewById(R.id.bottom_sheet);
        categoryView = view.findViewById(R.id.Categorys);
        podtypeView = view.findViewById(R.id.podtype);
        bg = view.findViewById(R.id.bg);
        back = view.findViewById(R.id.back);
        icon_podtype = view.findViewById(R.id.icon_podtype);
        name_podtype = view.findViewById(R.id.text_podtype);
        myDBManager = new MyDBManager(activity.getApplicationContext(),null,1);
        bottomSheetBehavior = BottomSheetBehavior.from(view);
        categories = myDBManager.getCategorys();
        initItem();
        InitCategorys();
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if(i == BottomSheetBehavior.STATE_COLLAPSED){
                    onClosed.OnEnd();
                }
            }
            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
    }
    public void setOnClickResult(OnClickResult onClickResult){
        this.onResult = onClickResult;
    }
    public boolean isOpen(){
        return bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED;
    }
    private void InitCategorys() {
        for(int i =0;i<categoryViews.size();i++){
            CategoryView view = categoryViews.get(i);
            Category category = categories.get(i);
            view.getIcon().setImageDrawable(activity.getResources().getDrawable(category.getDrawable_id()));
            view.getName().setText(category.getName());
            ColorDrawable cd = new ColorDrawable(Color.parseColor(category.getColor_id()));
            view.getIcon().setColorFilter(Color.parseColor("#ffffff"), android.graphics.PorterDuff.Mode.SRC_IN);
            view.getCircleColor().setImageDrawable(cd);
            view.getCircleColor().setOnClickListener(v->{
                if(category.getPoints() != null){
                    Page = 0;
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    onResult.OnResult(category);
                }else{
                    InitPodType(category.getCategories(),category);
                }
            });
        }
    }
    private void InitPodType(List<Category> categories,Category MainCategory){
        Page = 2;
        icon_podtype.setImageDrawable(activity.getResources().getDrawable(MainCategory.getDrawable_id()));
        bg.setCardBackgroundColor(Color.parseColor(MainCategory.getColor_id()));
        icon_podtype.setColorFilter(Color.parseColor("#ffffff"), android.graphics.PorterDuff.Mode.SRC_IN);
        name_podtype.setText(MainCategory.getName());
        for(int i =0;i<categories.size();i++){
            Category podtype = categories.get(i);
            PodTypeView podTypeView = podTypeViews.get(i);
            podTypeView.getMainView().setVisibility(View.VISIBLE);
            podTypeView.getName_podtype().setText(podtype.getName());
            podTypeView.getMainView().setOnClickListener(v->{
                Page = 0;
                onResult.OnResult(podtype);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            });
        }
        podtypeView.setVisibility(View.VISIBLE);
        animateView(0,()->{
            animateView(1,null,podtypeView);
            categoryView.setVisibility(View.GONE);
        },categoryView);
        animateView(1,()->{
            back.setOnClickListener(v->{
              initStartPage();
            });
        },back);
    }
    void initStartPage(){
        Page = 1;
        animateView(0,()->{
            back.setOnClickListener(view->{});
        },back);

        categoryView.setVisibility(View.VISIBLE);
        animateView(0,()->{
            animateView(1,null,categoryView);
            podtypeView.setVisibility(View.GONE);
        },podtypeView);
        initItem();
        InitCategorys();
    }
    private void initItem() {
        categoryViews = new ArrayList<>();
        podTypeViews = new ArrayList<>();
        for (int i=0;i<MyDBManager.NAMES.length;i++){
            ImageView icon = view.findViewById(getResourseId(activity,"icon_"+String.valueOf(i),activity.getPackageName()));
            CircleImageView color = view.findViewById(getResourseId(activity,"image_"+String.valueOf(i),activity.getPackageName()));
            TextView name = view.findViewById(getResourseId(activity,"name_"+String.valueOf(i),activity.getPackageName()));
            CategoryView categoryView = new CategoryView(color,icon,name);
            categoryViews.add(categoryView);
        }
        for(int i=0;i<8;i++){
            PodTypeView podTypeView = new PodTypeView();
            CardView cardView = view.findViewById(getResourseId(activity,"item_"+String.valueOf(i),activity.getPackageName()));
            cardView.setVisibility(View.GONE);
            TextView textView = view.findViewById(getResourseId(activity,"text_item_"+String.valueOf(i),activity.getPackageName()));
            podTypeView.setMainView(cardView);
            podTypeView.setName_podtype(textView);
            podTypeViews.add(podTypeView);
        }
    }

    public void Open(){
        initStartPage();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public static int getResourseId(Context context, String pVariableName, String pPackageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier( pVariableName, "id", pPackageName );
        } catch (Exception e) {
            throw new RuntimeException( "Error getting Resource ID.", e );
        }
    }
    private void animateView(float alpha, OnEventListener onEventListener, View... view) {
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

    public void setOnClosed(OnEventListener onClosed) {
        this.onClosed = onClosed;
    }

    public interface OnEventListener {
        void OnEnd();
    }

    public interface OnClickResult {
        void OnResult(Category category);
    }

}
