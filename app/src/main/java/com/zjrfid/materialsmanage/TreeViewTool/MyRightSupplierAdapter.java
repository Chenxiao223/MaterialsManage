package com.zjrfid.materialsmanage.TreeViewTool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zjrfid.materialsmanage.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8.
 */
public class MyRightSupplierAdapter extends BaseAdapter {
    private Context context;
    List<RightModel> list;

    public MyRightSupplierAdapter(Context context, List<RightModel> models) {
        super();
        this.context = context;
        this.list = models;
    }

    @Override
    public int getCount() {
        if (list!=null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list!=null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold viewHold;
        if (convertView==null) {
            viewHold=new ViewHold();
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_right_item_supplier, null);
            viewHold.textView0=(TextView) convertView.findViewById(R.id.right_item_textview0);
            viewHold.textView1=(TextView) convertView.findViewById(R.id.right_item_textview1);
            viewHold.textView2=(TextView) convertView.findViewById(R.id.right_item_textview2);
            viewHold.textView3=(TextView) convertView.findViewById(R.id.right_item_textview3);
            viewHold.textView4=(TextView) convertView.findViewById(R.id.right_item_textview4);
            viewHold.textView5=(TextView) convertView.findViewById(R.id.right_item_textview5);
            viewHold.textView6=(TextView) convertView.findViewById(R.id.right_item_textview6);
            convertView.setTag(viewHold);
        }else {
            viewHold=(ViewHold) convertView.getTag();
        }
        viewHold.textView0.setText(list.get(position).getText0());
        viewHold.textView1.setText(list.get(position).getText1());
        viewHold.textView2.setText(list.get(position).getText2());
        viewHold.textView3.setText(list.get(position).getText3());
        viewHold.textView4.setText(list.get(position).getText4());
        viewHold.textView5.setText(list.get(position).getText5());
        viewHold.textView6.setText(list.get(position).getText6());
        return convertView;
    }

    static class ViewHold{
        TextView textView0,textView1,textView2,textView3,textView4,textView5,textView6;
    }

}
