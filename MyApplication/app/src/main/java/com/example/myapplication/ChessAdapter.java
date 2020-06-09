package com.example.myapplication;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ChessAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Chess> listChess;

    public ChessAdapter(Context context, int layout, List<Chess> listChess) {
        this.context = context;
        this.layout = layout;
        this.listChess = listChess;
    }

    @Override
    public int getCount() {
        return listChess.size();
    }

    @Override
    public Object getItem(int position) {
        return listChess.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layout, null);

        TextView tvChessName = (TextView) convertView.findViewById(R.id.tvChessName);

        Chess chess = (Chess) getItem(position);
        tvChessName.setText(chess.getName());

        return convertView;
    }
}
