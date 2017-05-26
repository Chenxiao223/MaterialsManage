package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.TreeViewTool.Element;
import com.zjrfid.materialsmanage.TreeViewTool.MyLeftAdapter;
import com.zjrfid.materialsmanage.TreeViewTool.MyRightAdapter;
import com.zjrfid.materialsmanage.TreeViewTool.MyRightSupplierAdapter;
import com.zjrfid.materialsmanage.TreeViewTool.RightModel;
import com.zjrfid.materialsmanage.TreeViewTool.SyncHorizontalScrollView;
import com.zjrfid.materialsmanage.TreeViewTool.TreeViewAdapter;
import com.zjrfid.materialsmanage.TreeViewTool.TreeViewItemClickListener;
import com.zjrfid.materialsmanage.TreeViewTool.TreeViewItemClickListener2;
import com.zjrfid.materialsmanage.TreeViewTool.UtilTools;
import com.zjrfid.materialsmanage.acdbentity.IntRequsetBackRecord;
import com.zjrfid.materialsmanage.acdbentity.SupplierInfo;
import com.zjrfid.materialsmanage.acdbentity.WarehouseName;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.tool.SysApplication;

import org.apache.http.Header;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/12/23 0023.
 */
public class Activity_TreeView extends Activity {

    public IntRequsetBackRecord new_Right_IRBR =new IntRequsetBackRecord(0,0,0,0);
    public String new_treeview_hpsGuid="";// 被点击的供应商分类主键

    public TextView currentNum,totalNum;
    private HashMap<String, Integer> hashMapMySelf = new HashMap<>();
    private HashMap<Integer, String> hashMapParentSelf = new HashMap<>();
    private ArrayList<Element> list_element = new ArrayList<>();
    public ScrollView scrollView;
    public static Activity_TreeView treeView;
    /**
     * 树中的元素集合
     */
    private ArrayList<Element> elements;
    /**
     * 数据源元素集合
     */
    private ArrayList<Element> elementsData;
    protected static final int MSG_SHOW_Message = 1;

    private WarehouseName whname;
    private List<Integer> list_level = new ArrayList<>();//存节点level


    private ArrayList<Element> elements2;
    private ArrayList<Element> elementsData2;
    private int flag;
    public static LinearLayout ll_orientation;
    public static Button back;
    private LinearLayout leftContainerView;
    private ListView leftListView;
    private LinearLayout rightContainerView;
    private ListView rightListView;
    private SyncHorizontalScrollView titleHorsv;
    private SyncHorizontalScrollView contentHorsv;
    public List<RightModel> models = new ArrayList<>();
    public MyLeftAdapter adapter;
    public MyRightSupplierAdapter myRightAdapter;
    public List<String> leftlList = new ArrayList<>();
    public static HashMap<Integer, List> data = new HashMap<>();
    private TreeViewAdapter treeViewAdapter;

    private List<String>list_spin = new ArrayList<>();
    private Spinner spin_code;
    private ArrayAdapter<String> arrayAdapter;
    private int spinner_index=0;
    private EditText et_seek;
    public Button clear_wzcode_Btn;//清除物资搜索编码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treeview);


        clear_wzcode_Btn = (Button) findViewById(R.id.clear_wzcode_Btn);
        et_seek= (EditText) findViewById(R.id.et_seek);
        spin_code = (Spinner) findViewById(R.id.spin_code);
        currentNum = (TextView) findViewById(R.id.currentNum);
        totalNum = (TextView) findViewById(R.id.totalNum);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        treeView = this;
        SysApplication.getInstance().addActivity(this);
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", 10);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ll_orientation = (LinearLayout) findViewById(R.id.ll_orientation);
        back = (Button) findViewById(R.id.back);

        leftContainerView = (LinearLayout) findViewById(R.id.left_container);
        leftListView = (ListView) findViewById(R.id.left_container_listview);
        rightContainerView = (LinearLayout) findViewById(R.id.right_container);
        rightListView = (ListView) findViewById(R.id.right_container_listview);
        titleHorsv = (SyncHorizontalScrollView) findViewById(R.id.title_horsv);
        contentHorsv = (SyncHorizontalScrollView) findViewById(R.id.content_horsv);

