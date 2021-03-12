package com.yidiantong.fragment;


import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yidiantong.BactIntefacer;
import com.yidiantong.BaseFragment;
import com.yidiantong.R;
import com.yidiantong.adapter.House_Myfile_Adapter;
import com.yidiantong.bean.WeiXinBean;
import com.yidiantong.bean.XlseBean;
import com.yidiantong.presenter.warehouse.Myfile_Presenter;
import com.yidiantong.model.biz.warehouse.IMyfile;
import com.yidiantong.util.ExcelUtils;
import com.yidiantong.util.FileCopyUtil;
import com.yidiantong.util.FileCopyUtils;
import com.yidiantong.widget.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class House_MyFile_Fragment extends BaseFragment implements IMyfile {
    String TAG = "111111111111111";
    private RecyclerView rec;

    //新文件夹创建
    String path = Environment.getExternalStorageDirectory() + "/MyFiles/yidiantong_/";
    private LinearLayout ll;
    private ArrayList<WeiXinBean> list;

    private Button daoru_one;
    private House_Myfile_Adapter adapter;
    private Myfile_Presenter ppp;
    private String path_dan;
    private ImageView im_fragment_myfile;

    @Override
    protected int getLayout() {
        return R.layout.fragment_house__my_file_;
    }

    @Override
    protected void initView(View view) {
        Bundle arguments = getArguments();
        String msq = arguments.getString("msq");
        rec = view.findViewById(R.id.rv_fragment_Myfile);
        daoru_one = view.findViewById(R.id.daoru_one);
        ll = view.findViewById(R.id.ll);
        im_fragment_myfile = view.findViewById(R.id.Im_fragment_myfile);

        ppp = new Myfile_Presenter(getContext(),this );
        //设置布局管理器
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        //添加Android自带的分割线
        rec.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        list = new ArrayList<>();
        adapter = new House_Myfile_Adapter(list,msq);
        //设置Adapter
        rec.setAdapter(adapter);
        initgetData(arguments,msq);

        adapter.setOnClickItem(new House_Myfile_Adapter.OnClickItem() {



            @Override
            public void onClickListenerItem(int position) {
                adapter.setIndex(position);
                adapter.notifyDataSetChanged();
                path_dan = list.get(position).getPath();
            }
        });

        List<WeiXinBean> weiXinBeans = GetVideoFileName(path);
        if (weiXinBeans != null && weiXinBeans.size() > 0) {
            //这个展示
            for (int i = 0; i < weiXinBeans.size(); i++) {
                WeiXinBean bean = weiXinBeans.get(i);
                bean.codeone = "0";
                list.add(bean);
            }
            adapter.notifyDataSetChanged();
        }

//
//        if (arguments != null) {
//
//            if (msq.equals("2")) {
////                adapter.show = true;
//                daoru_one.setVisibility(View.VISIBLE);
//                daoru_one.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (list!=null&&!list.isEmpty()){
//                            UpFile();
//                        }else {
//                            ToastUtil.showShort(getContext(),"请选择导入文件");
//                        }
//                    }
//                });
//            } else {
////                adapter.show = false;
//   //             im_fragment_myfile.setVisibility(View.GONE);
//                daoru_one.setVisibility(View.GONE);
//                File file = new File(msq);
//                Log.i("文件老路径", msq);
//                String name = file.getName();
//                Log.i("文件名字", name);
//                //新文件创建
//                String newpath = Environment.getExternalStorageDirectory() + "/MyFiles/yidiantong_/" + name;
//                Log.i("文件夹", newpath);
//                try {
//                    //文件夹创建
//                    boolean b = FileCopyUtils.fileCopy(msq, path);
//                    Log.i("文件", b + "");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                //新文件创建
//                new FileCopyUtil().copyFile(msq, newpath);
//
//            }
//
//        }
    }
    public void initgetData(Bundle arguments, String msq){
        if (arguments != null) {

            if (msq.equals("2")) {
//                adapter.show = true;
                daoru_one.setVisibility(View.VISIBLE);
                daoru_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (list!=null&&!list.isEmpty()){
                            UpFile();
                        }else {
                            ToastUtil.showShort(getContext(),"请选择导入文件");
                        }
                    }
                });
            } else {
//                adapter.show = false;
                //             im_fragment_myfile.setVisibility(View.GONE);
                daoru_one.setVisibility(View.GONE);
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

            }

        }
    }

    public void UpFile() {

        if (path_dan == null||path_dan.length()==0) {
            ToastUtil.showShort(getContext(), "请选择文件");
        } else {
            File file = new File(path_dan);
            ExcelUtils.readExcel(file,new BactIntefacer() {
                @Override
                public void getSd(ArrayList<XlseBean> xs) {
//                    List<ContactBean> lists = new ArrayList<>();
//                    for (int i = 0; i < xs.size(); i++) {
//                        ContactBean b1 = new ContactBean();
//                        b1.setName(xs.get(i).getName());
//                        b1.setPhone(xs.get(i).getPhonenum());
//                        lists.add(b1);
//                    }
                    ppp.importSD(xs);
                    EventBus.getDefault().post("sucess");

                }

                @Override
                public void getWeixin(ArrayList<WeiXinBean> weiXinBeans) {

                }
            });
        }
    }

    // 获取当前目录下所有的xlsx文件
    private List<WeiXinBean> GetVideoFileName(String fileDir) {
        ArrayList<WeiXinBean> pathList = new ArrayList<>();
        File file = new File(fileDir);

        File[] subFile = file.listFiles();
        String path1 = file.getPath();
        if (subFile == null) {

        } else {
            for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
                // 判断是否为文件夹
                if (!subFile[iFileLength].isDirectory()) {
                    String filename = subFile[iFileLength].getName();
                    String path = subFile[iFileLength].getPath();
                    // 判断是否为xlsx结尾
                    if (filename.trim().toLowerCase().endsWith(".xlsx")) {
                        pathList.add(new WeiXinBean(filename, path));
                    } else {
                    }
                }
            }
        }
        return pathList;
    }


    @Override
    protected void initData() {

    }

    @Override
    public void checkedResult() {

    }

    @Override
    public void screeningResult(String str) {

    }

    @Override
    public void checkedAllResult(boolean isCheckedAll) {

    }

//    @Override
//    public void no(String s) {
//        Toast.makeText(context, "错了", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void ok() {
//        Toast.makeText(context, "成功，正在上传第"+a+"个", Toast.LENGTH_SHORT).show();
//        UpFile();
//    }

}
