package com.zjrfid.materialsmanage.TreeViewTool;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


import com.zjrfid.materialsmanage.UI.ActivityHomePage;
import com.zjrfid.materialsmanage.UI.Activity_TreeView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Administrator on 2016/12/7.
 */
public class TreeViewItemClickListener2 implements OnItemClickListener {
    private ArrayList<HashMap<String, String>> dataon;
    /**
     * adapter
     */
    private TreeViewAdapter treeViewAdapter;
    Context mcontext = null;

    public TreeViewItemClickListener2(TreeViewAdapter treeViewAdapter) {
        this.treeViewAdapter = treeViewAdapter;
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
            if (judge(element.getContentText())) {
                Activity_TreeView.treeView.return_value(element.getContentText());
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

    //判断是不是在仓库权限内
    public boolean judge(String hh) {
        boolean is = false;
        String cincode = hh.substring(0, hh.indexOf(" "));
        if (ActivityHomePage.homePage.list_code.contains(cincode)) {
            is = true;
        }
        return is;
    }

}