        list_spin.add("供应商名称");
        list_spin.add("供应商编码");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_spin);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_code.setAdapter(arrayAdapter);
        spin_code.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_index = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        et_seek.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                listern_searchcode_isempty();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listern_searchcode_isempty();
            }

            @Override
            public void afterTextChanged(Editable s) {
                listern_searchcode_isempty();
            }
        });


        init();




        if (flag == 0 || flag == 1 || flag == 2 || flag == 3 || flag == 4 || flag == 5 || flag == 6 || flag == 7 || flag == 8) {
            ListView treeview = (ListView) findViewById(R.id.treeview);
            treeViewAdapter = new TreeViewAdapter(elements, list_element, inflater, this);
            TreeViewItemClickListener2 treeViewItemClickListener = new TreeViewItemClickListener2(treeViewAdapter);
            treeview.setAdapter(treeViewAdapter);
            treeview.setOnItemClickListener(treeViewItemClickListener);
        } else {
            ListView treeview = (ListView) findViewById(R.id.treeview);
            treeViewAdapter = new TreeViewAdapter(elements, list_element, inflater, this);
            TreeViewItemClickListener treeViewItemClickListener = new TreeViewItemClickListener(treeViewAdapter, 1);
            treeview.setAdapter(treeViewAdapter);
            treeview.setOnItemClickListener(treeViewItemClickListener);
        }
        // 设置两个水平控件的联动
        titleHorsv.setScrollView(contentHorsv);
        contentHorsv.setScrollView(titleHorsv);
        //添加左边内容数据
        leftContainerView.setBackgroundColor(Color.GRAY);
        adapter = new MyLeftAdapter(this, leftlList);
        leftListView.setAdapter(adapter);
        UtilTools.setListViewHeightBasedOnChildren(leftListView);

        // 添加右边内容数据
        rightContainerView.setBackgroundColor(Color.GRAY);
        myRightAdapter = new MyRightSupplierAdapter(this, models);
        rightListView.setAdapter(myRightAdapter);
        UtilTools.setListViewHeightBasedOnChildren(rightListView);
        myRightAdapter.notifyDataSetChanged();
        rightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ActivityMaterialsInBound.materialsInBound.tv_supplier.setText(models.get(position).getText1().toString());
                ActivityMaterialsInBound.materialsInBound.cvencode = leftlList.get(position).toString();
                ActivityMaterialsInBound.materialsInBound.hpsnguid = models.get(position).getText7().toString();
                finish();
            }
        });

    }


    private void init() {
        if (flag == 13) {//表示从入库点

            // 击供货单位进来的
            elements = new ArrayList<Element>();
            list_element = new ArrayList<Element>();

            //添加节点  -- 节点名称，节点level，节点id，父节点id，是否有子节点，是否展开

            //添加最外层节点
            Element e1 = new Element("供应商分类", Element.TOP_LEVEL, 0, Element.NO_PARENT, true, false);

            //添加第一层节点
            Element e2 = new Element("01 修理材料", Element.TOP_LEVEL + 1, 1, e1.getId(), true, false);
            //添加第二层节点
            Element e3 = new Element("0101 配件", Element.TOP_LEVEL + 2, 2, e2.getId(), false, false);
//        //添加第三层节点

            //添加第二层节点
            Element e4 = new Element("02 行政办公", Element.TOP_LEVEL + 1, 3, e1.getId(), true, false);
            //添加第三层节点
            Element e5 = new Element("0201 劳动用品 ", Element.TOP_LEVEL + 2, 4, e4.getId(), false, false);

            //添加初始树元素
            elements.add(e1);
            //创建数据源
            list_element.add(e1);
            list_element.add(e2);
            list_element.add(e3);
            list_element.add(e4);
            list_element.add(e5);
        } else {
            elements = new ArrayList<Element>();
            list_element = new ArrayList<Element>();
            hashMapMySelf.clear();
            hashMapParentSelf.clear();
            //仓库接口
            HttpNetworkRequest.get("goods/rs/hpWarehouseRefer", new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {
                    Gson gson = new Gson();
                    whname = gson.fromJson(s, WarehouseName.class);
                    int size = whname.getJsonData().getList().size();
                    //hashMapMySelf （cwhcode, 索引点）,初始parent为“0”
                    hashMapMySelf.put("0", 0);
                    //list-element 元素集
                    list_element.add(new Element("仓库档案", Element.TOP_LEVEL, 0, Element.NO_PARENT, true, false));
                    //list_level元素层集
                    list_level.add(Element.TOP_LEVEL);

                    for (int j = 0; j < size; j++) {
                        hashMapMySelf.put(whname.getJsonData().getList().get(j).getCwhcode(), j + 1);
                        hashMapParentSelf.put(j + 1, whname.getJsonData().getList().get(j).getParentid());
                        list_level.add(Element.TOP_LEVEL + toLevelResult(whname.getJsonData().getList().get(j).getCwhcode()));
                    }
                    for (int k = 0; k < size; k++) {
                        String pid = hashMapParentSelf.get(k + 1);
                        int index = hashMapMySelf.get(pid);
                        boolean haschild = hashMapParentSelf.containsValue(whname.getJsonData().getList().get(k).getCwhcode());
                        list_element.add(new Element(whname.getJsonData().getList().get(k).getAlias(), list_level.get(k + 1), k + 1, index, haschild, false));
                    }
                    Message msg = new Message();
                    msg.obj = list_element;
                    msg.arg1 = size;
                    msg.what = MSG_SHOW_Message;
                    hMsg.sendMessage(msg);

                }

                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                    Toast.makeText(Activity_TreeView.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


    public void return_value(String context) {

        int index = context.indexOf(" ");
        String warehouse_code = context.substring(0, index);
        String warehouse_name = context.substring(index + 1);
        String warehouse_HpwGuid = "";
        for (WarehouseName.JsonData.Info info : whname.getJsonData().getList()) {
            if (info.getCwhcode().equals(warehouse_code)) {
                warehouse_HpwGuid = info.getHpwGuid();
            }
        }
        if (flag == 0) {
            ActivityGoodsReceipt.goodsReceipt.tv_warehouse.setText(warehouse_name);
        } else if (flag == 1) {
            ActivityMaterialsInBound.materialsInBound.tv_warehouse.setText(warehouse_name);
            ActivityMaterialsInBound.materialsInBound.warehouse = warehouse_name;
//            ActivityMaterialsInBound.materialsInBound.hpwGuid = str_hpwGuid;
            ActivityMaterialsInBound.materialsInBound.cwhcode2 = warehouse_code;
        } else if (flag == 2) {
            ActivityInventory.inventory.warehouse = warehouse_name;
            ActivityInventory.inventory.tv_warehouse.setText(warehouse_name);
//            ActivityInventory.inventory.hpwGuid = str_hpwGuid;
            ActivityInventory.inventory.cwhcode2 = warehouse_code;

            ActivityInventory.inventory.tv_warehouse.setText(warehouse_name);
            ActivityInventory.inventory.inventoryHeader.setCWHNAME(warehouse_name);
            ActivityInventory.inventory.inventoryHeader.setHPWGUID(warehouse_HpwGuid);
            ActivityInventory.inventory.inventoryHeader.setCWHCODE(warehouse_code);

        } else if (flag == 3) {
            ActivityInventoryList.inventoryList.tv_warehouse.setText(warehouse_name);
//            ActivityInventoryList.inventoryList.hpwGuid = str_hpwGuid;
//            ActivityInventoryList.inventoryList.cwhcode = warehouse_code;
        } else if (flag == 4) {
            ActivityInventoryLossesOut.inventoryLossesOut.tv_warehouse.setText(warehouse_name);
            ActivityInventoryLossesOut.inventoryLossesOut.cwhcode = warehouse_code;
            ActivityInventoryLossesOut.inventoryLossesOut.cwhname = warehouse_name;
//            ActivityInventoryLossesOut.inventoryLossesOut.hpwGuid = str_hpwGuid;
        } else if (flag == 5) {
            ActivityInventoryProFitInt.inventoryProFitInt.tv_warehouse.setText(warehouse_name);
            ActivityInventoryProFitInt.inventoryProFitInt.cwhcode = warehouse_code;
            ActivityInventoryProFitInt.inventoryProFitInt.cwhname = warehouse_name;
//            ActivityInventoryProFitInt.inventoryProFitInt.hpwGuid = str_hpwGuid;
        } else if (flag == 6) {
            ActivityMaterialOutboundOrder.materialOutboundOrder.tv_warehouse.setText(warehouse_name);
        } else if (flag == 7) {
            ActivityMaterialsOutBound.materialsOutBound.tv_warehouse.setText(warehouse_name);
            ActivityMaterialsOutBound.materialsOutBound.warehouse = warehouse_name;
//            ActivityMaterialsOutBound.materialsOutBound.hpwGuid = str_hpwGuid;
            ActivityMaterialsOutBound.materialsOutBound.cwhcode2 = warehouse_code;
        }else if (flag == 8) {
            ActivityInventoryQuery.inventoryQuery.tv_warehouse.setText(warehouse_name);
            ActivityInventoryQuery.inventoryQuery.warehouse = warehouse_name;
//            ActivityMaterialsOutBound.materialsOutBound.hpwGuid = str_hpwGuid;
            ActivityInventoryQuery.inventoryQuery.cwhcode = warehouse_code;
        }
        finish();
    }

    //点击回退
    public void back(View view) {
        finish();
    }


    private class StartHander extends Handler {
        WeakReference<Activity> mActivityRef;

        StartHander(Activity activity) {
            mActivityRef = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Activity activity = mActivityRef.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {

                case MSG_SHOW_Message:
                    list_element = (ArrayList<Element>) msg.obj;
                    elements.add(list_element.get(0));
                    treeViewAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }


    private Handler hMsg = new StartHander(this);

    //字符串长度，来判断层级
    public int toLevelResult(String str) {
        int i = str.length() / 2;
        return i;
    }

    //点击回退
    public void btn_back(View view) {
        ll_orientation.setVisibility(View.GONE);
    }

    //加载更多
    public void load_more(View view)
    {
        if(new_treeview_hpsGuid!=null&&!new_treeview_hpsGuid.equals("")&&!computePageIsLast(new_Right_IRBR))
        {
            HttpNetworkRequest.get("goods/rs/hpSuppliers?pageNum="+(new_Right_IRBR.getCurrentpage()+1)+"&hpsGuid=" + new_treeview_hpsGuid + "&cvencode=&cvenname=&numPerPage=10", new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {
                    Gson gson = new Gson();
                    SupplierInfo supplierInfo = gson.fromJson(s, SupplierInfo.class);
                    if (supplierInfo.getStatusCode().equals("200")) {//如果请求成功则执行
                        int size = supplierInfo.getJsonData().getList().size();
                        /**
                         * 这里需要把两个集合都清空一下，因为每次点击会重新请求网络获取数据，不清空的话下一次请求时还会保存上一次请求的数据
                         */
                        for (int j = 0; j < size; j++) {
                            Activity_TreeView.treeView.leftlList.add(supplierInfo.getJsonData().getList().get(j).getCvencode());
                            Activity_TreeView.treeView.models.add(
                                    new RightModel(supplierInfo.getJsonData().getList().get(j).getHpsGuid(),
                                            supplierInfo.getJsonData().getList().get(j).getCvenname(),
                                            supplierInfo.getJsonData().getList().get(j).getOrgname(),
                                            supplierInfo.getJsonData().getList().get(j).getTelephone1(),
                                            supplierInfo.getJsonData().getList().get(j).getContact1(),
                                            supplierInfo.getJsonData().getList().get(j).getArrWay(),
                                            supplierInfo.getJsonData().getList().get(j).getArrWarehouse(),
                                            supplierInfo.getJsonData().getList().get(j).getHpsnGuid(),
                                            "",1));
                        }
                        new_Right_IRBR = new IntRequsetBackRecord(supplierInfo.getJsonData().getCurrentPage(), supplierInfo.getJsonData().getNumPerPage(), supplierInfo.getJsonData().getPageNumShown(), supplierInfo.getJsonData().getTotalCount());
                        currentNum.setText("当前" + (new_Right_IRBR.getNumPerPage() * (new_Right_IRBR.getCurrentpage() - 1) + new_Right_IRBR.getPageNumShown()) + "条");
                        totalNum.setText("总共" + new_Right_IRBR.getTotalCount() + "条");
                        adapter.notifyDataSetChanged();
                        myRightAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(Activity_TreeView.treeView, "网络请求失败", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {

                }
            });
        }else{

            Toast.makeText(Activity_TreeView.treeView, "当前为最后一页", Toast.LENGTH_SHORT).show();
        }

    }

    //计算是否为最后一页 true为最后一页，false为非最后一页
    private boolean computePageIsLast(IntRequsetBackRecord new_irbr) {

        //当前页数
        int cpage = new_irbr.getCurrentpage();
        //每页条数
        int npage = new_irbr.getNumPerPage();
        //当前条数
        int nownum = new_irbr.getPageNumShown();
        //总条数
        int tcount = new_irbr.getTotalCount();

        int yucount = tcount % npage;

        int pagecount = 0;
        if (yucount > 0) {
            pagecount = tcount / npage + 1;
        } else {
            pagecount = tcount / npage;
        }
        boolean flag = false;
        if (cpage < pagecount) {

            flag = false;
        }
        if (cpage == pagecount) {
            flag = true;
        }
        if (cpage > pagecount) {
            flag = true;
        }
        return flag;
    }

    public void back_first(View view)
    {
        scrollView.scrollTo(0, 0);
    }


    //点击搜索
    public void seek(View view) {

        try{
            String supplier = et_seek.getText().toString();
            RequestParams params = new RequestParams();
            if (spinner_index == 0) {//等于0表示是供应商编码
                params.put("cvencode", "");
                params.put("cvenname", supplier);
            } else if (spinner_index == 1) {//等于1表示是供应商名称
                params.put("cvencode", supplier);
                params.put("cvenname", "");
            }

            //网络请求方法
            HttpNetworkRequest.get("goods/rs/hpSuppliers?pageNum=1&hpsGuid=&numPerPage=50",params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {
                    Gson gson = new Gson();
                    SupplierInfo supplierInfo = gson.fromJson(s, SupplierInfo.class);
                    if (supplierInfo.getStatusCode().equals("200")) {//如果请求成功则执行
                        int size = supplierInfo.getJsonData().getList().size();
                        leftlList.clear();
                        models.clear();
                        new_treeview_hpsGuid ="";
                        /**
                         * 这里需要把两个集合都清空一下，因为每次点击会重新请求网络获取数据，不清空的话下一次请求时还会保存上一次请求的数据
                         */
                        for (int j = 0; j < size; j++) {
                            Activity_TreeView.treeView.leftlList.add(supplierInfo.getJsonData().getList().get(j).getCvencode());
                            Activity_TreeView.treeView.models.add(
                                    new RightModel(supplierInfo.getJsonData().getList().get(j).getHpsGuid(),
                                            supplierInfo.getJsonData().getList().get(j).getCvenname(),
                                            supplierInfo.getJsonData().getList().get(j).getOrgname(),
                                            supplierInfo.getJsonData().getList().get(j).getTelephone1(),
                                            supplierInfo.getJsonData().getList().get(j).getContact1(),
                                            supplierInfo.getJsonData().getList().get(j).getArrWay(),
                                            supplierInfo.getJsonData().getList().get(j).getArrWarehouse(),
                                            supplierInfo.getJsonData().getList().get(j).getHpsnGuid(),
                                            "",1));
                        }
                        new_Right_IRBR = new IntRequsetBackRecord(supplierInfo.getJsonData().getCurrentPage(), supplierInfo.getJsonData().getNumPerPage(), supplierInfo.getJsonData().getPageNumShown(), supplierInfo.getJsonData().getTotalCount());
                        currentNum.setText("当前" + (new_Right_IRBR.getNumPerPage() * (new_Right_IRBR.getCurrentpage() - 1) + new_Right_IRBR.getPageNumShown()) + "条");
                        totalNum.setText("总共" + new_Right_IRBR.getTotalCount() + "条");
                        adapter.notifyDataSetChanged();
                        myRightAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(Activity_TreeView.treeView, "网络请求失败", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {

                }
            });
        }catch (Exception e){
            Toast.makeText(Activity_TreeView.treeView, "供应商数据请求失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        //让软键盘隐藏
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }





    public void listern_searchcode_isempty() {
        if (et_seek.getText().toString() == null || et_seek.getText().toString().equals("")) {
            clear_wzcode_Btn.setVisibility(View.GONE);
        } else {
            clear_wzcode_Btn.setVisibility(View.VISIBLE);
        }
    }

    //点击清空
    public void clear_wzcode(View view) {
        et_seek.setText("");
    }


}
