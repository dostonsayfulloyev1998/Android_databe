package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
 private FloatingActionButton fab;
    private TextView textView;
    ArrayList<Model> arrayList = new ArrayList<>();
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);

        fab =findViewById(R.id.fab);

        database = new Database(this);

        refreshData();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                LayoutInflater inflater = getLayoutInflater();
                builder.setTitle("add data");
                View view = inflater.inflate(R.layout.add_babe,null);
                EditText name = view.findViewById(R.id.name);
                EditText g_id = view.findViewById(R.id.g_id);
                builder.setView(view);

                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.addData(name.getText()+"",Integer.parseInt(g_id.getText()+""));

                        Toast.makeText(MainActivity.this,"bazaga qo`shilgan",Toast.LENGTH_SHORT).show();
                        refreshData();
                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setCancelable(true);
                    }
                });

                AlertDialog dialog  = builder.create();
                dialog.show();
            }
        });

    }

    void refreshData(){
        arrayList.add(new Model("a",1));
        arrayList = database.readData();
        String l = "";
        for(Model model : arrayList){
            l+=model.getName()+"  "+model.getG_id()+"\n\n";
        }
        textView.setText(l);
    }
}