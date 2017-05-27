package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.acdbentity.InventoryList;
import com.zjrfid.materialsmanage.adapter.AdapterWzpd;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.xListView.XListView;

import org.apache.http.Header;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chenxiao on 2017/4/8.
 * 盘点单
 */
public class ActivityInventoryList extends Activity implements XListView.IXListViewListener {
    public static ActivityInventoryList inventoryList;
    private RelativeLayout relative_screen;
    private LinearLayout Linear_screenUI;
    private XListView list_wzpd;
    public Button btn_delete, btn_cancel, btn_redact, btn_added, btn_inquire;
    private ImageView img_direction;
    private EditText et_pddh;
    public TextView tv_warehouse,tv_fquantity,tv_pquantity;
    public static AdapterWzpd adapterWzpd;
    private Handler mHandler;
    private boolean bln_is = true;
    boolean bln_judge = true;

    private boolean bln_check = true;
    private String pageN;
    private int page = 2;

    public InventoryList inventory;
    public HashMap<String, String> hm_inventoryList;
    public ArrayList<HashMap<String, String>> listscq = new ArrayList<HashMap<String, String>>();
    private List<String> list_hpcvGuid = new ArrayList<>();//盘点主键
    public String hpwGuid = "";
    public String cwhcode = ActivityHomePage.wareReadhouseBeanString;//仓库编码
    private List<InventoryList> list_inventory = new ArrayList<>();//盘点主键
    public String sum;//总条数

