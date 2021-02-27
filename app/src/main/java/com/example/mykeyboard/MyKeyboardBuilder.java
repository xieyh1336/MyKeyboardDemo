package com.example.mykeyboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.mykeyboard.util.GlobalLayoutListener;
import com.example.mykeyboard.util.OnKeyboardChangedListener;
import com.example.mykeyboard.util.ScreenUtils;

public class MyKeyboardBuilder implements View.OnClickListener {

    private static String TAG = "MyKeyboardBuilder";
    private Activity activity;
    private EditText editText;
    private View numView, letterView, systemTop;
    private ViewGroup parent, rootView;
    private InputMethodManager inputMethodManager;//键盘管理类
    private boolean isKeyboardShow = false;
    private boolean isSwitch = false;//是否切换
    private AnimationSet animationSetShow, animationSetHide;//动画效果

    public MyKeyboardBuilder(Builder builder){
        this.activity = builder.activity;
        this.editText = builder.editText;
        this.parent = builder.parent;
        this.rootView = builder.rootView;
        init();
    }

    private void init(){
        buildNumKeyboard();
        buildLetterKeyboard();
        buildSystemTop();
        initAnimation();
        parent.setVisibility(View.GONE);
        inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        setListener();
    }

    @SuppressLint("InflateParams")
    private void buildNumKeyboard(){
        numView = LayoutInflater.from(activity).inflate(R.layout.my_keyboard_number, null);
        numView.findViewById(R.id.ll_switch).setOnClickListener(this);
        numView.findViewById(R.id.tv_chinese).setOnClickListener(this);
        numView.findViewById(R.id.tv_600).setOnClickListener(this);
        numView.findViewById(R.id.tv_300).setOnClickListener(this);
        numView.findViewById(R.id.tv_00).setOnClickListener(this);
        numView.findViewById(R.id.tv_ABC).setOnClickListener(this);
        numView.findViewById(R.id.tv_1).setOnClickListener(this);
        numView.findViewById(R.id.tv_4).setOnClickListener(this);
        numView.findViewById(R.id.tv_7).setOnClickListener(this);
        numView.findViewById(R.id.tv_0).setOnClickListener(this);
        numView.findViewById(R.id.tv_2).setOnClickListener(this);
        numView.findViewById(R.id.tv_5).setOnClickListener(this);
        numView.findViewById(R.id.tv_8).setOnClickListener(this);
        numView.findViewById(R.id.iv_delete).setOnClickListener(this);
        numView.findViewById(R.id.tv_3).setOnClickListener(this);
        numView.findViewById(R.id.tv_6).setOnClickListener(this);
        numView.findViewById(R.id.tv_9).setOnClickListener(this);
        numView.findViewById(R.id.tv_enter).setOnClickListener(this);
        numView.findViewById(R.id.iv_hide).setOnClickListener(this);
        parent.addView(numView);
        numView.setVisibility(View.GONE);
    }

    @SuppressLint("InflateParams")
    private void buildLetterKeyboard(){
        letterView = LayoutInflater.from(activity).inflate(R.layout.my_keyboard_letter, null);
        letterView.findViewById(R.id.ll_switch).setOnClickListener(this);
        letterView.findViewById(R.id.tv_chinese).setOnClickListener(this);
        letterView.findViewById(R.id.iv_hide).setOnClickListener(this);
        letterView.findViewById(R.id.tv_Q).setOnClickListener(this);
        letterView.findViewById(R.id.tv_W).setOnClickListener(this);
        letterView.findViewById(R.id.tv_E).setOnClickListener(this);
        letterView.findViewById(R.id.tv_R).setOnClickListener(this);
        letterView.findViewById(R.id.tv_T).setOnClickListener(this);
        letterView.findViewById(R.id.tv_Y).setOnClickListener(this);
        letterView.findViewById(R.id.tv_U).setOnClickListener(this);
        letterView.findViewById(R.id.tv_I).setOnClickListener(this);
        letterView.findViewById(R.id.tv_O).setOnClickListener(this);
        letterView.findViewById(R.id.tv_P).setOnClickListener(this);
        letterView.findViewById(R.id.tv_A).setOnClickListener(this);
        letterView.findViewById(R.id.tv_S).setOnClickListener(this);
        letterView.findViewById(R.id.tv_D).setOnClickListener(this);
        letterView.findViewById(R.id.tv_F).setOnClickListener(this);
        letterView.findViewById(R.id.tv_G).setOnClickListener(this);
        letterView.findViewById(R.id.tv_H).setOnClickListener(this);
        letterView.findViewById(R.id.tv_J).setOnClickListener(this);
        letterView.findViewById(R.id.tv_K).setOnClickListener(this);
        letterView.findViewById(R.id.tv_L).setOnClickListener(this);
        letterView.findViewById(R.id.tv_Z).setOnClickListener(this);
        letterView.findViewById(R.id.tv_X).setOnClickListener(this);
        letterView.findViewById(R.id.tv_C).setOnClickListener(this);
        letterView.findViewById(R.id.tv_V).setOnClickListener(this);
        letterView.findViewById(R.id.tv_B).setOnClickListener(this);
        letterView.findViewById(R.id.tv_N).setOnClickListener(this);
        letterView.findViewById(R.id.tv_M).setOnClickListener(this);
        letterView.findViewById(R.id.iv_delete).setOnClickListener(this);
        letterView.findViewById(R.id.tv_123).setOnClickListener(this);
        letterView.findViewById(R.id.tv_space).setOnClickListener(this);
        letterView.findViewById(R.id.tv_enter).setOnClickListener(this);
        parent.addView(letterView);
        letterView.setVisibility(View.GONE);
    }

