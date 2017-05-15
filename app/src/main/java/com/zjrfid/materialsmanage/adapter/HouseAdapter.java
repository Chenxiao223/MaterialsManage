package com.zjrfid.materialsmanage.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.UI.ActivityBatch;
import com.zjrfid.materialsmanage.acdbentity.House;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public class HouseAdapter extends BaseAdapter {
    private Context context;
    private List<House> ll;
    private LayoutInflater mInflater;
    OnItemEditText onItemEditText;
    private int item;

    public void setOnItemEditText(OnItemEditText onItemEditText) {
        this.onItemEditText = onItemEditText;
    }

    public HouseAdapter(Context context, List<House> ll) {
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
            convertView = mInflater.inflate(R.layout.list_batch, null);
            viewHolder = new ViewHolder();
            viewHolder.wzdanjia = (TextView) convertView.findViewById(R.id.tv_wzhansuidanjia);
            viewHolder.wzshuliang = (TextView) convertView.findViewById(R.id.tv_wzshuliang);
            viewHolder.bianma = (TextView) convertView.findViewById(R.id.tv_wzbianma);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_wzname);
            viewHolder.huowei = (TextView) convertView.findViewById(R.id.tv_wzhuowei);
            viewHolder.pici = (TextView) convertView.findViewById(R.id.tv_wzpici);
            viewHolder.editText = (EditText) convertView.findViewById(R.id.rkshuliang);
            viewHolder.cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
            convertView.setTag(viewHolder); // 将缓存对象作为标记保存到可复用view之上
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final House houses = ll.get(position);
        viewHolder.wzdanjia.setText("物资单价：" + houses.getWzdanjia());
        viewHolder.wzshuliang.setText("库存数量：" + houses.getWzshuliang());
        viewHolder.editText.setText("" + houses.getRkshuliang());
        viewHolder.bianma.setText("物资编码：" + houses.getBianma());
        viewHolder.name.setText("物资名称：" + houses.getName());
        viewHolder.huowei.setText("货位：" + houses.getHuowei());
        viewHolder.pici.setText("批次：" + houses.getPici());
        viewHolder.cb.setChecked(houses.getIs().equals("true"));
        final ViewHolder finalViewHolder1 = viewHolder;
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalViewHolder1.cb.isChecked()) {
                    ActivityBatch.batch.mlist.get(position).setIs("true");
                } else {
                    ActivityBatch.batch.mlist.get(position).setIs("false");
                }
            }
        });
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    int rk = houses.getWzshuliang();
                    int num = Integer.parseInt(s.toString());
                    if (rk < num) {
                        Toast.makeText(context, "库存不足！", Toast.LENGTH_SHORT).show();
                        finalViewHolder.editText.setText("");
                    } else {
                        onItemEditText.setText(s.toString(), position);
                    }
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return convertView;
    }

    public static class ViewHolder {
        TextView wzshuliang, wzdanjia, bianma, name, huowei, pici;
        EditText editText;
        public CheckBox cb;
    }

    public interface OnItemEditText {
        public void setText(String s, int p);
    }

}
