package com.example.stockmarket.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.stockmarket.R;
import com.example.stockmarket.entity.Product;

import java.util.List;

/**
 * Created by fangzhu on 2015/4/23.
 */
public class LeftAdapter extends ArrayAdapter<Product> {
    List<Product> objects;
    public LeftAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(getContext(), R.layout.item_left, null);
            viewHolder.tv_item = (TextView)convertView.findViewById(R.id.tv_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.tv_item.setText(objects.get(position).getName());
        return convertView;
    }

    @Override
    public int getCount() {
        return objects.size();

    }

    class ViewHolder {
        TextView tv_item;
    }
}
