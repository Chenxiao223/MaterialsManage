package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.TreeViewTool.Element;
import com.zjrfid.materialsmanage.TreeViewTool.TakePhotoPopWin;
import com.zjrfid.materialsmanage.TreeViewTool.TreeViewAdapter;
import com.zjrfid.materialsmanage.TreeViewTool.TreeViewItemClickListener2;
import com.zjrfid.materialsmanage.TreeViewTool.TreeViewItemClickListener3;
import com.zjrfid.materialsmanage.acdbentity.CgoodsAllocation;
import com.zjrfid.materialsmanage.acdbentity.WarehouseName;
import com.zjrfid.materialsmanage.adapter.AdapterQueryGoodsAllocation;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.tool.SysApplication;

import org.apache.http.Header;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhouyu on 2017/4/29.
 */
public class ActivityGoodsAllocation extends AppCompatActivity {

    public static ActivityGoodsAllocation goodsAllocation;
    private ListView GA_lv_treeview;

    private LinearLayout GA_ll_tree;

    private EditText et_seek;
    private Button clear_query_code_Btn;

    private CgoodsAllocation mCGA = new CgoodsAllocation();
    private TreeViewAdapter treeViewAdapter;

    protected static final int MSG_SHOW_Message = 1;
    protected static final int MSG_SHOW_Message_Result = 2;

    private Spinner spin_code;
    private HashMap<String, Integer> hashMapMySelf = new HashMap<>();
    private HashMap<Integer, String> hashMapParentSelf = new HashMap<>();
    private ArrayList<Element> list_element = new ArrayList<>();
    private ArrayList<Element> elements = new ArrayList<>();
    private List<Integer> list_level = new ArrayList<>();//存节点level
    private ArrayAdapter<String> arrayAdapter;
    private List<String> list_spin = new ArrayList<>();
    private static int flag = 0;
    private String et_query_tiaojian = "";
    public static String cwhcodeFromOuter;//仓库编码
    private static String cwhnameFromOuter;//仓库名称

