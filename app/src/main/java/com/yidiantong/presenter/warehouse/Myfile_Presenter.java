package com.yidiantong.presenter.warehouse;

import android.content.Context;

import com.yidiantong.bean.ContactBean;
import com.yidiantong.bean.XlseBean;
import com.yidiantong.bean.request.ImportAddressBookDto;
import com.yidiantong.model.biz.warehouse.IMyfile;
import com.yidiantong.model.impl.warehouse.Myfile_lmpl;

import java.util.ArrayList;
import java.util.List;

public class Myfile_Presenter implements Myfile_lmpl.OnCallBackListeners{
    private Context mContext;
    private IMyfile v;
    private Myfile_lmpl m;

    public Myfile_Presenter(Context context, IMyfile v) {
        this.mContext = context;
        this.v = v;
        m = new Myfile_lmpl();

    }
    // 查询是否可用
    public void importSD(ArrayList<XlseBean> xlseBeans) {
        ImportAddressBookDto importAddressBookDto = new ImportAddressBookDto();
        List<ContactBean> canImportList = new ArrayList<>();
        for (XlseBean xlseBean : xlseBeans) {
            ContactBean contactBean1 = new ContactBean();

            contactBean1.setPhone(xlseBean.getPhonenum());
            contactBean1.setName(xlseBean.getName());
            canImportList.add(contactBean1);

        }
        importAddressBookDto.setPhoneList(canImportList);
        m.importContactss(mContext, importAddressBookDto, this);

    }
    @Override
    public void onSearchSuccess(List<String> phoneList) {

    }

    @Override
    public void onSearchFailure(String msg) {

    }

    @Override
    public void onImportContactsSuccesssss() {

    }

    @Override
    public void onImportContactsFailure(String msg) {

    }
}
