package com.ducmp.bai2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.ducmp.bai2.model.Account;
import com.ducmp.bai2.model.Book;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "quan_ly_sach";

    //    table account
    public static final String ACCOUNT_TABLE_NAME = "account";
    private static final String ACCOUNT_ID = "account_id";
    private static final String ACCOUNT_NAME = "account_name";
    private static final String ACCOUNT_PHONE = "account_phone";
    private static final String ACCOUNT_ADDRESS = "account_address";
    private static final String ACCOUNT_AGE = "account_age";
    private static final String ACCOUNT_EMAIL = "account_email";
    private static final String ACCOUNT_USERNAME = "account_username";
    private static final String ACCOUNT_PASSWORD = "account_password";
    //table book
    public static final String BOOK_TABLE_NAME = "book";
    private static final String BOOK_ID = "book_id";
    private static final String BOOK_NAME = "book_name";
    private static final String BOOK_AUTHOR = "book_author";
    private static final String BOOK_TYPE = "book_type";
    private static final String BOOK_AMOUNT = "book_amount";

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("DB Manager", "DB Manager");

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table account
        String createTableAccount = "CREATE TABLE " + ACCOUNT_TABLE_NAME + " (" +
                ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ACCOUNT_NAME + " TEXT, " +
                ACCOUNT_PHONE + " TEXT, " +
                ACCOUNT_ADDRESS + " TEXT, " +
                ACCOUNT_AGE + " INTEGER, " +
                ACCOUNT_EMAIL + " TEXT, " +
                ACCOUNT_USERNAME + " TEXT, " +
                ACCOUNT_PASSWORD + " TEXT " +
                ")";
        db.execSQL(createTableAccount);

        //create table book
        String createTableBook = "CREATE TABLE " + BOOK_TABLE_NAME + " (" +
                BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BOOK_NAME + " TEXT, " +
                BOOK_AUTHOR + " TEXT, " +
                BOOK_TYPE + " TEXT, " +
                BOOK_AMOUNT + " INTEGER " +
                ")";
        db.execSQL(createTableBook);
        Toast.makeText(context, "Create Database successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BOOK_TABLE_NAME);
        onCreate(db);
        Toast.makeText(context, "Drop successfully", Toast.LENGTH_SHORT).show();
    }

    public void register(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACCOUNT_ID, account.getId());
        values.put(ACCOUNT_NAME, account.getName());
        values.put(ACCOUNT_EMAIL, account.getEmail());
        values.put(ACCOUNT_PHONE, account.getPhone());
        values.put(ACCOUNT_AGE, account.getAge());
        values.put(ACCOUNT_ADDRESS, account.getAddress());
        values.put(ACCOUNT_USERNAME, account.getUsername());
        values.put(ACCOUNT_PASSWORD, account.getPassword());
        db.insert(ACCOUNT_TABLE_NAME, null, values);
        db.close();
    }


    public Account checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ACCOUNT_TABLE_NAME, new String[]{ACCOUNT_ID,
                        ACCOUNT_NAME, ACCOUNT_PHONE, ACCOUNT_ADDRESS, ACCOUNT_AGE, ACCOUNT_EMAIL,
                        ACCOUNT_USERNAME, ACCOUNT_PASSWORD}, ACCOUNT_USERNAME + "=? AND " + ACCOUNT_PASSWORD + "=? ",
                new String[]{String.valueOf(username), String.valueOf(password)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Account account = new Account(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7)
        );
        cursor.close();
        db.close();
        return account;
    }

    public List<Book> getAllBook() {

        List<Book> listBook = new ArrayList<Book>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + BOOK_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setIdBook(cursor.getInt(0));
                book.setNameBook(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setBookType(cursor.getString(3));
                book.setAmount(cursor.getInt(4));
                listBook.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listBook;
    }

    public Book getBookById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(BOOK_TABLE_NAME, new String[]{BOOK_ID, BOOK_NAME, BOOK_AUTHOR,
                        BOOK_TYPE, BOOK_AMOUNT}, BOOK_ID + "=? ",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Book book = new Book();
        book.setIdBook(cursor.getInt(0));
        book.setNameBook(cursor.getString(1));
        book.setAuthor(cursor.getString(2));
        book.setBookType(cursor.getString(3));
        book.setAmount(cursor.getInt(4));
        cursor.close();
        db.close();
        return book;
    }

    public int updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BOOK_NAME, book.getNameBook());
        values.put(BOOK_AUTHOR, book.getAuthor());
        values.put(BOOK_TYPE, book.getBookType());
        values.put(BOOK_AMOUNT, book.getAmount());
        return db.update(BOOK_TABLE_NAME, values, BOOK_ID + "=?", new String[]{String.valueOf(book.getIdBook())});
    }

    public int deleteBook(int bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(BOOK_TABLE_NAME, BOOK_ID + "=?", new String[]{String.valueOf(bookId)});
    }

    public void createBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BOOK_NAME, book.getNameBook());
        values.put(BOOK_AUTHOR, book.getAuthor());
        values.put(BOOK_TYPE, book.getBookType());
        values.put(BOOK_AMOUNT, book.getAmount());
        db.insert(BOOK_TABLE_NAME, null, values);
        db.close();
    }

    public List<Book> searchByName(String key) {
        Cursor cursor = null;
        List<Book> listBook = new ArrayList<Book>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + BOOK_TABLE_NAME;
        String querySearch = "SELECT * FROM " + BOOK_TABLE_NAME + " WHERE " + BOOK_NAME + " like '%" + key + "%'";
        if (key == null || key.equals("")) {
            cursor = db.rawQuery(query, null);
        } else {
            cursor = db.rawQuery(querySearch, null);
        }
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setIdBook(cursor.getInt(0));
                book.setNameBook(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setBookType(cursor.getString(3));
                book.setAmount(cursor.getInt(4));
                listBook.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listBook;
    }
}
