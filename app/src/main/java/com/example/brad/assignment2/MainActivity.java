package com.example.brad.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/*
    Collaborated with Shayne Longpre on this assignment.
    SavedState code comes from posts in StackOverflow.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get listview
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        //create list
        list = new ArrayList<String>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        if (savedInstanceState != null) { //there is a saved state
            String[] savedList = savedInstanceState.getStringArray("list");
            if (savedList != null) { //there are saved list items
                int savedSize = savedList.length;
                for (int i = 0; i < savedSize; i++) list.add(savedList[i]);
            }
        }
    }

    //removes something from the list
    public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
        list.remove(index);
        adapter.notifyDataSetChanged();
    }

    //for rotating
    public void onSaveInstanceState (Bundle savedState) {
        super.onSaveInstanceState(savedState);
        int adapterSize = adapter.getCount();
        String[] savedList = new String[adapterSize];
        for (int i = 0; i < adapterSize; i++) savedList[i] = adapter.getItem(i);
        savedState.putStringArray("list", savedList);
    }

    //adds item to list
    public void add_item (View view) {
        EditText et = (EditText) findViewById(R.id.add_text);
        String item = et.getText().toString();
        if (item.isEmpty()) { //empty string
            Toast.makeText(this, "Don't be dumb, enter actual text.", Toast.LENGTH_SHORT).show();
            return;
        } //else add to list
        list.add(item);
        adapter.notifyDataSetChanged();
    }
}
