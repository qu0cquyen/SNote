package com.snote.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.snote.Models.Content;
import com.snote.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    private Context context;
    private List<Content> lstContent;

    public HomeAdapter(Context context, List<Content> list_content){
        this.context = context;
        this.lstContent = list_content;
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_content_recycler, parent, false);
        HomeHolder homeHolderView = new HomeHolder(v);
        return homeHolderView;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        Content content = lstContent.get(position);
        holder.setDetails(content);

    }

    @Override
    public int getItemCount() {
        return lstContent.size();
    }

    class HomeHolder extends RecyclerView.ViewHolder {

        CardView cv;
        ImageView imageView;
        TextView txtTitle;
        TextView txtDescription;

        HomeHolder(@NonNull View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.home_recyclerView_cardView);
            txtTitle = itemView.findViewById(R.id.home_recyclerView_txtTitle);
            txtDescription = itemView.findViewById(R.id.home_recyclerView_description);
            imageView = itemView.findViewById(R.id.home_recyclerView_image);
        }

        void setDetails(Content content){
            txtTitle.setText(content.getTitle());
            txtDescription.setText(content.getDescription());
            Glide.with(context)
                    .load(R.drawable.login)
                    .override(imageView.getMaxWidth(), imageView.getMaxHeight())
                    .into(imageView);
        }


    }
}
