package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        Dog dog=new Dog("white",2);
        databaseAdaper.add(dog);
    }

    public void deleteClick(View view){

        int ID = Integer.parseInt(editText.getText().toString());
        databaseAdaper.delete(ID);
        System.out.println(ID);

    }

    public void updateClick(View view){
        Dog dog=new Dog(1,"black",2);
        databaseAdaper.update(dog);
    }

    public void findByIdClick(View view){
        Dog dog=databaseAdaper.findById(1);
        System.out.println(dog.inf());
    }

    public void findAllClick(View view){
        ArrayList<Dog> dogs=databaseAdaper.findAll();
        int size=dogs.size();
        for (int i = 0; i <size ; i++) {
            System.out.println(dogs.get(i).inf());
        }
    }
}

