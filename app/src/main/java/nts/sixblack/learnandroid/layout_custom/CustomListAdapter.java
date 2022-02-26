package nts.sixblack.learnandroid.layout_custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import nts.sixblack.learnandroid.R;
import nts.sixblack.learnandroid.model.Employee;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private List<Employee> list;
    private Context context;
    private LayoutInflater layoutInflater;
    public CustomListAdapter(Context context, List<Employee> list){
        this.context = context;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
//    trả về dữ liệu của 1 item trong list
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        convertView = layoutInflater.inflate(R.layout.layout_item_list_custom, null);
        viewHolder.txtName = convertView.findViewById(R.id.nameItemListCustom);
        viewHolder.txtAge = convertView.findViewById(R.id.ageItemListCustom);
        Employee employee = list.get(position);
        viewHolder.txtName.setText(employee.getName());
        viewHolder.txtAge.setText(String.valueOf(employee.getAge()));
        convertView.setTag(viewHolder);
        return convertView;
    }
//    tượng trưng cho các thành phần cho trong item
    static class ViewHolder{
        TextView txtName;
        TextView txtAge;
    }
}
