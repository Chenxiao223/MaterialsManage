package com.zjrfid.materialsmanage.TreeViewTool;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


import com.google.gson.Gson;
import com.zjrfid.materialsmanage.UI.ActivityBinding;
import com.zjrfid.materialsmanage.UI.ActivityMaterialsInBound;
import com.zjrfid.materialsmanage.UI.Activity_TreeView;
import com.zjrfid.materialsmanage.acdbentity.Archives;
import com.zjrfid.materialsmanage.acdbentity.SupplierInfo;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Administrator on 2016/12/7.
 */
public class TreeViewItemClickListener implements OnItemClickListener {
    private ArrayList<HashMap<String, String>> dataon;

    /**
     * adapter
     */
    private TreeViewAdapter treeViewAdapter;
    Context mcontext = null;
    private int i;

    public TreeViewItemClickListener(TreeViewAdapter treeViewAdapter, int i) {
        this.treeViewAdapter = treeViewAdapter;
        this.i = i;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        //点击的item代表的元素
        Element element = (Element) treeViewAdapter.getItem(position);
        //树中的元素
        ArrayList<Element> elements = treeViewAdapter.getElements();
        //元素的数据源
        ArrayList<Element> elementsData = treeViewAdapter.getElementsData();
        //点击没有子项的item直接返回
        if (!element.isHasChildren()) {
            if (i == 0) {//这里0表示物资绑定
                ActivityBinding.ll_orientation.setVisibility(View.VISIBLE);
                ActivityBinding.back.setVisibility(View.VISIBLE);
                //请求网络
                String str = ActivityBinding.binding.mcc.getJsonData().getList().get(position - 1).getCinvccode();
                NetworkRequests(str);
            } else {
                Activity_TreeView.ll_orientation.setVisibility(View.VISIBLE);
                Activity_TreeView.back.setVisibility(View.VISIBLE);
                //请求网络  2 4
                String str;
                if (position==2){
                    str = "0101";
                }else{
                    str="0201";
                }
                NetworkRequests2(str);
            }
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

    public void NetworkRequests(String parameter) {
        //物资档案详细信息接口
        HttpNetworkRequest.get("goods/rs/hpInventory?pageNum=1&hpicGuid=" + parameter + "&cinvcname=&cinvname=&cinvstd=", new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, String s, Object o) {
                Gson gson = new Gson();
                Archives archives = gson.fromJson(s, Archives.class);
                if (archives.getStatusCode().equals("200")) {//如果请求成功则执行
                    int size = archives.getJsonData().getList().size();
                    /**
                     * 这里需要把两个集合都清空一下，因为每次点击会重新请求网络获取数据，不清空的话下一次请求时还会保存上一次请求的数据
                     */
                    ActivityBinding.binding.leftlList.clear();
                    ActivityBinding.binding.models.clear();
                    for (int j = 0; j < size; j++) {
                        ActivityBinding.binding.leftlList.add(archives.getJsonData().getList().get(j).getCINVCODE());
                        ActivityBinding.binding.models.add(new RightModel(archives.getJsonData().getList().get(j).getCINVNAME(),
                                archives.getJsonData().getList().get(j).getCINVSTD(),
                                archives.getJsonData().getList().get(j).getOLDUNITNAME(), archives.getJsonData().getList().get(j).getCINVCNAME(),
                                archives.getJsonData().getList().get(j).getBUSINFO(), archives.getJsonData().getList().get(j).getCWHNAME(),
                                archives.getJsonData().getList().get(j).getCPARENTID(), archives.getJsonData().getList().get(j).getOLDCORD(),
                                archives.getJsonData().getList().get(j).getRFID(), estimate(archives, j)));
                    }
                    ActivityBinding.binding.adapter.notifyDataSetChanged();
                    ActivityBinding.binding.myRightAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ActivityBinding.binding, "网络请求失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {

            }

        });
    }

    //判断RFID是否为null
    public int estimate(Archives archives, int j) {
        if (archives.getJsonData().getList().get(j).getRFID() == null) {
            return 0;
        } else {
            return 1;
        }
    }

    public void NetworkRequests2(String parameter) {
        //供应商信息接口
        HttpNetworkRequest.get("goods/rs/hpSuppliers?pageNum=1&hpsGuid="+parameter+"&cvencode=&cvenname=&numPerPage=10", new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, String s, Object o) {
                Gson gson = new Gson();
                SupplierInfo supplierInfo = gson.fromJson(s, SupplierInfo.class);
                if (supplierInfo.getStatusCode().equals("200")) {//如果请求成功则执行
                    int size = supplierInfo.getJsonData().getList().size();
                    /**
                     * 这里需要把两个集合都清空一下，因为每次点击会重新请求网络获取数据，不清空的话下一次请求时还会保存上一次请求的数据
                     */
                    Activity_TreeView.treeView.leftlList.clear();
                    Activity_TreeView.treeView.models.clear();
                    for (int j = 0; j < size; j++) {
                        Activity_TreeView.treeView.leftlList.add(supplierInfo.getJsonData().getList().get(j).getHpsGuid());
                        Activity_TreeView.treeView.models.add(new RightModel(supplierInfo.getJsonData().getList().get(j).getHpsGuid(),
                                supplierInfo.getJsonData().getList().get(j).getCvenname(),supplierInfo.getJsonData().getList().get(j).getHpsnGuid(), "","", "","", "","", 1));

                    }
                    Activity_TreeView.treeView.adapter.notifyDataSetChanged();
                    Activity_TreeView.treeView.myRightAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(Activity_TreeView.treeView, "网络请求失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {

            }

        });
    }
}

