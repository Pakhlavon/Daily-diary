package nisd.uz.dailydiary.Adapter;

import android.content.Context;
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

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {
    private Context context;
    LayoutInflater inflater;
    private ArrayList<Daily> ArrayList;
    DbHelper dbHelper;
    private List<Daily> dailyList;
    public Adapter(Context context, List<Daily> dailyList)
    {
        this.dailyList=dailyList;
        this.context=context;
        dbHelper=new DbHelper(context);
        this.ArrayList=new ArrayList<Daily>();
        inflater=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
                Daily daily= dailyList.get(position);

                holder.sana.setText(daily.getSana());
               holder.title.setText(daily.getTitle());
    }

    @Override
    public int getItemCount() {
        return dailyList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        public TextView id,title,sana;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
//            id=(TextView)itemView.findViewById(R.id.id);
            title=(TextView)itemView.findViewById(R.id.title);
            sana=(TextView)itemView.findViewById(R.id.sana);

        }
    }
}
