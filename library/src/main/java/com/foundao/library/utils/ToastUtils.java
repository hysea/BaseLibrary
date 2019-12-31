package com.foundao.library.utils;

import android.content.Context;
import androidx.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.foundao.library.R;
import com.foundao.library.base.BaseApp;

public class ToastUtils {
    /**
     * 全局Toast，防止多次点击重复出现
     */
    private static Toast sToast;

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void showToast(CharSequence text) {
//        if (sToast == null) {
//            // 解决小米Toast首次弹出自带应用名称
        sToast = Toast.makeText(BaseApp.getAppContext(), null, Toast.LENGTH_SHORT);
        sToast.setText(text);
//        } else {
//            sToast.setText(text);
//        }
        sToast.show();
    }

    public static void showToast(@StringRes int strId) {
//        if (sToast == null) {
        // 解决小米Toast首次弹出自带应用名称
        sToast = Toast.makeText(BaseApp.getAppContext(), null, Toast.LENGTH_SHORT);
        sToast.setText(strId);
//        } else {
//            sToast.setText(strId);
//        }
        sToast.show();
    }

    public static void dissmissToast() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }

    /**
     * 将Toast封装在一个方法中，在其它地方使用时直接输入要弹出的内容即可
     */
    public static void toastMessage(String messages) {
        //LayoutInflater的作用：对于一个没有被载入或者想要动态载入的界面，都需要LayoutInflater.inflate()来载入，LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化
        LayoutInflater inflater = (LayoutInflater) BaseApp.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.toast_set_font_success, null); //加載layout下的布局
        TextView tvMessage = view.findViewById(R.id.tv_message);
        tvMessage.setText(messages); //toast内容
        Toast toast = new Toast(BaseApp.getAppContext().getApplicationContext());
        toast.setGravity(Gravity.CENTER, 12, 20);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        toast.setDuration(Toast.LENGTH_SHORT);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        toast.setView(view); //添加视图文件
        toast.show();
    }
}
