package com.yidiantong;

import com.yidiantong.bean.WeiXinBean;
import com.yidiantong.bean.XlseBean;

import java.util.ArrayList;

public interface BactIntefacer {
    void getSd(ArrayList<XlseBean> xlseBeans);
    void getWeixin(ArrayList<WeiXinBean>weiXinBeans);
}
