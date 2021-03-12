package com.yidiantong.view.myhome;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.yidiantong.MainActivity;
import com.yidiantong.R;
import com.yidiantong.base.BaseActivity;
import com.yidiantong.bean.TalkTimeInfoBean;
import com.yidiantong.bean.UserInfoBean;
import com.yidiantong.util.LoadImageUtils;
import com.yidiantong.view.company.CompanyInfoActivity;
import com.yidiantong.view.mine.MineInfoActivity;
import com.yidiantong.view.setting.SettingActivity;
import com.yidiantong.view.warehouse.HouseActivity;
import com.yidiantong.widget.ToastUtil;

public class MyHomeActivity extends BaseActivity {

    public final static int REQUEST_CODE_MINE_UPDATE = 0xa1;
    public final static int REQUEST_CODE_MINE_UPDATE_SHICHANG= 0xa4;
    private Button bt_phino;
    private LinearLayout ll_my_business;
    private LinearLayout ll_setting;
    private LinearLayout ll_house;
    private LinearLayout ll_mine_info;
    private ImageView mMyhomeHead;
    private LinearLayout mLl;

    private LinearLayout mMyFirm;
    private LinearLayout mMyFile;
    private LinearLayout mSettingsss;
    private UserInfoBean userInfoBean;
    private TextView tv_myname;
    private TextView tv_myphone;
    private TalkTimeInfoBean talkTimeInfoBean;
    private ImageView im_my_home;
    private TextView tv_myhome_discounts_;
    private TextView tv_myhome_minute;
    private LinearLayout ll_youhui;


    @Override
    public void getIntentData() {


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_home;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_home);


        userInfoBean = (UserInfoBean) getIntent().getSerializableExtra("userInfoBean");
        talkTimeInfoBean = (TalkTimeInfoBean) getIntent().getSerializableExtra("talkTimeInfoBean");
        initView();
        initClick();
        if (userInfoBean!=null){
            tv_myname.setText(userInfoBean.getTitle());
            tv_myphone.setText(userInfoBean.getPhoneNumber());
            LoadImageUtils.loadImage(mMyhomeHead, userInfoBean.getPath());
        }
        if (talkTimeInfoBean!=null){
            String trafficSurplus = talkTimeInfoBean.getTrafficSurplus();
            int shichang = Integer.parseInt(trafficSurplus);
            int floor = (int) Math.floor(shichang/60);
            Log.i("剩余时长",floor+"");
            if (floor<60){
                im_my_home.setImageResource(R.drawable.pay_red);
                tv_myhome_discounts_.setTextColor(getResources().getColor(R.color.red_d81717));
                tv_myhome_minute.setTextColor(Color .parseColor( "#f21212"));
                tv_myhome_minute.setText(floor+"");
            }else {
                tv_myhome_minute.setText(floor+"");
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void initClick() {
        bt_phino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyHomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MineInfoActivity.class);
                intent.putExtra("MyHome",userInfoBean);
                ((Activity) mContext).startActivityForResult(intent, REQUEST_CODE_MINE_UPDATE);
            }
        });
        mMyFirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CompanyInfoActivity.class);
                mContext.startActivity(intent);
            }
        });
        mMyFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyHomeActivity.this, HouseActivity.class);
                intent.putExtra("chuanzhi","3");
                startActivity(intent);
            }
        });
        mSettingsss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SettingActivity.class);
                mContext.startActivity(intent);
            }
        });
        ll_youhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showShort(MyHomeActivity.this,"尽请期待！！");
            }
        });
    }

    private void initView() {
        bt_phino = findViewById(R.id.bt_phino);
        mMyhomeHead = findViewById(R.id.myhome_head);
        mLl = findViewById(R.id.ll);
        mMyFirm = findViewById(R.id.my_firm);
        mMyFile = findViewById(R.id.my_file);
        mSettingsss = findViewById(R.id.settingsss);
        tv_myname = findViewById(R.id.tv_myname);
        tv_myphone = findViewById(R.id.tv_Myphone);
        im_my_home = findViewById(R.id.im_my_home);
        tv_myhome_discounts_ = findViewById(R.id.tv_myhome_discounts_);
        tv_myhome_minute = findViewById(R.id.tv_myhome_minute);
        ll_youhui = findViewById(R.id.ll_youhui);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_MINE_UPDATE && resultCode == RESULT_OK) {
            if (data != null) {
                userInfoBean = (UserInfoBean) data.getSerializableExtra("userInfoBean");
                tv_myname.setText(userInfoBean.getTitle());
                tv_myphone.setText(userInfoBean.getPhoneNumber());
                LoadImageUtils.loadImage(mMyhomeHead, userInfoBean.getPath());
            }
        }

    }

}
