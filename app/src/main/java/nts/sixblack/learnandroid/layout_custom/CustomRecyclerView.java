package nts.sixblack.learnandroid.layout_custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
        Employee employee = list.get(position);
        holder.txtName.setText(employee.getName().toString());
        holder.txtAge.setText(String.valueOf(employee.getAge()));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(employee);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class EmployeeHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout layout;
        public TextView txtName;
        public TextView txtAge;
        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layoutItemRecyclerView);
            txtName = itemView.findViewById(R.id.txtRecyclerViewName);
            txtAge = itemView.findViewById(R.id.txtRecyclerViewAge);
        }
    }

    public void click(Employee employee){
        Toast.makeText(context, employee.getName()+" "+employee.getAge(),Toast.LENGTH_SHORT).show();
    }
}
