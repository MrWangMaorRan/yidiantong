package com.yidiantong.presenter.warehouse;

import android.content.Context;
import android.content.Intent;

import com.yidiantong.bean.UpLoadFileBean;
import com.yidiantong.bean.UserInfoBean;

import com.yidiantong.bean.WeiXinBean;
import com.yidiantong.httpUtils.OkhttpUtil;
import com.yidiantong.model.biz.warehouse.House;
import com.yidiantong.model.impl.warehouse.Houseimpl;

public class HousePresenter implements Houseimpl.OnCallBackListener {


    private  House house;
    private  Context mContext;
    private final Houseimpl houseimpl;
    private WeiXinBean upLoadFileBean1;

    public HousePresenter(Context mContext, House house){
        this.mContext=mContext;
        this.house=house;
        houseimpl = new Houseimpl();

    }
    public  void upWeinxin(){
        houseimpl.getWeixin(mContext,null,"file", OkhttpUtil.FILE_TYPE_IMAGE,this);
    }
    @Override
    public void onWeiXinFileSuccess(WeiXinBean upLoadFileBean) {
        upLoadFileBean1 = upLoadFileBean;
        house.onWeinxin(upLoadFileBean);


    }

    @Override
    public void onWeiXinFileFaulure(String msg) {

    }
}
