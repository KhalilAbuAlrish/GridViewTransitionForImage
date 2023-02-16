package com.example.gridviewtransitionforimage.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.gridviewtransitionforimage.Adapter.GridAdapter;
import com.example.gridviewtransitionforimage.Module.Item;
import com.example.gridviewtransitionforimage.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    GridAdapter gridAdapter;
    List<Item> list;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView=findViewById(R.id.grid_view);
        list=new ArrayList<>();
       gridAdapter=new GridAdapter(MainActivity.this,R.layout.grid_item,list);
       gridView.setAdapter(gridAdapter);

        createTempData();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Item item=list.get(position);
                ImageView imageView=view.findViewById(R.id.item_grid_image);

                Intent intent=new Intent(MainActivity.this,DetailsActivity.class);

                int[]screenLocation=new int[2];
                imageView.getLocationOnScreen(screenLocation);

                intent.putExtra("left",screenLocation[0]);
                intent.putExtra("top",screenLocation[1]);
                intent.putExtra("height",imageView.getHeight());
                intent.putExtra("width",imageView.getWidth());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("image",item.getImage());

                startActivity(intent);






            }
        });


    }






    public void createTempData(){
     //   list=new ArrayList<>();

        list.add(new Item("image 1","https://media.product.which.co.uk/prod/images/1500_750/gm-c75e9472-c546-41f9-868d-b9eddd09758e-android-main.jpeg"));
        list.add(new Item("image 2","https://media.wired.co.uk/photos/606d9b367aff197af7c72a2f/4:3/w_2668,h_2001,c_limit/wired-uk-android-tips-1.jpg"));
        list.add(new Item("image 3","https://storage.googleapis.com/gweb-uniblog-publish-prod/original_images/HeroHomepage_2880x1200.jpg"));
        list.add(new Item("image 4","https://img.uswitch.com/qhi9fkhtpbo3/hSSkIfF0OsQQGuiCCm0EQ/6c1a9b54de813e0a71a85edb400d58d8/rsz_1android.jpg"));
        list.add(new Item("image 5","https://www.showmetech.com.br/wp-content/uploads//2020/10/hist_android.jpg"));
        list.add(new Item("image 6","https://media.kasperskydaily.com/wp-content/uploads/sites/92/2019/12/09084248/android-device-identifiers-featured.jpg"));
        list.add(new Item("image 7","https://www.techrepublic.com/wp-content/uploads/2022/05/android-13-beta-2-news.jpeg"));
        list.add(new Item("image 8","https://technochoose.com/wp-content/uploads/2022/10/Android_thumb800.jpg"));
        list.add(new Item("image 9","https://www.fita.in/wp-content/uploads/2019/10/android.jpg"));
        list.add(new Item("image 10","https://cdn.mos.cms.futurecdn.net/De6HMXQy8UW49U2VR97BUN.png"));


       gridAdapter.updateGridData(list);
    }





}