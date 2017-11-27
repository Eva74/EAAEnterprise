package com.example.armle.ninjapath.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.armle.ninjapath.R;
import com.example.armle.ninjapath.model.Courses;

import java.util.ArrayList;

/**
 * Created by armle on 11/24/2017.
 */

public class SemesterCourseRecyclerAdapter extends RecyclerView.Adapter<SemesterCourseRecyclerAdapter.CoursesViewHolder> {

    //This class talks to the database and gets the information from it
    private ArrayList<Courses> courseList;
    public ImageView overflow;
    private Context mContext;
    private ArrayList<Courses> mFilteredList;

    public SemesterCourseRecyclerAdapter(ArrayList<Courses> courseList, Context mContext){
        this.courseList = courseList;
        this.mContext = mContext;
        this.mFilteredList = courseList;
    }


    public class CoursesViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewCRN;
        public AppCompatTextView textViewCourseName;
        public AppCompatTextView textViewProfessor;
        public AppCompatTextView textViewSeats;
        public AppCompatTextView textViewLocation;
        public AppCompatTextView textViewStarTime;
        public AppCompatTextView textViewEndTime;
        public AppCompatTextView textViewDays;

        public CoursesViewHolder(View view){
            super(view);
            textViewCRN = (AppCompatTextView) view.findViewById(R.id.textViewCRN);
            textViewCourseName = (AppCompatTextView) view.findViewById(R.id.textViewCourseName);
            textViewProfessor = (AppCompatTextView) view.findViewById(R.id.textViewProfessor);
            textViewSeats = (AppCompatTextView) view.findViewById(R.id.textViewSeats);
            textViewLocation = (AppCompatTextView) view.findViewById(R.id.textViewLocation);
            textViewStarTime = (AppCompatTextView) view.findViewById(R.id.textViewStartTime);
            textViewEndTime = (AppCompatTextView) view.findViewById(R.id.textViewEndTime);
            textViewDays = (AppCompatTextView) view.findViewById(R.id.textViewDays);
        }


    }

    @Override
    public CoursesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_courses_recycler, parent, false);

        return new CoursesViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final CoursesViewHolder holder, int position){
        holder.textViewCRN.setText(courseList.get(position).getCrn());
        holder.textViewCourseName.setText(courseList.get(position).getCourse_name());
        holder.textViewProfessor.setText(courseList.get(position).getProfessor());
        holder.textViewSeats.setText(Integer.toString(courseList.get(position).getSeats()));
        holder.textViewLocation.setText(courseList.get(position).getLocation());
        holder.textViewStarTime.setText(courseList.get(position).getStart_time());
        holder.textViewEndTime.setText(courseList.get(position).getEnd_time());
        holder.textViewDays.setText(courseList.get(position).getDays());
    }
    @Override
    public int getItemCount(){
        return mFilteredList.size();
    }



}
