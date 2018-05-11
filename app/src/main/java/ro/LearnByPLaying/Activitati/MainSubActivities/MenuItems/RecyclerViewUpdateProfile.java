package ro.LearnByPLaying.Activitati.MainSubActivities.MenuItems;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.stefan.proiect_learningbyplayinggame.R;
import java.util.ArrayList;
import java.util.HashMap;
import ro.LearnByPLaying.Utilitare.StringUtils;

/**
 * Created by Stefan on 5/11/2018.
 */

public class RecyclerViewUpdateProfile  extends RecyclerView.Adapter<RecyclerViewUpdateProfile.ViewHolder>{
    private static final String TAG = "RecyclerViewUpdateProfile- ";
    private Context nContext;
    private ArrayList<String> afisareProprietate;
    private ArrayList<String> valoareProprietate;
    private ArrayList<View.OnClickListener> clickListener = new ArrayList<>();
    private ArrayList<String> hint;

    public RecyclerViewUpdateProfile(Context nContext, HashMap<String,ArrayList<String>> values,ArrayList<View.OnClickListener> clickListeners) {
        Log.d("Activitati", "<<<<< IN RecyclerViewUpdateProfile() >>>>");
        Log.d("Activitati", TAG+"values: "+ StringUtils.readObject(values));
        this.nContext = nContext;
        this.afisareProprietate = values.get("afisareProprietati");
        this.valoareProprietate = values.get("valoareProprietati");
        this.hint = values.get("hinturi");
        this.clickListener = clickListeners;
    }

    @Override
    public RecyclerViewUpdateProfile.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("Activitati", TAG+"ViewGroup: " + parent + " viewType: " + viewType);
        return new RecyclerViewUpdateProfile.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_profile_user, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewUpdateProfile.ViewHolder holder, int position) {
        Log.d("Activitati", TAG+"onBindViewHolder: CALLED");
        holder.textView.setText(afisareProprietate.get(position));
        holder.textViewValue.setText(valoareProprietate.get(position));
        holder.inputLayoutUpdate.setHint(hint.get(position));
        holder.inputLayoutUpdateValue.getText().clear();
        holder.updateBtn.setOnClickListener(clickListener.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("Activitati", TAG+"getItemCount: CALLED "+afisareProprietate.size());
        return afisareProprietate.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         TextView textView;
         TextView textViewValue;
         TextInputLayout inputLayoutUpdate;
         TextInputEditText inputLayoutUpdateValue;
         Button updateBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            Log.d("Activitati", TAG+"ViewHolder: CALLED");
            textView = itemView.findViewById(R.id.textView);
            textViewValue = itemView.findViewById(R.id.textViewValue);
            inputLayoutUpdate = itemView.findViewById(R.id.inputLayoutUpdate);
            inputLayoutUpdateValue = itemView.findViewById(R.id.inputLayoutUpdateValue);
            updateBtn = itemView.findViewById(R.id.updateBtn);
        }
    }
}
