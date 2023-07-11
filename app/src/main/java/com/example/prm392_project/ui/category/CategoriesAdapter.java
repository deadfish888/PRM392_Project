package com.example.prm392_project.ui.category;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prm392_project.R;
import com.example.prm392_project.data.model.Category;
import com.example.prm392_project.ui.common.OnItemClickListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Category}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    @NonNull
    private final Context context;
    private final OnItemClickListener onBookClickListener;
    private List<Category> items;
    private int[] bNumber;
    public CategoriesAdapter(@NonNull Context context, OnItemClickListener onBookClickListener) {
        this.context = context;
        this.onBookClickListener = onBookClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setCategoryItem(items.get(position), bNumber[position]);
    }

    @Override
    public int getItemCount() {
        return items==null ? 0: items.size();
    }
    public void setCategories(List<Category> categories, int[] bNumber) {
        this.items = categories;
        this.bNumber = bNumber;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mNumber;
        public View cateItem;

        public ViewHolder(View binding) {
            super(binding);
            mIdView = binding.findViewById(R.id.cate_name);
            mNumber = binding.findViewById(R.id.tvNumber);
            cateItem = binding;
        }

        public void setCategoryItem(Category category, int bNumber) {
            mIdView.setText(category.getName());
            mNumber.setText(String.valueOf(bNumber));
            cateItem.setOnClickListener(view -> onBookClickListener.onItemClicked(view, category));
        }
    }
}