    private List<String> list_reuslt = new ArrayList<>();
    private List<String> list_code = new ArrayList<>();
    private ListView GA_lv_reult;
    private AdapterQueryGoodsAllocation arrayAdapter_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_allocation);
        //
        goodsAllocation = this;
        SysApplication.getInstance().addActivity(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Intent intent = getIntent();
        cwhcodeFromOuter = intent.getStringExtra("cwhcode");//仓库编码
        cwhnameFromOuter = intent.getStringExtra("cwhname");//仓库名称
        GA_ll_tree = (LinearLayout) findViewById(R.id.GA_ll_tree);
        GA_lv_treeview = (ListView) findViewById(R.id.GA_lv_treeview);

        et_seek = (EditText) findViewById(R.id.et_seek);
        clear_query_code_Btn = (Button) findViewById(R.id.clear_query_code_Btn);
        spin_code = (Spinner) findViewById(R.id.spin_code);

        // 仓库下所有货位
        GA_lv_treeview = (ListView) findViewById(R.id.GA_lv_treeview);
        treeViewAdapter = new TreeViewAdapter(elements, list_element, inflater, this);
        TreeViewItemClickListener3 treeViewItemClickListener = new TreeViewItemClickListener3(treeViewAdapter);
        GA_lv_treeview.setAdapter(treeViewAdapter);
        GA_lv_treeview.setOnItemClickListener(treeViewItemClickListener);
        //搜索的货位
        GA_lv_reult = (ListView) findViewById(R.id.GA_lv_reult);
        arrayAdapter_result = new AdapterQueryGoodsAllocation(this, list_reuslt);
        GA_lv_reult.setAdapter(arrayAdapter_result);
        GA_lv_reult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String value = list_reuslt.get(position).toString();
                String code = list_code.get(position).toString();
                return_value(value,code);
                return;
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
                et_query_tiaojian = et_seek.getText().toString();
            }
        });

        list_spin.add("货位编码");
        list_spin.add("货位名称");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list_spin);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_code.setAdapter(arrayAdapter);
        spin_code.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flag = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        RequestGoodsAllocationInfo("", "", true);
    }

    /*
    * 网络请求货位列表信息
    * 数据获取成功，保存于mCGA
    * 数据获取失败，mCGA=null
    * flag为true，全查，falge为false，选择查询
     */
    private void RequestGoodsAllocationInfo(String str_cposcode, String str_cparentid, final Boolean flag) {

        RequestParams rp = new RequestParams();
        rp.put("cposcode", str_cposcode);
        rp.put("cparentid", str_cparentid);
        rp.put("hpwGuid", cwhcodeFromOuter);

        HttpNetworkRequest.get("goods/rs/hpPositionRefer", rp, new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, String s, Object o) {
                Gson gson = new Gson();
                mCGA = gson.fromJson(s, CgoodsAllocation.class);
                collectGoodsAllocationInfo(mCGA, flag);
            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {

            }
        });
    }

    //flag为true，全查，falge为false，选择查询,   选择不同的适配器和listview
    private void collectGoodsAllocationInfo(CgoodsAllocation newCGA, Boolean flag) {
        if (flag) {
            int size = newCGA.getJsonData().getList().size();

            //hashMapMySelf （cwhcode, 索引点）,初始parent为“0”
            hashMapMySelf.put(cwhcodeFromOuter, 0);
            //list-element 元素集
            list_element.add(new Element(cwhnameFromOuter, Element.TOP_LEVEL, 0, Element.NO_PARENT, true, false));
            //list_level元素层集
            list_level.add(Element.TOP_LEVEL);

            for (int j = 0; j < size; j++) {
                hashMapMySelf.put(newCGA.getJsonData().getList().get(j).getCposcode(), j + 1);
                hashMapParentSelf.put(j + 1, newCGA.getJsonData().getList().get(j).getParentid());
                list_level.add(Element.TOP_LEVEL + toLevelResult(newCGA.getJsonData().getList().get(j).getCposcode()));
            }
            for (int k = 0; k < size; k++) {
                String pid = hashMapParentSelf.get(k + 1);
                int index = hashMapMySelf.get(pid);
                boolean haschild = hashMapParentSelf.containsValue(newCGA.getJsonData().getList().get(k).getCposcode());
                list_element.add(new Element(newCGA.getJsonData().getList().get(k).getAlias(), list_level.get(k + 1), k + 1, index, haschild, false));
            }
            Message msg = new Message();
            msg.obj = list_element;
            msg.arg1 = size;
            msg.what = MSG_SHOW_Message;
            hMsg.sendMessage(msg);

        } else {

            int size = newCGA.getJsonData().getList().size();
            for (int i = 0; i < size; i++) {
                list_reuslt.add(newCGA.getJsonData().getList().get(i).getAlias());
                list_code.add(newCGA.getJsonData().getList().get(i).getCposcode());
            }
            Message msg = new Message();
            msg.obj = list_reuslt;
            msg.arg1 = size;
            msg.what = MSG_SHOW_Message_Result;
            hMsg.sendMessage(msg);

        }
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
                case MSG_SHOW_Message_Result:
                    arrayAdapter_result.notifyDataSetChanged();
                    break;

            }
        }
    }

    private Handler hMsg = new StartHander(this);

    //字符串长度，来判断层级
    public int toLevelResult(String str) {

        int i = str.length() / 2;
        if (i >= 3) {
            i = 3;
        }
        return i - 1;
    }

    //回退到listview初始页面
    public void back_FirstItem(View view) {

        if (list_element.size() != 0) {
            //  GA_lv_treeview.smoothScrollToPosition(0);
            GA_lv_treeview.setSelection(0);
        }
        if (list_reuslt.size() != 0) {
            // GA_lv_reult.smoothScrollToPosition(0);
            GA_lv_reult.setSelection(0);
        }

    }

    //搜索
    public void goods_allocation_query(View view) {

        if (et_query_tiaojian.equals("") || et_query_tiaojian == null) {
            GA_lv_treeview.setVisibility(View.VISIBLE);
            GA_lv_reult.setVisibility(View.GONE);
        } else {
            GA_lv_treeview.setVisibility(View.GONE);
            GA_lv_reult.setVisibility(View.VISIBLE);
            list_reuslt.clear();
            switch (flag) {
                case 0:
                    RequestGoodsAllocationInfo(et_query_tiaojian, "", false);
                    break;
                case 1:
                    RequestGoodsAllocationInfo("", et_query_tiaojian, false);
                    break;
                default:
                    break;
            }
        }

    }

    //清空查询选项
    public void clear_query_code(View view) {
        et_seek.setText("");
    }


    //清空查询按钮 显示隐藏
    public void listern_searchcode_isempty() {
        if (et_seek.getText().toString() == null || et_seek.getText().toString().equals("")) {
            clear_query_code_Btn.setVisibility(View.GONE);
        } else {
            clear_query_code_Btn.setVisibility(View.VISIBLE);

        }
    }

    //点击箭头
    public void back(View view) {
        finish();
    }

    public void return_value(String value,String code) {
        String name = value.substring(value.indexOf(" "));//货位名称
        TakePhotoPopWin.instance.tv_huowei.setText(name.trim());//货位名称,trim()方法是用来去空格的
        TakePhotoPopWin.instance.hwbm = code; //货位编码
        finish();
    }

}
