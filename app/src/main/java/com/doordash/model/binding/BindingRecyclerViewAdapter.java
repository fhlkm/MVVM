package com.doordash.model.binding;

import android.database.Observable;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;

public class BindingRecyclerViewAdapter<T> extends RecyclerView.Adapter<BindingRecyclerViewAdapter.ViewHolder>{
    private ObservableList<T> items;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return items==null?0:items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    public void setItems(Collection<T> mItems){
        if(items ==mItems){
            return;
        }
        if(null != items){
            notifyItemRangeRemoved(0,items.size());
        }

        if(items instanceof ObservableArrayList){
            items = (ObservableList<T>) mItems;
            notifyItemRangeInserted(0, this.items.size());
        }else {
            items =new ObservableArrayList<>();
            items.addAll(mItems);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

