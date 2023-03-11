package com.enjoy.mvpshop.ui.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.enjoy.mvpshop.R;
import com.enjoy.mvpshop.bean.Goods;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;

public class  HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private RecyclerView recyclerView;
    private LayoutInflater layoutInflater;
    private Context context;
    private List<Goods> data;
    private OnItemClickListener listener;

    public HomeRecyclerViewAdapter(RecyclerView recyclerView, Context context, List<Goods> data) {
        this.recyclerView = recyclerView;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void setGoods(List<Goods> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                      int viewType) {
        View view = layoutInflater.inflate(viewType, parent, false);
        view.setOnClickListener(this);
        if (viewType == R.layout.home_recycler_text_image) {
            return new MultiViewHolder(view);
        }
        return new SigleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Goods goods = data.get(position);
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case R.layout.home_recycler_banner:
                ((Banner) holder.itemView).setAdapter(new BannerImageAdapter<String>(goods.getBanners()) {
                    @Override
                    public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                        //图片加载自己实现
                        Glide.with(holder.itemView)
                                .load(data)
                                .apply(RequestOptions.centerCropTransform())
                                .into(holder.imageView);
                    }
                }).addBannerLifecycleObserver((LifecycleOwner) context)//添加生命周期观察者
                        .setIndicator(new CircleIndicator(context));
                break;

            case R.layout.home_recycler_image:
                Glide.with(holder.itemView)
                        .load(goods.getImageUrl())
                        .apply(RequestOptions.centerCropTransform())
                        .into((ImageView) holder.itemView);
                break;
            case R.layout.home_recycler_text:
                ((TextView) holder.itemView).setText(goods.getText());
                break;
            case R.layout.home_recycler_text_image:
                MultiViewHolder multiViewHolder = (MultiViewHolder) holder;
                multiViewHolder.textView.setText(goods.getText());
                Glide.with(holder.itemView)
                        .load(goods.getImageUrl())
                        .apply(RequestOptions.centerCropTransform())
                        .into(multiViewHolder.imageView);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Goods goods = data.get(position);
        if (goods.getBanners() != null) {
            return R.layout.home_recycler_banner;
        } else if (goods.getImageUrl() == null) {
            //显示文字
            return R.layout.home_recycler_text;
        } else if (goods.getText() == null) {
            //显示图片
            return R.layout.home_recycler_image;
        } else {
            //文字+图片
            return R.layout.home_recycler_text_image;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            int position = recyclerView.getChildAdapterPosition(v);
            listener.onItemClick(data.get(position));
        }

    }


    class SigleViewHolder extends RecyclerView.ViewHolder {

        public SigleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class MultiViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public MultiViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    public void setOnItemClickLisnter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Goods goods);
    }
}
