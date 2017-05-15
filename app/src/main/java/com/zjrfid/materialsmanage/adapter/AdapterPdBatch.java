package com.zjrfid.materialsmanage.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.UI.ActivityBatchPd;
import com.zjrfid.materialsmanage.acdbentity.House;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public class AdapterPdBatch extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> list;
    private int i = 0;

    public AdapterPdBatch(Context context, ArrayList<HashMap<String, String>> list) {
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
            convertView = inflater.inflate(R.layout.list_batch2, null);
            viewHold.text1 = (TextView) convertView.findViewById(R.id.text1);
            viewHold.text2 = (TextView) convertView.findViewById(R.id.text2);
            viewHold.text3 = (TextView) convertView.findViewById(R.id.text3);
            viewHold.text4 = (TextView) convertView.findViewById(R.id.text4);
            viewHold.text5 = (TextView) convertView.findViewById(R.id.text5);
            viewHold.cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
            viewHold.cb.setVisibility(View.VISIBLE);
            if (i == 1) {//如果传进来的参数为1，就隐藏复选框
                viewHold.cb.setVisibility(View.GONE);
            } else if (i == 2) {//如果i为2，就全选
                if (!ActivityBatchPd.batch.list_position.contains(position)){
                    viewHold.cb.setChecked(true);
                }
            } else if (i == 3) {//如果i为3，就全不选
                viewHold.cb.setChecked(false);
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
        if (list.get(position).get("content6").equals("true")) {
//            ActivityBatchPd.batch.listView.setEnabled(false);
        }
        viewHold.cb.setChecked(list.get(position).get("flag").equals("true"));
        return convertView;
    }


    public static class ViewHold {
        public CheckBox cb;
        TextView text1, text2, text3, text4, text5;
    }

    //1表示隐藏复选框的方法，0表示显示
    public void hind(int i) {
        this.i = i;
    }

    //全选和全不选
    public void checkAll(int i) {
        this.i = i;
    }


}
