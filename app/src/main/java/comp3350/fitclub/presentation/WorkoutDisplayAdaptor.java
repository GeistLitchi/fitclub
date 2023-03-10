package comp3350.fitclub.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import comp3350.fitclub.R;
import comp3350.fitclub.objects.Exercise;
import comp3350.fitclub.objects.Workout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDisplayAdaptor extends RecyclerView.Adapter<WorkoutDisplayAdaptor.ViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private Context context;
    private List<Workout> list;

    public WorkoutDisplayAdaptor(Context context, List<Workout> arr, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        list = arr;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public WorkoutDisplayAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_workout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, recyclerViewInterface);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WorkoutDisplayAdaptor.ViewHolder holder, int position) {
         holder.textView.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);

            textView = (TextView) view.findViewById(R.id.workout_exercise_list);
        }

        public TextView getTextView() {
//            return textView;
            return null;
        }

    }
}
