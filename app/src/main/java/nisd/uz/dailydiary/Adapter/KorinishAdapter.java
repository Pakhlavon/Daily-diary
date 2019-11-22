package nisd.uz.dailydiary.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import nisd.uz.dailydiary.Model.Daily;
import nisd.uz.dailydiary.R;
import nisd.uz.dailydiary.db.DbHelper;

public class KorinishAdapter extends RecyclerView.Adapter<KorinishAdapter.ViewHolder> {

    private java.util.ArrayList<Daily> ArrayList;
    SQLiteDatabase db;
    private Context context;
    private LayoutInflater inflater;
    private List<Daily> dailyList;
    DbHelper dbHelper;

    public KorinishAdapter(Context context,List<Daily> dailyList)
    {
        this.context=context;
        this.dailyList=dailyList;
        this.dailyList=new ArrayList<>();
//        dbHelper=new DbHelper(context);
//        this.dailyList.addAll(dailyList);
        inflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public KorinishAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_korinish,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Daily daily =dailyList.get(position);

            holder.korinishtitle.setText(daily.getTitle());
            holder.desctitle.setText(daily.getDescription());
    }

    @Override
    public int getItemCount() {
        return dailyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView korinishtitle,desctitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            korinishtitle=(TextView)itemView.findViewById(R.id.korinishtitle);
            desctitle=(TextView)itemView.findViewById(R.id.desctitle);
        }
    }

}
