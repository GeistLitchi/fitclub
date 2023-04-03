package comp3350.fitclub.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.fitclub.R;
import comp3350.fitclub.objects.Workout;

public class CustomWorkoutAdapter extends RecyclerView.Adapter<CustomWorkoutAdapter.ViewHolder> {

    Context context;
    List<Workout> list;
    private final RecyclerViewInterface recyclerViewInterface;          // reference to recyclerViewInterface

    CustomWorkoutAdapter(Context context, List<Workout> arr, RecyclerViewInterface recyclerViewInterface){

        this.context = context;
        list = arr;
        this.recyclerViewInterface = recyclerViewInterface;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout_workout_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, recyclerViewInterface);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            textView = itemView.findViewById(R.id.textviewWL);

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
