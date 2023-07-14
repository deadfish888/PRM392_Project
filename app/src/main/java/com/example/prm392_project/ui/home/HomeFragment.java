package com.example.prm392_project.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.R;
import com.example.prm392_project.data.model.Category;
import com.example.prm392_project.databinding.FragmentHomeBinding;
import com.example.prm392_project.ui.MainActivity;
import com.example.prm392_project.ui.common.OnItemClickListener;
import com.google.gson.Gson;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private BooksAdapter booksAdapter;
    HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this, new HomeViewModelFactory()).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setHasOptionsMenu(true);

        Category searchCategory = null;
        Bundle arg = getArguments();
        if(arg != null && !arg.isEmpty()){
            String data = arg.getString("search_category");
            searchCategory = new Gson().fromJson(data, Category.class);
            arg.clear();
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(searchCategory.getName());
        }

        ContentLoadingProgressBar progress = root.findViewById(R.id.progress);
        RecyclerView booksRecyclerView = root.findViewById(R.id.book_recycler_view);
        OnItemClickListener onBookClickListener = (view, book) -> {
            String bookJson = new Gson().toJson(book);
            Bundle args = new Bundle();
            args.putString("bookInfo", bookJson);
            NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_nav_home_to_bookInfoFragment, args);
        };
        booksAdapter = new BooksAdapter(root.getContext(), onBookClickListener);
        booksRecyclerView.setAdapter(booksAdapter);
        booksRecyclerView.setLayoutManager(new GridLayoutManager(root.getContext(), 2));
        progress.show();
        if (searchCategory != null){
            homeViewModel.searchBooks(null, searchCategory.getId()).observe(getViewLifecycleOwner(), books -> {
                booksAdapter.setBooks(books);
                progress.hide();
            });
        }else {
            homeViewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
                booksAdapter.setBooks(books);
                progress.hide();
            });
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                homeViewModel.searchBooks(query, 0).observe(getViewLifecycleOwner(), books -> {
                    booksAdapter.setBooks(books);
                    ((MainActivity) getActivity()).getSupportActionBar().setTitle("Search");
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}