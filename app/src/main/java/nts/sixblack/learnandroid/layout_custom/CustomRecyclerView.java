package nts.sixblack.learnandroid.layout_custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import nts.sixblack.learnandroid.R;
import nts.sixblack.learnandroid.model.Employee;

import java.util.List;

public class CustomRecyclerView extends RecyclerView.Adapter<CustomRecyclerView.EmployeeHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<Employee> list;

    public CustomRecyclerView(Context context, List<Employee> list){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_recycler_view, parent, false);
        return new EmployeeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {
        holder.txtName.setText(list.get(position).getName().toString());
        holder.txtAge.setText(String.valueOf(list.get(position).getAge()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class EmployeeHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtAge;
        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtRecyclerViewName);
            txtAge = itemView.findViewById(R.id.txtRecyclerViewAge);
        }
    }
}
