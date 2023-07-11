package com.example.prm392_project.ui.category;

import android.content.Context;
import android.os.Bundle;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.prm392_project.R;
import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.model.Category;
import com.example.prm392_project.databinding.FragmentCategoryBinding;
import com.example.prm392_project.ui.common.OnItemClickListener;
import com.google.gson.Gson;

import java.util.Arrays;

/**
 * A fragment representing a list of Items.
 */
public class CategoryFragment extends Fragment {
    private FragmentCategoryBinding binding;
    private CategoriesAdapter categoriesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ContentLoadingProgressBar progress = root.findViewById(R.id.progress);
        RecyclerView categoriesRecyclerView = root.findViewById(R.id.category_recycler_view);
        OnItemClickListener onBookClickListener = (view, book) -> {
        };
        CategoryViewModel categoryViewModel = new ViewModelProvider(this, new CategoryViewModelFactory()).get(CategoryViewModel.class);
        categoriesAdapter = new CategoriesAdapter(root.getContext(), onBookClickListener);
        categoriesRecyclerView.setAdapter(categoriesAdapter);
        progress.show();
        categoryViewModel.getAllCategories().observe(getViewLifecycleOwner(), categories -> {
            int[] bookNumbers = new int[categories.size()];
            Arrays.fill(bookNumbers, 0);
            categoryViewModel.getAllBooks().observe(getViewLifecycleOwner(), books -> {
                if (!progress.isShown()) return;
                for (int i = 0; i < categories.size(); i++) {
                    Category category = categories.get(i);
                    int categoryId = category.getId();
                    for (Book book : books) {
                        if (book.getCategoryId() == categoryId) {
                            bookNumbers[i]++;
                        }
                    }
                }
                categoriesAdapter.setCategories(categories, bookNumbers);
                progress.hide();
            });
        });

        return root;
    }

}