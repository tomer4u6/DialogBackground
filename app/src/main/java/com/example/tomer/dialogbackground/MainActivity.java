//Version 1.0
package com.example.tomer.dialogbackground;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    final String[] colors={"Red","Green","Blue"};
    Button btn_multi,btn_single,btn_rst;
    LinearLayout LL;
    AlertDialog.Builder adb;
    AlertDialog ad;
    int[] colorInt = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_multi = (Button)findViewById(R.id.btn_multi);
        btn_single = (Button)findViewById(R.id.btn_single);
        btn_rst = (Button)findViewById(R.id.btn_rst);
        LL = (LinearLayout)findViewById(R.id.LL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String st = "";
        st = item.getTitle().toString();
        if(st.equals("Credits")){
            Intent t = new Intent(this, SecondActivity.class);
            startActivity(t);
        }
        return super.onOptionsItemSelected(item);
    }
    public void multi_click(View view) {
        colorInt[0]=0;
        colorInt[1]=0;
        colorInt[2]=0;
        adb = new AlertDialog.Builder(this);
        adb.setTitle("Combine background color");
        adb.setMultiChoiceItems(colors,null, new DialogInterface.OnMultiChoiceClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            colorInt[i] = 255;
                        }
                        else{
                            colorInt[i] = 0;
                        }
                    }
                });
        adb.setCancelable(false);
        adb.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LL.setBackgroundColor(Color.argb(255,colorInt[0],colorInt[1],colorInt[2]));
                ad.dismiss();
            }
        });
        adb.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ad.cancel();
            }
        });
        ad = adb.create();
        ad.show();

    }

    public void reset(View view) {
        LL.setBackgroundColor(Color.WHITE);
    }

    public void single_click(View view) {
        adb = new AlertDialog.Builder(this);
        adb.setTitle("Change background");
        adb.setCancelable(false);
        adb.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0: LL.setBackgroundColor(Color.RED);
                    break;
                    case 1: LL.setBackgroundColor(Color.GREEN);
                    break;
                    case 2: LL.setBackgroundColor(Color.BLUE);
                    break;
                }
                ad.dismiss();
            }
        });
        adb.setNeutralButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ad.cancel();
            }
        });
        ad = adb.create();
        ad.show();
        }
    }
