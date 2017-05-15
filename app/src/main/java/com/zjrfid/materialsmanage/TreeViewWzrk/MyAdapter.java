package com.zjrfid.materialsmanage.TreeViewWzrk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.zjrfid.materialsmanage.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private List<String> mList=new ArrayList<String>();



    public MyAdapter(Context context,  List<String> mList) {
        super();
        this.context = context;
        this.mList = mList;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup partent) {
        // TODO Auto-generated method stub
        ViewHold viewHold=null;
        if(convertView==null){
            viewHold=new ViewHold();
            convertView=mInflater.inflate(R.layout.list_treeview, null);
            viewHold.text=(TextView) convertView.findViewById(R.id.tv_code);
            convertView.setTag(viewHold);
        }else{
            viewHold=(ViewHold)convertView.getTag();
        }
        viewHold.text.setText(mList.get(position));
        return convertView;
    }
    public static  class ViewHold{
        TextView text;
    }
}
