package com.yidiantong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yidiantong.R;
import com.yidiantong.bean.WeiXinBean;

import java.util.List;

public class House_Myfile_Adapter extends RecyclerView.Adapter<House_Myfile_Adapter.Hokder> {
    private final String mMsq;
    private List<WeiXinBean> mDatas;
    public boolean show;
    private int mPositionNotify=-1;

    public House_Myfile_Adapter(List<WeiXinBean> data, String msq) {
        this.mDatas = data;
        this.mMsq =msq;
    }
    @NonNull
    @Override
    public Hokder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itme_fragment_myfile, parent, false);
        return new Hokder(view);
    }
    //单选
    public void setIndex(int index) {
        mPositionNotify=index;
    }
    @Override
    public void onBindViewHolder(@NonNull Hokder holder, int position) {
        WeiXinBean weiXinBean = mDatas.get(position);
        holder.tv_myfile.setText(weiXinBean.getName());
        if(mPositionNotify==position){
            holder.im_fragment_myfile.setImageResource(R.drawable.ic_radio_off);
        }else{
            holder.im_fragment_myfile.setImageResource(R.drawable.ic_radio_on);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.onClickListenerItem(position);
            }
        });
        if(mMsq.equals("2")){
            holder.im_fragment_myfile.setVisibility(View.VISIBLE);
        }else{
            holder.im_fragment_myfile.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class Hokder extends RecyclerView.ViewHolder {


        private final TextView tv_myfile;
        private final ImageView im_fragment_myfile;


        public Hokder(@NonNull View itemView) {
            super(itemView);
            tv_myfile = itemView.findViewById(R.id.tv_myfile);
            im_fragment_myfile = itemView.findViewById(R.id.Im_fragment_myfile);

        }
    }
    public interface OnClickItem{
        void onClickListenerItem(int position);
    }
    OnClickItem onClickItem;

    public OnClickItem getOnClickItem() {
        return onClickItem;
    }

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }
}
