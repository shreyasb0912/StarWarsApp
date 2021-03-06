package com.shreyasbhondve.starwarsapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shreyasbhondve.starwarsapp.R;
import com.shreyasbhondve.starwarsapp.pojo.APIResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private RecyclerViewAdapter.ClickListener clickListener;
    List<APIResponse.StarWarCharacter> starWarCharacterList = null;
    Context mContext;

    @Inject
    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        this.starWarCharacterList = new ArrayList<>();
    }

    public RecyclerViewAdapter(List<APIResponse.StarWarCharacter> starWarCharacterList) {
        this.starWarCharacterList = starWarCharacterList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtName.setText(starWarCharacterList.get(position).getName());
        holder.txtGender.setText(starWarCharacterList.get(position).getGender());
    }

    @Override
    public int getItemCount() {
        return starWarCharacterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName;
        public TextView txtGender;

        private CardView constraintLayoutContainer;

        ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtGender = itemView.findViewById(R.id.txtGender);

            constraintLayoutContainer = itemView.findViewById(R.id.card_view);

            constraintLayoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.launchIntent(starWarCharacterList.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface ClickListener {
        void launchIntent(APIResponse.StarWarCharacter starWarCharacter);
    }

    public void setData(List<APIResponse.StarWarCharacter> starWarCharacterList) {
        this.starWarCharacterList.addAll(starWarCharacterList);
        notifyDataSetChanged();
    }
}
