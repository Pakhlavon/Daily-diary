package nisd.uz.dailydiary;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import nisd.uz.dailydiary.db.DbHelper;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class QoshishActivity extends AppCompatActivity {

    private EditText etsarlavha;
    private TextInputEditText txtinputtxt;
    private Button btnqoshish;
    DbHelper dbHelper;
    SQLiteDatabase db;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qoshish);
        dbHelper=new DbHelper(this);
        etsarlavha=(EditText)findViewById(R.id.etsarlavha);
        txtinputtxt=(TextInputEditText)findViewById(R.id.txtinputtxt);
        btnqoshish=(Button)findViewById(R.id.btnqoshish);



        btnqoshish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                String formattedDate = simpleDateFormat.format(c);

                String title= String.valueOf(etsarlavha.getText());
                String description= String.valueOf(txtinputtxt.getText());

                if (dbHelper.insertData(formattedDate,description,title))
                {
                    Toast.makeText(QoshishActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(QoshishActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(QoshishActivity.this, "Not Successfull", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}
