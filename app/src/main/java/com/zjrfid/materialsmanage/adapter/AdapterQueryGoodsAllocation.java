package com.zjrfid.materialsmanage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.zjrfid.materialsmanage.R;
import java.util.List;

/**
 * Created by zhouyu on 2017/5/4.
 */
public class AdapterQueryGoodsAllocation extends BaseAdapter{

    private List<String> mList;
    private LayoutInflater mInflater;//布局装载器对象

    public AdapterQueryGoodsAllocation(Context context,List<String> mList) {
        this.mList = mList;
        this.mInflater =LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        //将布局文件转化为View对象
        //如果view未被实例化过，缓存池中没有对应的缓存
        if (convertView == null) {
            viewHolder = new ViewHolder();
            // 由于我们只需要将XML转化为View，并不涉及到具体的布局，所以第二个参数通常设置为null
            convertView = mInflater.inflate(R.layout.list_goods_allocation_result, null);
            viewHolder.GoodsAlias = (TextView) convertView.findViewById(R.id.ResultItem);
            convertView.setTag(viewHolder);
        }else{//如果缓存池中有对应的view缓存，则直接通过getTag取出viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // 设置控件的数据
        viewHolder.GoodsAlias.setText(mList.get(position));

        return convertView;
    }


    class ViewHolder{

        public TextView GoodsAlias;

    }
}
