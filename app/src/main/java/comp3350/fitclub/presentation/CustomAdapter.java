package comp3350.fitclub.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import comp3350.fitclub.R;

public class CustomAdapter extends ArrayAdapter<String> {

    private String[] arr;               // variable to store the list of strings


    public CustomAdapter(@NonNull Context context, int resource, @NonNull String[] arr) {
        super(context, resource, arr);
        this.arr = arr;             // assignment of this arr
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return arr[position];           // return the item from arr at position
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout2, parent,false); //inflating the layout so that we can use findViewById and passing in our new custom adapter layout

        TextView name = convertView.findViewById(R.id.exeName);         // getting the object by id which we want to edit
        name.setText(getItem(position));                                // putting the name of the excresice

        return convertView;
    }
}
