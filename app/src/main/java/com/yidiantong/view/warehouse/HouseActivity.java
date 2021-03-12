package com.yidiantong.view.warehouse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.yidiantong.GetRealPath;
import com.yidiantong.R;
import com.yidiantong.adapter.Vp_Fragment_Adapter;
import com.yidiantong.base.BaseActivity;
import com.yidiantong.bean.WeiXinBean;
import com.yidiantong.fragment.House_MyFile_Fragment;
import com.yidiantong.model.biz.warehouse.House;
import com.yidiantong.presenter.warehouse.HousePresenter;

import java.util.ArrayList;

public class HouseActivity extends BaseActivity implements House {


    private HousePresenter housePresenter;

    private TabLayout tb;
    private ArrayList<String> mtitleList;
    private ArrayList<Fragment> fragments;
    private ViewPager vp;
    private Toolbar toolbar;
    private String fileTruePath;

    @Override
    public void getIntentData() {
        //初始化控件
        initView();
        //添加标题
        initTitile();
        //添加fragment
        fragments = new ArrayList<>();
        // fragments.add(new House_firm_Fragment());

        Intent intent = getIntent();
        String chuanzhi = intent.getStringExtra("chuanzhi");
        if (chuanzhi!=null){
            if (chuanzhi.equals("2")){
            //2是弹窗跳过来
                House_MyFile_Fragment  house_myFile_fragment = new House_MyFile_Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("msq","2");
                house_myFile_fragment.setArguments(bundle);
                fragments.add(house_myFile_fragment);
            }else {
                House_MyFile_Fragment  house_myFile_fragment = new House_MyFile_Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("msq","3");
                house_myFile_fragment.setArguments(bundle);
                fragments.add(house_myFile_fragment);
            }
        }
        //微信文件
        getroute();
        //设置适配器
        vp.setAdapter(new Vp_Fragment_Adapter(getSupportFragmentManager(), fragments, mtitleList));
        //将tablayout与fragment关联
        tb.setupWithViewPager(vp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        tb.setSelectedTabIndicatorHeight(0);




    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }





    private void initTitile() {
        mtitleList = new ArrayList<>();
       // mtitleList.add("企业文件");
        mtitleList.add("我的文件");
        tb.setTabMode(TabLayout.MODE_FIXED);
        tb.addTab(tb.newTab().setText(mtitleList.get(0)));
       // tb.addTab(tb.newTab().setText(mtitleList.get(1)));
    }

    private void initView() {
        tb = findViewById(R.id.house_tablayout);
        vp = findViewById(R.id.house_Vp);
        toolbar = findViewById(R.id.house_toolbar);
    }

    private  void  getroute() {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.ACTION_VIEW.equals(action)) {
                Uri uri = intent.getData();
                Log.i("真实路径", uri.toString());
//                String str = Uri.decode(uri.getEncodedPath());
//                    String data = intent.getDataString();
//                    String str = Uri.decode(data);

                //获取到真实路径 GetRealPath.getFPUriToPath（）
                String path = GetRealPath.getFPUriToPath(this, uri);
                String[] dataStr = path.split("/");
                fileTruePath = "";
                //  String fileTruePath = "";
                for (int i = 4; i < dataStr.length; i++) {
                    fileTruePath = fileTruePath + "/" + dataStr[i];
                    //fileTruePath = "/"+dataStr[i];

                }
                House_MyFile_Fragment  house_myFile_fragment = new House_MyFile_Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("msq",path);
                house_myFile_fragment.setArguments(bundle);
                fragments.add(house_myFile_fragment);
                // transaction.commit();
            }


    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_house;
    }

    @Override
    public void init(Bundle savedInstanceState) {
//        housePresenter = new HousePresenter(this, this);
//        if (fileTruePath!=null){
//            housePresenter.Setpath(fileTruePath);
//        }
    }

    @Override
    public void onWeinxin(WeiXinBean weiXinBean) {
       // String path = weiXinBean.getData().getPath();

    }
}
