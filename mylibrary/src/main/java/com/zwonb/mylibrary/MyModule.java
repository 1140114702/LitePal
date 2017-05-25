package com.zwonb.mylibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zwonb.utillibrary.Book;

import org.litepal.LitePal;
import org.litepal.LitePalDB;

/**
 * Created by zyb on 2017/5/23.
 */

public class MyModule extends AppCompatActivity {

    private LitePalDB litePalDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylibrary_main_layout);

        findViewById(R.id.add_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteDatabase("my_module");
            }
        });

        findViewById(R.id.add_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                litePalDB = new LitePalDB("my_module",2);
                litePalDB.addClassName(ModuleBean.class.getName());
                LitePal.use(litePalDB);
                ModuleBean bean = new ModuleBean();
                bean.setName("zwonb");
                bean.setNumber(1140);
                bean.setLive("live");
                bean.save();

//                LitePal.useDefault();//默认使用的是主模块的litepal.xml
//                Book book = new Book();
//                book.setName("module");
//                book.setAuthor("module");
//                book.setPages(454);
//                book.setPrice(16.96);
//                book.setPress("module");
//                book.save();
            }
        });

    }
}
