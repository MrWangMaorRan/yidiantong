package com.yidiantong.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Xml;
import android.view.View;
import android.widget.TextView;

import com.yidiantong.BaseFragment;
import com.yidiantong.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * A simple {@link Fragment} subclass.
 */
public class House_MyFile_Fragment extends BaseFragment {


    @Override
    protected int getLayout() {
        return R.layout.fragment_house__my_file_;
    }

    @Override
    protected void initView(View view) {
       Bundle arguments = getArguments();
       String msg = arguments.getString("msq");

    }

    @Override
    protected void initData() {

    }


}
