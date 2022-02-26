package nts.sixblack.learnandroid;

import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import nts.sixblack.learnandroid.layout_custom.CustomRecyclerView;
import nts.sixblack.learnandroid.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Employee> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        list = new ArrayList<Employee>();
        list.add(new Employee("A", 10));
        list.add(new Employee("B", 11));
        list.add(new Employee("C", 12));
        list.add(new Employee("D", 13));
        list.add(new Employee("E", 14));
        list.add(new Employee("F", 15));
        list.add(new Employee("G", 16));
        list.add(new Employee("G", 17));
        list.add(new Employee("J", 18));
        list.add(new Employee("A", 10));
        list.add(new Employee("B", 11));
        list.add(new Employee("C", 12));
        list.add(new Employee("D", 13));
        list.add(new Employee("E", 14));
        list.add(new Employee("F", 15));
        list.add(new Employee("G", 16));
        list.add(new Employee("G", 17));
        list.add(new Employee("J", 18));

        CustomRecyclerView customRecyclerView = new CustomRecyclerView(RecyclerViewActivity.this, list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecyclerViewActivity.this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customRecyclerView);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }
}