    @SuppressLint("InflateParams")
    private void buildSystemTop(){
        systemTop = LayoutInflater.from(activity).inflate(R.layout.my_system_keyboard_top, null);
        systemTop.findViewById(R.id.ll_switch).setOnClickListener(this);
        systemTop.findViewById(R.id.iv_hide).setOnClickListener(this);
        parent.addView(systemTop);
        systemTop.setVisibility(View.GONE);
    }

    private void initAnimation(){
        TranslateAnimation translateAnimationShow = new TranslateAnimation(
                0, 0, ScreenUtils.dip2px(activity, 250), 0);
        animationSetShow = new AnimationSet(false);
        animationSetShow.addAnimation(translateAnimationShow);
        animationSetShow.setDuration(500);
        animationSetShow.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.e(TAG, "动画开始");
                parent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        TranslateAnimation translateAnimationHide = new TranslateAnimation(
                0, 0, 0, ScreenUtils.dip2px(activity, 250));
        animationSetHide = new AnimationSet(false);
        animationSetHide.addAnimation(translateAnimationHide);
        animationSetHide.setDuration(500);
        animationSetHide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e(TAG, "动画结束");
                parent.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void setListener(){
        //对编辑框监听
        //由于编辑框第一次点击的时候如果有焦点的话，是不触发点击事件的，所以先把焦点去掉
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "点击了编辑框");
                //第一次点击后，再重新设置焦点
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                if (!isKeyboardShow && !isParentShow()){
                    //自定义键盘和系统键盘都没弹出的时候，显示自定义键盘的数字
                    Log.e(TAG, "隐藏状态下，显示数字布局");
                    setNumShow(true);//数字显示
                    setLetterShow(false);//字母隐藏
                    setSystemKeyboardShow(false);
                    setParentShow(true);
                }
            }
        });
        //根布局键盘监听
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new GlobalLayoutListener(rootView, new OnKeyboardChangedListener() {
            @Override
            public void onChange(boolean isShow, int keyboardHeight, int screenWidth, int screenHeight) {
                Log.e(TAG, "键盘是否展开: " + isShow);
                Log.e(TAG, "键盘高度(px): " + keyboardHeight);
                Log.e(TAG, "屏幕宽度(px): " + screenWidth);
                Log.e(TAG, "屏幕可用高度(px): " + screenHeight);
                if (keyboardHeight > 0){
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) systemTop.getLayoutParams();
                    params.setMargins(0, 0, 0, keyboardHeight);
                    systemTop.setLayoutParams(params);
                    systemTop.requestLayout();
                }
                isKeyboardShow = isShow;
                if (isShow){
                    systemTop.setVisibility(View.VISIBLE);
                    if (isSwitch){
                        isSwitch = false;
                    }
                } else {
                    systemTop.setVisibility(View.GONE);
                    Log.e(TAG, "isSwitch：" + isSwitch);
                    if (isSwitch){
                        isSwitch = false;
                    } else {
                        setParentShow(false);
                    }
                }
            }
        }));
    }

    public boolean isNumShow(){
        return numView.getVisibility() == View.VISIBLE;
    }

    public void setNumShow(boolean isShow){
        if (isShow){
            numView.setVisibility(View.VISIBLE);
        } else {
            numView.setVisibility(View.GONE);
        }
    }

    public boolean isLetterShow(){
        return letterView.getVisibility() == View.VISIBLE;
    }

    public void setLetterShow(boolean isShow){
        if (isShow){
            letterView.setVisibility(View.VISIBLE);
        } else {
            letterView.setVisibility(View.GONE);
        }
    }

    public boolean isSystemTopShow(){
        return systemTop.getVisibility() == View.VISIBLE;
    }

    public void setSystemTopShow(boolean isShow){
        if (isShow){
            systemTop.setVisibility(View.VISIBLE);
        } else {
            systemTop.setVisibility(View.GONE);
        }
    }

    public boolean isParentShow(){
        return parent.getVisibility() == View.VISIBLE;
    }

    public void setParentShow(boolean isShow){
        if (isShow){
            parent.startAnimation(animationSetShow);
        } else {
            parent.startAnimation(animationSetHide);
        }
    }

    private void setSystemKeyboardShow(boolean isShow){
        if (inputMethodManager != null){
            if (isShow){
                inputMethodManager.showSoftInput(editText, 0);
            } else {
                inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        }
    }

    public void setHeight(int px){
        numView.getLayoutParams().height = px;
        letterView.getLayoutParams().height = px;
        numView.requestLayout();
        letterView.requestLayout();
    }

    /**
     * 向光标后添加字符
     * @param text 需要添加的字符
     */
    @SuppressLint("SetTextI18n")
    private void insertText(String text){
        if (editText == null || text == null){
            return;
        }
        int index = editText.getSelectionStart();//获取光标位置
        Editable editable = editText.getText();
        editable.insert(index, text);
    }

    /**
     * 删除/退格事件
     */
    private void delete(){
        if (editText == null || editText.getText().toString().length() <= 0){
            return;
        }
        int index = editText.getSelectionStart();
        Editable editable = editText.getText();
        editable.delete(index - 1, index);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_switch:
            case R.id.tv_123:
            case R.id.tv_ABC:
                //切换
                isSwitch = true;
                if (isNumShow()){
                    setNumShow(false);
                    setLetterShow(true);
                    setSystemKeyboardShow(false);
                } else if (isLetterShow() || isKeyboardShow){
                    setLetterShow(false);
                    setNumShow(true);
                    setSystemKeyboardShow(false);
                }
                break;
            case R.id.tv_chinese:
                //系统键盘
                isSwitch = true;
                setParentShow(true);
                setNumShow(false);
                setLetterShow(false);
                setSystemTopShow(true);
                setSystemKeyboardShow(true);
                break;

            //以下是数字键盘
            case R.id.tv_600:
                insertText("600");
                break;
            case R.id.tv_300:
                insertText("300");
                break;
            case R.id.tv_00:
                insertText("00");
                break;
            case R.id.tv_1:
                insertText("1");
                break;
            case R.id.tv_4:
                insertText("4");
                break;
            case R.id.tv_7:
                insertText("7");
                break;
            case R.id.tv_0:
                insertText("0");
                break;
            case R.id.tv_2:
                insertText("2");
                break;
            case R.id.tv_5:
                insertText("5");
                break;
            case R.id.tv_8:
                insertText("8");
                break;
            case R.id.iv_delete:
                delete();
                break;
            case R.id.tv_enter:
            case R.id.iv_hide:
                //隐藏
                isSwitch = false;
                setSystemKeyboardShow(false);
                setParentShow(false);
                break;
            case R.id.tv_3:
                insertText("3");
                break;
            case R.id.tv_6:
                insertText("6");
                break;
            case R.id.tv_9:
                insertText("9");
                break;
            //以下是字母键盘
            case R.id.tv_Q:
                insertText("Q");
                break;
            case R.id.tv_W:
                insertText("W");
                break;
            case R.id.tv_E:
                insertText("E");
                break;
            case R.id.tv_R:
                insertText("R");
                break;
            case R.id.tv_T:
                insertText("T");
                break;
            case R.id.tv_Y:
                insertText("Y");
                break;
            case R.id.tv_U:
                insertText("U");
                break;
            case R.id.tv_I:
                insertText("I");
                break;
            case R.id.tv_O:
                insertText("O");
                break;
            case R.id.tv_P:
                insertText("P");
                break;
            case R.id.tv_A:
                insertText("A");
                break;
            case R.id.tv_S:
                insertText("S");
                break;
            case R.id.tv_D:
                insertText("D");
                break;
            case R.id.tv_F:
                insertText("F");
                break;
            case R.id.tv_G:
                insertText("G");
                break;
            case R.id.tv_H:
                insertText("H");
                break;
            case R.id.tv_J:
                insertText("J");
                break;
            case R.id.tv_K:
                insertText("K");
                break;
            case R.id.tv_L:
                insertText("L");
                break;
            case R.id.tv_Z:
                insertText("Z");
                break;
            case R.id.tv_X:
                insertText("X");
                break;
            case R.id.tv_C:
                insertText("C");
                break;
            case R.id.tv_V:
                insertText("V");
                break;
            case R.id.tv_B:
                insertText("B");
                break;
            case R.id.tv_N:
                insertText("N");
                break;
            case R.id.tv_M:
                insertText("M");
                break;
            case R.id.tv_space:
                insertText(" ");
                break;
        }
    }


    public static class Builder{
        private Activity activity;
        private EditText editText;
        private ViewGroup parent, rootView;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder setEditText(EditText editText){
            this.editText = editText;
            return this;
        }

        public Builder setViewGroup(ViewGroup parent){
            this.parent = parent;
            return this;
        }

        public Builder setRootView(ViewGroup rootView){
            this.rootView = rootView;
            return this;
        }

        public MyKeyboardBuilder build(){
            return new MyKeyboardBuilder(this);
        }
    }
}
