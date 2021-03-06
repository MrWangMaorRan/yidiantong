package com.yidiantong.model.impl.warehouse;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yidiantong.api.ApiConstants;
import com.yidiantong.bean.ResponseCallAllBean;
import com.yidiantong.httpUtils.CallBackUtil;
import com.yidiantong.httpUtils.HttpUtil;
import com.yidiantong.util.StringUtils;
import com.yidiantong.util.log.LogUtils;
import com.yidiantong.widget.ToastUtil;

import java.util.List;

import okhttp3.Call;

public class Myfile_lmpl {

    /**
     * 批量查询号码是否可以导入
     *
     * @param mContext
     * @param object
     * @param onCallBackListeners
     */
    public void importContactss(Context mContext, Object object, Myfile_lmpl.OnCallBackListeners onCallBackListeners) {
        HttpUtil.okHttpPostObjJson_2(mContext, ApiConstants.importContacts, object, new CallBackUtil.CallBackString(mContext) {
            @Override
            public void onFailure(Call call, Exception e) {
                LogUtils.i("PickContactImpl", "PickContactImpl importContacts onFailure --> " + e.getMessage());

                onCallBackListeners.onImportContactsFailure("");
            }

            @Override
            public void onResponse(String response) {
                try {
                    LogUtils.i("MyfileContactImpl", "PickContactImpl importContacts onResponse ---> " + response.toString());
                      ToastUtil.showShort(mContext,"电话号码已导入，无法重复导入");
                    if (!StringUtils.isNullOrBlank(response)) {
                        ResponseCallAllBean responseCallAllBean = new Gson().fromJson(
                                response, new TypeToken<ResponseCallAllBean>() {
                                }.getType());
                        if (responseCallAllBean.getCode() == 0) {
                            String jsonStr = new Gson().toJson(responseCallAllBean.getData());
                            onCallBackListeners.onImportContactsSuccesssss();
                            ToastUtil.showShort(mContext,"导入成功");
                        } else {
                            onCallBackListeners.onImportContactsFailure(responseCallAllBean.getMessage());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public interface OnCallBackListeners {
        // 成功
        void onSearchSuccess(List<String> phoneList);

        // 失败
        void onSearchFailure(String msg);

        // 成功
        void onImportContactsSuccesssss();

        // 失败
        void onImportContactsFailure(String msg);
    }
}
