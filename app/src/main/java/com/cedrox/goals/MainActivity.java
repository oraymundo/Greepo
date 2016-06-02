package com.cedrox.goals;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import com.cedrox.goals.adapters.GoalAdapter;
import com.cedrox.goals.adapters.OnSwipeTouchListener;
import com.cedrox.goals.dao.GoalDAO;
import com.cedrox.goals.entity.Goal;

public class MainActivity extends AppCompatActivity  {

    ListView listView;
    GoalAdapter goalAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView1);
        GoalDAO goalDAO = new GoalDAO();

        goalAdapter = new GoalAdapter(this, goalDAO.readAll(getApplicationContext()));


        listView.setAdapter(goalAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /** Called when the user clicks the Send button */
    public void save(View view) {
        Intent intent = getIntent();

        EditText description = (EditText) findViewById(R.id.goalDescription);

        GoalDAO goalDAO = new GoalDAO();
        goalDAO.insert(new Goal(goalAdapter.getGoalList().size(),description.getText().toString(), 0,"",""),view.getContext());

        description.setText("");

        View view1 = this.getCurrentFocus();
        if (view1 != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }

        ((GoalAdapter) listView.getAdapter()).clear();
        ((GoalAdapter) listView.getAdapter()).addAll(goalDAO.readAll(view.getContext()));
        ((GoalAdapter) listView.getAdapter()).notifyDataSetChanged();

        startActivity(intent);
    }


}
