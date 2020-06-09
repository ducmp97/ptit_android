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

import java.util.ArrayList;
import java.util.Arrays;

public class BookDetailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView toolbarTitle;
    private Toolbar toolbar;
    private EditText edtId, edtName, edtAuthor, edtAmount;
    private Spinner spBookType;
    private ImageView imgLogout;
    private Button btnSave, btnClear;
    private int bookId;
    private Book bookDetail;
    private String bookType = "";
    public String[] item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        init();
        item = getResources().getStringArray(R.array.bookType);
        spinnerAdapter();
        setDataToForm(bookDetail);

        onClick();
    }

    public void init() {
        toolbar = findViewById(R.id.tb);
        toolbarTitle = findViewById(R.id.toolbar);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtAmount = findViewById(R.id.edtAmount);
        edtAuthor = findViewById(R.id.edtAuthor);
        spBookType = findViewById(R.id.spBookType);
        imgLogout = findViewById(R.id.imgLogout);
        btnClear = findViewById(R.id.btnClear);
        btnSave = findViewById(R.id.btnSave);
        edtId.setEnabled(false);
        bookId = getBookId();
        bookDetail = getBookById(bookId);
        setToolbar();
    }

    public void setToolbar(){
        toolbarTitle.setText("Book Detail");
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

    public int getBookId() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("bookId");
        return id;
    }

    public Book getBookById(int bookId) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
        return databaseHelper.getBookById(bookId);
    }


    public void setDataToForm(Book bookDetail) {
        edtId.setText(bookDetail.getIdBook() + "");
        edtName.setText(bookDetail.getNameBook());
        edtAmount.setText(bookDetail.getAmount() + "");
        edtAuthor.setText(bookDetail.getAuthor());
        int index = new ArrayList<String>(Arrays.asList(item)).indexOf(bookDetail.getBookType());
        spBookType.setSelection(index);
    }

    public void reset() {
        edtName.getText().clear();
        edtAuthor.getText().clear();
        edtAmount.getText().clear();
        spBookType.setSelection(0);
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
                reset();
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
                    Book book = new Book(bookId, name, author, bookType, amount);
                    DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
                    databaseHelper.updateBook(book);
                    Toast.makeText(getBaseContext(), "Update book success", Toast.LENGTH_SHORT).show();
                    reset();
                    setResult(RESULT_OK, null);
                    finish();
                } else {
                    Toast.makeText(BookDetailActivity.this, "Data require", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.title_logout);
                String message = getResources().getString(R.string.message_logout);
                Untils.showDialogLogout(BookDetailActivity.this, title, message);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bookType = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
