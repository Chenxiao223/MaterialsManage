package com.zjrfid.materialsmanage.UI;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.acdbentity.LoginInfo;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.tool.SysApplication;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import static com.zjrfid.materialsmanage.R.drawable.shape_text;


/**
 * Created by chenxiao on 2016/9/8.
 */
public class ActivityLogin extends Activity {
    public static ActivityLogin activityLogin;
    public EditText tv_username, tv_password, edit_server;
    private CheckBox ck_remember;
    public static LoginInfo login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_login);
        //
        activityLogin = this;
        tv_username = (EditText) findViewById(R.id.edit_username);
        tv_password = (EditText) findViewById(R.id.edit_password);
        tv_username.setInputType(EditorInfo.TYPE_CLASS_PHONE);
//        tv_password.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        tv_username.setText(getData("user"));
        tv_password.setText(getData("pwd"));
        edit_server = (EditText) findViewById(R.id.edit_server);
        edit_server.setText("http://172.16.2.27:8080/");
        //默认是无法输入的，长按之后才可输入
        edit_server.setFocusable(false);
        edit_server.setFocusableInTouchMode(false);
        edit_server.setClickable(false);
        ck_remember = (CheckBox) findViewById(R.id.ck_remember);
        if (!getData("user").equals("")) {
            ck_remember.setChecked(true);
        }
        SysApplication.getInstance().addActivity(this);
        //监听CheckBox
        ck_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences preferences = getSharedPreferences("remember", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = preferences.edit();
                if (isChecked) {
                    editor.putBoolean("is",true);
                } else {
                    editor.putBoolean("is",false);
                }
                editor.commit();
            }
        });
        //点击服务器输入框
        edit_server.setOnLongClickListener(new View.OnLongClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public boolean onLongClick(View v) {
                edit_server.setBackground(getResources().getDrawable(R.drawable.shape_text));
                edit_server.setFocusable(true);
                edit_server.setFocusableInTouchMode(true);
                edit_server.setClickable(true);
                return false;
            }
        });
    }

    public void login(View view) {
        if (tv_username.getText().toString().equals("")) {
            tv_username.setError("账号不能为空！");
        } else if (tv_password.getText().toString().equals("")) {
            tv_password.setError("密码不能为空！");
        } else {
            String username = tv_username.getText().toString();
            String password = tv_password.getText().toString();
            RequestParams params = new RequestParams();
//            params.put("deviceid", "1231231321");
            params.put("username", username);
            params.put("password", password);
            HttpNetworkRequest.post("public/rs/device/login", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {
                    try {
                        SharedPreferences preferences = getSharedPreferences("remember", Context.MODE_PRIVATE);
                        final SharedPreferences.Editor editor = preferences.edit();
                        if (getBool()) {
                            String user = tv_username.getText().toString();
                            String pwd = tv_password.getText().toString();
                            editor.putString("user", user);
                            editor.putString("pwd", pwd);
                        } else {
                            editor.putString("user", "");
                            editor.putString("pwd", "");
                        }
                        editor.commit();

                        JSONObject jsonObject = new JSONObject(s);
                        if (jsonObject.getString("message").equals("登录成功")) {
                            startActivity(new Intent(ActivityLogin.this, ActivityHomePage.class));
                            JSONObject object = new JSONObject(jsonObject.getString("jsonData"));
                            setLogin(object.getString("jobnumber"), object.getString("username"), object.getString("joindate"), object.getString("orgname"),
                                    object.getString("userid"), object.getString("cardnumber"), object.getString("fullname"), object.getString("mobile"));
                            finish();
                        } else {
                            Toast.makeText(ActivityLogin.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                    Toast.makeText(ActivityLogin.this, "登录失败", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    public void setLogin(String jobnumber, String username, String joindate, String orgname, String userid, String cardnumber, String fullname, String mobile) {
        login = new LoginInfo();
        login.setJobnumber(jobnumber);
        login.setUsername(username);
        login.setJoindate(joindate);
        login.setOrgname(orgname);
        login.setUserid(userid);
        login.setCardnumber(cardnumber);
        login.setFullname(fullname);
        login.setMobile(mobile);
    }

    public String getData(String hh) {
        String str = "";
        SharedPreferences preferences = getSharedPreferences("remember", Context.MODE_PRIVATE);
        if (hh.equals("user")) {
            str = preferences.getString("user", "");
        } else if (hh.equals("pwd")) {
            str = preferences.getString("pwd", "");
        }
        return str;
    }

    public boolean getBool() {
        SharedPreferences preferences = getSharedPreferences("remember", Context.MODE_PRIVATE);
        return preferences.getBoolean("is",false);
    }
}
