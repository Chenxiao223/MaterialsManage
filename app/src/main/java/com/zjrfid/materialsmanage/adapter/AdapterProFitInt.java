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
public class AdapterProFitInt extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> intlist;
    private int i = 0;

    public AdapterProFitInt(Context context, ArrayList<HashMap<String, String>> intlist) {
        this.context = context;
        this.intlist = intlist;
        inflater = LayoutInflater.from(context);
    }




    @Override
    public int getCount() {
        return intlist.size();
    }

    @Override
    public Object getItem(int position) {
        return intlist.get(position);
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
            convertView = inflater.inflate(R.layout.list_inventoryprofitint, null);
            viewHold.tv_Inventorystorage= (TextView) convertView.findViewById(R.id.tv_Inventorystorage);
            viewHold.tv_Inventorystoragetime= (TextView) convertView.findViewById(R.id.tv_Inventorystoragetime);
            viewHold.tv_intwarehouse= (TextView) convertView.findViewById(R.id.tv_intwarehouse);
            viewHold.tv_intbatch= (TextView) convertView.findViewById(R.id.tv_intbatch);
            viewHold.tv_intMaterialcoding= (TextView) convertView.findViewById(R.id.tv_intMaterialcoding);
            viewHold.tv_intMaterialname= (TextView) convertView.findViewById(R.id.tv_intMaterialname);
            viewHold.tv_intLocationname= (TextView) convertView.findViewById(R.id.tv_intLocationname);
            viewHold.tv_intLocationcoding= (TextView) convertView.findViewById(R.id.tv_intLocationcoding);
            viewHold.tv_intspecifications1= (TextView) convertView.findViewById(R.id.tv_intspecifications1);
            viewHold.tv_intspecifications2= (TextView) convertView.findViewById(R.id.tv_intspecifications2);
            viewHold.tv_intnumber= (TextView) convertView.findViewById(R.id.tv_intnumber);
            viewHold.tv_intunitprice= (TextView) convertView.findViewById(R.id.tv_intunitprice);
            viewHold.tv_intTotalamount= (TextView) convertView.findViewById(R.id.tv_intTotalamount);
            viewHold.tv_intNotaxunitprice= (TextView) convertView.findViewById(R.id.tv_intNotaxunitprice);
            viewHold.tv_intNotaxunitamoun= (TextView) convertView.findViewById(R.id.tv_intNotaxunitamoun);
            viewHold.tv_intResponsibleforinventory= (TextView) convertView.findViewById(R.id.tv_intResponsibleforinventory);
            viewHold.tv_intSingleMan= (TextView) convertView.findViewById(R.id.tv_intSingleMan);
            viewHold.tv_intAuditorMan= (TextView) convertView.findViewById(R.id.tv_intAuditorMan);
            viewHold.tv_intdepartment= (TextView) convertView.findViewById(R.id.tv_intdepartment);
            viewHold.tv_intRemarks= (TextView) convertView.findViewById(R.id.tv_intRemarks);
            viewHold.tv_intdigital= (TextView) convertView.findViewById(R.id.tv_intdigital);
            viewHold.outBox2 = (CheckBox) convertView.findViewById(R.id.intcheckBox2);
            if (i == 1) {//如果传进来的参数为1，就隐藏复选框
                viewHold.outBox2.setVisibility(View.GONE);
            }
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.tv_Inventorystorage.setText(intlist.get(position).get("content1"));//盘点出库单号
        viewHold.tv_Inventorystoragetime.setText(intlist.get(position).get("content2"));//盘点出库日期
        viewHold.tv_intwarehouse.setText(intlist.get(position).get("content3"));//仓库
        viewHold.tv_intbatch.setText(intlist.get(position).get("content4"));//批号
        viewHold.tv_intMaterialcoding.setText(intlist.get(position).get("content5"));// 物资编码
        viewHold.tv_intMaterialname.setText(intlist.get(position).get("content6"));//物资名称
        viewHold.tv_intLocationname.setText(intlist.get(position).get("content7"));//货位名称
        viewHold.tv_intLocationcoding.setText(intlist.get(position).get("content8"));//货位编码
        viewHold.tv_intspecifications1.setText(intlist.get(position).get("content9"));//规格型号1
        viewHold.tv_intspecifications2.setText(intlist.get(position).get("content10"));//规格型号2
        viewHold.tv_intnumber.setText(intlist.get(position).get("content11"));//数量
        viewHold.tv_intunitprice.setText(intlist.get(position).get("content12"));//单价
        viewHold.tv_intTotalamount.setText(intlist.get(position).get("content13"));//合计金额
        viewHold.tv_intNotaxunitprice.setText(intlist.get(position).get("content14"));//无税单价
        viewHold.tv_intNotaxunitamoun.setText(intlist.get(position).get("content15"));//无税金额
        viewHold.tv_intResponsibleforinventory.setText(intlist.get(position).get("content16"));//负责盘点人
        viewHold.tv_intSingleMan.setText(intlist.get(position).get("content17"));//制单人
        viewHold.tv_intAuditorMan.setText(intlist.get(position).get("content18"));//审核人
        viewHold.tv_intdepartment.setText(intlist.get(position).get("content19"));//部门
        viewHold.tv_intRemarks.setText(intlist.get(position).get("content20"));//备注
        viewHold.tv_intdigital.setText((position+1)+"");//显示条目
        viewHold.outBox2.setChecked(intlist.get(position).get("flag").equals("true"));//复选框
        return convertView;
    }

    public static class ViewHold {
        public CheckBox outBox2 ;
        TextView  tv_Inventorystorage,tv_Inventorystoragetime,tv_intwarehouse,tv_intbatch,tv_intMaterialcoding,tv_intMaterialname,tv_intLocationname,tv_intLocationcoding
                ,tv_intspecifications1,tv_intspecifications2,tv_intnumber,tv_intunitprice,tv_intTotalamount,tv_intNotaxunitprice,tv_intNotaxunitamoun,tv_intResponsibleforinventory
                ,tv_intSingleMan,tv_intAuditorMan,tv_intdepartment,tv_intRemarks,tv_intdigital;
    }
    //隐藏复选框的方法,如果传进来的参数为1，就隐藏复选框
    public void hind(int i) {
        this.i = i;
    }
}
