package com.example.zhou.litepaltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connector.getDatabase();
            }
        });
        //添加数据
        findViewById(R.id.add_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
            }
        });
        //更新数据
        findViewById(R.id.update_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?", "The Da Vinci Code", "Dan Brown");
            }
        });
        //删除数据
        findViewById(R.id.delete_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataSupport.deleteAll(Book.class, "price < ?", "15");
            }
        });
        //查询数据
        findViewById(R.id.query_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> list = DataSupport.findAll(Book.class);
                for (Book book : list) {
                    Log.e("binbin", "book name is "+book.getName());
                    Log.e("binbin", "book author is "+book.getAuthor());
                    Log.e("binbin", "book name is "+book.getPages());
                    Log.e("binbin", "book price is "+book.getPrice());
                    Log.e("binbin", "book press is "+book.getPress());
                    Log.e("binbin", "book id is "+book.getId());
                    Log.e("binbin", "=======================\n");
                }

            }
        });
        //删除所有数据
        findViewById(R.id.delete_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataSupport.deleteAll(Book.class);
            }
        });
    }
}
