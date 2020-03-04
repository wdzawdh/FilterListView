package com.cw.filterlistview.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cw.filterlistview.R;
import com.cw.filterlistview.widget.adapter.FilterBaseAdapter;

/**
 * @author Cw
 * @date 16/8/20
 */
public class CompareAdapter extends FilterBaseAdapter {

    @Override
    public Object getItem(int section, int position) {
        return null;
    }

    @Override
    public long getItemId(int section, int position) {
        return 0;
    }

    @Override
    public View getItemView(int section, int position, View convertView, ViewGroup parent) {
        View layout;
        if (convertView == null) {
            layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        } else {
            layout = convertView;
        }
        ((TextView) layout.findViewById(R.id.textItem)).setText(getGroupsList().get(section).getValue().get(position).getCompareField());
        return layout;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        View layout;
        if (convertView == null) {
            layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, null);
        } else {
            layout = convertView;
        }
        ((TextView) layout.findViewById(R.id.textItem)).setText(getGroupsList().get(section).getKey());
        return layout;
    }
}