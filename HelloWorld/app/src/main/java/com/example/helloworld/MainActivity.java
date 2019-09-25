package com.example.helloworld;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] data = {"Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango", };

    private List<Fruit> fruitList = new ArrayList<>();

    private void initFruits() {
        for (int i=0; i<2; i++){
            Fruit apple = new Fruit("Apple", R.drawable.img_1);
            fruitList.add(apple);
            Fruit Banana = new Fruit("Banana", R.drawable.img_2);
            fruitList.add(Banana);
            Fruit Orange = new Fruit("Orange", R.drawable.img_1);
            fruitList.add(Orange);
            Fruit Watermelon = new Fruit("Watermelon", R.drawable.img_2);
            fruitList.add(Watermelon);
            Fruit Pear = new Fruit("Pear", R.drawable.img_1);
            fruitList.add(Pear);
            Fruit Grape = new Fruit("Grape", R.drawable.img_2);
            fruitList.add(Grape);
            Fruit Pineapple = new Fruit("Pineapple", R.drawable.img_1);
            fruitList.add(Pineapple);
            Fruit Strawberry = new Fruit("Strawberry", R.drawable.img_2);
            fruitList.add(Strawberry);
            Fruit Cherry = new Fruit("Cherry", R.drawable.img_1);
            fruitList.add(Cherry);
            Fruit Mango = new Fruit("Mango", R.drawable.img_2);
            fruitList.add(Mango);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

////      隐藏系统自带的标题栏
//        ActionBar actionBar = getActionBar();
//        if (actionBar != null){
//            actionBar.hide();
//        }

////      ArrayAdapter适配器的一种实现类，通过泛型来指定需要适配的数据类型，然后在构造函数中把要适配的数据传入。构造函数参数说明：1.当前上下文；2.ListVIew子项布局的id；3.要适配的数据
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
//        ListView listView = (ListView) findViewById(R.id.list_view);
////      将构建好的适配器对象传进去，这样ListView和数据之间的关联就建立完成了。
//        listView.setAdapter(adapter);

        initFruits(); // 初始化数据

/*      //使用ListView
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

//      为ListView注册一个监听器，当用户点击了ListView中的任何一个子项时，就会回调onItemClick方法。在这个方法中通过position参数判断用户点击的是哪一个子项。
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();

            }
        });
*/

//使用RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);  // 使用的是线性布局的方式
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // 该方法来设置布局的排列方式，默认是纵向排列的，传入LinearLayoutManager.HORIZONTAL表示让布局横向滚动。
        recyclerView.setLayoutManager(layoutManager);  // layoutManager用于指定RecyclerView的布局方式
        RecycleFruitAdapter adapter = new RecycleFruitAdapter(fruitList);  // 建立RecyclerView和数据之间的关联
        recyclerView.setAdapter(adapter);

    }
}
