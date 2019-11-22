package nisd.uz.dailydiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import nisd.uz.dailydiary.Adapter.KorinishAdapter;
import nisd.uz.dailydiary.Model.Daily;
import nisd.uz.dailydiary.db.DbHelper;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class UmumiyOlish extends AppCompatActivity {

    private Context context;
    private List<Daily> dailyList =new ArrayList<>();
    public RecyclerView recyclerViewkorinish;
    public KorinishAdapter korinishAdapter;
    LinearLayoutManager layoutInflater;
    DbHelper dbHelper;
    private int mtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umumiy_olish);
        dbHelper=new DbHelper(this);

        mtitle=getIntent().getIntExtra("id",0);
        recyclerViewkorinish=(RecyclerView)findViewById(R.id.recyclerViewkorinish);

        recyclerViewkorinish.setHasFixedSize(true);
        layoutInflater= new LinearLayoutManager(getApplicationContext());
        recyclerViewkorinish.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
//        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(UmumiyOlish.this,LinearLayoutManager.VERTICAL,false);
//        recyclerViewkorinish.setLayoutManager(linearLayoutManager1);
        dailyList=dbHelper.korinish(mtitle);
        korinishAdapter=new KorinishAdapter(UmumiyOlish.this,dailyList);
        recyclerViewkorinish.setAdapter(korinishAdapter);


    }
}
