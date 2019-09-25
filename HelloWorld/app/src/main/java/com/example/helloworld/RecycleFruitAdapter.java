package com.example.helloworld;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleFruitAdapter extends RecyclerView.Adapter<RecycleFruitAdapter.ViewHolder> {
    private List<Fruit> mFruitList;

    //定义了一个内部类ViewHolder，构造函数中传入view参数，这个参数通常是RecyclerView子项的最外层布局
    static class ViewHolder extends RecyclerView.ViewHolder{
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;
        public ViewHolder(View view){
            super(view);
            fruitView = view; // 保存子项最外层布局的实例
            //获取布局中的实例
            fruitImage = view.findViewById(R.id.fruit_image);
            fruitName = view.findViewById(R.id.fruit_name);
        }
    }

    //构造函数，用于把要展示的数据源传进来并赋值给一个全局变量
    public RecycleFruitAdapter(List<Fruit> fruitList){
        mFruitList = fruitList;
    }

    //必须重写父类的方法之一，将fruit_item布局加载进来，然后创建一个ViewHolder实例
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        // 为最外层布局注册点击事件：点击文字由于TextView并没有注册点击事件，因此点击文字这个事件会被子项的最外层布局捕获到
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "you clicked view "+fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        // 为ImageView注册点击事件
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(v.getContext(), "you clicked image "+fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    //必须重写父类的方法之一，用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动到屏幕内的时候执行
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.geteImageId());
        holder.fruitName.setText(fruit.getName());
    }

    //必须重写父类的方法之一，用于高速RecyclerView一共有多少子项
    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
