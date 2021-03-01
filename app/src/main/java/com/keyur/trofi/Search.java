package com.keyur.trofi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class Search extends AppCompatActivity {
    ImageView add,delete;
    private LinearLayout parentLinearLayout;
    Button search;

    static int counter=0;

    AutoCompleteTextView autoCompleteTextView;

    String[] arr = { "Broccoli", "Corn","Cucumber","Pumpkin", "Tomato","Potato","Green pepper","Onion","Pumpkin","Radish","Sweet potato","Cabbage","Garlic"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        parentLinearLayout=(LinearLayout) findViewById(R.id.parent_linear_layout);

        add=findViewById(R.id.add_field_button);
        delete=findViewById(R.id.delete_button);
        search=findViewById(R.id.search_btn);

        autoCompleteTextView=findViewById(R.id.number_edit_text);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, arr);

        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=autoCompleteTextView.getText().toString().trim();
                if(s1.equals("Potato") || s1.equals("Tomato")){
                    startActivity(new Intent(getApplicationContext(),PoatatoTomato.class));
                }else {
                    Toast.makeText(Search.this, "Please enter appropriate ingredients", Toast.LENGTH_SHORT).show();
                }

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter<4) {
                    counter++;
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View rowView = inflater.inflate(R.layout.field, null);
                    overridePendingTransition(R.anim.slide_out,R.anim.slide_in);
                    // Add the new row before the add field button.
                    parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
                }
                else{
                    Toast.makeText(Search.this, "Maximum limit reached", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public void onDelete(View v) {
        counter--;
        parentLinearLayout.removeView((View) v.getParent());
    }

}