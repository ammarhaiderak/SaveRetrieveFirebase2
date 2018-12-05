package com.example.ammar.saveretrievefirebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAP extends ArrayAdapter<Item>  {

    public MyAP(@NonNull Context context, @NonNull ArrayList<Item>l) {
        super(context, 0,l);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    Item obj=this.getItem(position);

     if(convertView==null){

         LayoutInflater li=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

         convertView=li.inflate(R.layout.sample_layout,null);
     }

        TextView itemname=convertView.findViewById(R.id.itemname);
        TextView itemid=convertView.findViewById(R.id.itemid);
        TextView iteml=convertView.findViewById(R.id.iteml);
        TextView items=convertView.findViewById(R.id.items);
        TextView itemm=convertView.findViewById(R.id.itemm);
       // ImageView imageView=convertView.findViewById(R.id.img);
      //  imageView.setImageURI(Uri.parse(obj.imageurl));

       // Picasso.get().load(obj.imageurl).into(imageView);

        itemname.setText(obj.getItemname());
        itemid.setText(obj.getItemid());
        iteml.setText(obj.getSizel());
        items.setText(obj.getSizes());
        itemm.setText(obj.getSizem());







        return convertView;
    }
}
