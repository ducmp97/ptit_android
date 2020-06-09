package com.ducmp.bai2.activity.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ducmp.bai2.R;
import com.ducmp.bai2.database.DatabaseHelper;
import com.ducmp.bai2.model.Book;

import java.util.List;

public class ListBookAdapter extends BaseAdapter {

    private List<Book> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListBookAdapter(List<Book> listData, Context context) {
        this.listData = listData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_item, null);
            holder = new ViewHolder();
            holder.txtName = convertView.findViewById(R.id.txtName);
            holder.imgDelete = convertView.findViewById(R.id.imgDelete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        final Book book = this.listData.get(position);
        holder.txtName.setText(book.getNameBook());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Confirm Delete");
                builder1.setMessage("Do you want to delete "+book.getNameBook()+ " ?");
                builder1.setCancelable(true);

                builder1.setNegativeButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DatabaseHelper db = new DatabaseHelper(context);
                                int idDelete = db.deleteBook(book.getIdBook());
                                listData.remove(book);
                                notifyDataSetChanged();
                            }
                        });
                builder1.setPositiveButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        return convertView;
    }

    public static class ViewHolder {
        TextView txtName;
        ImageView imgDelete;
    }
}
