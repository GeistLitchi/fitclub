package comp3350.fitclub.presentation;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import comp3350.fitclub.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutDisplayAdaptor extends RecyclerView.Adapter<WorkoutDisplayAdaptor.ViewHolder> {

    @Override
    public WorkoutDisplayAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(WorkoutDisplayAdaptor.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

//            textView = (TextView) view.findViewById(R.id.textView);
        }

        public TextView getTextView() {
//            return textView;
            return null;
        }

    }
}
