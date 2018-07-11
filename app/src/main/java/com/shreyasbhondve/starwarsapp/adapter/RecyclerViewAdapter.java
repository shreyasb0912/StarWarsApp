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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtName.setText(starWarCharacterList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return starWarCharacterList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;

        private CardView constraintLayoutContainer;

        ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);


            constraintLayoutContainer = itemView.findViewById(R.id.card_view);

            constraintLayoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.launchIntent();
                }
            });
        }
    }

    public interface ClickListener {
        void launchIntent();
    }

    public void setData(List<APIResponse.StarWarCharacter> starWarCharacterList) {
        this.starWarCharacterList.addAll(starWarCharacterList);
        notifyDataSetChanged();
    }
}
