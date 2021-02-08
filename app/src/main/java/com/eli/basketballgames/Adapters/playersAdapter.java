package com.eli.basketballgames.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eli.basketballgames.Activities.AddPlayer;
import com.eli.basketballgames.Models.Player;
import com.eli.basketballgames.R;

import java.util.ArrayList;
import java.util.List;

public class playersAdapter extends RecyclerView.Adapter<playersAdapter.ViewHolder> {
    private List<Player> players1=new ArrayList<Player>();
    private Context context;
    private OnItemClickLister listener;
    public playersAdapter(){
        //this.players=players;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_item, parent, false);
        return new ViewHolder(view);
    }
    public Player getPlayerAt(int position){
        return players1.get(position);
    }
public interface OnItemClickLister{
     public void onClick(Player player);
}
public void setOnItemClickListenner(OnItemClickLister listener){
       this.listener=listener ;
}
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.name.setText(players1.get(position).getName());
       holder.scores.setText(""+players1.get(position).getPointsPerGame());

    }
public void setPlayers(List<Player> players){
        this.players1=players;

}
    @Override
    public int getItemCount() {
        return this.players1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView scores;
        TextView edit;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name1);
            scores = (TextView) itemView.findViewById(R.id.scores);
            edit = (TextView) itemView.findViewById(R.id.edit);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    if(listener!=null&&pos!=RecyclerView.NO_POSITION)
                    listener.onClick(players1.get(pos));
                }
            });
        }




    }
}
