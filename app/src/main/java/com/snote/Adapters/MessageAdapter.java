package com.snote.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snote.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private Context context;
    private List<String> list_friends;

    public MessageAdapter(Context c, List<String> lstFriends){
        this.context = c;
        this.list_friends = lstFriends;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_message_content_recycler, parent, false);
        MessageHolder messageHolder = new MessageHolder(view);

        return messageHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        String s = list_friends.get(position);
        holder.setDetails(s);
    }


    @Override
    public int getItemCount() {
        return list_friends.size();
    }

    class MessageHolder extends RecyclerView.ViewHolder{
        public MessageHolder(@NonNull View itemView) {
            super(itemView);
        }

        private void setDetails(String s){

        }
    }
}
