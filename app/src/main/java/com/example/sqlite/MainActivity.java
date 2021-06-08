package com.example.sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements MedicineAdapter.InnerItemOnclickListener, AdapterView.OnItemClickListener {
    private DatabaseAdaper databaseAdaper;
    private List<Medicine> medicineList = new ArrayList<>();
    private ListView listview;

    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=(ListView)findViewById(R.id.list_view);

        listview.setOnItemClickListener(this::onItemClick);


        databaseAdaper=new DatabaseAdaper(this);

        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setSearchWay(new SearchView.SearchWay<Medicine>(){

            @Override
            public List<Medicine> getData() {
                //返回数据源
                return medicineList;
            }

            @Override
            public boolean matchItem(Medicine item, String s) {
                //如果串item中包含串s，则匹配
                return item.getName().contains(s);
            }

            @Override
            public void update(List<Medicine> resultList) {
                //更新ListView的数据
                Log.e("成功","yes");
                setListViewData(resultList);

            }
        });


        initMedicine();
    }

    private void initMedicine() {
        ArrayList<Medicine> medicines =databaseAdaper.findAll();
        int size= medicines.size();
        for (int i = 0; i <size ; i++) {
            medicineList.add(medicines.get(i));
            System.out.println(medicines.get(i).inf());
        }
        setListViewData(medicineList);
    }
    private void setListViewData(List<Medicine> list){
        //设置ListView的适配器
         MedicineAdapter  adapter=new MedicineAdapter(MainActivity.this,R.id.list_view,list);
        adapter.setOnInnerItemOnClickListener(this);

        listview.setAdapter(adapter);
        Log.e("adapter","success");

    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart://监听菜单按钮 

                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog, null);
                TextView cancel =view.findViewById(R.id.choosepage_cancel);
                TextView sure =view.findViewById(R.id.choosepage_sure);
                final EditText edit_name =view.findViewById(R.id.choosepage_edittext);
                final EditText edit_familyName=view.findViewById(R.id.edit_familyName);
                final EditText edit_method =view.findViewById(R.id.edit_method);
                final EditText edit_introduction =view.findViewById(R.id.edit_introduction);
                final EditText edit_deadline =view.findViewById(R.id.edit_deadline);
                final Dialog dialog= builder.create();



                dialog.show();
                dialog.getWindow().setContentView(view);
                //使editext可以唤起软键盘
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();

                        Medicine medicine = new Medicine(
                                edit_name.getText().toString(),
                                edit_familyName.getText().toString(),
                                edit_introduction.getText().toString(),
                                edit_method.getText().toString(),
                                edit_deadline.getText().toString()
                        );
                        databaseAdaper.add(medicine);
                        System.out.println(medicine.inf());
                        initMedicine();
                        dialog.dismiss();
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void itemClick(View v) {
        int position;
        position = (Integer) v.getTag();
        switch (v.getId()) {
            case R.id.button_edit:
                Medicine medicine =medicineList.get(position);
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog, null);
                TextView cancel =view.findViewById(R.id.choosepage_cancel);
                TextView sure =view.findViewById(R.id.choosepage_sure);
                final EditText edit_name =view.findViewById(R.id.choosepage_edittext);
                final EditText edit_familyName=view.findViewById(R.id.edit_familyName);
                final EditText edit_method =view.findViewById(R.id.edit_method);
                final EditText edit_introduction =view.findViewById(R.id.edit_introduction);
                final EditText edit_deadline =view.findViewById(R.id.edit_deadline);
                final Dialog dialog= builder.create();

                edit_name.setText(medicine.getName());
                edit_familyName.setText(medicine.getFamily_name());
                edit_method.setText(medicine.getMethod());
                edit_introduction.setText(medicine.getIntroduction());
                edit_deadline.setText(medicine.getDeadline());

                dialog.show();
                dialog.getWindow().setContentView(view);
                //使editext可以唤起软键盘
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "修改完成", Toast.LENGTH_SHORT).show();
                        Medicine newMedicine = new Medicine(
                                medicine.getId(),
                                edit_name.getText().toString(),
                                edit_familyName.getText().toString(),
                                edit_introduction.getText().toString(),
                                edit_method.getText().toString(),
                                edit_deadline.getText().toString()
                        );
                        medicineList.set(position,newMedicine);
                        databaseAdaper.update(newMedicine);
                        setListViewData(medicineList);
                        dialog.dismiss();
                    }
                });

                break;
            case R.id.button_delete:
                Medicine medicine2 =medicineList.get(position);
                AlertDialog.Builder dialog2 =new AlertDialog.Builder(MainActivity.this);
                dialog2.setTitle("Waring");
                dialog2.setMessage("这将会删除本条数据");
                dialog2.setCancelable(false);
                dialog2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        medicineList.remove(medicine2);
                        databaseAdaper.delete(medicine2.getId());
                        setListViewData(medicineList);
                    }
                });
                dialog2.setNegativeButton("取消",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog2.show();

                Log.e("内部item--2-->", position + "");

                break;
            default:
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view2, int position, long id) {
        Medicine medicine =medicineList.get(position);
       /* AlertDialog.Builder dialog =new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle(medicine.getName());
        dialog.setMessage(medicine.INF());
        dialog.setCancelable(false);
        dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();*/
        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
         View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_inf, null);
        TextView cancel =view.findViewById(R.id.cancel);
        TextView sure =view.findViewById(R.id.sure);
        TextView edit_id  = view.findViewById(R.id.edit_id);
        TextView title = view.findViewById(R.id.title);
        final TextView edit_name =view.findViewById(R.id.edit_name);
        final TextView edit_familyName=view.findViewById(R.id.edit_familyName);
        final TextView edit_method =view.findViewById(R.id.edit_method);
        final TextView edit_introduction =view.findViewById(R.id.edit_introduction);
        final TextView edit_deadline =view.findViewById(R.id.edit_deadline);
        final Dialog dialog= builder.create();

        title.setText(medicine.getName());
        edit_id.setText(String.valueOf(medicine.getId()));
        edit_name.setText(medicine.getName());
        edit_familyName.setText(medicine.getFamily_name());
        edit_method.setText(medicine.getMethod());
        edit_introduction.setText(medicine.getIntroduction());
        edit_deadline.setText(medicine.getDeadline());

        dialog.show();
        dialog.getWindow().setContentView(view);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}


