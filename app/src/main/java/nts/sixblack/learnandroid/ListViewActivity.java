package nts.sixblack.learnandroid;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import nts.sixblack.learnandroid.layout_custom.CustomListAdapter;
import nts.sixblack.learnandroid.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private ListView listView;
    private ListView listViewCustom;
    private Button btnClick;
    private TextView txtShow;
    private List<Employee> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = (ListView) findViewById(R.id.listView);
        listViewCustom = (ListView) findViewById(R.id.listViewCustom);
        btnClick = (Button) findViewById(R.id.btnClick);
        txtShow = (TextView) findViewById(R.id.txtShow);

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

        ArrayAdapter<Employee> arrayAdapter = new ArrayAdapter<Employee>(ListViewActivity.this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(arrayAdapter);

        listViewCustom.setAdapter(new CustomListAdapter(ListViewActivity.this, list));
        listViewCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickListItem(parent, view, position, id);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1,1,1,"Update");
        menu.add(2,2,2,"Delete");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    private void createPopupMenu(){
        PopupMenu popupMenu = new PopupMenu(this, txtShow);
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getTitle().equals("About Me")){
                    Toast.makeText(ListViewActivity.this, "Nguyễn Thanh Sáu", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ListViewActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void clickListItem(AdapterView<?> parent, View view, int position, long id){
        Toast.makeText(ListViewActivity.this, "You click "+list.get(position).getName()+" "+list.get(position).getAge(),Toast.LENGTH_SHORT).show();
    }
}