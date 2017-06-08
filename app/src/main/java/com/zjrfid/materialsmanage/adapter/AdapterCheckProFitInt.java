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

/**
 * Created by Administrator on 2017/4/10.
 */
public class AdapterCheckProFitInt extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> checkintlist;
    private int i = 0;

    public AdapterCheckProFitInt(Context context, ArrayList<HashMap<String, String>> checkintlist) {
        this.context = context;
        this.checkintlist = checkintlist;
        inflater = LayoutInflater.from(context);
    }




    @Override
    public int getCount() {
        return checkintlist.size();
    }

    @Override
    public Object getItem(int position) {
        return checkintlist.get(position);
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
            convertView = inflater.inflate(R.layout.list_checkprofitint, null);
            viewHold.tv_checkIntbatch= (TextView) convertView.findViewById(R.id.tv_checkIntbatch);
            viewHold.tv_checkIntMaterialcoding= (TextView) convertView.findViewById(R.id.tv_checkIntMaterialcoding);
            viewHold.tv_checkIntLocationname= (TextView) convertView.findViewById(R.id.tv_checkIntLocationname);
            viewHold.tv_checkIntLocationcoding= (TextView) convertView.findViewById(R.id.tv_checkIntLocationcoding);
            viewHold.tv_checkIntspecifications1= (TextView) convertView.findViewById(R.id.tv_checkIntspecifications1);
            viewHold.tv_checkIntspecifications2= (TextView) convertView.findViewById(R.id.tv_checkIntspecifications2);
            viewHold.tv_checkIntnumber= (TextView) convertView.findViewById(R.id.tv_checkIntnumber);
            viewHold.tv_checkIntunitprice= (TextView) convertView.findViewById(R.id.tv_checkIntunitprice);
            viewHold.tv_checkIntTotalamount= (TextView) convertView.findViewById(R.id.tv_checkIntTotalamount);
            viewHold.tv_checkIntNotaxunitprice= (TextView) convertView.findViewById(R.id.tv_checkIntNotaxunitprice);
            viewHold.tv_checkIntNotaxunitamoun= (TextView) convertView.findViewById(R.id.tv_checkIntNotaxunitamoun);
            viewHold.tv_checkIntResponsibleforinventory= (TextView) convertView.findViewById(R.id.tv_checkIntResponsibleforinventory);
            viewHold.tv_checkIntRemarks= (TextView) convertView.findViewById(R.id.tv_checkIntRemarks);
            viewHold.tv_checkIntdigital= (TextView) convertView.findViewById(R.id.tv_checkIntdigital);
            viewHold.checkIntBox1 = (CheckBox) convertView.findViewById(R.id.checkIntBox1);
            if (i == 1) {//如果传进来的参数为1，就隐藏复选框
                viewHold.checkIntBox1.setVisibility(View.GONE);
            }
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.tv_checkIntMaterialcoding.setText(checkintlist.get(position).get("content1"));// 物资编码
        viewHold.tv_checkIntbatch.setText(checkintlist.get(position).get("content2"));//批号
        viewHold.tv_checkIntLocationname.setText(checkintlist.get(position).get("content3"));//货位名称
        viewHold.tv_checkIntLocationcoding.setText(checkintlist.get(position).get("content4"));//货位编码
        viewHold.tv_checkIntspecifications1.setText(checkintlist.get(position).get("content5"));//规格型号1
        viewHold.tv_checkIntspecifications2.setText(checkintlist.get(position).get("content6"));//单位
        viewHold.tv_checkIntnumber.setText(checkintlist.get(position).get("content7"));//数量
        viewHold.tv_checkIntunitprice.setText(checkintlist.get(position).get("content8"));//单价
        viewHold.tv_checkIntTotalamount.setText(checkintlist.get(position).get("content9"));//合计金额
        viewHold.tv_checkIntNotaxunitprice.setText(checkintlist.get(position).get("content10"));//无税单价
        viewHold.tv_checkIntNotaxunitamoun.setText(checkintlist.get(position).get("content11"));//无税金额
        viewHold.tv_checkIntResponsibleforinventory.setText(checkintlist.get(position).get("content12"));//负责盘点人
        viewHold.tv_checkIntRemarks.setText(checkintlist.get(position).get("content13"));//备注
        viewHold.tv_checkIntdigital.setText((position+1)+"");//显示条目
        viewHold.checkIntBox1.setChecked(checkintlist.get(position).get("flag").equals("true"));//复选框
        return convertView;
    }

    public static class ViewHold {
        public CheckBox checkIntBox1 ;
        TextView   tv_checkIntMaterialcoding,tv_checkIntbatch, tv_checkIntLocationname, tv_checkIntLocationcoding, tv_checkIntspecifications1,
                tv_checkIntspecifications2, tv_checkIntnumber, tv_checkIntunitprice, tv_checkIntTotalamount, tv_checkIntNotaxunitprice,
                tv_checkIntNotaxunitamoun, tv_checkIntResponsibleforinventory, tv_checkIntRemarks, tv_checkIntdigital;
    }
    //隐藏复选框的方法,如果传进来的参数为1，就隐藏复选框
    public void hind(int i) {
        this.i = i;
    }


}
