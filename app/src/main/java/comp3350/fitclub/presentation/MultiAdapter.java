package comp3350.fitclub.presentation;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import comp3350.fitclub.R;
import comp3350.fitclub.objects.Exercise;

public class MultiAdapter extends RecyclerView.Adapter<MultiAdapter.MultiViewHolder> {

    private Context context;                                            // context to use from
    private ArrayList<Exercise> list;

    MultiAdapter(Context context, ArrayList<Exercise> arr){
        this.context = context;
        list = arr;
    }

    @NonNull
    @Override
    public MultiAdapter.MultiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.multi_select_layout, parent, false);
        MultiViewHolder holder = new MultiViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MultiAdapter.MultiViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MultiViewHolder extends RecyclerView.ViewHolder {

        private TextView exe_name;
        private ImageView isChecked;

        public MultiViewHolder(@NonNull View itemView) {
            super(itemView);
            exe_name = itemView.findViewById(R.id.exe_name);
            isChecked = itemView.findViewById(R.id.img_isChecked);
        }

        //getting the selected items
        void bind(final Exercise exercise){
            isChecked.setVisibility(exercise.isChecked() ? View.VISIBLE : View.GONE);
            exe_name.setText(exercise.getExerciseName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    exercise.setChecked(!exercise.isChecked());
                    isChecked.setVisibility(exercise.isChecked() ? View.VISIBLE : View.GONE);
                }
            });
        }
    }

    // getting all selected items
    public ArrayList<Exercise> getAll(){return list;}

    // getting selected when button is clicked
    public ArrayList<Exercise> getSelected(){
        ArrayList<Exercise> selected = new ArrayList<>();

        for (int i = 0; i < list.size(); i++){
            if(list.get(i).isChecked()){
                selected.add(list.get(i));
            }
        }

        return selected;
    }
}
