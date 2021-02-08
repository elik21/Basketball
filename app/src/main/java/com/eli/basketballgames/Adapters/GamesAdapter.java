package com.eli.basketballgames.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eli.basketballgames.Models.Game;
import com.eli.basketballgames.Models.Player;
import com.eli.basketballgames.R;

import java.util.ArrayList;
import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {
private List<Game> players1=new ArrayList<Game>();

private OnItemClickLister listener;

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.player_item, parent, false);
        return new ViewHolder(view);
        }
public Game getPlayerAt(int position){
        return players1.get(position);
        }
public interface OnItemClickLister{
    public void onClick(Game player);
}
    public void setOnItemClickListenner(GamesAdapter.OnItemClickLister listener){
        this.listener=listener ;
    }
    @Override
    public void onBindViewHolder(@NonNull GamesAdapter.ViewHolder holder, int position) {
        holder.name.setText(players1.get(position).getGuestTeam());
        holder.scores.setText(""+players1.get(position).getHostTeam());

    }
    public void setPlayers(List<Game> players){
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
