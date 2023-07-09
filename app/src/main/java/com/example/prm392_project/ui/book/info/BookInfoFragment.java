package com.example.prm392_project.ui.book.info;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prm392_project.R;
import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.databinding.FragmentBookInfoBinding;
import com.google.gson.Gson;

public class BookInfoFragment extends Fragment {
    private FragmentBookInfoBinding binding;
    private BookInfoViewModel mViewModel;
    private Book bookInfo;
    private TextView txtBookTitle;
    private TextView txtAuthor;
    private TextView txtCategory;
    private TextView txtDes;

    public static BookInfoFragment newInstance() {
        return new BookInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentBookInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        txtBookTitle = root.findViewById(R.id.txtBookTitle);
        txtAuthor = root.findViewById(R.id.txtAuthor);
        txtCategory = root.findViewById(R.id.txtCategory);
        txtDes = root.findViewById(R.id.txtContent);

        Bundle args = getArguments();
        if (args != null) {
            String data = args.getString("bookInfo");
            bookInfo = new Gson().fromJson(data, Book.class);
        }
        getActivity().getActionBar().setTitle(bookInfo.getTitle());

        txtBookTitle.setText(bookInfo.getTitle());
        txtAuthor.setText(bookInfo.getAuthor());
        txtDes.setText(bookInfo.getContent());

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BookInfoViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}