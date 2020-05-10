package com.snote.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snote.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonalAdapter extends RecyclerView.Adapter<PersonalAdapter.PersonalHolder> {
    private Context context;
    private List<String> list_features;

    public PersonalAdapter(Context c, List<String> lstFeatures){
        this.context = c;
        this.list_features = lstFeatures;
    }

    @NonNull
    @Override
    public PersonalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_personal_content_recycler, parent,false);
        PersonalHolder pHolder = new PersonalHolder(view);

        return pHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalHolder holder, int position) {
        String featureName = list_features.get(position);

        holder.setDetails(featureName);

    }

    @Override
    public int getItemCount() {
        return list_features.size();
    }

    class PersonalHolder extends RecyclerView.ViewHolder{

        TextView mFeatureName;

        PersonalHolder(@NonNull View itemView) {
            super(itemView);

            mFeatureName = itemView.findViewById(R.id.personal_recycler_txtFeature);
        }

        void setDetails(String featureName){
            mFeatureName.setText(featureName);
        }
    }
}
