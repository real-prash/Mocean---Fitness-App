package com.example.moceanskeleton.presentation.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moceanskeleton.R;
import com.example.moceanskeleton.objects.Food;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {

    private final ArrayList<Food> FOOD_LIST;
    private final ArrayList<Food> filteredList;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView foodImageView;
        public TextView foodTextView1;
        public TextView foodTextView2;
        public TextView foodCalorieCount;
        public TextView foodID;

        public RelativeLayout foodItemButton;

        public ViewHolder(@NonNull View itemView, OnItemClickListener clickListener) {
            super(itemView);
            foodImageView = itemView.findViewById(R.id.card_image_view);
            foodTextView1 = itemView.findViewById(R.id.text_view_1);
            foodTextView2 = itemView.findViewById(R.id.text_view_2);
            foodCalorieCount = itemView.findViewById(R.id.text_view_4);
            foodID = itemView.findViewById(R.id.foodID);
            foodItemButton = itemView.findViewById(R.id.food_item_button);

            foodItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View w) {
                    if (clickListener != null) {
                        int position = getAdapterPosition();
                        TextView clickedFoodID = itemView.findViewById(R.id.foodID);
                        int myFoodID = Integer.valueOf(clickedFoodID.getText().toString());
                        if (position != RecyclerView.NO_POSITION) {
                            clickListener.onItemClick(myFoodID);
                        }
                    }
                }
            });
        }
    }

    public RecyclerViewAdapter(ArrayList<Food> myFood) {
        FOOD_LIST = myFood;
        filteredList = new ArrayList<>(myFood);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        ViewHolder myView = new ViewHolder(v, clickListener);
        return myView;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food currentFoodItem = filteredList.get(position);

        holder.foodImageView.setImageResource(currentFoodItem.getImageResource());
        holder.foodTextView1.setText(currentFoodItem.getFoodName());
        holder.foodTextView2.setText(currentFoodItem.getFoodGroup());
        holder.foodCalorieCount.setText(String.valueOf(currentFoodItem.getFoodCalories()));
        holder.foodID.setText(String.valueOf(currentFoodItem.getFoodID()));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return foodFilter;
    }

    private final Filter foodFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Food> filteredFoodList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredFoodList.addAll(FOOD_LIST);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Food food : FOOD_LIST) {
                    if (food.getFoodName().toLowerCase().contains(filterPattern)) {
                        filteredFoodList.add(food);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredFoodList;

            return results;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList.clear();
            filteredList.addAll((List<Food>) results.values);
            notifyDataSetChanged();
        }
    };
}
