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
public class AdapterLossesOut extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> outlist;
    private int i = 0;

    public AdapterLossesOut(Context context, ArrayList<HashMap<String, String>> outlist) {
        this.context = context;
        this.outlist = outlist;
        inflater = LayoutInflater.from(context);
    }




    @Override
    public int getCount() {
        return outlist.size();
    }

    @Override
    public Object getItem(int position) {
        return outlist.get(position);
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
            convertView = inflater.inflate(R.layout.list_inventorylossesout, null);
            viewHold.tv_InventoryOutbound= (TextView) convertView.findViewById(R.id.tv_InventoryOutbound);
            viewHold.tv_InventoryOutboundtime= (TextView) convertView.findViewById(R.id.tv_InventoryOutboundtime);
            viewHold.tv_outwarehouse= (TextView) convertView.findViewById(R.id.tv_outwarehouse);
            viewHold.tv_outbatch= (TextView) convertView.findViewById(R.id.tv_outbatch);
            viewHold.tv_outMaterialcoding= (TextView) convertView.findViewById(R.id.tv_outMaterialcoding);
            viewHold.tv_outMaterialname= (TextView) convertView.findViewById(R.id.tv_outMaterialname);
            viewHold.tv_outLocationname= (TextView) convertView.findViewById(R.id.tv_outLocationname);
            viewHold.tv_outLocationcoding= (TextView) convertView.findViewById(R.id.tv_outLocationcoding);
            viewHold.tv_outspecifications1= (TextView) convertView.findViewById(R.id.tv_outspecifications1);
            viewHold.tv_outspecifications2= (TextView) convertView.findViewById(R.id.tv_outspecifications2);
            viewHold.tv_outnumber= (TextView) convertView.findViewById(R.id.tv_outnumber);
            viewHold.tv_outunitprice= (TextView) convertView.findViewById(R.id.tv_outunitprice);
            viewHold.tv_outTotalamount= (TextView) convertView.findViewById(R.id.tv_outTotalamount);
            viewHold.tv_outNotaxunitprice= (TextView) convertView.findViewById(R.id.tv_outNotaxunitprice);
            viewHold.tv_outNotaxunitamoun= (TextView) convertView.findViewById(R.id.tv_outNotaxunitamoun);
            viewHold.tv_outResponsibleforinventory= (TextView) convertView.findViewById(R.id.tv_outResponsibleforinventory);
            viewHold.tv_outSingleMan= (TextView) convertView.findViewById(R.id.tv_outSingleMan);
            viewHold.tv_outAuditorMan= (TextView) convertView.findViewById(R.id.tv_outAuditorMan);
            viewHold.tv_outdepartment= (TextView) convertView.findViewById(R.id.tv_outdepartment);
            viewHold.tv_outRemarks= (TextView) convertView.findViewById(R.id.tv_outRemarks);
            viewHold.tv_outdigital= (TextView) convertView.findViewById(R.id.tv_outdigital);
            viewHold.outBox = (CheckBox) convertView.findViewById(R.id.outcheckBox1);
            if (i == 1) {//如果传进来的参数为1，就隐藏复选框
                viewHold.outBox.setVisibility(View.GONE);
            }
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.tv_InventoryOutbound.setText(outlist.get(position).get("content1"));//盘点出库单号
        viewHold.tv_InventoryOutboundtime.setText(outlist.get(position).get("content2"));//盘点出库日期
        viewHold.tv_outwarehouse.setText(outlist.get(position).get("content3"));//仓库
        viewHold.tv_outbatch.setText(outlist.get(position).get("content4"));//批号
        viewHold.tv_outMaterialcoding.setText(outlist.get(position).get("content5"));// 物资编码
        viewHold.tv_outMaterialname.setText(outlist.get(position).get("content6"));//物资名称
        viewHold.tv_outLocationname.setText(outlist.get(position).get("content7"));//货位名称
        viewHold.tv_outLocationcoding.setText(outlist.get(position).get("content8"));//货位编码
        viewHold.tv_outspecifications1.setText(outlist.get(position).get("content9"));//规格型号1
        viewHold.tv_outspecifications2.setText(outlist.get(position).get("content10"));//规格型号2
        viewHold.tv_outnumber.setText(outlist.get(position).get("content11"));//数量
        viewHold.tv_outunitprice.setText(outlist.get(position).get("content12"));//单价
        viewHold.tv_outTotalamount.setText(outlist.get(position).get("content13"));//合计金额
        viewHold.tv_outNotaxunitprice.setText(outlist.get(position).get("content14"));//无税单价
        viewHold.tv_outNotaxunitamoun.setText(outlist.get(position).get("content15"));//无税金额
        viewHold.tv_outResponsibleforinventory.setText(outlist.get(position).get("content16"));//负责盘点人
        viewHold.tv_outSingleMan.setText(outlist.get(position).get("content17"));//制单人
        viewHold.tv_outAuditorMan.setText(outlist.get(position).get("content18"));//审核人
        viewHold.tv_outdepartment.setText(outlist.get(position).get("content19"));//部门
        viewHold.tv_outRemarks.setText(outlist.get(position).get("content20"));//备注
        viewHold.tv_outdigital.setText((position+1)+"");//显示条目
        viewHold.outBox.setChecked(outlist.get(position).get("flag").equals("true"));//复选框
        return convertView;
    }

    public static class ViewHold {
        public CheckBox outBox ;
        TextView  tv_InventoryOutbound,tv_InventoryOutboundtime,tv_outwarehouse,tv_outbatch,tv_outMaterialcoding,tv_outMaterialname,tv_outLocationname,tv_outLocationcoding
                ,tv_outspecifications1,tv_outspecifications2,tv_outnumber,tv_outunitprice,tv_outTotalamount,tv_outNotaxunitprice,tv_outNotaxunitamoun,tv_outResponsibleforinventory
                ,tv_outSingleMan,tv_outAuditorMan,tv_outdepartment,tv_outRemarks,tv_outdigital;
    }
    //隐藏复选框的方法,如果传进来的参数为1，就隐藏复选框
    public void hind(int i) {
        this.i = i;
    }
}
