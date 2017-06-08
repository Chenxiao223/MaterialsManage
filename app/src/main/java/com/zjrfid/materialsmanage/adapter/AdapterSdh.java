package com.zjrfid.materialsmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zjrfid.materialsmanage.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterSdh extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> list;
    private int i = 0;

    public AdapterSdh(Context context, ArrayList<HashMap<String, String>> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold viewHold = null;
        if (convertView == null) {
            viewHold = new ViewHold();
            convertView = inflater.inflate(R.layout.list_sdh, null);
            viewHold.text1 = (TextView) convertView.findViewById(R.id.text1);
            viewHold.text2 = (TextView) convertView.findViewById(R.id.text2);
            viewHold.text3 = (TextView) convertView.findViewById(R.id.text3);
            viewHold.text4 = (TextView) convertView.findViewById(R.id.text4);
            viewHold.text5 = (TextView) convertView.findViewById(R.id.text5);
            viewHold.text6 = (TextView) convertView.findViewById(R.id.text6);
            viewHold.text7 = (TextView) convertView.findViewById(R.id.text7);
            viewHold.text8 = (TextView) convertView.findViewById(R.id.text8);
            viewHold.text9 = (TextView) convertView.findViewById(R.id.text9);
            viewHold.tv_number= (TextView) convertView.findViewById(R.id.tv_number);
            viewHold.cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
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
        viewHold.tv_number.setText(position+1+"");
        viewHold.cb.setChecked(list.get(position).get("flag").equals("true"));
        return convertView;
    }


    public static class ViewHold {
        public CheckBox cb;
        TextView text1, text2, text3, text4, text5, text6, text7,text8, text9,tv_number;
    }
}
