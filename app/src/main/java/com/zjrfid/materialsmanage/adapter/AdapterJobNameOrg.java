package com.zjrfid.materialsmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.acdbentity.CpersonOrgDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public class AdapterJobNameOrg extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<CpersonOrgDriver.JsonDataBean.Bean> list;
    private int i = 0;

    public AdapterJobNameOrg(Context context, List<CpersonOrgDriver.JsonDataBean.Bean> list) {
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
            convertView = inflater.inflate(R.layout.list_job_name_org, null);
            viewHold.tv_jobNumber = (TextView) convertView.findViewById(R.id.tv_jobNumber);
            viewHold.tv_personName = (TextView) convertView.findViewById(R.id.tv_personName);
            viewHold.tv_orgName = (TextView) convertView.findViewById(R.id.tv_orgName);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.tv_jobNumber.setText(list.get(position).getJobnumber());
        viewHold.tv_personName.setText(list.get(position).getUsername());
        viewHold.tv_orgName.setText(list.get(position).getOrgname());
        return convertView;
    }


    public static class ViewHold {
        TextView tv_jobNumber, tv_personName, tv_orgName;
    }


}
