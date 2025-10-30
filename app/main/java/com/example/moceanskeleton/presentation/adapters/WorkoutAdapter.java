package com.example.moceanskeleton.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moceanskeleton.R;
import com.example.moceanskeleton.objects.Workout;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    private OnItemClickListener clickListener;
    private final List<Workout> WORKOUT_LIST;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView workoutName, workoutID;
        public RelativeLayout workoutItemButton;

        public ImageView workoutImageView;

        public ViewHolder(@NonNull View itemView, OnItemClickListener clickListener) {
            super(itemView);
            workoutName = itemView.findViewById(R.id.workoutNameTextView);
            workoutID = itemView.findViewById(R.id.workoutID);
            workoutItemButton = itemView.findViewById(R.id.workout_item_button);
            workoutImageView = itemView.findViewById(R.id.workout_image_view);

            workoutItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View w) {
                    if (clickListener != null) {
                        int position = getAdapterPosition();
                        TextView clickedWorkoutID = itemView.findViewById(R.id.workoutID);
                        int myWorkoutID = Integer.valueOf(clickedWorkoutID.getText().toString());
                        if (position != RecyclerView.NO_POSITION) {
                            clickListener.onItemClick(myWorkoutID);
                        }
                    }
                }
            });
        }
    }

    public WorkoutAdapter(List<Workout> myWorkouts) {
        WORKOUT_LIST = myWorkouts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_item, parent, false);
        ViewHolder myView = new ViewHolder(v, clickListener);
        return myView;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Workout currentWorkoutItem = WORKOUT_LIST.get(position);
        holder.workoutImageView.setImageResource(R.drawable.workout_icon);
        holder.workoutName.setText(currentWorkoutItem.getName());
        holder.workoutID.setText(String.valueOf(currentWorkoutItem.getWorkoutID()));
    }

    @Override
    public int getItemCount() {
        return WORKOUT_LIST.size();
    }
}