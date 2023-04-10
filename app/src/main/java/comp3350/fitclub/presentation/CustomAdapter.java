package comp3350.fitclub.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comp3350.fitclub.R;
import comp3350.fitclub.logic.LikedLogic;
import comp3350.fitclub.objects.Exercise;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;          // refrence to recyclerViewInterface
    private Context context;                                            // context to use from
    private List<Exercise> list;                                   // list of all exercise
    private final LikedLogic liked = new LikedLogic();

    //constructor
    CustomAdapter(Context context, List<Exercise> arr, RecyclerViewInterface recyclerViewInterface){
        RecyclerViewInterface recyclerViewInterface1;
        this.context = context;
        list = arr;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    // this method creates the view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, recyclerViewInterface);
        return viewHolder;
    }

    //this method put in text from our data into layout
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.exercise_name.setText(list.get(position).getExerciseName());

        //check if exercise is liked, to decide the visibility of favorite icon
        if (liked.isContains(list.get(position))) {
            holder.imgFavorite.setVisibility(View.VISIBLE);
        } else {
            holder.imgFavorite.setVisibility(View.INVISIBLE);
        }

    }

    // this method returns length/ size of list
    @Override
    public int getItemCount() {
        return list.size();
    }

    // this method hold our view which we need to get reference to elements from the layout
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView exercise_name;
        ImageView imgView;
        ImageView imgFavorite;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            exercise_name = itemView.findViewById(R.id.exercise_name);
            imgView = itemView.findViewById(R.id.image_exe);
            imgFavorite = itemView.findViewById(R.id.image_favorite);

            itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view) {
                    //change the status of favorite icon status
                    if(imgFavorite.getVisibility() == View.VISIBLE)
                    {
                        imgFavorite.setVisibility(View.INVISIBLE);
                    }else
                    {
                        imgFavorite.setVisibility(View.VISIBLE);
                    }

                    //call onItemLongClick function in activity file
                    if(recyclerViewInterface != null)
                    {
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION)
                        {
                            recyclerViewInterface.onItemLongClick(pos);
                        }
                    }
                    return true;
                }
            });

            // creating onClickListner for future use
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
