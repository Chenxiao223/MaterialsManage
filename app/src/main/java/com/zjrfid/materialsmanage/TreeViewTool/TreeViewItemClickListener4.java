package com.zjrfid.materialsmanage.TreeViewTool;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.UI.ActivityGoodsAllocation;
import com.zjrfid.materialsmanage.UI.ActivityGoodsAllocationRFIDBind;
import com.zjrfid.materialsmanage.acdbentity.CgoodsAllocation;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Administrator on 2016/12/7.
 */
public class TreeViewItemClickListener4 implements OnItemClickListener {
    private ArrayList<HashMap<String, String>> dataon;
    private String code="";
    Element element;
    /**
     * adapter
     */
    private TreeViewAdapter treeViewAdapter;
    Context mcontext = null;

    public TreeViewItemClickListener4(TreeViewAdapter treeViewAdapter) {
        this.treeViewAdapter = treeViewAdapter;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        //点击的item代表的元素
        element = (Element) treeViewAdapter.getItem(position);
        //树中的元素
        ArrayList<Element> elements = treeViewAdapter.getElements();
        //元素的数据源
        ArrayList<Element> elementsData = treeViewAdapter.getElementsData();

        //点击没有子项的item直接返回
        if (!element.isHasChildren()) {
            String scode =element.getContentText().substring(0, element.getContentText().indexOf(" "));//货位编码
            ActivityGoodsAllocationRFIDBind.goodsAllocationRfidBind.request_GoodsAllcationRfid(scode.substring(0,2)+scode);
            return;
        }
        if (element.isExpanded()) {
            element.setExpanded(false);
            //删除节点内部对应子节点数据，包括子节点的子节点...
            ArrayList<Element> elementsToDel = new ArrayList<Element>();
            for (int i = position + 1; i < elements.size(); i++) {
                if (element.getLevel() >= elements.get(i).getLevel())
                    break;
                elementsToDel.add(elements.get(i));
            }
            elements.removeAll(elementsToDel);
            treeViewAdapter.notifyDataSetChanged();
        } else {
            element.setExpanded(true);
            //从数据源中提取子节点数据添加进树，注意这里只是添加了下一级子节点，为了简化逻辑
            int i = 1;//注意这里的计数器放在for外面才能保证计数有效
            for (Element e : elementsData) {
                if (e.getParendId() == element.getId()) {
                    e.setExpanded(false);
                    elements.add(position + i, e);
                    i++;
                }
            }
            treeViewAdapter.notifyDataSetChanged();
        }
    }

    private String RequestGoodsAllocationInfo(String str_cparentid) {
        try {
            RequestParams rp = new RequestParams();
            rp.put("cposcode", "");
            rp.put("cparentid", str_cparentid.trim());
            rp.put("hpwGuid", ActivityGoodsAllocation.cwhcodeFromOuter);

            HttpNetworkRequest.get("goods/rs/hpPositionRefer", rp, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {
                    Gson gson = new Gson();
                    CgoodsAllocation mCGA = gson.fromJson(s, CgoodsAllocation.class);
                    try {
                        code=mCGA.getJsonData().getList().get(0).getCposcode();
                        ActivityGoodsAllocation.goodsAllocation.return_value(element.getContentText(),code);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(mcontext, "出错", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mcontext, "出错", Toast.LENGTH_SHORT).show();
        }
        return code;
    }

}

