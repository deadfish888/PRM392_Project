package com.example.prm392_project.ui.book.info;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prm392_project.R;
import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.repository.CommentRepository;
import com.example.prm392_project.databinding.FragmentBookInfoBinding;
import com.example.prm392_project.ui.common.OnItemClickListener;
import com.example.prm392_project.ui.home.HomeViewModelFactory;
import com.google.gson.Gson;

import java.util.Collections;

public class BookInfoFragment extends Fragment {
    private FragmentBookInfoBinding binding;
    private CommentAdapter commentAdapter;
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
        BookInfoViewModel bookInfoViewModel= new ViewModelProvider(this, new BookInfoViewModelFactory()).get(BookInfoViewModel.class);

        binding = FragmentBookInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ContentLoadingProgressBar progress = root.findViewById(R.id.info_progress);
        RecyclerView recyclerView = root.findViewById(R.id.comment_recycler_view);
        OnItemClickListener onItemClickListener = (view, cmt) -> {
        };

        txtBookTitle = root.findViewById(R.id.txtBookTitle);
        txtAuthor = root.findViewById(R.id.txtAuthor);
        txtCategory = root.findViewById(R.id.txtCategory);
        txtDes = root.findViewById(R.id.txtContent);

        Bundle args = getArguments();
        if (args != null) {
            String data = args.getString("bookInfo");
            bookInfo = new Gson().fromJson(data, Book.class);
        }
        getActivity().setTitle(bookInfo.getTitle());

        txtBookTitle.setText(bookInfo.getTitle());
        txtAuthor.setText(bookInfo.getAuthor());
        txtDes.setText(bookInfo.getContent());

        commentAdapter = new CommentAdapter(root.getContext(), onItemClickListener);
        recyclerView.setAdapter(commentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        progress.show();
        bookInfoViewModel.getAllComments(bookInfo.getId()).observe(getViewLifecycleOwner(), cmts -> {
            Collections.reverse(cmts);
            commentAdapter.setItems(cmts);
            progress.hide();
        });
        bookInfoViewModel.getCategoryById(bookInfo.getCategoryId()).observe(getViewLifecycleOwner(), cate -> {
            txtCategory.setText(cate.getName());
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}