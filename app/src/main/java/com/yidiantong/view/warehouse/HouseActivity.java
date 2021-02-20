package com.yidiantong.view.warehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.yidiantong.R;
import com.yidiantong.base.BaseActivity;
import com.yidiantong.bean.WeiXinBean;
import com.yidiantong.model.biz.warehouse.House;
import com.yidiantong.presenter.warehouse.HousePresenter;

public class HouseActivity extends BaseActivity implements House {


    private HousePresenter housePresenter;

    @Override
    public void getIntentData() {


    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        housePresenter = new HousePresenter(this, this);
    }

    @Override
    public void onWeinxin(WeiXinBean weiXinBean) {
        String path = weiXinBean.getData().getPath();

    }
}
