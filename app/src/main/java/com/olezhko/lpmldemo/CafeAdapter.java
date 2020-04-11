package com.olezhko.lpmldemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CafeAdapter extends ArrayAdapter<Cafe> {

    public CafeAdapter(@NonNull Context context, @NonNull List<Cafe> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cafe_list_item, parent, false);
        }

        Cafe currentCafe = getItem(position);

        ImageView picture = convertView.findViewById(R.id.cafe_image);
        picture.setImageResource(currentCafe.getPictureId());

        TextView name = convertView.findViewById(R.id.cafe_name);
        name.setText(currentCafe.getName());

        TextView address = convertView.findViewById(R.id.cafe_address);
        address.setText(currentCafe.getAddress());

        return convertView;
    }
}
