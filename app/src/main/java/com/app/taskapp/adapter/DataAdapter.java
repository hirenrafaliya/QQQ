package com.app.taskapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.taskapp.databinding.ItemDataBinding;
import com.app.taskapp.listener.OnDataItemClickedListener;
import com.app.taskapp.model.Data;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private final List<Data> dataList = new ArrayList<>();
    private final OnDataItemClickedListener onDataItemClickedListener;

    public DataAdapter(OnDataItemClickedListener onDataItemClickedListener) {
        this.onDataItemClickedListener = onDataItemClickedListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemDataBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvName.setText(dataList.get(position).getName());
        holder.binding.tvAddress.setText(dataList.get(position).getAddress());

        holder.binding.layoutParent.setOnClickListener((View view) ->
                onDataItemClickedListener.onDataItemClicked(dataList.get(holder.getAdapterPosition()), holder.getAdapterPosition()));

        holder.binding.btnDelete.setOnClickListener(view -> {
            dataList.remove(dataList.get(position));
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void modifyData(Data data, int position) {
        dataList.set(position, data);
        notifyItemChanged(position);
    }

    public void insertData(Data data) {
        dataList.add(data);
        notifyItemInserted(dataList.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemDataBinding binding;

        public ViewHolder(@NonNull ItemDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
