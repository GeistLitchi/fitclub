package comp3350.fitclub.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import comp3350.fitclub.R;
import comp3350.fitclub.objects.Exercise;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private RecyclerViewInterface recyclerViewInterface;
    private Context context;
    private ArrayList<Exercise> list;
    private ArrayList<Exercise> listFull;

    CustomAdapter(Context context, ArrayList<Exercise> arr, RecyclerViewInterface recyclerViewInterface){
        RecyclerViewInterface recyclerViewInterface1;
        this.context = context;
        list = arr;
        this.recyclerViewInterface = recyclerViewInterface;

        listFull = new ArrayList<>(list); // keep a list with full content of exercises
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, recyclerViewInterface);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.exercise_name.setText(list.get(position).getExerciseName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public Filter getFilter()
    {
        return exerciseFilter;
    }

    private Filter exerciseFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Exercise> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0)
            {
                filteredList.addAll(listFull);
            }else
            {
                Map<String,Integer> difficultyMap = new HashMap<>(); //create a map with spinner ("Easy") to int difficulty (1)
                difficultyMap.put("All",0);
                difficultyMap.put("Easy",1);
                difficultyMap.put("Medium",2);
                difficultyMap.put("Hard",3);
                int difficulty = difficultyMap.get(constraint.toString());

                if(difficulty != 0)
                {
                    for(Exercise exercise : listFull)
                    {
                        if(exercise.getDifficulty() == difficulty)
                        {
                            filteredList.add(exercise);
                        }
                    }
                }else
                {
                    filteredList.addAll(listFull);
                }

            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            list.clear();
            list.addAll((Collection<? extends Exercise>) results.values);
            notifyDataSetChanged();


        }
    };



    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView exercise_name;
        ImageView imgView;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            exercise_name = itemView.findViewById(R.id.exercise_name);
            imgView = itemView.findViewById(R.id.image_exe);





            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }

}
