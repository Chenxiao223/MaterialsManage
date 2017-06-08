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
public class AdapterCheckLossesOut extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> checkoutlist;
    private int i = 0;

    public AdapterCheckLossesOut(Context context, ArrayList<HashMap<String, String>> checkoutlist) {
        this.context = context;
        this.checkoutlist = checkoutlist;
        inflater = LayoutInflater.from(context);
    }




    @Override
    public int getCount() {
        return checkoutlist.size();
    }

    @Override
    public Object getItem(int position) {
        return checkoutlist.get(position);
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
            convertView = inflater.inflate(R.layout.list_lossesout, null);
            viewHold.tv_checkOutbatch= (TextView) convertView.findViewById(R.id.tv_checkOutbatch);
            viewHold.tv_checkOutMaterialcoding= (TextView) convertView.findViewById(R.id.tv_checkOutMaterialcoding);
            viewHold.tv_checkOutLocationname= (TextView) convertView.findViewById(R.id.tv_checkOutLocationname);
            viewHold.tv_checkOutLocationcoding= (TextView) convertView.findViewById(R.id.tv_checkOutLocationcoding);
            viewHold.tv_checkOutspecifications1= (TextView) convertView.findViewById(R.id.tv_checkOutspecifications1);
            viewHold.tv_checkOutspecifications2= (TextView) convertView.findViewById(R.id.tv_checkOutspecifications2);
            viewHold.tv_checkOutnumber= (TextView) convertView.findViewById(R.id.tv_checkOutnumber);
            viewHold.tv_checkOutunitprice= (TextView) convertView.findViewById(R.id.tv_checkOutunitprice);
            viewHold.tv_checkOutTotalamount= (TextView) convertView.findViewById(R.id.tv_checkOutTotalamount);
            viewHold.tv_checkOutNotaxunitprice= (TextView) convertView.findViewById(R.id.tv_checkOutNotaxunitprice);
            viewHold.tv_checkOutNotaxunitamoun= (TextView) convertView.findViewById(R.id.tv_checkOutNotaxunitamoun);
            viewHold.tv_checkOutResponsibleforinventory= (TextView) convertView.findViewById(R.id.tv_checkOutResponsibleforinventory);
            viewHold.tv_checkOutRemarks= (TextView) convertView.findViewById(R.id.tv_checkOutRemarks);
            viewHold.tv_checkOutdigital= (TextView) convertView.findViewById(R.id.tv_checkOutdigital);
            viewHold.checkOutBox1 = (CheckBox) convertView.findViewById(R.id.checkOutBox1);
            if (i == 1) {//如果传进来的参数为1，就隐藏复选框
                viewHold.checkOutBox1.setVisibility(View.GONE);
            }
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.tv_checkOutMaterialcoding.setText(checkoutlist.get(position).get("content1"));// 物资编码
        viewHold.tv_checkOutbatch.setText(checkoutlist.get(position).get("content2"));//批号
        viewHold.tv_checkOutLocationname.setText(checkoutlist.get(position).get("content3"));//物资名称
        viewHold.tv_checkOutLocationcoding.setText(checkoutlist.get(position).get("content4"));//货位编码
        viewHold.tv_checkOutspecifications1.setText(checkoutlist.get(position).get("content5"));//规格型号1
        viewHold.tv_checkOutspecifications2.setText(checkoutlist.get(position).get("content6"));//单位
        viewHold.tv_checkOutnumber.setText(checkoutlist.get(position).get("content7"));//数量
        viewHold.tv_checkOutunitprice.setText(checkoutlist.get(position).get("content8"));//单价
        viewHold.tv_checkOutTotalamount.setText(checkoutlist.get(position).get("content9"));//合计金额
        viewHold.tv_checkOutNotaxunitprice.setText(checkoutlist.get(position).get("content10"));//无税单价
        viewHold.tv_checkOutNotaxunitamoun.setText(checkoutlist.get(position).get("content11"));//无税金额
        viewHold.tv_checkOutResponsibleforinventory.setText(checkoutlist.get(position).get("content12"));//负责盘点人
        viewHold.tv_checkOutRemarks.setText(checkoutlist.get(position).get("content13"));//备注
        viewHold.tv_checkOutdigital.setText((position+1)+"");//显示条目
        viewHold.checkOutBox1.setChecked(checkoutlist.get(position).get("flag").equals("true"));//复选框
        return convertView;
    }

    public static class ViewHold {
        public CheckBox checkOutBox1 ;
        TextView   tv_checkOutMaterialcoding,tv_checkOutbatch, tv_checkOutLocationname, tv_checkOutLocationcoding, tv_checkOutspecifications1,
        tv_checkOutspecifications2, tv_checkOutnumber, tv_checkOutunitprice, tv_checkOutTotalamount, tv_checkOutNotaxunitprice, tv_checkOutNotaxunitamoun,
       tv_checkOutResponsibleforinventory, tv_checkOutRemarks, tv_checkOutdigital;
    }
    //隐藏复选框的方法,如果传进来的参数为1，就隐藏复选框
    public void hind(int i) {
        this.i = i;
    }
}
