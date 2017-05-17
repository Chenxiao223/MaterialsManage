package com.zjrfid.materialsmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.acdbentity.House;
import com.zjrfid.materialsmanage.acdbentity.House3;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public class HouseAdapter2 extends BaseAdapter {
    private Context context;
    private List<House3> ll;
    private LayoutInflater mInflater;

    public HouseAdapter2(Context context, List<House3> ll) {
        super();
        this.context = context;
        this.ll = ll;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return ll.size();
    }

    @Override
    public Object getItem(int position) {
        return ll.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_batch3, null);
            viewHolder = new ViewHolder();
            viewHolder.wzdanjia = (TextView) convertView.findViewById(R.id.tv_wzhansuidanjia);
            viewHolder.wzshuliang = (TextView) convertView.findViewById(R.id.tv_wzshuliang);
            viewHolder.bianma = (TextView) convertView.findViewById(R.id.tv_wzbianma);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_wzname);
            viewHolder.huowei = (TextView) convertView.findViewById(R.id.tv_wzhuowei);
            viewHolder.pici = (TextView) convertView.findViewById(R.id.tv_wzpici);
            viewHolder.tv_cwhname= (TextView) convertView.findViewById(R.id.tv_cwhname);
            convertView.setTag(viewHolder); // 将缓存对象作为标记保存到可复用view之上
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final House3 houses = ll.get(position);
        viewHolder.wzdanjia.setText("物资单价：" + houses.getWzdanjia());
        viewHolder.wzshuliang.setText("库存数量：" + houses.getWzshuliang());
        viewHolder.bianma.setText("物资编码：" + houses.getBianma());
        viewHolder.name.setText("物资名称：" + houses.getName());
        viewHolder.huowei.setText("货位：" + houses.getHuowei());
        viewHolder.pici.setText("批次：" + houses.getPici());
        viewHolder.tv_cwhname.setText("仓库："+houses.getCwhname());
        return convertView;
    }

    public static class ViewHolder {
        TextView wzshuliang, wzdanjia, bianma, name, huowei, pici,tv_cwhname;
    }

}
