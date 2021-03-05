package com.yidiantong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yidiantong.R;
import com.yidiantong.bean.WeiXinBean;

import java.util.List;

public class House_Myfile_Adapter extends RecyclerView.Adapter<House_Myfile_Adapter.Hokder> {
    private List<WeiXinBean> mDatas;
    public House_Myfile_Adapter(List<WeiXinBean> data) {
        this.mDatas = data;
    }
    @NonNull
    @Override
    public Hokder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_fragment_myfile, parent, false);
        return new Hokder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Hokder holder, int position) {
        holder.tv_myfile.setText(mDatas.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击事件
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class Hokder extends RecyclerView.ViewHolder {

        private final TextView tv_myfile;

        public Hokder(@NonNull View itemView) {
            super(itemView);
            tv_myfile = itemView.findViewById(R.id.tv_myfile);
        }
    }
}
