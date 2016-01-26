package com.cedrox.goals.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cedrox.goals.R;
import com.cedrox.goals.dao.GoalDAO;
import com.cedrox.goals.entity.Goal;
import com.cedrox.goals.sqlhelpers.GoalDbHelper;

import java.util.List;

/**
 * Created by edelarosaraymun on 1/7/16.
 */
public class GoalAdapter extends ArrayAdapter<Goal> {

    private List<Goal> goalList = null;
    private Context context;

    public GoalAdapter(Context context, List<Goal> goalList){
        super(context, R.layout.content_main , goalList);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.goalList = goalList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.descriptionTextview);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.statusCheckbox);
        name.setText(goalList.get(position).getDescription());
        cb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                GoalDAO goalDAO = new GoalDAO();
                goalDAO.remove(goalList.get(position).getDescription(),getContext());
                clear();
                addAll(goalDAO.readAll(getContext()));
                notifyDataSetChanged();
            }
        });

        return convertView;
    }


    public List<Goal> getGoalList() {
        return goalList;
    }

    public void setGoalList(List<Goal> goalList) {
        this.goalList = goalList;
    }




}
