package com.doordash.model.binding;

import android.database.Observable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.doordash.adapter.binder.common.ItemBinder;

import java.util.Collection;

public class BindingRecyclerViewAdapter<T> extends RecyclerView.Adapter<BindingRecyclerViewAdapter.ViewHolder>{
    private ObservableList<T> items;
    private ItemBinder<T> itemBinder;
    private LayoutInflater inflater;

    public BindingRecyclerViewAdapter(ItemBinder<T> itemBinder, @Nullable Collection<T> items) {
        this.itemBinder = itemBinder;
        setItems(items);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if(null ==inflater){
            inflater=LayoutInflater.from(viewGroup.getContext());
        }
        //here depends on getItemViewType, viewType==layout
        ViewDataBinding binding = DataBindingUtil.inflate(inflater,viewType,viewGroup,false);
        return new ViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return items==null?0:items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final T item = items.get(position);
        //layout and RestaurantModel
        viewHolder.binding.setVariable(itemBinder.getBindingVariable(item),item);
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemViewType(int position)
    {
        return itemBinder.getLayoutRes(items.get(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;
        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    public void setItems(@Nullable Collection<T> items)
    {
        if (this.items == items)
        {
            return;
        }

        if (this.items != null)
        {

            notifyItemRangeRemoved(0, this.items.size());
        }

        if (items instanceof ObservableList)
        {
            this.items = (ObservableList<T>) items;
            notifyItemRangeInserted(0, this.items.size());
        }
        else if (items != null)
        {
            this.items = new androidx.databinding.ObservableArrayList<>();
            this.items.addAll(items);
        }
        else
        {
            this.items = null;
        }
    }
}

