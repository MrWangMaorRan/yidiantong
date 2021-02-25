package com.yidiantong.presenter.warehouse;

import android.content.Context;
import android.content.Intent;

import com.yidiantong.bean.UpLoadFileBean;
import com.yidiantong.bean.UserInfoBean;

import com.yidiantong.bean.WeiXinBean;
import com.yidiantong.httpUtils.OkhttpUtil;
import com.yidiantong.model.biz.warehouse.House;
import com.yidiantong.model.impl.warehouse.Houseimpl;

import java.io.File;

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
    public  void Setpath(String path){
//        File file = new File(path);
//        houseimpl.getWeixin(mContext,file,"file", OkhttpUtil.FILE_TYPE_FILE,this);
    }
    @Override
    public void onWeiXinFileSuccess(WeiXinBean upLoadFileBean) {
//        upLoadFileBean1 = upLoadFileBean;
//        house.onWeinxin(upLoadFileBean);


    }

    @Override
    public void onWeiXinFileFaulure(String msg) {

    }
}
