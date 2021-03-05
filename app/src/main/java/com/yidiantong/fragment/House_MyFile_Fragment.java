package com.yidiantong.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;

import com.yidiantong.BaseFragment;
import com.yidiantong.R;
import com.yidiantong.adapter.House_Myfile_Adapter;
import com.yidiantong.bean.WeiXinBean;
import com.yidiantong.util.FileCopyUtil;
import com.yidiantong.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class House_MyFile_Fragment extends BaseFragment {
    String TAG = "111111111111111";
    private RecyclerView rv_fragment_myfile;

    //新文件夹创建
   String path = Environment.getExternalStorageDirectory() + "/MyFiles/yidiantong_/";

    @Override
    protected int getLayout() {
        return R.layout.fragment_house__my_file_;
    }

    @Override
    protected void initView(View view) {
        rv_fragment_myfile = view.findViewById(R.id.rv_fragment_Myfile);
        Bundle arguments = getArguments();
        if (arguments == null) {
            getData();
        } else {

            String msq = arguments.getString("msq");
            File file = new File(msq);
            Log.i("文件老路径", msq);
            String name = file.getName();
            Log.i("文件名字", name);
            //新文件创建
            String newpath = Environment.getExternalStorageDirectory() + "/MyFiles/yidiantong_/" + name;
            Log.i("文件夹", newpath);
            try {
                //文件夹创建
                boolean b = FileCopyUtils.fileCopy(msq, path);
                Log.i("文件", b + "");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //新文件创建
            new FileCopyUtil().copyFile(msq, newpath);
            //列表显示
            getData();
//            Thread thread = new Thread() {
//                @Override
//                public void run() {
//                    super.run();
//
//
//                    }
//            };
//                thread.start();

//            // 获取当前目录下所有的xlsx文件
//            ArrayList<WeiXinBean> weiXinBeans = new ArrayList<>();
//            List<String> strings = GetVideoFileName(path);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//            House_Myfile_Adapter house_myfile_adapter = new House_Myfile_Adapter(strings);
//
//
//            //设置布局管理器
//            rv_fragment_myfile.setLayoutManager(layoutManager);
//            //设置Adapter
//            rv_fragment_myfile.setAdapter(house_myfile_adapter);
//            house_myfile_adapter.notifyDataSetChanged();
//            //设置分隔线
//            //  rv_fragment_myfile.addItemDecoration( new DividerGridItemDecoration(this ));
//            //添加Android自带的分割线
//            rv_fragment_myfile.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        }
    }

    private void getData() {
        // 获取当前目录下所有的xlsx文件
        ArrayList<WeiXinBean> weiXinBeans = new ArrayList<>();
        List<WeiXinBean> strings = GetVideoFileName(path);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        House_Myfile_Adapter house_myfile_adapter = new House_Myfile_Adapter(strings);
        for (int i = 0; i <strings.size() ; i++) {
            WeiXinBean weiXinBean = new WeiXinBean(strings.get(i).getName(),strings.get(i).getPath());
            weiXinBeans.add(weiXinBean);
        }

        //设置布局管理器
        rv_fragment_myfile.setLayoutManager(layoutManager);
        //设置Adapter
        rv_fragment_myfile.setAdapter(house_myfile_adapter);
        house_myfile_adapter.notifyDataSetChanged();
        //添加Android自带的分割线
        rv_fragment_myfile.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }


    // 获取当前目录下所有的xlsx文件
        private List<WeiXinBean> GetVideoFileName (String fileDir){
            ArrayList<WeiXinBean> pathList = new ArrayList<>();
            File file = new File(fileDir);
            File[] subFile = file.listFiles();
            String path1 = file.getPath();
            for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
                // 判断是否为文件夹
                if (!subFile[iFileLength].isDirectory()) {
                    String filename = subFile[iFileLength].getName();
                    String path = subFile[iFileLength].getPath();
                    // 判断是否为xlsx结尾
                    if (filename.trim().toLowerCase().endsWith(".xlsx")) {
                        pathList.add(new WeiXinBean(filename,path));
                    } else {
                    }
                }
            }
            return pathList;
        }




//    // 获取当前目录下所有的xlsx文件
//    private List<String> GetVideoFileName(String fileDir) {
//        ArrayList<String> pathList = new ArrayList<>();
//        File file = new File(fileDir);
//        File[] subFile = file.listFiles();
//
//        for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
//            // 判断是否为文件夹
//            if (!subFile[iFileLength].isDirectory()) {
//                String filename = subFile[iFileLength].getName();
//                String path = subFile[iFileLength].getPath();
//                // 判断是否为xlsx结尾
//                if (filename.trim().toLowerCase().endsWith(".xlsx")) {
//                    pathList.add(filename);
//
//                } else  {
//                }
//            }
//        }
//        return pathList;
//
//    }
    @Override
    protected void initData() {

    }


}
