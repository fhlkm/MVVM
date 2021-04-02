package com.doordash.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.doordash.binding.binder.common.ItemBinder;

import java.lang.ref.WeakReference;
import java.util.Collection;

public class BindingRecyclerViewAdapter<T> extends RecyclerView.Adapter<BindingRecyclerViewAdapter.ViewHolder> implements View.OnClickListener{
   static String TAG="DD:BindingRecyclerViewAdapter";
    private ObservableList<T> items;
    private ItemBinder<T> itemBinder;
    private LayoutInflater inflater;
    private final WeakReferenceOnListChangedCallback onListChangedCallback;
    public ClickHandler<T> clickHandler;
    private static final int ITEM_MODEL = -125;
    private static final int ITEM_INDEX = -126;
    public BindingRecyclerViewAdapter(ItemBinder<T> itemBinder, @Nullable Collection<T> items) {
        this.itemBinder = itemBinder;
        this.onListChangedCallback = new WeakReferenceOnListChangedCallback<>(this);
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

//        Log.i(TAG,"onBindViewHolder");
        final T item = items.get(position);
//BR.bindingdish or BR.bindingstore and data bean
        viewHolder.binding.setVariable(itemBinder.getBindingVariable(item),item);
        viewHolder.binding.getRoot().setTag(ITEM_MODEL, item);
        viewHolder.binding.getRoot().setTag(ITEM_INDEX, position);
        viewHolder.binding.getRoot().setOnClickListener(this);
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemViewType(int position)
    {
        return itemBinder.getLayoutRes(items.get(position));
    }

    @Override
    public void onClick(View v) {
        if (clickHandler != null)
        {
            T item = (T) v.getTag(ITEM_MODEL);
            int pos = (int) v.getTag(ITEM_INDEX);
            clickHandler.onClick(item,pos);
        }
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
            this.items.removeOnListChangedCallback(onListChangedCallback);
            notifyItemRangeRemoved(0, this.items.size());
        }

        if (items instanceof ObservableList)
        {
            this.items = (ObservableList<T>) items;
            notifyItemRangeInserted(0, this.items.size());
            this.items.addOnListChangedCallback(onListChangedCallback);
        }
        else if (items != null)
        {
            this.items = new ObservableArrayList<>();
            this.items.addOnListChangedCallback(onListChangedCallback);
            this.items.addAll(items);
        }
        else
        {
            this.items = null;
        }
    }


    public void setClickHandler(ClickHandler<T> clickHandler)
    {
        this.clickHandler = clickHandler;
    }

    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback
    {

        private final WeakReference<BindingRecyclerViewAdapter<T>> adapterReference;

        public WeakReferenceOnListChangedCallback(BindingRecyclerViewAdapter<T> bindingRecyclerViewAdapter)
        {
            this.adapterReference = new WeakReference<>(bindingRecyclerViewAdapter);
        }

        @Override
        public void onChanged(ObservableList sender)
        {
            Log.i(TAG,"onChanged");
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null)
            {
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount)
        {
            Log.i(TAG,"onItemRangeChanged");
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null)
            {
                adapter.notifyItemRangeChanged(positionStart, itemCount);
            }
        }

        @Override
        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount)
        {
            Log.i(TAG,"onItemRangeInserted");
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null)
            {
                adapter.notifyItemRangeInserted(positionStart, itemCount);
            }
        }

        @Override
        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount)
        {
            Log.i(TAG,"onItemRangeMoved");
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null)
            {
                adapter.notifyItemMoved(fromPosition, toPosition);
            }
        }

        @Override
        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount)
        {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null)
            {
                adapter.notifyItemRangeRemoved(positionStart, itemCount);
            }
        }
    }
}

