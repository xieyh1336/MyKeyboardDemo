package com.example.mykeyboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MyKeyboard implements View.OnClickListener {

    private static String TAG = "MyKeyboard";
    private Context context;
    private View numView, letterView;
    private EditText editText;
    private PopupWindow numPopup, letterPopup;

    public MyKeyboard(Context context) {
        this.context = context;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
        init();
    }

    private void init(){
        buildNumKeyboard();
        buildLetterKeyboard();
        //对编辑框作监听
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
                //这里可以添加点击判断键盘是否显示从而进行操作
                if (!numPopup.isShowing()){
                    Log.e(TAG, "显示数字键盘");
                    numPopup.showAtLocation(numView, Gravity.BOTTOM, 0, 0);
                }
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return true;
            }
        });
    }

    @SuppressLint("InflateParams")
    private void buildNumKeyboard(){
        numView = LayoutInflater.from(context).inflate(R.layout.my_keyboard_number, null);
        numPopup = new PopupWindow(numView, -1, -2, false);
        numPopup.setAnimationStyle(R.style.MyKeyboard);//设置自定义键盘进出动画
        numPopup.setOutsideTouchable(false);//设置区域外不可点击
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
        numPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.e(TAG, "弹窗消失监听");
            }
        });
    }

    @SuppressLint("InflateParams")
    private void buildLetterKeyboard(){
        letterView = LayoutInflater.from(context).inflate(R.layout.my_keyboard_letter, null);
        letterPopup = new PopupWindow(letterView, -1, -2, false);
        letterPopup.setAnimationStyle(R.style.MyKeyboard);
        letterPopup.setOutsideTouchable(false);
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
                if (numPopup.isShowing()){
                    //切换取消动画
                    numPopup.setAnimationStyle(0);
                    letterPopup.setAnimationStyle(0);
                    numPopup.dismiss();
                    letterPopup.showAtLocation(letterView, Gravity.BOTTOM, 0, 0);
                } else if (letterPopup.isShowing()){
                    //切换取消动画
                    numPopup.setAnimationStyle(-1);
                    letterPopup.setAnimationStyle(-1);
                    letterPopup.dismiss();
                    numPopup.showAtLocation(letterView, Gravity.BOTTOM, 0, 0);
                }
                break;
            case R.id.tv_chinese:
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
            case R.id.tv_ABC:
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
                numPopup.setAnimationStyle(R.style.MyKeyboard);//重新设置动画
                letterPopup.setAnimationStyle(R.style.MyKeyboard);//重新设置动画
                if (numPopup.isShowing()){
                    numPopup.dismiss();
                }
                if (letterPopup.isShowing()){
                    letterPopup.dismiss();
                }
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
            case R.id.tv_W:
            case R.id.tv_E:
            case R.id.tv_R:
            case R.id.tv_T:
            case R.id.tv_Y:
            case R.id.tv_U:
            case R.id.tv_I:
            case R.id.tv_O:
            case R.id.tv_P:
            case R.id.tv_A:
            case R.id.tv_S:
            case R.id.tv_D:
            case R.id.tv_F:
            case R.id.tv_G:
            case R.id.tv_H:
            case R.id.tv_J:
            case R.id.tv_K:
            case R.id.tv_L:
            case R.id.tv_Z:
            case R.id.tv_X:
            case R.id.tv_C:
            case R.id.tv_V:
            case R.id.tv_B:
            case R.id.tv_N:
            case R.id.tv_M:
            case R.id.tv_123:
            case R.id.tv_space:
        }
    }
}
