package com.example.zhou.litepaltest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zwonb.mylibrary.MyModule;
import com.zwonb.utillibrary.Test;

import org.litepal.LitePal;
import org.litepal.LitePalDB;
import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.FindMultiCallback;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
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

                //-------------------------------------------------------//
                //动态添加数据库
//                LitePalDB litePalDB = new LitePalDB("dongtai",1);
//                litePalDB.addClassName(Book.class.getName());
//                LitePal.use(litePalDB);
                //使用默认的数据库配置(litepal.xml)
//                LitePalDB litePalDB1 = LitePalDB.fromDefault("dongtai2");
//                LitePal.use(litePalDB1);
                //切换回到默认的数据库(litepal.xml)
//                LitePal.useDefault();
                //删除数据库
//                LitePal.deleteDatabase("dongtai");
                //-------------------------------------------------------//

                //异步查询
//                DataSupport.findAllAsync(Book.class).listen(new FindMultiCallback() {
//                    @Override
//                    public <T> void onFinish(List<T> t) {
//                        List<Book> t1 = (List<Book>) t;
//                    }
//                });

                //不存在name=zwonb就创建，存在就更新
//                Book book = new Book();
//                book.setName("zwonb");
//                book.setPrice(13.4);
//                //...
//                book.saveOrUpdate("name = ?", book.getName());
            }
        });
        //添加数据
        findViewById(R.id.add_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.useDefault();
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();

//                List<Book> books = new ArrayList<>();
//                DataSupport.saveAll(books);
            }
        });
        //更新数据
        findViewById(R.id.update_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                //更新name=The Da Vinci Code和author=Dan Brown的这条数据
                book.updateAll("name = ? and author = ?", "The Da Vinci Code", "Dan Brown");
            }
        });
        //删除数据
        findViewById(R.id.delete_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //删除price<15的所以数据条目
                DataSupport.deleteAll(Book.class, "price < ?", "15");
            }
        });
        //查询数据
        findViewById(R.id.query_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //查询所有数据
                List<Book> list = DataSupport.findAll(Book.class);
                for (Book book : list) {
                    Log.e("binbin", "book name is " + book.getName());
                    Log.e("binbin", "book author is " + book.getAuthor());
                    Log.e("binbin", "book name is " + book.getPages());
                    Log.e("binbin", "book price is " + book.getPrice());
                    Log.e("binbin", "book press is " + book.getPress());
                    Log.e("binbin", "book id is " + book.getId());
                    Log.e("binbin", "=======================\n");
                }

                //查询id=1的数据
//                Book book = DataSupport.find(Book.class, 1);

                //查询多条id的数据
//                DataSupport.findAll(Book.class,1,2,3);

                //查询第一条数据
//                Book first = DataSupport.findFirst(Book.class);

                //查询最后一条数据
//                DataSupport.findLast(Book.class);

                //查询name=The Da Vinci Code的所有数据
//                    List<Book> books = DataSupport.where("name = ?", "The Da Vinci Code").find(Book.class);

                //只查询name和price 且price>14的数据
//                    List<Book> books = DataSupport.select("name", "price").where("price > ?", "14").find(Book.class);
//                    books.get(0).getName();
//                    books.get(0).getPrice();

                //只查询name和price 且price>14的数据 结果按照price降序排序（asc是升序）
//                    List<Book> books = DataSupport.select("name", "price").where("price > ?", "14").order("price desc").find(Book.class);

                //只查询name和price 且price>14的数据 结果按照price降序排序（asc是升序）
//                List<Book> books = DataSupport.select("name", "price").where("price > ?", "14").order("price desc").find(Book.class);
//                for (Book book : books) {
//                    Log.e("binbin", "onClick: " + book.getName());
//                    Log.e("binbin", "onClick: " + book.getPrice());
//                    Log.e("binbin", "onClick: " + book.getAuthor());
//                }
                //只查询name和price 且price>14的数据 结果按照price降序排序（asc是升序）
                // limit(10)指定查询前10条
//                List<Book> books = DataSupport.select("name", "price").where("price > ?", "14").order("price desc").limit(10).find(Book.class);

                //只查询name和price 且price>14的数据 结果按照price降序排序（asc是升序）
                // limit(10)指定查询10条 offset(10)表示偏移10条数据，即从第11条开始查到第20条
//                List<Book> books = DataSupport.select("name", "price").where("price > ?", "14").order("price desc").limit(10).offset(10).find(Book.class);

                //激进查询 isEager==true --不推荐 效率低 只能查一个层次的关联表
                /**
                 * 推荐使用懒查询
                 *
                 public class News extends DataSupport{

                 ...

                 public List<Comment> getComments() {
                 return DataSupport.where("news_id = ?", String.valueOf(id)).find(Comment.class);
                 }

                 }
                 */

                //原生查询
//                Cursor bySQL = DataSupport.findBySQL("select * from book where price > ?", "14");
//                List<Book> list1 = new ArrayList<>();
//                if (bySQL != null && bySQL.moveToFirst()) {
//                    do {
//                        int id = bySQL.getInt(bySQL.getColumnIndex("id"));
//                        double price = bySQL.getDouble(bySQL.getColumnIndex("price"));
//                        String name = bySQL.getString(bySQL.getColumnIndex("name"));
//                        // ···
//                        Book book = new Book();
//                        book.setPrice(price);
//                        book.setName(name);
//                        // ···
//                        list1.add(book);
//                    } while (bySQL.moveToNext());
//                    bySQL.close();
//                }

                //聚合函数查询

                //查询book表中一共有多少条数据
//                int count = DataSupport.count(Book.class);
                //查询book表中price>15一共有多少条数据
//                int count = DataSupport.where("price>?", "15").count(Book.class);
                //...
                //查询book表中name中数据的总数量
//                int price = DataSupport.sum(Book.class, "name", int.class);
                //book表中name平均有多少条数据(一般用于集合)
//                double name = DataSupport.average(Book.class, "name");
                //book表中name最多的数据(一般用于集合)
//                int max = DataSupport.max(Book.class, "name", int.class);
                //最少的数据(一般用于集合)
//                int min = DataSupport.min(Book.class, "name", int.class);

                //------------------------------------------------------------------//
                //查询price>14的第一条数据
//                Book first = DataSupport.where("price > 14").findFirst(Book.class);
                //查询name=zwonb是否存在
                boolean isExist = DataSupport.isExist(Book.class, "name=?", "zwonb");
            }
        });
        //删除所有数据
        findViewById(R.id.delete_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //删除这个表里面所有的数据
                DataSupport.deleteAll(Book.class);
                Intent intent = new Intent(MainActivity.this, MyModule.class);
                startActivity(intent);
            }
        });
    }

    public void deleteDB(View view) {
        LitePal.deleteDatabase("BookStore");
    }
}
