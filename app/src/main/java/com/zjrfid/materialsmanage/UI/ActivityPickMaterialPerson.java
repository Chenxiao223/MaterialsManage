package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.acdbentity.Cdepartment;
import com.zjrfid.materialsmanage.acdbentity.CpersonOrgDriver;
import com.zjrfid.materialsmanage.acdbentity.RequsetBackRecord;
import com.zjrfid.materialsmanage.adapter.AdapterJobNameOrg;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.tool.SysApplication;
import com.zjrfid.materialsmanage.xListView.XListView;
import com.zjrfid.materialsmanage.xListView.XListView.IXListViewListener;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhouyu on 2017/4/27.
 * 领料人员和领料部门
 */
public class ActivityPickMaterialPerson extends Activity implements XListView.IXListViewListener {

    private XListView lv_JobNameOrg;
    private TextView tv_TotalCount;
    private ImageView iv_DropOrPlug;
    private EditText et_JobNumber, et_PersonName;
    private Spinner spinner_Departement;
    private LinearLayout layout_query_menu;
    private String query_jobnumber = "";
    private String query_personname = "";
    private int query_department = 0;
    private RequsetBackRecord mRBR = new RequsetBackRecord("0", "0", "0", "0");

    private boolean isHideFlag = true;
    private Handler mHandler;
    private ArrayList<Cdepartment.Bean> list_department = new ArrayList<>();//部门名称和编号
    private List<CpersonOrgDriver.JsonDataBean.Bean> list_personorgdriver = new ArrayList<>();
    private AdapterJobNameOrg adapterJobNameOrg;
    private ArrayAdapter<String> adapter_Department;
    private ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_material_person);

        SysApplication.getInstance().addActivity(this);
        lv_JobNameOrg = (XListView) findViewById(R.id.lv_JobNameOrg);
        lv_JobNameOrg.setPullLoadEnable(true);//设置让它上拉，FALSE为不让上拉，便不加载更多数据
        et_JobNumber = (EditText) findViewById(R.id.et_JobNumber);
        et_PersonName = (EditText) findViewById(R.id.et_PersonName);
        spinner_Departement = (Spinner) findViewById(R.id.spinner_Departement);
        layout_query_menu = (LinearLayout) findViewById(R.id.layout_query_menu);
        tv_TotalCount = (TextView) findViewById(R.id.tv_TotalCount);
        iv_DropOrPlug = (ImageView) findViewById(R.id.iv_DropOrPlug);

        adapter_Department = new ArrayAdapter<String>(this, R.layout.spinner_item, R.id.tv_item, list);
        adapter_Department.setDropDownViewResource(R.layout.spinner_item);
        spinner_Departement.setAdapter(adapter_Department);
        adapterJobNameOrg = new AdapterJobNameOrg(ActivityPickMaterialPerson.this, list_personorgdriver);
        lv_JobNameOrg.setAdapter(adapterJobNameOrg);
        lv_JobNameOrg.setXListViewListener(this);
        isHideFlag = true;
        mHandler = new Handler();
        requestDepartment();
        onRefresh();//加载第一页
        /*
        选中项返回
         */
        lv_JobNameOrg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CpersonOrgDriver.JsonDataBean.Bean mBean = list_personorgdriver.get(position - 1);
                ActivityMaterialsOutBound.materialsOutBound.tv_acquisition_staff.setText(mBean.getUsername());//领料人员
                ActivityMaterialsOutBound.materialsOutBound.tv_section.setText(mBean.getOrgname());//领料部门
                finish();
            }
        });
        /*
        设置工号监听
         */
        et_JobNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_JobNumber.getText().length() != 0) {
                    query_jobnumber = et_JobNumber.getText().toString();
                }
            }
        });
        /*
        设置名称监听
         */
        et_PersonName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_PersonName.getText().length() != 0) {
                    query_personname = et_PersonName.getText().toString();
                }
            }
        });
        /*
        选择部门
        */
        spinner_Departement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hideSoftKeyBoard(view);
                query_department = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                query_department = 0;
            }
        });


    }

    public void back(View view) {
        finish();
    }

    /*
       隐藏键盘
    */
    private void hideSoftKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) ActivityPickMaterialPerson.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /*
    上下拉布局
     */
    public void DropAndUp(View view) {

        hideSoftKeyBoard(view);
        HideAndShowQueryMenu();
    }

    /*
    隐藏显示
     */
    public void HideAndShowQueryMenu() {
        if (isHideFlag) {
            iv_DropOrPlug.setImageResource(R.drawable.down);
            layout_query_menu.setVisibility(View.VISIBLE);
            isHideFlag = false;
        } else {

            iv_DropOrPlug.setImageResource(R.drawable.up);
            layout_query_menu.setVisibility(View.GONE);
            isHideFlag = true;
        }
    }

    /*
    清除查询项
     */
    public void clearQuery(View view) {
        hideSoftKeyBoard(view);
        query_jobnumber = "";
        query_personname = "";
        query_department = 0;
        et_JobNumber.setText("");
        et_PersonName.setText("");
        spinner_Departement.setSelection(0);
    }

    /*
    查询领料人员
     */
    public void query(View view) {
        hideSoftKeyBoard(view);
        HideAndShowQueryMenu();
        list_personorgdriver.clear();
        mRBR = new RequsetBackRecord("0", "0", "0", "0");
        requestJobNameOrgInfo("1");
    }

    /*
    网络请求领料人员列表
     */
    public void requestJobNameOrgInfo(String nowPage) {
        RequestParams rparms = new RequestParams();
        rparms.put("jobnumber", query_jobnumber);
        rparms.put("username", query_personname);
        rparms.put("orgname", list_department.get(query_department).getDeptid());
        rparms.put("isPurchase", "");
        rparms.put("numPerPage", "10");
        rparms.put("pageNum", nowPage);

        HttpNetworkRequest.get("goods/rs/hpPersonAndOrgAndDriver", rparms, new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, String s, Object o) {
                CpersonOrgDriver mCpersonOrgDriver = new CpersonOrgDriver();
                Gson gson = new Gson();
                mCpersonOrgDriver = gson.fromJson(s, CpersonOrgDriver.class);
                for (int j=0;j<mCpersonOrgDriver.getJsonData().getList().size();j++){
                    list_personorgdriver.add(mCpersonOrgDriver.getJsonData().getList().get(j));
                }
                adapterJobNameOrg.notifyDataSetChanged();
                mRBR = setRequestBackRecord(mCpersonOrgDriver);
                tv_TotalCount.setText(mRBR.getTotalCount());//显示总共多少条数据
            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                Log.e("error","出错了");
            }
        });
    }

    /*
    设置返回数据记录
     */
    public RequsetBackRecord setRequestBackRecord(CpersonOrgDriver newCPOD) {
        RequsetBackRecord newRBR = new RequsetBackRecord("0", "0", "0", "0");
        newRBR.setCurrentpage(newCPOD.getJsonData().getCurrentPage());
        newRBR.setNumPerPage(newCPOD.getJsonData().getNumPerPage());
        newRBR.setPageNumShown(newCPOD.getJsonData().getPageNumShown());
        newRBR.setTotalCount(newCPOD.getJsonData().getTotalCount());
        return newRBR;
    }

    /*
    网络请求部门
    第0项是默认未选
     */
    public void requestDepartment() {

        HttpNetworkRequest.get("goods/rs/hpOrgrefer", new BaseHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, String s, Object o) {
                Gson gson = new Gson();
                Cdepartment mdepartment = gson.fromJson(s, Cdepartment.class);
                list_department = mdepartment.getJsonData().getList();


                for (int j = 0; j < list_department.size(); j++) {
                    list.add(list_department.get(j).getOrgname());
                }
                list.add(0, "部门");
                list_department.add(0, new Cdepartment.Bean("", "部门"));
                adapter_Department.notifyDataSetChanged();

            }

            @Override
            public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
            }
        });

    }


    /*
    计算当前页是否为最后一页,true为最后一页，false为非最后页
     */
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

    //刷新第一页
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list_personorgdriver.clear();
                mRBR = new RequsetBackRecord("0", "0", "0", "0");
                requestJobNameOrgInfo("1");
                onLoad();
            }
        }, 1000);
    }

    //加载更多
    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mRBR.getCurrentpage().equals("0")) {
                    onRefresh();
                    return;
                } else {
                    if (computePageIsLast(mRBR.getCurrentpage(), mRBR.getNumPerPage(), mRBR.getPageNumShown(), mRBR.getTotalCount())) {
                        Toast.makeText(ActivityPickMaterialPerson.this, "当前已经是最后一页", Toast.LENGTH_SHORT).show();
                    } else {
                        //当前页增加一页
                        mRBR.setCurrentpage(String.valueOf(Integer.valueOf(mRBR.getCurrentpage()) + 1));
                        requestJobNameOrgInfo(mRBR.getCurrentpage());
                    }
                    onLoad();
                    return;
                }
            }
        }, 1000);
    }

    /*
 停止刷新
 */
    private void onLoad() {
        lv_JobNameOrg.stopRefresh();
        lv_JobNameOrg.stopLoadMore();
        lv_JobNameOrg.setRefreshTime("刚刚");
    }
}
