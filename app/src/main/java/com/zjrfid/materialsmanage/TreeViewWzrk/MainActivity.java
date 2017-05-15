package com.zjrfid.materialsmanage.TreeViewWzrk;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.TreeViewTool.TakePhotoPopWin;
import com.zjrfid.materialsmanage.tool.SysApplication;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * 树中的元素集合
     */
    private ArrayList<Element> elements;
    /**
     * 数据源元素集合
     */
    private ArrayList<Element> elementsData;
    static LinearLayout linearLayout;
    private ListView listView;
    private MyAdapter myAdapter;
    private List<String> rightlist;
    public static int i;
    static Context context;
    TreeViewAdapter treeViewAdapter;
    TreeViewItemClickListener treeViewItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.treeview_wzrk);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SysApplication.getInstance().addActivity(this);
        init();
        linearLayout = (LinearLayout) findViewById(R.id.ll_list);
        listView = (ListView) findViewById(R.id.listview);
        ListView treeview = (ListView) findViewById(R.id.treeview);


        initRightData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TakePhotoPopWin.instance.tv_huowei.setText(rightlist.get(position));
                finish();
            }
        });
        treeViewAdapter = new TreeViewAdapter(elements, elementsData, inflater, this);
        treeViewItemClickListener = new TreeViewItemClickListener(treeViewAdapter);
        treeview.setAdapter(treeViewAdapter);
        treeview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击的item代表的元素
                Element element = (Element) treeViewAdapter.getItem(position);
                //树中的元素
                ArrayList<Element> elements = treeViewAdapter.getElements();
                //元素的数据源
                ArrayList<Element> elementsData = treeViewAdapter.getElementsData();

                //点击没有子项的item直接返回
                if (!element.isHasChildren()) {
                    if (element.getContentText().equals("01 配件仓库")) {
                        i = 0;
                    } else if (element.getContentText().equals("01 办公室用品仓库")) {
                        i = 1;
                    } else if (element.getContentText().equals("01 工作服劳保用品仓库")) {
                        i = 2;
                    } else {
                        i = 3;
                    }
                    initRightData();
                    MainActivity.linearLayout.setVisibility(View.VISIBLE);
                    findViewById(R.id.back).setVisibility(View.VISIBLE);//回退按钮显示
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
        });


    }

    private void initRightData() {
        if (i == 0) {
            rightlist = new ArrayList<String>();
            rightlist.add("0101100001 一楼A01货架第一层第一格");
            rightlist.add("0101100002 一楼A01货架第一层第二格");
            rightlist.add("0101100003 一楼A01货架第二层第一格");
            rightlist.add("0101100004 一楼A01货架第二层第二格");
            rightlist.add("0101100005 一楼A01货架第三层第一格");
        } else if (i == 1) {
            rightlist = new ArrayList<String>();
            rightlist.add("0102200001 二楼G01货架");
            rightlist.add("0102200002 二楼G02货架");
            rightlist.add("0102200003 二楼G03");
            rightlist.add("0102200004 二楼H03");
            rightlist.add("0102200005 二楼H04");
        } else if (i == 2) {
            rightlist = new ArrayList<String>();
            rightlist.add("0103300001 二楼H01");
            rightlist.add("0103300002 二楼H03");
            rightlist.add("0103300003 二楼H02");
        } else if (i == 3) {
            rightlist = new ArrayList<String>();
            rightlist.add("0104400001 W01");
            rightlist.add("0104400002 W02");
            rightlist.add("0104400003 W03");
            rightlist.add("0104400004 W04");
        }
        myAdapter = new MyAdapter(MainActivity.this, rightlist);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }


    private void init() {
        elements = new ArrayList<Element>();
        elementsData = new ArrayList<Element>();

        //添加节点  -- 节点名称，节点level，节点id，父节点id，是否有子节点，是否展开

        //添加最外层节点
        Element e1 = new Element("01 配件仓库", Element.TOP_LEVEL, 0, Element.NO_PARENT, false, false);
        //添加最外层节点
        Element e2 = new Element("01 办公室用品仓库", Element.TOP_LEVEL, 0, Element.NO_PARENT, false, false);
        //添加最外层节点
        Element e3 = new Element("01 工作服劳保用品仓库", Element.TOP_LEVEL, 0, Element.NO_PARENT, false, false);
        //添加最外层节点
        Element e4 = new Element("01 危险品仓库", Element.TOP_LEVEL, 0, Element.NO_PARENT, false, false);
        //添加最外层节点
        Element e5 = new Element("01 车载设备仓库", Element.TOP_LEVEL, 0, Element.NO_PARENT, false, false);
        //添加最外层节点
        Element e6 = new Element("01 轮胎仓库", Element.TOP_LEVEL, 0, Element.NO_PARENT, false, false);
        //添加最外层节点
        Element e7 = new Element("01 电瓶仓库", Element.TOP_LEVEL, 0, Element.NO_PARENT, false, false);

        //添加初始树元素
        elements.add(e1);
        elements.add(e2);
        elements.add(e3);
        elements.add(e4);
        elements.add(e5);
        elements.add(e6);
        elements.add(e7);
        //创建数据源
        elementsData.add(e1);
        elementsData.add(e2);
        elementsData.add(e3);
        elementsData.add(e4);
        elementsData.add(e5);
        elementsData.add(e6);
        elementsData.add(e7);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    //点击回退
    public void btn_back(View view) {
        linearLayout.setVisibility(View.GONE);
    }
    //点击箭头回退
    public void back(View view) {
        finish();
    }
}
