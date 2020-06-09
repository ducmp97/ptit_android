package com.ducmp.bai2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ducmp.bai2.R;
import com.ducmp.bai2.database.DatabaseHelper;
import com.ducmp.bai2.model.Book;
import com.ducmp.bai2.untils.Untils;

public class BookActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Toolbar toolbar;
    private Spinner spBookType;
    private TextView txtToolbar;
    private EditText edtName, edtAuthor, edtAmount;
    private Button btnClear, btnSave;
    private ImageView imgLogout;
    private String bookType = "";
    public String[] item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        init();
        spinnerAdapter();
        onClick();
    }

    public void init() {
        toolbar = findViewById(R.id.tb);
        spBookType = findViewById(R.id.spBookType);
        txtToolbar = findViewById(R.id.toolbar);
        edtName = findViewById(R.id.edtName);
        edtAuthor = findViewById(R.id.edtAuthor);
        edtAmount = findViewById(R.id.edtAmount);
        btnClear = findViewById(R.id.btnClear);
        btnSave = findViewById(R.id.btnSave);
        imgLogout = findViewById(R.id.imgLogout);
        item = getResources().getStringArray(R.array.bookType);
        setToolbar();

    }

    public void setToolbar(){
        txtToolbar.setText("Add Book");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.textWhite), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void spinnerAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_spinner_item, item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBookType.setAdapter(adapter);
        spBookType.setOnItemSelectedListener(this);
    }

    public void onClick() {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resset();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String author = edtAuthor.getText().toString().trim();
                String amountStr = edtAmount.getText().toString().trim();
                if (!name.equals("") && !author.equals("") && !amountStr.equals("")) {
                    int amount = Integer.parseInt(amountStr);
                    Book book = new Book(name, author, bookType, amount);
                    DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
                    databaseHelper.createBook(book);
                    Toast.makeText(getBaseContext(), "Add book success", Toast.LENGTH_SHORT).show();
                    resset();
                    setResult(RESULT_OK, null);
                    finish();
                } else {
                    Toast.makeText(BookActivity.this, "Data require", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.title_logout);
                String message = getResources().getString(R.string.message_logout);
                Untils.showDialogLogout(BookActivity.this, title, message);
            }
        });
    }


    public void resset() {
        edtName.getText().clear();
        edtAmount.getText().clear();
        edtAuthor.getText().clear();
        spBookType.setSelection(0);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bookType = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
