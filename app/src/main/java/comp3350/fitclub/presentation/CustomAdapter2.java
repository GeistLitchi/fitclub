package comp3350.fitclub.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import comp3350.fitclub.R;
import comp3350.fitclub.logic.ExerciseList;
import comp3350.fitclub.objects.Exercise;

public class CustomAdapter2 extends ArrayAdapter<Exercise> {

    private List<Exercise> list;


    public CustomAdapter2(@NonNull Context context, int resource, @NonNull List<Exercise> objects) {
        super(context, resource, objects);
        list = objects;
//        list = (ArrayList) list;
    }

    @Nullable
    @Override
    public Exercise getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout2,parent,false);

        TextView name = convertView.findViewById(R.id.exeName);
        name.setText(getItem(position).getExerciseName());

        return convertView;
    }
}
