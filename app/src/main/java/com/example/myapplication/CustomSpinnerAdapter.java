package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {
    public CustomSpinnerAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.spinner_item_with_flag, parent, false);

        TextView textView = view.findViewById(R.id.text1);
        ImageView imageView = view.findViewById(R.id.imageFlag);

        String country = getItem(position);
        textView.setText(country);

        if (country != null) {
            imageView.setImageResource(getFlagDrawable(country));
        }

        return view;
    }

    private int getFlagDrawable(String country) {
        switch (country) {
            case "Djibouti":
                return R.drawable.flag_djibouti;
            case "Bahrain":
                return R.drawable.flag_bahrain;
            case "Saint Vincent":
                return R.drawable.flag_saint_vincent;
            case "Georgia":
                return R.drawable.flag_georgia;
            case "Malta":
                return R.drawable.flag_malta;
            default:
                return R.drawable.background;
        }
    }
}
