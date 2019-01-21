package com.example.administrator.litepalsave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button btn_create, btn_add, btn_delete, btn_update, btn_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化UI控件
    }

    private void initView() {
        btn_create = (Button) findViewById(R.id.btn_create);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_query = (Button) findViewById(R.id.btn_query);

        btn_create.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_query.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                createDatabase();//创建数据库
                Toast.makeText(this, "Create Database successfully", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_add:
                addData();//添加数据
                Toast.makeText(this, "Add Data successfully", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:
                deleteData();//删除数据
                Toast.makeText(this, "Delete Data successfully", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_update:
                updateData();//更新数据
                Toast.makeText(this, "Update Data successfully", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_query:
                queryData();//查询数据
                Toast.makeText(this, "Query Data successfully", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void createDatabase() {
        LitePal.getDatabase();//通过assets中的litepal.xml文件中的信息，创建BookStore数据库，并根据对象映射列表中的信息创建Book表
    }

    private void addData() {
        Book book = new Book();//创建Book实例
        book.setName("The Da Vinci Code");//设置书名
        book.setAuthor("Dan Brown");//设置作者
        book.setPages(454);//设置页数
        book.setPrice(16.96);//设置价格
        book.setPress("Unknow");//设置出版社
        book.save();//保存数据
    }

    private void updateData() {
        Book book = new Book();//创建Book实例
        book.setPrice(14.95);//设置价格
        book.setPress("Anchor");//设置出版社
        book.updateAll("name=? and author=?", "The Da Vinci Code", "Dan Brown");
    }

    private void deleteData() {
        DataSupport.deleteAll(Book.class, "pages < ?", "500");
    }

    private void queryData() {
        List<Book> books = DataSupport.findAll(Book.class);//获取Book表中数据，并且放入一个List集合
        //增强for遍历集合数据，并且打印
        for (Book book : books) {
            Log.e(TAG, "book name is " + book.getName());
            Log.e(TAG, "book author is " + book.getAuthor());
            Log.e(TAG, "book pages is " + book.getPages());
            Log.e(TAG, "book price is" + book.getPrice());
            Log.e(TAG, "book press is " + book.getPress());
        }
    }

}
