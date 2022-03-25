package nts.sixblack.learnandroid.layout_custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import nts.sixblack.learnandroid.R;
import nts.sixblack.learnandroid.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomRecyclerViewUser extends RecyclerView.Adapter<CustomRecyclerViewUser.UserHolder>{

    private LayoutInflater inflater;
    private Context context;
    private List<User> list = new ArrayList<User>(Collections.emptyList());

    public CustomRecyclerViewUser(Context context, List<User> list){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public CustomRecyclerViewUser.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_recycler_view, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerViewUser.UserHolder holder, int position) {
        User user = list.get(position);
        holder.txtName.setText(user.getName().toString());
        holder.txtAge.setText(user.getPhone());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout layout;
        public TextView txtName;
        public TextView txtAge;
        public UserHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layoutItemRecyclerView);
            txtName = itemView.findViewById(R.id.txtRecyclerViewName);
            txtAge = itemView.findViewById(R.id.txtRecyclerViewAge);
        }
    }
}
