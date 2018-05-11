package ro.LearnByPLaying.Activitati.Testing_new_activitys;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Stefan on 5/11/2018.
 */

public class RecyclerViewInputsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class ViewHolderTypeEditText extends RecyclerView.ViewHolder {

        public ViewHolderTypeEditText(View itemView) {
            super(itemView);
        }
    }

    class ViewHolderTypeComboBox extends RecyclerView.ViewHolder {
        public ViewHolderTypeComboBox(View itemView) {
            super(itemView);

        }
    }
}