    private List<String> list_chandler2 = new ArrayList<>();//审核人,此集合专门用来传值的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_list);
        //
        inventoryList = this;
        initView();
        getPddQuery("1");
    }

    public void initView() {
        relative_screen = (RelativeLayout) findViewById(R.id.relative_screen);
        Linear_screenUI = (LinearLayout) findViewById(R.id.Linear_screenUI);
        list_wzpd = (XListView) findViewById(R.id.list_wzpd);
        list_wzpd.setPullLoadEnable(true);//设置让它上拉，FALSE为不让上拉，便不加载更多数据
        tv_pquantity= (TextView) findViewById(R.id.tv_pquantity);
        tv_fquantity= (TextView) findViewById(R.id.tv_fquantity);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_cancel = (Button) findViewById(R.id.btn_redact);
        btn_redact = (Button) findViewById(R.id.btn_redact);
        btn_added = (Button) findViewById(R.id.btn_added);
        btn_inquire = (Button) findViewById(R.id.btn_inquire);
        img_direction = (ImageView) findViewById(R.id.img_direction);
        et_pddh = (EditText) findViewById(R.id.et_pddh);
        tv_warehouse = (TextView) findViewById(R.id.tv_warehouse);

        //点击筛选
        relative_screen.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   if (bln_is) {
                                                       Linear_screenUI.setVisibility(View.VISIBLE);
                                                       img_direction.setImageResource(R.drawable.up);
                                                       bln_is = false;
                                                       //加载数据
//                                                       getWarehouseName();
                                                   } else {
                                                       Linear_screenUI.setVisibility(View.GONE);
                                                       img_direction.setImageResource(R.drawable.down);
                                                       bln_is = true;
                                                   }
                                                   InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                                   imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                                               }
                                           }
        );
        //点击请选择仓库
        tv_warehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityInventoryList.this, Activity_TreeView.class);
                intent.putExtra("flag", 3);
                startActivity(intent);
            }
        });

        adapterWzpd = new AdapterWzpd(ActivityInventoryList.this, listscq);
        list_wzpd.setAdapter(adapterWzpd);
        list_wzpd.setXListViewListener(this);
        mHandler = new Handler();

        //点击listview
        list_wzpd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * 如果没有点编辑，则点击item就直接跳转，如果点击了编辑，则点击item只能选中CheckBox
                 */
                if (bln_judge) {
                    Intent intent = new Intent(ActivityInventoryList.this, ActivityInventory.class);
                    intent.putExtra("get", 1);
                    intent.putExtra("position", position);
                    intent.putExtra("hpcvGuid", list_hpcvGuid.get(position - 1));//物资名称
                    intent.putExtra("chandler", list_chandler2.get(position - 1));//审核人
                    startActivity(intent);
                } else {
                    // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                    AdapterWzpd.ViewHold holder = (AdapterWzpd.ViewHold) view.getTag();
                    // 改变CheckBox的状态
                    holder.cb.toggle();
                    // 将CheckBox的选中状况记录下来
                    // 调整选定条目
                    if (holder.cb.isChecked() == true) {
                        listscq.get(position - 1).put("flag", "true");
                    } else {
                        listscq.get(position - 1).put("flag", "false");
                    }

                }
            }
        });
    }

    //点击回退
    public void back(View view) {
        finish();
    }

    //点击查询
    public void query(View view) {
        //再一次查询是要把上一次查询的结果清空
        et_pddh.clearFocus();
        onRefresh();
        //让软键盘隐藏
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }


    public void cancel_chaxun(View view) {
        //显示布局
        showLayout();
        bln_is = true;
        //让软键盘隐藏
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    public void cancel_et_pddh(View view) {
        et_pddh.clearFocus();
        //让软键盘隐藏
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);

    }

    public void showLayout() {
        Linear_screenUI.setVisibility(View.GONE);
        btn_redact.setVisibility(View.VISIBLE);
        btn_cancel.setVisibility(View.VISIBLE);
        img_direction.setImageResource(R.drawable.down);
        btn_delete.setVisibility(View.GONE);//删除按钮隐藏
        btn_added.setVisibility(View.VISIBLE);//新增按钮显示
        btn_inquire.setVisibility(View.GONE);//查看按钮隐藏
        btn_redact.setText("编辑");
    }

    //刷新
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    pageN = "1";
                    listscq.clear();
                    bln_is = true;
                    hm_inventoryList.clear();
                    bln_judge = true;

                    bln_check = true;
                    page = 2;
                    list_hpcvGuid.clear();
                    list_inventory.clear();
                    list_chandler2.clear();
                    getPddQuery("1");
                    dataChanged();
                    onLoad();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //显示布局
                showLayout();
            }
        }, 2000);
    }

    //加载更多
    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (inventory != null) {
                        if (page < ((Integer.parseInt(inventory.getJsonData().getTotalCount()) / 10) + 3)) {
                            getPddQuery(pageN);
                            dataChanged();
                        } else {
                            Toast.makeText(ActivityInventoryList.this, "最后一页了", Toast.LENGTH_SHORT).show();
                        }
                        onLoad();
                        //显示布局
                        showLayout();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 2000);
    }

    //刷新适配器
    public static void dataChanged() {
        adapterWzpd.notifyDataSetChanged();
    }

    /**
     * 停止刷新，
     */
    private void onLoad() {
        list_wzpd.stopRefresh();
        list_wzpd.stopLoadMore();
        list_wzpd.setRefreshTime(getTime());
    }

    //获取当前的日期及时间
    public String getTime() {
        Date date = new Date();
        java.text.DateFormat format = new SimpleDateFormat("HH:mm:ss ");//大写的HH是24小时制，小写的hh则是12小时制
        String time = format.format(date);
        return time;
    }

    //点击编辑
    public void redact(View view) {
        //点击编辑显示checkbox
        if (bln_judge) {//点击编辑
            btn_redact.setText("取消");
            adapterWzpd.hind(0);//显示复选框
            btn_delete.setVisibility(View.VISIBLE);//删除按钮显示
            btn_added.setVisibility(View.GONE);//新增按钮隐藏
            btn_inquire.setVisibility(View.VISIBLE);//全选按钮显示
            bln_judge = false;


        } else {//点击取消
            btn_redact.setText("编辑");
            adapterWzpd.hind(1);//隐藏复选框
            btn_delete.setVisibility(View.GONE);//删除按钮隐藏
            btn_added.setVisibility(View.VISIBLE);//新增按钮显示
            btn_inquire.setVisibility(View.GONE);//全选按钮隐藏
            bln_judge = true;

            bln_check = true;
            btn_inquire.setText("全选");

            //点击取消后，把所有选中状态清除
            for (int j = 0; j < listscq.size(); j++) {
                listscq.get(j).put("flag", "false");
            }
        }
        dataChanged();//因为判断里有显示或隐藏复选框，所以要刷新适配器
    }

    //点击删除
    public void delete(View view) {

        int count = 0;
        List<String> list_chandler_selected = new ArrayList<>();
        List<String> list_hpcvguid_selected = new ArrayList<>();

        for (int i = 0; i < listscq.size(); i++) {
            if (listscq.get(i).get("flag").equals("true")) {
                count++;
                list_chandler_selected.add(listscq.get(i).get("content9"));
                list_hpcvguid_selected.add(listscq.get(i).get("HPCVGUID"));
            }

        }
        if (count == 0) {
            Toast.makeText(this, "请选择一项删除", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < list_hpcvguid_selected.size(); i++) {
            if (list_chandler_selected.get(i) == null||list_chandler_selected.get(i).equals("")) {//如果审核人为空，那么说明未审核，可以删除
                //调用删除入库单详细信息单条接口
                HttpNetworkRequest.delete("goods/rs/hpCheckvouch?str=" + list_hpcvguid_selected.get(i), new BaseHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                        try {
                            JSONObject jsonObject = new JSONObject(rawResponse);
                            if (jsonObject.getString("message").equals("删除成功")) {
                                Toast.makeText(ActivityInventoryList.this, "删除成功", Toast.LENGTH_SHORT).show();
                                //显示布局
                                showLayout();
                            }
                        } catch (Exception e) {
                            String msg = e.getMessage();
                            Toast.makeText(ActivityInventoryList.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                        Toast.makeText(ActivityInventoryList.this, "服务器请求失败，删除不成功", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(ActivityInventoryList.this, "此条已通过审核，不能删除", Toast.LENGTH_SHORT).show();
            }
        }
        //刷新数据，for循环执行完再执行
        onRefresh();

    // 通知列表数据修改，刷新适配器
    dataChanged();
    //清空集合

    //将所有选中的条目删除
    for(int i = 0; i<listscq.size();i++)
    {
        listscq.get(i).put("flag", "false");
    }


}


    //点击新增
    public void Added(View view) {
        startActivity(new Intent(this, ActivityInventory.class));
    }

    //点击全选
    public void check(View view) {
        if (bln_check) {
            btn_inquire.setText("全不选");
            adapterWzpd.checkAll(2);//参数为2表示全选
            for (int k = 0; k < listscq.size(); k++) {
                listscq.get(k).put("flag", "true");

            }
            bln_check = false;
        } else {
            btn_inquire.setText("全选");
            adapterWzpd.checkAll(3);//参数为3表示全不选
            for (int k = 0; k < listscq.size(); k++) {
                listscq.get(k).put("flag", "false");

            }
            bln_check = true;
        }
        dataChanged();
    }

    //盘点单查询（列表）
    public void getPddQuery(final String pageNum) {
        RequestParams params = new RequestParams();
        params.put("pageNum", pageNum);//页码
        params.put("ccvcode", et_pddh.getText().toString());//盘点单号
        params.put("cwhcode", cwhcode);//仓库编码
        params.put("cpersonname",ActivityLogin.login.getFullname());//登录人的名字
        if (tv_warehouse.getText().toString().equals("请选择仓库")) {
            params.put("cwhname", "");
        } else {
            params.put("cwhname", tv_warehouse.getText().toString());//仓库名称
        }
        params.put("hpwGuid", hpwGuid);//仓库主键
        //盘点单查询（列表）接口
        HttpNetworkRequest.get("goods/rs/hpCheckvouchs", params, new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                Gson gson = new Gson();
                inventory = gson.fromJson(rawResponse, InventoryList.class);
                if (inventory.getJsonData().getList().size() != 0) {
                    try {
                        sum = inventory.getJsonData().getTotalCount();//总数
                        tv_fquantity.setText(inventory.getTotalInfo().getTOTALFQUANTITY());//账面数量
                        tv_pquantity.setText(inventory.getTotalInfo().getTOTALPQUANTITY());//盘点数量
                        JSONObject jsonObject = new JSONObject(rawResponse);
                        if (jsonObject.getString("message").equals("操作成功")) {
                            //将类的对象存入集合
                            list_inventory.add(inventory);
                            for (int i = 0; i < inventory.getJsonData().getList().size(); i++) {
                                list_hpcvGuid.add(inventory.getJsonData().getList().get(i).getHPCVGUID());
                                list_chandler2.add(inventory.getJsonData().getList().get(i).getCHANDLER());
                            }
                            addData(inventory.getJsonData().getList().size());
                            pageN = String.valueOf(page++);
                            Toast.makeText(ActivityInventoryList.this, "当前为第 " + pageNum + " 页", Toast.LENGTH_SHORT).show();
                            dataChanged();//刷新适配器
                        }
                    } catch (Exception e) {
                        String msg = e.getMessage();
                        Toast.makeText(ActivityInventoryList.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                Toast.makeText(ActivityInventoryList.this, " 服务器请求失败，查询盘点单列表失败", Toast.LENGTH_SHORT).show();

            }
        });
    }

    //给集合添加数据
    public void addData(int n) {
        for (int i = 0; i < n; i++) {
            hm_inventoryList = new HashMap<String, String>();
            hm_inventoryList.put("content1", inventory.getJsonData().getList().get(i).getCCVCODE());//盘点单号
            hm_inventoryList.put("content2", inventory.getJsonData().getList().get(i).getDCVDATE());//盘点日期
            hm_inventoryList.put("content3", inventory.getJsonData().getList().get(i).getCWHNAME());//盘点仓库
            hm_inventoryList.put("content4", inventory.getJsonData().getList().get(i).getCORDCODE());//出库类别
            hm_inventoryList.put("content5", inventory.getJsonData().getList().get(i).getCIRDCODE());//入库类别
            hm_inventoryList.put("content6", inventory.getJsonData().getList().get(i).getORGNAME());//部门
            hm_inventoryList.put("content7", inventory.getJsonData().getList().get(i).getCPERSONNAME());//盘点负责人
            hm_inventoryList.put("content8", inventory.getJsonData().getList().get(i).getCMAKER());//制单人
            hm_inventoryList.put("content9", inventory.getJsonData().getList().get(i).getCHANDLER());//审核人
            hm_inventoryList.put("content10", inventory.getJsonData().getList().get(i).getCDEMO());//备注
            hm_inventoryList.put("flag", "false");
            hm_inventoryList.put("HPCVGUID", inventory.getJsonData().getList().get(i).getHPCVGUID());
            listscq.add(hm_inventoryList);
            dataChanged();
            //隐藏复选框
            adapterWzpd.hind(1);

        }
    }

    public void queryClear(View view) {

        hpwGuid = "";
        et_pddh.setText("");
        tv_warehouse.setText("请选择仓库");
        et_pddh.clearFocus();
    }
}
