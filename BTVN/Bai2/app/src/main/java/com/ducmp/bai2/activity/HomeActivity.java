package com.ducmp.bai2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ducmp.bai2.R;
import com.ducmp.bai2.activity.adapter.ListBookAdapter;
import com.ducmp.bai2.database.DatabaseHelper;
import com.ducmp.bai2.model.Book;
import com.ducmp.bai2.untils.Untils;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ImageView imgLogout;
    private Button btnAddBook, btnSeach;
    private TextView toolbar;
    private ListView listView;
    private List<Book> list;
    private EditText edtSeach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    public void init() {
        imgLogout = findViewById(R.id.imgLogout);
        btnAddBook = findViewById(R.id.btnAddBook);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setText("Home");
        btnSeach = findViewById(R.id.btnSearch);
        edtSeach = findViewById(R.id.edtSearch);
        listView = findViewById(R.id.lv);
        list = getData();
        setAdapter(list);
        click();
    }

    public void click() {
        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.title_logout);
                String message = getResources().getString(R.string.message_logout);
                Untils.showDialogLogout(HomeActivity.this, title, message);
            }
        });

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddBook();
            }
        });

        btnSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list = searchData();
                setAdapter(list);
            }
        });
    }

    public void goToAddBook() {
        Intent intent = new Intent(HomeActivity.this, BookActivity.class);
        startActivityForResult(intent, 1);
    }


    public void goToDetail(int id) {
        Intent intent = new Intent(HomeActivity.this, BookDetailActivity.class);
        intent.putExtra("bookId", id);
        startActivityForResult(intent, 2);
    }

    public void setAdapter(final List<Book> list) {
        listView.setAdapter(new ListBookAdapter(list, this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int bookId = list.get(position).getIdBook();
                goToDetail(bookId);
            }
        });
    }

    public List<Book> getData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        List<Book> list = databaseHelper.getAllBook();
        return list;

    }

    public List<Book> searchData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        String searchKey = edtSeach.getText().toString().trim();
        return databaseHelper.searchByName(searchKey);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode == RESULT_OK) && (requestCode == 1)) {
            Intent refresh = new Intent(this, HomeActivity.class);
            startActivity(refresh);
            this.finish();
        }

        if ((resultCode == RESULT_OK) && (requestCode == 2)) {
            Intent refresh = new Intent(this, HomeActivity.class);
            startActivity(refresh);
            this.finish();
        }
    }

}
