package com.example.gridviewtransitionforimage.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gridviewtransitionforimage.Module.Item;
import com.example.gridviewtransitionforimage.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridAdapter extends ArrayAdapter<Item> {

    Context mContext;
    int layoutResourceId;
    List<Item> listItem;



    public GridAdapter(@NonNull Context context, int layoutResourceId,List<Item>list) {
        super(context, layoutResourceId);

        this.mContext=context;
        this.layoutResourceId=layoutResourceId;
        this.listItem=list;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View myView=convertView;

        if(myView==null){
            LayoutInflater inflater=((Activity)mContext).getLayoutInflater();
            myView=inflater.inflate(layoutResourceId,parent,false);
        }


        TextView titlePic=myView.findViewById(R.id.item_grid_title);
        ImageView imagePic=myView.findViewById(R.id.item_grid_image);

        titlePic.setText(listItem.get(position).getTitle());
        Picasso.with(mContext).load(listItem.get(position).getImage()).into(imagePic);





        return myView;
    }


    @Override
    public int getCount() {
        return listItem.size();
    }


    public void updateGridData(List<Item>list){
        this.listItem=list;
    }


}
