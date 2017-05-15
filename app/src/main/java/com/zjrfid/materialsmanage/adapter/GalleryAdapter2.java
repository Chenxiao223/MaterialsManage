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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GalleryAdapter2 extends
        RecyclerView.Adapter<GalleryAdapter2.ViewHolder> {

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private LayoutInflater mInflater;
    private ArrayList<HashMap<String, String>> mDatas;

    public GalleryAdapter2(Context context, ArrayList<HashMap<String, String>> list) {
        mInflater = LayoutInflater.from(context);
        mDatas = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        TextView text1, text2, text3, text4, text5;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.list_batch2,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.text1 = (TextView) view.findViewById(R.id.text1);
        viewHolder.text2 = (TextView) view.findViewById(R.id.text2);
        viewHolder.text3 = (TextView) view.findViewById(R.id.text3);
        viewHolder.text4 = (TextView) view.findViewById(R.id.text4);
        viewHolder.text5 = (TextView) view.findViewById(R.id.text5);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.text1.setText(mDatas.get(i).get("content1"));
        viewHolder.text2.setText(mDatas.get(i).get("content2"));
        viewHolder.text3.setText(mDatas.get(i).get("content3"));
        viewHolder.text4.setText(mDatas.get(i).get("content4"));
        viewHolder.text5.setText(mDatas.get(i).get("content5"));

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
