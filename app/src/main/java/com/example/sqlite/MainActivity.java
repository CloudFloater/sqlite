package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseAdaper databaseAdaper;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseAdaper=new DatabaseAdaper(this);
        editText = findViewById(R.id.edit1);

    }


    public void addClick(View view){
        Medicine medicine =new Medicine("阿莫西林","JACK","","","2019-12-11");
        databaseAdaper.add(medicine);
    }

    public void deleteClick(View view){

        int ID = Integer.parseInt(editText.getText().toString());
        databaseAdaper.delete(ID);
        System.out.println(ID);

    }

    public void updateClick(View view){
        int ID = Integer.parseInt(editText.getText().toString());
        Medicine medicine =new Medicine(ID,"感冒灵","Black","","","2019-12-23");
        databaseAdaper.update(medicine);
    }

    public void findByIdClick(View view){
        int ID = Integer.parseInt(editText.getText().toString());
        Medicine medicine =databaseAdaper.findById(ID);
        System.out.println(medicine.inf());
    }

    public void findAllClick(View view){
        ArrayList<Medicine> medicines =databaseAdaper.findAll();
        int size= medicines.size();
        for (int i = 0; i <size ; i++) {
            System.out.println(medicines.get(i).inf());
        }
    }
    public void findByNameClick(View view){
        String family_name = editText.getText().toString();
        ArrayList<Medicine> medicines =databaseAdaper.findByName(family_name);
        int size= medicines.size();
        for (int i = 0; i <size ; i++) {
            System.out.println(medicines.get(i).inf());
        }
    }
}


