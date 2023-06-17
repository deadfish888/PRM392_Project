package com.example.prm392_project.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.R;
import com.example.prm392_project.databinding.FragmentHomeBinding;
import com.example.prm392_project.ui.common.OnItemClickListener;
import com.google.gson.Gson;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private BooksAdapter booksAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this, new HomeViewModelFactory()).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ContentLoadingProgressBar progress = root.findViewById(R.id.progress);
        RecyclerView booksRecyclerView = root.findViewById(R.id.book_recycler_view);
        OnItemClickListener onBookClickListener = (view, book) -> {
//            Intent intent = new Intent(getActivity(), BlogsActivity.class);
//            String categoryJson = new Gson().toJson(book);
//            intent.putExtra("Category", categoryJson);
//            intent.putExtra("CallerActivity", getActivity().getClass().getSimpleName());
//            startActivity(intent);
        };
        booksAdapter = new BooksAdapter(root.getContext(), onBookClickListener);
        booksRecyclerView.setAdapter(booksAdapter);
        booksRecyclerView.setLayoutManager(new GridLayoutManager(root.getContext(), 2));
        progress.show();
        homeViewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
            booksAdapter.setBooks(books);
            progress.hide();
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}