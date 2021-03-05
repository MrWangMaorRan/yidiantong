package com.yidiantong.view.myhome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yidiantong.R;
import com.yidiantong.base.BaseActivity;
import com.yidiantong.util.ScreenUtil;

public class MyHomeActivity extends BaseActivity {


    @Override
    public void getIntentData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_home;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //rlContainer为Activity根布局的id, llContent是需要下移的布局的id
        ScreenUtil.makeStatusBarTransparent(this, findViewById(R.id.lll), findViewById(R.id.fy));

    }
}
