package ro.LearnByPLaying.Activitati.Chat_AI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stefan.proiect_learningbyplayinggame.R;

import java.util.ArrayList;

import ro.LearnByPLaying.Utilitare.TypeWriter;

/**
 * Created by Stefan on 5/8/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> LIST_MESSAGES = new ArrayList<>();

    private String text_message_name = new String();

    private Context nContext;
    private String typeHumanOrBot;

    public RecyclerViewAdapter(ArrayList<String> MESSAGE_BODY, String USER_NAME, Context nContext,String typeHumanOrBot) {
        Log.d("Activitati","<<<<< IN RecyclerViewAdapter() >>>>");
        this.LIST_MESSAGES = MESSAGE_BODY;
        this.text_message_name = USER_NAME;
        this.nContext = nContext;
        this.typeHumanOrBot= typeHumanOrBot;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("Activitati","RecyclerViewAdapter - RecyclerViewAdapter.onCreateViewHolder()");
        Log.d("Activitati","ViewGroup: "+parent+" viewType: "+viewType);
        View view = null;
        switch (viewType) {
            case 2: return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_send,parent, false));
            case 0: return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_receive,parent, false));
        }
//        if(typeHumanOrBot.equals("HUMAN")){
//             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_send,parent, false);
//        }else{
//             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_receive,parent, false);
//        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder: CALLED");
        switch (holder.getItemViewType()) {
            case 2:
                holder.text_message_body.setText(LIST_MESSAGES.get(position));
                holder.text_message_name.setText(text_message_name);
                break;
            case 0:
                holder.text_message_body.setCharacterDelay(50);
                holder.text_message_body.animateText(LIST_MESSAGES.get(position));
                holder.text_message_name.setText("Ben");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return LIST_MESSAGES.size();
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position % 2 * 2;
    }

    /**
     * Tine widget-urile in memorie pentru fiecare widget ...
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image_message_profile;
        TextView text_message_name;
        TypeWriter text_message_body;
        public ViewHolder(View item){
            super(item);
            image_message_profile = item.findViewById(R.id.image_message_profile);
            text_message_name = item.findViewById(R.id.text_message_name);
            text_message_body = item.findViewById(R.id.text_message_body);
        }
    }

    public class ViewHolderBot extends RecyclerView.ViewHolder{
        ImageView image_message_profile;
        TextView text_message_name;
        TypeWriter text_message_body;
        public ViewHolderBot(View item){
            super(item);
            image_message_profile = item.findViewById(R.id.image_message_profile);
            text_message_name = item.findViewById(R.id.text_message_name);
            text_message_body = item.findViewById(R.id.text_message_body);
        }
    }
}
