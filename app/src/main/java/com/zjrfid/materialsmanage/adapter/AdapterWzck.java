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

public class AdapterWzck extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> list;
    private int i = 0;

    public AdapterWzck(Context context, ArrayList<HashMap<String, String>> list) {
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
        if (viewHold == null) {
            viewHold = new ViewHold();
            convertView = inflater.inflate(R.layout.list_wzck, null);
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
            viewHold.text22 = (TextView) convertView.findViewById(R.id.text22);
            viewHold.text23 = (TextView) convertView.findViewById(R.id.text23);
            viewHold.text24 = (TextView) convertView.findViewById(R.id.text24);
            viewHold.text25 = (TextView) convertView.findViewById(R.id.text25);
            viewHold.text26 = (TextView) convertView.findViewById(R.id.text26);
            viewHold.tv_digital= (TextView) convertView.findViewById(R.id.tv_digital);
            viewHold.cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
            if (i == 1) {//如果传进来的参数为1，就隐藏复选框
                viewHold.cb.setVisibility(View.GONE);
            }
//            else if (i==2){//如果i为2，就全选
//                viewHold.cb.setChecked(true);
//            }else if (i == 3) {//如果i为3，就全不选
//                viewHold.cb.setChecked(false);
//            }
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
        viewHold.text23.setText(list.get(position).get("content23"));
        viewHold.text24.setText(list.get(position).get("content24"));
        viewHold.text25.setText(list.get(position).get("content25"));
        viewHold.text26.setText(list.get(position).get("content26"));
        viewHold.tv_digital.setText((position+1)+"");
        viewHold.cb.setChecked(list.get(position).get("flag").equals("true"));
        return convertView;
    }


    public static class ViewHold {
        public CheckBox cb;
        TextView text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, text12, text13, text14, text15, text16, text17, text18, text19, text20, text21, text22,
                text23, text24, text25, text26,tv_digital;
    }

    //隐藏复选框的方法,如果传进来的参数为1，就隐藏复选框
    public void hind(int i) {
        this.i = i;
    }

    //全选和全不选
    public void checkAll(int i) {
        this.i = i;
    }

}
