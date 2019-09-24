package com.example.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// 自定义适配器
public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;

//    ArrayAdapter适配器的一种实现类，通过泛型来指定需要适配的数据类型，然后在构造函数中把要适配的数据传入。构造函数参数说明：1.当前上下文；2.ListVIew子项布局的id；3.要适配的数据
    public  FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

//  getView方法在每个子项被滚动到屏幕内的时候会被调用
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
//convertView参数用于将之前加载好的布局进行缓存，以便之后可以进行重用
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);  // 为这个子项加载我们传入的布局
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);  // 将viewHolder存储在view中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取viewHolder
        }
        viewHolder.fruitImage.setImageResource(fruit.geteImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }
}

// 用来对控件的实例进行缓存
class ViewHolder {
    ImageView fruitImage;
    TextView fruitName;
}
