package nisd.uz.dailydiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import nisd.uz.dailydiary.Adapter.Adapter;
import nisd.uz.dailydiary.Model.Daily;
import nisd.uz.dailydiary.db.DbHelper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    BottomAppBar bottomAppBar;
    DbHelper dbHelper;
    public RecyclerView recyclerView;
    LayoutInflater layoutInflater;
    Adapter adapter;
    List<Daily> dailyList =new ArrayList<>();
    ArrayList<Daily> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.fab);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        setSupportActionBar(bottomAppBar);
        dbHelper=new DbHelper(this);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);


        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager1);
        dailyList=dbHelper.barchaishlar();
        adapter=new Adapter(MainActivity.this,dailyList);
        recyclerView.setAdapter(adapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(MainActivity.this,QoshishActivity.class);
                startActivity(intent);
//                Snackbar.make(view, "Fab is clicked", Snackbar.LENGTH_LONG)
//                        .setAction("UNDO", null)
//                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.setting:
                Intent intent=new Intent(this, QoshishActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

