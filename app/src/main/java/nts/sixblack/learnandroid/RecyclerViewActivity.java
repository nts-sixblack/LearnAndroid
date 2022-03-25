package nts.sixblack.learnandroid;

import android.content.Intent;
import android.view.MotionEvent;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import nts.sixblack.learnandroid.layout_custom.CustomRecyclerView;
import nts.sixblack.learnandroid.layout_custom.CustomRecyclerViewUser;
import nts.sixblack.learnandroid.model.Employee;
import nts.sixblack.learnandroid.model.ResponObject;
import nts.sixblack.learnandroid.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Employee> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        Bundle bundle = getIntent().getExtras();
        ResponObject responObject = (ResponObject) bundle.get("responObject");
        List<User> listUser = new ArrayList<User>();
        listUser = (ArrayList) responObject.getData();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

//        list = new ArrayList<Employee>();
//        list.add(new Employee("A", 10));
//        list.add(new Employee("B", 11));
//        list.add(new Employee("C", 12));
//        list.add(new Employee("D", 13));
//        list.add(new Employee("E", 14));
//        list.add(new Employee("F", 15));
//        list.add(new Employee("G", 16));
//        list.add(new Employee("G", 17));
//        list.add(new Employee("J", 18));
//        list.add(new Employee("A", 10));
//        list.add(new Employee("B", 11));
//        list.add(new Employee("C", 12));
//        list.add(new Employee("D", 13));
//        list.add(new Employee("E", 14));
//        list.add(new Employee("F", 15));
//        list.add(new Employee("G", 16));
//        list.add(new Employee("G", 17));
//        list.add(new Employee("J", 18));
//
//        CustomRecyclerView customRecyclerView = new CustomRecyclerView(RecyclerViewActivity.this, list);

        CustomRecyclerViewUser customRecyclerView = new CustomRecyclerViewUser(RecyclerViewActivity.this, listUser);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecyclerViewActivity.this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customRecyclerView);

    }
}