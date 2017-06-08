package com.zjrfid.materialsmanage.tool;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
public class LogingDialog {
    static Dialog mDialog;
    public static void showRoundProcessDialog(Context mContext, int layout) {
        DialogInterface.OnKeyListener keyListener = new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_SEARCH) {
                    return true;
                }
                return false;
            }
        };

        mDialog = new AlertDialog.Builder(mContext).create();
        mDialog.setOnKeyListener(keyListener);
        mDialog.show();
        // 注意此处要放在show之后 否则会报异常
        mDialog.setContentView(layout);
    }

    public static void clossDialog(){
        mDialog.dismiss();
    }
}
