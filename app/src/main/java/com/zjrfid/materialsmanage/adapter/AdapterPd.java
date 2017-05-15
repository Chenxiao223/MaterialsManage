package com.zjrfid.materialsmanage.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.UI.ActivityBatchPd;
import com.zjrfid.materialsmanage.UI.ActivityInventory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdapterPd extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> list;

    private boolean ChekcBoxIsHide_Flag = true;//checkbox显示隐藏
    private boolean EditTextIsEditable_Flag = true; //edittext可编辑
    OnItemEditText onItemEditText;


    public void setOnItemEditText(OnItemEditText onItemEditText) {
        this.onItemEditText = onItemEditText;
    }

    public AdapterPd(Context context, ArrayList<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHold viewHold = null;
        if (viewHold == null) {
            viewHold = new ViewHold();
            convertView = inflater.inflate(R.layout.list_pd, null);
            viewHold.text1 = (TextView) convertView.findViewById(R.id.text1);
            viewHold.text2 = (TextView) convertView.findViewById(R.id.text2);
            viewHold.text3 = (TextView) convertView.findViewById(R.id.text3);
            viewHold.text4 = (TextView) convertView.findViewById(R.id.text4);
            viewHold.text5 = (TextView) convertView.findViewById(R.id.text5);
            viewHold.text6 = (TextView) convertView.findViewById(R.id.text6);
            viewHold.text7 = (TextView) convertView.findViewById(R.id.text7);
            viewHold.et_inventory = (EditText) convertView.findViewById(R.id.et_inventory);
            //切换数字键盘
            viewHold.et_inventory.setInputType(EditorInfo.TYPE_CLASS_PHONE);
            viewHold.text9 = (TextView) convertView.findViewById(R.id.text9);
            viewHold.text10 = (TextView) convertView.findViewById(R.id.text10);
            viewHold.text11 = (TextView) convertView.findViewById(R.id.text11);
            viewHold.text12 = (TextView) convertView.findViewById(R.id.text12);
            viewHold.text13 = (TextView) convertView.findViewById(R.id.text13);
            viewHold.text14 = (TextView) convertView.findViewById(R.id.text14);
            viewHold.text15 = (TextView) convertView.findViewById(R.id.text15);
            viewHold.text16 = (TextView) convertView.findViewById(R.id.text16);
            viewHold.text17 = (TextView) convertView.findViewById(R.id.text17);
            viewHold.text18 = (TextView) convertView.findViewById(R.id.text18);
            viewHold.et_remar = (EditText) convertView.findViewById(R.id.et_remar);
            viewHold.layout_pd = (RelativeLayout) convertView.findViewById(R.id.layout_pd);
            viewHold.layout_pd.setOnClickListener(this);
            viewHold.cb = (CheckBox) convertView.findViewById(R.id.checkBox1);

            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }

        viewHold.text1.setText(list.get(position).get("content1"));//批号
        viewHold.text2.setText(list.get(position).get("content2"));//物资编码
        viewHold.text3.setText(list.get(position).get("content3"));//货位
        viewHold.text4.setText(list.get(position).get("content4"));//物资名称
        viewHold.text5.setText(list.get(position).get("content5"));//规格型号
        viewHold.text6.setText(list.get(position).get("content6"));//单位
        viewHold.text7.setText(list.get(position).get("content7"));//账面数量
        viewHold.et_inventory.setText(list.get(position).get("content8"));//盘点数量
        viewHold.text9.setText(list.get(position).get("content9"));//盈亏数量
        viewHold.text10.setText(list.get(position).get("content10"));//含税单价
        viewHold.text11.setText(list.get(position).get("content11"));//无税单价
        viewHold.text12.setText(list.get(position).get("content12"));//盈亏含税金额
        viewHold.text13.setText(list.get(position).get("content13"));//盈亏无税金额
        viewHold.text14.setText(list.get(position).get("content14"));//账面含税金额
        viewHold.text15.setText(list.get(position).get("content15"));//账面无税金额
        viewHold.text16.setText(list.get(position).get("content16"));//税率
        viewHold.text17.setText(list.get(position).get("content17"));//账面税额
        viewHold.text18.setText(list.get(position).get("content18"));//盈亏税额
        viewHold.et_remar.setText(list.get(position).get("content19"));//备注
        viewHold.cb.setChecked(list.get(position).get("flag").equals("true"));
        if (list.get(position).get("delFlag").equals("1")) {
            convertView.setVisibility(View.GONE);
            AbsListView.LayoutParams param = new AbsListView.LayoutParams(0, 1);
            convertView.setLayoutParams(param);
        }

        final ViewHold finalViewHold1 = viewHold;
        viewHold.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalViewHold1.cb.isChecked()) {
                    list.get(position).put("flag", "true");
                } else {
                    list.get(position).put("flag", "false");
                }
            }
        });

        //盘点数量
        final ViewHold finalViewHold = viewHold;
        final View finalConvertView = convertView;

        if (ChekcBoxIsHide_Flag == true) {
            viewHold.cb.setVisibility(View.GONE);
        }
        if (ChekcBoxIsHide_Flag == false) {
            viewHold.cb.setVisibility(View.VISIBLE);
        }
        if (EditTextIsEditable_Flag == true) {
            Toast.makeText(context, "可以输入", Toast.LENGTH_SHORT).show();
            viewHold.et_inventory.setInputType(InputType.TYPE_CLASS_TEXT);
            viewHold.et_remar.setInputType(InputType.TYPE_CLASS_TEXT);
            viewHold.et_inventory.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!TextUtils.isEmpty(s)) {
                        //计算算法
                        DecimalFormat df = new DecimalFormat("0.000000");//小数点后保留6位小数
                        //s表示输入的值
                        double text9 = 0;//盈亏数量
                        double text12 = 0;//盈亏含税金额
                        double text13 = 0;//盈亏无税金额
                        double text18 = 0;//盈亏含税金额-盈亏无税金额//盈亏税额


                        text9 = Double.parseDouble(s.toString()) - Double.parseDouble(list.get(position).get("content7"));
                        text12 = text9 * Double.parseDouble(ActivityInventory.inventory.listscq.get(position).get("content10"));
                        text13 = text9 * Double.parseDouble(ActivityInventory.inventory.listscq.get(position).get("content11"));
                        text18 = text12 - text13;//盈亏含税金额-盈亏无税金额

                        finalViewHold.text9.setText(text9 + "");//盈亏数量
                        finalViewHold.text12.setText(df.format(text12) + "");//盈亏含税金额
                        finalViewHold.text13.setText(df.format(text13) + "");//盈亏无税金额
                        finalViewHold.text18.setText(df.format(text18) + "");//盈亏税额
                        onItemEditText.setText(s.toString(), position);
                    } else {

                        finalViewHold.text9.setText("");//盈亏数量
                        finalViewHold.text12.setText("");//盈亏含税金额
                        finalViewHold.text13.setText("");//盈亏无税金额
                        finalViewHold.text18.setText("");//盈亏税额
                        onItemEditText.setText(s.toString(), position);

                    }
                }
            });
            //备注
            viewHold.et_remar.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!TextUtils.isEmpty(s)) {

                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }
        if (EditTextIsEditable_Flag == false) {
            Toast.makeText(context, "无法输入", Toast.LENGTH_SHORT).show();
            viewHold.et_inventory.setInputType(InputType.TYPE_NULL);
            viewHold.et_remar.setInputType(InputType.TYPE_NULL);
        }

        return convertView;
    }

    @Override//整个布局的点击事件，目的是让软键盘消失
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_pd:
                v.requestFocus();
                v.clearFocus();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                //并把焦点获取回来，让et失去焦点
                break;
        }
    }


    public static class ViewHold {
        public CheckBox cb;
        TextView text1, text2, text3, text4, text5, text6, text7, text9, text10, text11, text12, text13, text14, text15, text16, text17, text18;
        EditText et_inventory, et_remar;
        RelativeLayout layout_pd;
    }

    //隐藏复选框
    public void HideCheckBox(boolean flag) {
        this.ChekcBoxIsHide_Flag = flag;
    }

    public void EditTextIsEditable(boolean flag) {
        this.EditTextIsEditable_Flag = flag;
    }

    public interface OnItemEditText {
        public void setText(String s, int p);
    }


}
