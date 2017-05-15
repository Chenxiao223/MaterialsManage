package com.zjrfid.materialsmanage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.acdbentity.House;

import java.util.List;

public class GalleryAdapter extends
        RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private LayoutInflater mInflater;
    private List<House> mDatas;

    public GalleryAdapter(Context context, List<House> datats) {
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        TextView tv_wzbianma, tv_wzname, tv_wzshuliang, tv_wzhansuidanjia, tv_wzhuowei, tv_wzpici, rkshuliang;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.list_batch,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.tv_wzbianma = (TextView) view.findViewById(R.id.tv_wzbianma);
        viewHolder.tv_wzname = (TextView) view.findViewById(R.id.tv_wzname);
        viewHolder.tv_wzshuliang = (TextView) view.findViewById(R.id.tv_wzshuliang);
        viewHolder.tv_wzhansuidanjia = (TextView) view.findViewById(R.id.tv_wzhansuidanjia);
        viewHolder.tv_wzhuowei = (TextView) view.findViewById(R.id.tv_wzhuowei);
        viewHolder.tv_wzpici = (TextView) view.findViewById(R.id.tv_wzpici);
        viewHolder.rkshuliang = (TextView) view.findViewById(R.id.rkshuliang);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        House houses =mDatas.get(i);
        viewHolder.tv_wzbianma.setText("物资编码："+houses.getBianma());
        viewHolder.tv_wzname.setText("物资名称："+houses.getName());
        viewHolder.tv_wzshuliang.setText("库存数量："+houses.getWzshuliang());
        viewHolder.tv_wzhansuidanjia.setText("物资单价："+houses.getWzdanjia());
        viewHolder.tv_wzhuowei.setText("货位："+houses.getHuowei());
        viewHolder.tv_wzpici.setText("批次："+houses.getPici());
        viewHolder.rkshuliang.setText(""+houses.getRkshuliang());

        if (mOnItemClickLitener != null) {
            viewHolder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, i);
                }
            });

        }

    }

}
