package com.zjrfid.materialsmanage.UI;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zjrfid.materialsmanage.R;
import com.zjrfid.materialsmanage.TreeViewTool.Element;
import com.zjrfid.materialsmanage.TreeViewTool.MyLeftAdapter;
import com.zjrfid.materialsmanage.TreeViewTool.MyRightAdapter;
import com.zjrfid.materialsmanage.TreeViewTool.RightModel;
import com.zjrfid.materialsmanage.TreeViewTool.SyncHorizontalScrollView;
import com.zjrfid.materialsmanage.TreeViewTool.TreeViewAdapter;
import com.zjrfid.materialsmanage.TreeViewTool.TreeViewItemClickListener;
import com.zjrfid.materialsmanage.TreeViewTool.UtilTools;
import com.zjrfid.materialsmanage.acdbentity.MaterialClassifiCation;
import com.zjrfid.materialsmanage.acdbentity.MaterialSpecificFilesInfo;
import com.zjrfid.materialsmanage.http.BaseHttpResponseHandler;
import com.zjrfid.materialsmanage.http.HttpNetworkRequest;
import com.zjrfid.materialsmanage.rfid.Result;
import com.zjrfid.materialsmanage.rfid.RfidOperation;
import com.zjrfid.materialsmanage.tool.SysApplication;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityBinding extends Activity implements View.OnClickListener {
    public static ActivityBinding binding;
    private TextView textview1, textview2, textview3, textview4, textview5, textview6, textview7, textview8, textview9, textview10;
    public ImageView iv_verdict;
    public Button btn_binding;
    Animation animation;
    private int m_sign;//设置标志位
    static Context context;
    private HashMap<String, Integer> hashMapMySelf = new HashMap<>();
    private HashMap<Integer, String> hashMapParentSelf = new HashMap<>();
    private ArrayList<Element> list_element = new ArrayList<>();
    private int flag = 3;//标志位为默认为3
    private LinearLayout layout_bd;

    /**
     * 树中的元素集合
     */
    private ArrayList<Element> elements;
    /**
     * 数据源元素集合
     */
    private ArrayList<Element> elementsData;

    private LinearLayout leftContainerView;
    private ListView leftListView;
    public List<String> leftlList = new ArrayList<>();
    private LinearLayout rightContainerView;
    private ListView rightListView;
    public List<RightModel> models = new ArrayList<>();
    private SyncHorizontalScrollView titleHorsv;
    private SyncHorizontalScrollView contentHorsv;
    public static LinearLayout ll_orientation;
    public static Button back;
    private boolean is = true;
    private EditText et_seek;
    private boolean judge = false;
    public MyRightAdapter myRightAdapter;
    MaterialSpecificFilesInfo msfi;
    private List<Integer> list_level = new ArrayList<>();//存节点level
    protected static final int MSG_SHOW_Message = 1;
    private TreeViewAdapter treeViewAdapter;
    public MaterialClassifiCation mcc;
    public MyLeftAdapter adapter;
    private Spinner spin_code;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> list_spin = new ArrayList<>();
    public Button clear_wzcode_Btn;//清除物资搜索编码


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);
        //
        binding = this;
        context = this;
        initView();
        SysApplication.getInstance().addActivity(this);
    }

    public void initView() {
        layout_bd = (LinearLayout) findViewById(R.id.layout_bd);
        layout_bd.setOnClickListener(this);
        clear_wzcode_Btn = (Button) findViewById(R.id.clear_wzcode_Btn);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        init();
        ll_orientation = (LinearLayout) findViewById(R.id.ll_orientation);
        back = (Button) findViewById(R.id.back);
        ListView treeview = (ListView) findViewById(R.id.lv_treeview);
        list_spin.add("物资编码");
        list_spin.add("旧编码");
        spin_code = (Spinner) findViewById(R.id.spin_code);
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

        leftContainerView = (LinearLayout) findViewById(R.id.left_container);
        leftListView = (ListView) findViewById(R.id.left_container_listview);
        rightContainerView = (LinearLayout) findViewById(R.id.right_container);
        rightListView = (ListView) findViewById(R.id.right_container_listview);
        titleHorsv = (SyncHorizontalScrollView) findViewById(R.id.title_horsv);
        contentHorsv = (SyncHorizontalScrollView) findViewById(R.id.content_horsv);
        et_seek = (EditText) findViewById(R.id.et_seek);
//        et_seek.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        // 设置两个水平控件的联动
        titleHorsv.setScrollView(contentHorsv);
        contentHorsv.setScrollView(titleHorsv);
        textview1 = (TextView) findViewById(R.id.textView1);
        textview2 = (TextView) findViewById(R.id.textView2);
        textview3 = (TextView) findViewById(R.id.textView3);
        textview4 = (TextView) findViewById(R.id.textView4);
        textview5 = (TextView) findViewById(R.id.textView5);
        textview6 = (TextView) findViewById(R.id.textView6);
        textview7 = (TextView) findViewById(R.id.textView7);
        textview8 = (TextView) findViewById(R.id.textView8);
        textview9 = (TextView) findViewById(R.id.textView9);
        textview10 = (TextView) findViewById(R.id.textView10);
        // 添加左边内容数据
        leftContainerView.setBackgroundColor(Color.YELLOW);
        adapter = new MyLeftAdapter(this, leftlList);
        leftListView.setAdapter(adapter);
        UtilTools.setListViewHeightBasedOnChildren(leftListView);
        // 添加右边内容数据
        rightContainerView.setBackgroundColor(Color.GRAY);
        myRightAdapter = new MyRightAdapter(this, models);
        rightListView.setAdapter(myRightAdapter);
        UtilTools.setListViewHeightBasedOnChildren(rightListView);
        rightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //网络请求，把物资编码传进去请求网络，和搜索用的是同样的网络请求代码
                NetworkRequests(leftlList.get(position));
                m_sign = position;
                btn_binding.setClickable(true);//让按钮可点击
                //隐藏打钩的图片
                iv_verdict.clearAnimation();
                iv_verdict.setVisibility(View.INVISIBLE);
                //0表示绑定，1表示未绑定
                if (models.get(position).getI() == 0) {
                    is = false;
                    btn_binding.setText("标签解绑");
                } else {
                    is = true;
                    btn_binding.setText("标签绑定");
                }
            }
        });
        treeViewAdapter = new TreeViewAdapter(elements, list_element, inflater, this);
        TreeViewItemClickListener treeViewItemClickListener = new TreeViewItemClickListener(treeViewAdapter, 0);
        treeview.setAdapter(treeViewAdapter);
        treeview.setOnItemClickListener(treeViewItemClickListener);
        //
        iv_verdict = (ImageView) findViewById(R.id.iv_verdict);
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
    }

    private void init() {
        try {
            elements = new ArrayList<Element>();
            elementsData = new ArrayList<Element>();
            list_element = new ArrayList<Element>();

            btn_binding = (Button) findViewById(R.id.btn_binding);
            //添加节点  -- 节点名称，节点level，节点id，父节点id，是否有子节点，是否展开
            //物资分类接口
            HttpNetworkRequest.get("goods/rs/hpInventoryassortment", new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {
                    Gson gson = new Gson();
                    mcc = gson.fromJson(s, MaterialClassifiCation.class);
                    int size = mcc.getJsonData().getList().size();
                    hashMapMySelf.put("0", 0);
                    list_element.add(new Element("物资分类", Element.TOP_LEVEL, 0, Element.NO_PARENT, true, false));
                    list_level.add(Element.TOP_LEVEL);
                    for (int j = 0; j < size; j++) {
                        hashMapMySelf.put(mcc.getJsonData().getList().get(j).getCinvccode(), j + 1);
                        hashMapParentSelf.put(j + 1, mcc.getJsonData().getList().get(j).getParentid());
                        list_level.add(Element.TOP_LEVEL + estimate(mcc.getJsonData().getList().get(j).getCinvccode()));
                    }
                    for (int k = 0; k < size; k++) {
                        String pid = hashMapParentSelf.get(k + 1);
                        int index = hashMapMySelf.get(pid);
                        boolean haschild = hashMapParentSelf.containsValue(mcc.getJsonData().getList().get(k).getCinvccode());
                        list_element.add(new Element(mcc.getJsonData().getList().get(k).getAlias(), list_level.get(k + 1), k + 1, index, haschild, false));
                    }
                    Message msg = new Message();
                    msg.obj = list_element;
                    msg.arg1 = size;
                    msg.what = MSG_SHOW_Message;
                    hMsg.sendMessage(msg);
                }

                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                    Toast.makeText(ActivityBinding.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            show("出错");
        }

    }

    //点击绑定
    public void binding(View view) {

        //让图片显示
        if (is == true) {//绑定
            //扫描RFID
            if (getRfid().equals("")) {
                Toast.makeText(ActivityBinding.this, "没有结果，请重新扫描", Toast.LENGTH_SHORT).show();
            } else {

//                models.get(m_sign).setI(0);//设置为绑定
//                models.get(m_sign).setText8(getRfid());//设置RFID
                getrfid(getRfid());

            }
        } else {//解绑
            //隐藏图片
            iv_verdict.clearAnimation();
            iv_verdict.setVisibility(View.INVISIBLE);
            btn_binding.setText("标签绑定");
            is = true;
//            models.get(m_sign).setI(1);//设置为未绑定
//            models.get(m_sign).setText8("");//设置RFID
            Toast.makeText(ActivityBinding.this, "解绑成功", Toast.LENGTH_SHORT).show();
            //点击解绑之后，把框里的数据清空
            textview1.setText("");
            textview2.setText("");
            textview3.setText("");
            textview4.setText("");
            textview5.setText("");
            textview6.setText("");
            textview7.setText("");
            textview8.setText("");
            textview9.setText("");
            textview10.setText("");
            btn_binding.setClickable(false);
            uploadInfo("null");//上传数据给服务器
        }
        //

        myRightAdapter.notifyDataSetChanged();
    }

    //点击扫描RFID
    public void smRfid(View view) {
        try {
            RequestParams params = new RequestParams();
            if (getRfid().equals("")) {
                textview1.setText("");
                textview2.setText("");
                textview3.setText("");
                textview4.setText("");
                textview5.setText("");
                textview6.setText("");
                textview7.setText("");
                textview8.setText("");
                textview9.setText("");
                textview10.setText("");
                is = true;
                btn_binding.setText("标签绑定");
            } else {
                params.put("rfid", getRfid());
                //物资档案接口
                HttpNetworkRequest.get("goods/rs/hpInventory?pageNum=1&hpicGuid=&cinvname=&cinvcode=&oldcord=", params, new BaseHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, String s, Object o) {
                        Gson gson = new Gson();
                        msfi = gson.fromJson(s, MaterialSpecificFilesInfo.class);
                        if (msfi.getJsonData().getList().size() == 0) {
                            Toast.makeText(ActivityBinding.this, "此RFID未绑定", Toast.LENGTH_SHORT).show();
                            textview1.setText("");
                            textview2.setText("");
                            textview3.setText("");
                            textview4.setText("");
                            textview5.setText("");
                            textview6.setText("");
                            textview7.setText("");
                            textview8.setText("");
                            textview9.setText("");
                            textview10.setText("");
                        } else {
                            if (msfi.getJsonData().getList().size() != 0) {
                                textview1.setText("物资编码：" + msfi.getJsonData().getList().get(0).getCINVCODE());
                                textview2.setText("物资名称：" + msfi.getJsonData().getList().get(0).getCINVNAME());
                                textview3.setText("规格型号：" + msfi.getJsonData().getList().get(0).getCINVSTD());
                                textview4.setText("计量单位：" + msfi.getJsonData().getList().get(0).getOLDUNITNAME());
                                textview5.setText("分类名称：" + msfi.getJsonData().getList().get(0).getCINVCNAME());
                                textview6.setText("车辆信息：" + msfi.getJsonData().getList().get(0).getBUSINFO());
                                textview7.setText("默认仓库：" + msfi.getJsonData().getList().get(0).getCWHNAME());
                                textview8.setText("默认货位：" + msfi.getJsonData().getList().get(0).getCPARENTID());
                                textview9.setText("旧编码：" + msfi.getJsonData().getList().get(0).getOLDCORD());
                                textview10.setText("RFID：" + msfi.getJsonData().getList().get(0).getRFID());
                                btn_binding.setClickable(true);//让按钮可以点击
                                //0表示绑定，1表示未绑定
                                if (msfi.getJsonData().getList().get(0).getRFID() != null) {
                                    is = false;
                                    btn_binding.setText("标签解绑");
                                } else {
                                    is = true;
                                    btn_binding.setText("标签绑定");
                                }
                                judge = false;//相当于归零,以便第二次使用
                            }
                        }

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                        Toast.makeText(ActivityBinding.this, "网络故障", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            show("出错");
        }
    }

    //点击回退
    public void back(View view) {
        finish();
    }

    public void btn_back(View view) {
        ll_orientation.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
    }

    //点击搜索
    public void seek(View view) {
        //隐藏图片
        iv_verdict.clearAnimation();
        iv_verdict.setVisibility(View.INVISIBLE);
        //网络请求方法
        NetworkRequests(et_seek.getText().toString());
        //让软键盘隐藏
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    //绑定信息上传
    public void uploadInfo(String rfid) {
        try {
            RequestParams params = new RequestParams();
            params.put("hpiGuid", msfi.getJsonData().getList().get(0).getHPIGUID());
            params.put("navTabId", "hpInventory");
            params.put("callbackType", "");
            params.put("callBackMethod", "");
            params.put("cinvcode", msfi.getJsonData().getList().get(0).getCINVCODE());
            params.put("oldcord", msfi.getJsonData().getList().get(0).getOLDCORD());
            params.put("cinvcname", msfi.getJsonData().getList().get(0).getCINVCNAME());
            params.put("hpicGuid", msfi.getJsonData().getList().get(0).getHPICGUID());
            params.put("cinvname", msfi.getJsonData().getList().get(0).getCINVNAME());
            params.put("shortname", msfi.getJsonData().getList().get(0).getSHORTNAME());
            params.put("guaranteeperiod", msfi.getJsonData().getList().get(0).getGUARANTEEPERIOD());
            params.put("dsdate", msfi.getJsonData().getList().get(0).getDSDATE());
            params.put("cinvstd", msfi.getJsonData().getList().get(0).getCINVSTD());
            params.put("dedate", "");
            params.put("oldunitname", msfi.getJsonData().getList().get(0).getOLDUNITNAME());
            params.put("ccomunitname", msfi.getJsonData().getList().get(0).getCCOMUNITNAME());
            params.put("convrate", "");
            params.put("cunitname", "");
            params.put("cunit", "");
            params.put("ftaxrate", msfi.getJsonData().getList().get(0).getFTAXRATE());
            params.put("cinvaddcode", "");
            params.put("businfo", "");
            params.put("fitcar", "");
            params.put("brand", "");
            params.put("factory", "");
            params.put("rfid", rfid);
            params.put("cdemo", "");
            params.put("ibatch", msfi.getJsonData().getList().get(0).getIBATCH());
            params.put("iturnover", msfi.getJsonData().getList().get(0).getITURNOVER());
            params.put("isale", msfi.getJsonData().getList().get(0).getISALE());
            params.put("iasset", "");
            params.put("isexchange", "");
            params.put("iswarning", "");
            params.put("filename", "");
            params.put("cwhname", msfi.getJsonData().getList().get(0).getCWHNAME());//
            params.put("hpwGuid", msfi.getJsonData().getList().get(0).getHPWGUID());
            params.put("cwhcode", "");//
            params.put("cposname", msfi.getJsonData().getList().get(0).getCPOSCODE());
            params.put("cposcode", msfi.getJsonData().getList().get(0).getCPOSCODE());
            params.put("imin", "");
            params.put("imax", "");
            //苏州说：CGETPRICETYPE参数的值有两种，默认和采购合同，默认是1，采购合同是0
            if (msfi.getJsonData().getList().get(0).getCGETPRICETYPE().equals("默认")) {
                params.put("cgetpricetype", "1");
            } else if (msfi.getJsonData().getList().get(0).getCGETPRICETYPE().equals("采购合同")) {
                params.put("cgetpricetype", "0");
            }
            params.put("csafestock1", "");
            params.put("csafestock11", "");
            params.put("csafestock2", "");
            params.put("csafestock22", "");
            params.put("csafestock3", "");
            params.put("csafestock33", "");
            //修改物资档案信息RFID码单条接口
            HttpNetworkRequest.post("goods/rs/hpInventorys", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(s);
                        if (jsonObject.getString("message").equals("操作成功")) {
                            Toast.makeText(ActivityBinding.this, "绑定成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ActivityBinding.this, "绑定失败", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            show("出错");
        }
    }

    //判断字符串最后一个字母出现的index
    public int estimate(String str) {
        String s = str;
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if ('A' < s.charAt(i) && s.charAt(i) < 'z') {
                count++;
                break;
            }
        }
        if (count == 0) {
            Log.i("info", "没有字母存在！");
        }
        return (str.length() - count + 1) / 3;
    }

    @Override
    public void onClick(View v) {
        ;
        switch (v.getId()) {
            case R.id.layout_pd:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                break;
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
            }
        }
    }


    private Handler hMsg = new StartHander(this);

    public void NetworkRequests(String parameters) {
        try {
            RequestParams params = new RequestParams();
            if (flag == 0) {//等于0表示是物资编码
                params.put("cinvcode", parameters);
                params.put("oldcord", "");
            } else if (flag == 1) {//等于1表示是旧编码
                params.put("cinvcode", "");
                params.put("oldcord", parameters);
            }
            //物资档案接口
            HttpNetworkRequest.get("goods/rs/hpInventory?pageNum=1&hpicGuid=&cinvname=", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {
                    Gson gson = new Gson();
                    msfi = gson.fromJson(s, MaterialSpecificFilesInfo.class);
                    if (msfi.getJsonData().getList().size() != 0) {
                        textview1.setText("物资编码：" + msfi.getJsonData().getList().get(0).getCINVCODE());
                        textview2.setText("物资名称：" + msfi.getJsonData().getList().get(0).getCINVNAME());
                        textview3.setText("规格型号：" + msfi.getJsonData().getList().get(0).getCINVSTD());
                        textview4.setText("计量单位：" + msfi.getJsonData().getList().get(0).getOLDUNITNAME());
                        textview5.setText("分类名称：" + msfi.getJsonData().getList().get(0).getCINVCNAME());
                        textview6.setText("车辆信息：" + msfi.getJsonData().getList().get(0).getBUSINFO());
                        textview7.setText("默认仓库：" + msfi.getJsonData().getList().get(0).getCWHNAME());
                        textview8.setText("默认货位：" + msfi.getJsonData().getList().get(0).getCPARENTID());
                        textview9.setText("旧编码：" + msfi.getJsonData().getList().get(0).getOLDCORD());
                        textview10.setText("RFID：" + msfi.getJsonData().getList().get(0).getRFID());
                        btn_binding.setClickable(true);//让按钮可以点击
                        //0表示绑定，1表示未绑定
                        if (msfi.getJsonData().getList().get(0).getRFID() != null) {
                            is = false;
                            btn_binding.setText("标签解绑");
                        } else {
                            is = true;
                            btn_binding.setText("标签绑定");
                        }
                        judge = false;//相当于归零,以便第二次使用
                    } else {
                        Toast.makeText(ActivityBinding.this, "没有搜索到", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                    Toast.makeText(ActivityBinding.this, "网络故障", Toast.LENGTH_SHORT).show();
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
            show("出错");
        }
    }

    //扫描获取rfid
    public String getRfid() {
        Result result = RfidOperation.readUnGivenTid((short) 3, (short) 3);
        return result.getReadInfo().toString();
    }

    public void getrfid(String rfid) {
        try {
            RequestParams params = new RequestParams();
            params.put("rfid", rfid);
            //物资档案接口
            HttpNetworkRequest.get("goods/rs/hpInventory?pageNum=1&hpicGuid=&cinvname=&cinvcode=&oldcord=", params, new BaseHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, String s, Object o) {
                    Gson gson = new Gson();
                    MaterialSpecificFilesInfo msfi2 = gson.fromJson(s, MaterialSpecificFilesInfo.class);
                    if (msfi2.getJsonData().getList().size() != 0) {
                        Toast.makeText(ActivityBinding.this, "此RFID已绑定其他物资", Toast.LENGTH_SHORT).show();
                    } else {
                        //布局的改变
                        iv_verdict.setVisibility(View.VISIBLE);
                        animation = AnimationUtils.loadAnimation(ActivityBinding.this, R.anim.alls);
                        iv_verdict.startAnimation(animation);//动画效果
                        btn_binding.setText("标签解绑");
                        is = false;
                        textview10.setText("RFID：" + getRfid());
                        //如果没绑定就上传数据给服务器
                        uploadInfo(getRfid());
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, Throwable throwable, String s, Object o) {
                    Toast.makeText(ActivityBinding.this, "网络故障", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            show("出错");
        }
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

    public void show(String str) {
        Toast.makeText(ActivityBinding.this, str, Toast.LENGTH_SHORT).show();
    }
}
