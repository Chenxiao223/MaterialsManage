package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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
import com.zjrfid.materialsmanage.acdbentity.InventoryLossesOut;
import com.zjrfid.materialsmanage.acdbentity.WarehouseAuthority;
import com.zjrfid.materialsmanage.adapter.AdapterLossesOut;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.xListView.XListView;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/4/8.   盘亏出库单（列表）界面
 */
public class ActivityInventoryLossesOut extends Activity implements XListView.IXListViewListener {
    public static ActivityInventoryLossesOut inventoryLossesOut;
    private InventoryLossesOut ilout;
    private WarehouseAuthority whay;
    private InventoryLossesOut foreilout; //前一次的请求结果
    private ImageView img_direction;
    private RelativeLayout relative_query;
    private LinearLayout linear_queryUI;
    private EditText et_chukudocument;
    public TextView tv_warehouse, tv_oddnumber, tv_storehouse;
    private boolean bln_is = true;
    boolean bln_judge = true;
    public String ccode, cwhcode, cwhname;
    public ArrayList<HashMap<String, String>> outlistscq = new ArrayList<HashMap<String, String>>();
    private List<InventoryLossesOut> list_ilout = new ArrayList<>();//存放实体类的集合
    public HashMap<String, String> map;
    public static AdapterLossesOut adapterLossesOut;
    private XListView lv_wzrk;
    private Handler mHandler;
    private String pageN = "1";
    private int page = 1;
    private List<String> list_hprguid = new ArrayList<>();//存放hprguid主键的集合
    private Button btn_delete, btn_select, btn_cancel;
    private List<WarehouseAuthority.WareReadhouseBean> wareReadhouseBeanList;
    private String wareReadhouseBeanString = ActivityHomePage.wareReadhouseBeanString;
    private Integer delete_index;
    boolean btn_choose = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventorylossesout);
        inventoryLossesOut = this;
        initView();//初始化控件
    }

    //点击回退
    public void back(View view) {
        finish();
    }

    private void initView() {
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_select = (Button) findViewById(R.id.btn_select);
        img_direction = (ImageView) findViewById(R.id.img_direction);//向下箭头
        relative_query = (RelativeLayout) findViewById(R.id.relative_query);
        linear_queryUI = (LinearLayout) findViewById(R.id.linear_queryUI);
        et_chukudocument = (EditText) findViewById(R.id.et_chukudocument);//出库单号
        tv_warehouse = (TextView) findViewById(R.id.tv_warehouse);
        tv_oddnumber = (TextView) findViewById(R.id.tv_oddnumber);//显示的出库单据号
        tv_storehouse = (TextView) findViewById(R.id.tv_storehouse);//显示的仓库
        lv_wzrk = (XListView) findViewById(R.id.lv_wzck);
        lv_wzrk.setPullLoadEnable(true);// 设置让它上拉，FALSE为不让上拉，便不加载更多数据
        //点击筛选
        relative_query.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  if (bln_is) {
                                                      linear_queryUI.setVisibility(View.VISIBLE);
                                                      img_direction.setImageResource(R.drawable.up);
                                                      bln_is = false;
                                                  } else {
                                                      linear_queryUI.setVisibility(View.GONE);
                                                      img_direction.setImageResource(R.drawable.down);
                                                      bln_is = true;
                                                  }
                                              }
                                          }
        );

        adapterLossesOut = new AdapterLossesOut(ActivityInventoryLossesOut.this, outlistscq);
        lv_wzrk.setAdapter(adapterLossesOut);
        lv_wzrk.setXListViewListener(this);
        mHandler = new Handler();
        lv_wzrk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (bln_judge) {
                    Intent intent = new Intent(ActivityInventoryLossesOut.this, ActivityCheckInventoryLossesOut.class);
                    intent.putExtra("hprGuid", list_hprguid.get(position - 1));//把主键传过去
                    startActivity(intent);
                } else {
                    // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                    // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                    AdapterLossesOut.ViewHold holder = (AdapterLossesOut.ViewHold) view.getTag();
                    // 改变CheckBox的状态
                    holder.outBox.toggle();
                    // 将CheckBox的选中状况记录下来
                    // 调整选定条目
                    if (holder.outBox.isChecked() == true) {
                        outlistscq.get(position - 1).put("flag", "true");
                        change(true,position);
                    } else {
                        outlistscq.get(position - 1).put("flag", "false");
                        change(false, position);
                    }
                }
            }
        });


        //点击请选择仓库
        tv_warehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ActivityInventoryLossesOut.this, Activity_TreeView.class);
                intent1.putExtra("flag", 4);
                startActivity(intent1);
            }
        });

        //设置 盘点单号输入框编辑监听事件
        et_chukudocument.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tv_oddnumber.setText(s.toString());
                ccode = s.toString();
            }
        });

        //设置仓库编辑框 监听事件
        tv_warehouse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tv_storehouse.setText(s.toString());
                cwhname = s.toString();
            }
        });

        onRefresh();
    }

    //点击查询 清空
    public void chaxunclear(View view) {
        et_chukudocument.setText("");
        tv_warehouse.setText("");
    }

    //点击查询
    public void query(View view) {

        //显示布局
        linear_queryUI.setVisibility(View.GONE);
        img_direction.setImageResource(R.drawable.down);

        outlistscq.clear();
        ilout = null;
        foreilout = null;
        list_ilout.clear();
        list_hprguid.clear();


        bln_is = true;
        pageN = "1";
        page = 1;

        NetworkRequest("1");
        adapterLossesOut.notifyDataSetChanged();

    }

    private void NetworkRequest(final String pageNum) {

        //接口参数
        RequestParams params = new RequestParams();
        params.put("pageNum", pageNum);
        params.put("cwhname", cwhname);
        params.put("hpwGuid", "");
        params.put("ccode", ccode);
        params.put("cwhcode", wareReadhouseBeanString);

        //盘点出库单查询（列表）接口
        HttpNetworkRequest.get("goods/rs/hpCheckoutbound", params, new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, String s, Object o) {
                Gson gson = new Gson();
                ilout = gson.fromJson(s, InventoryLossesOut.class);
                foreilout = ilout;
                list_ilout.add(ilout);
                for (int j = 0; j < ilout.getJsonData().getList().size(); j++) {
                    list_hprguid.add(ilout.getJsonData().getList().get(j).getHPRGUID());

                }
                AddData(ilout.getJsonData().getList().size());
                pageN = String.valueOf(page + 1);
                Toast.makeText(ActivityInventoryLossesOut.this, "当前为第 " + pageNum + " 页", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                Toast.makeText(ActivityInventoryLossesOut.this, "盘点出库单查询,服务器请求失败", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void AddData(int n) {
        for (int i = 0; i < n; i++) {
            map = new HashMap<String, String>();
            map.put("content1", ilout.getJsonData().getList().get(i).getCCODE());//盘点出库单号
            map.put("content2", ilout.getJsonData().getList().get(i).getDKEEPDATE());//盘点出库日期
            map.put("content3", ilout.getJsonData().getList().get(i).getCWHNAME());//仓库
            map.put("content4", ilout.getJsonData().getList().get(i).getCBATCH());//批号
            map.put("content5", ilout.getJsonData().getList().get(i).getCINVCODE());//物资编码
            map.put("content6", ilout.getJsonData().getList().get(i).getCINVNAME());//物资名称
            map.put("content7", ilout.getJsonData().getList().get(i).getCPARENTID());//货位名称
            map.put("content8", ilout.getJsonData().getList().get(i).getCPOSCODE());//货位编码
            map.put("content9", ilout.getJsonData().getList().get(i).getCINVSTD());//规格型号
            map.put("content10", ilout.getJsonData().getList().get(i).getCCOMUNITNAME());//规格型号
            map.put("content11", ilout.getJsonData().getList().get(i).getFQUANTITY());//数量
            map.put("content12", ilout.getJsonData().getList().get(i).getFTAXPRICE());//单价
            map.put("content13", ilout.getJsonData().getList().get(i).getTAXAMOUNT());//合计金额
            map.put("content14", ilout.getJsonData().getList().get(i).getFUNITPRICE());//无税单价
            map.put("content15", ilout.getJsonData().getList().get(i).getFMONDEY());//无税金额
            map.put("content16", ilout.getJsonData().getList().get(i).getCPERSONNAME());//负责盘点人
            map.put("content17", ilout.getJsonData().getList().get(i).getCMAKER());//制单人
            map.put("content18", ilout.getJsonData().getList().get(i).getCHANDLER());//审核人
            map.put("content19", ilout.getJsonData().getList().get(i).getORGNAME());//部门
            map.put("content20", ilout.getJsonData().getList().get(i).getCDEMO());//备注
            map.put("flag", "false");
            map.put("HPRGUID", ilout.getJsonData().getList().get(i).getHPRGUID());
            outlistscq.add(map);
            adapterLossesOut.hind(1);//隐藏复选框
        }
        dataChanged();
    }

    //刷新适配器
    public void dataChanged() {
        adapterLossesOut.notifyDataSetChanged();
    }


    //点击选择
    public void select(View view) {
        if (outlistscq.size() > 0) {
            //点击编辑显示checkbox
            adapterLossesOut.hind(0);//显示复选框
            btn_delete.setVisibility(View.VISIBLE);//删除按钮显示
            btn_select.setVisibility(View.GONE);//新增按钮隐藏
            btn_cancel.setVisibility(View.VISIBLE);//取消按钮显示
            bln_judge = false;

            dataChanged();//因为判断里有显示或隐藏复选框，所以要刷新适配器
        } else {
            Toast.makeText(ActivityInventoryLossesOut.this, "无数据项可选择", Toast.LENGTH_SHORT).show();
        }

    }

    //点击删除
    public void delete(View view) {

        int count = 0;
        List<String> list_chandler_selected = new ArrayList<>();
        List<String> list_hprguid_selected = new ArrayList<>();

        for (int i = 0; i < outlistscq.size(); i++) {
            if (outlistscq.get(i).get("flag").equals("true")) {
                count++;
                list_chandler_selected.add(outlistscq.get(i).get("content18"));
                list_hprguid_selected.add(outlistscq.get(i).get("HPRGUID"));
            }

        }
        if (count == 0) {
            Toast.makeText(ActivityInventoryLossesOut.this, "请选择一项删除", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < list_hprguid_selected.size(); i++) {
            if (list_chandler_selected.get(i) == null || list_chandler_selected.get(i).equals("")) {//如果审核人为空，那么说明未审核，可以删除
                //调用删除出库接口
                HttpNetworkRequest.delete("goods/rs/hpCheckoutbound?str=" + list_hprguid_selected.get(i), new BaseHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String rawResponse, Object response) {
                        try {
                            JSONObject jsonObject = new JSONObject(rawResponse);
                            if (jsonObject.getString("message").equals("删除成功")) {
                                Toast.makeText(ActivityInventoryLossesOut.this, "删除成功", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            String msg = e.getMessage();
                            Toast.makeText(ActivityInventoryLossesOut.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable e, String rawData, Object errorResponse) {
                        Toast.makeText(ActivityInventoryLossesOut.this, "服务器请求失败，删除不成功", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(ActivityInventoryLossesOut.this, "已审核，不能删除", Toast.LENGTH_SHORT).show();
            }
        }
        btn_delete.setVisibility(View.GONE);//删除按钮隐藏
        btn_cancel.setVisibility(View.GONE);
        btn_select.setVisibility(View.VISIBLE);//选择按钮显示
        bln_judge = true;
        // 通知列表数据修改
        adapterLossesOut.hind(1);//隐藏复选框
        dataChanged();//因为判断里有显示或隐藏复选框，所以要刷新适配器
        onRefresh();
    }




    //点击取消
    public void cancel(View view) {

        for (int i = 0; i < outlistscq.size(); i++) {
            outlistscq.get(i).put("flag", "false");
        }
        //点击编辑显示checkbox
        btn_cancel.setVisibility(View.GONE);
        btn_delete.setVisibility(View.GONE);//删除按钮隐藏
        btn_select.setVisibility(View.VISIBLE);//选择按钮显示
        bln_judge = true;
        // 通知列表数据修改
        adapterLossesOut.hind(1);//隐藏复选框
        dataChanged();//因为判断里有显示或隐藏复选框，所以要刷新适配器
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                try {
                    page = 1;
                    pageN = "1";
                    list_hprguid.clear();
                    outlistscq.clear();
                    foreilout = null;
                    ilout = null;
                    list_ilout.clear();
                    NetworkRequest("1");
                    adapterLossesOut.notifyDataSetChanged();
                    btn_delete.setVisibility(View.GONE);
                    btn_cancel.setVisibility(View.GONE);
                    btn_select.setVisibility(View.VISIBLE);
                    onLoad();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (foreilout == null) {
                    onRefresh();
                    return;
                } else {

                    if (computePageIsLast(foreilout.getJsonData().getCurrentPage(), foreilout.getJsonData().getNumPerPage(), foreilout.getJsonData().getPageNumShown(), foreilout.getJsonData().getTotalCount())) {
                        NetworkRequest(pageN);
                    } else {
                        Toast.makeText(ActivityInventoryLossesOut.this, "当前已经是最后一页", Toast.LENGTH_SHORT).show();
                    }
                    for (int i = 0; i < outlistscq.size(); i++) {
                        outlistscq.get(i).put("flag", "false");
                    }
                    adapterLossesOut.notifyDataSetChanged();
                    btn_delete.setVisibility(View.GONE);
                    btn_cancel.setVisibility(View.GONE);
                    btn_select.setVisibility(View.VISIBLE);

                    onLoad();
                    return;
                }
            }
        }, 2000);
    }

    /**
     * 停止刷新，
     */
    private void onLoad() {
        lv_wzrk.stopRefresh();
        lv_wzrk.stopLoadMore();
        lv_wzrk.setRefreshTime(getTime());
    }

    //获取当前的日期及时间
    public String getTime() {
        Date date = new Date();
        java.text.DateFormat format = new SimpleDateFormat("HH:mm:ss");//大写的HH是24小时制，小写的hh则是12小时制
        String time = format.format(date);
        return time;
    }


    private boolean computePageIsLast(String currentpage, String numPerPage, String pageNumShown, String totalCount) {
        //当前页数
        int cpage = Integer.valueOf(currentpage);
        //每页条数
        int npage = Integer.valueOf(numPerPage);
        //当前条数
        int nownum = Integer.valueOf(pageNumShown);
        //总条数
        int tcount = Integer.valueOf(totalCount);

        int yucount = tcount % npage;

        int pagecount = 0;
        if (yucount > 0) {
            pagecount = tcount / npage + 1;
        } else {
            pagecount = tcount / npage;
        }
        boolean flag = false;
        if (cpage < pagecount) {

            flag = true;
        }
        if (cpage == pagecount) {
            flag = false;
        }
        if (cpage > pagecount) {
            flag = false;
        }
        return flag;


    }


    public void back_from_query_ui(View view) {
        linear_queryUI.setVisibility(View.GONE);
        img_direction.setImageResource(R.drawable.down);
        bln_is = true;
    }

    //同一入库单自动多选
    public void change(boolean bln, int posision) {
        for (int k = 0; k < outlistscq.size(); k++) {
            if (bln) {
                if (outlistscq.get(k).get("content1").equals(outlistscq.get(posision - 1).get("content1"))) {
                    outlistscq.get(k).put("flag", "true");
                }
            } else {
                if (outlistscq.get(k).get("content1").equals(outlistscq.get(posision - 1).get("content1"))) {
                    outlistscq.get(k).put("flag", "false");
                }
            }
        }
        dataChanged();
    }

}
