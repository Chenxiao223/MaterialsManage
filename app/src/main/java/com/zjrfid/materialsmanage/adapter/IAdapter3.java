package com.zjrfid.materialsmanage.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjrfid.materialsmanage.R;

import java.util.ArrayList;
import java.util.HashMap;

public class IAdapter3 extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> list;
    private int i = 0;
    OnItemEdittext onItemEdittext;
    public static int location;
    public static int count=0;

    public void setOnItemEdittext(OnItemEdittext onItemEdittext){
        this.onItemEdittext=onItemEdittext;
    }

    public IAdapter3(Context context, ArrayList<HashMap<String, String>> list) {
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
            convertView = inflater.inflate(R.layout.list_materials_out_bound, null);
            viewHold.text1 = (TextView) convertView.findViewById(R.id.text1);
            viewHold.text2 = (TextView) convertView.findViewById(R.id.text2);
            viewHold.text3 = (TextView) convertView.findViewById(R.id.text3);
            viewHold.text4 = (TextView) convertView.findViewById(R.id.text4);
            viewHold.text5 = (TextView) convertView.findViewById(R.id.text5);
            viewHold.text6 = (TextView) convertView.findViewById(R.id.text6);
            viewHold.text7 = (TextView) convertView.findViewById(R.id.text7);
            viewHold.text8 = (TextView) convertView.findViewById(R.id.text8);
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
            viewHold.text19 = (TextView) convertView.findViewById(R.id.text19);
            viewHold.text20 = (TextView) convertView.findViewById(R.id.text20);
            viewHold.text21 = (TextView) convertView.findViewById(R.id.text21);
            viewHold.text22 = (EditText) convertView.findViewById(R.id.text22);
            viewHold.layout_ck= (RelativeLayout) convertView.findViewById(R.id.layout_ck);
            viewHold.cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
            if (i == 1) {//如果传进来的参数为1，就隐藏复选框
                viewHold.cb.setVisibility(View.GONE);
            }
            if (list.get(position).get("delFlag").equals("1")){
                convertView.setVisibility(View.GONE);
                AbsListView.LayoutParams param = new AbsListView.LayoutParams( 0,1);
                convertView.setLayoutParams(param);
            }
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.text1.setText(list.get(position).get("content1"));
        viewHold.text2.setText(list.get(position).get("content2"));
        viewHold.text3.setText(list.get(position).get("content3"));
        viewHold.text4.setText(list.get(position).get("content4"));
        viewHold.text5.setText(list.get(position).get("content5"));
        viewHold.text6.setText(list.get(position).get("content6"));
        viewHold.text7.setText(list.get(position).get("content7"));
        viewHold.text8.setText(list.get(position).get("content8"));
        viewHold.text9.setText(list.get(position).get("content9"));
        viewHold.text10.setText(list.get(position).get("content10"));
        viewHold.text11.setText(list.get(position).get("content11"));
        viewHold.text12.setText(list.get(position).get("content12"));
        viewHold.text13.setText(list.get(position).get("content13"));
        viewHold.text14.setText(list.get(position).get("content14"));
        viewHold.text15.setText(list.get(position).get("content15"));
        viewHold.text16.setText(list.get(position).get("content16"));
        viewHold.text17.setText(list.get(position).get("content17"));
        viewHold.text18.setText(list.get(position).get("content18"));
        viewHold.text19.setText(list.get(position).get("content19"));
        viewHold.text20.setText(list.get(position).get("content20"));
        viewHold.text21.setText(list.get(position).get("content21"));
        viewHold.text22.setText(list.get(position).get("content22"));
        viewHold.cb.setChecked(list.get(position).get("flag").equals("true"));

        final ViewHold finalViewHold = viewHold;
        viewHold.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location=position;
                if (finalViewHold.cb.isChecked()){
                    count++;
                    list.get(position).put("flag","true");
                }else{
                    count--;
                    list.get(position).put("flag","false");
                }
            }
        });

        viewHold.text22.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    onItemEdittext.setText(s.toString(), position);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return convertView;
    }


    public static class ViewHold {
        public CheckBox cb;
        TextView text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, text12, text13, text14, text15, text16, text17,text18,text19,text20,text21;
        RelativeLayout layout_ck;
        EditText text22;
    }
        //隐藏复选框的方法,如果传进来的参数为1，就隐藏复选框
        public void hind(int i) {
            this.i = i;
        }

    public interface OnItemEdittext{
        public void setText(String s,int p);
    }
}
