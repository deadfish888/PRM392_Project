package com.example.prm392_project.ui.book.info;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prm392_project.R;
import com.example.prm392_project.data.DTO.Comment.CommentCreateDTO;
import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.model.Comment;
import com.example.prm392_project.data.repository.CommentRepository;
import com.example.prm392_project.databinding.FragmentBookInfoBinding;
import com.example.prm392_project.ui.MainActivity;
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
    private EditText edtComment;
    private Button btnPostComment;
    private Observer<Comment> postCommentObserver;
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
        edtComment = root.findViewById(R.id.edtComment);
        btnPostComment = root.findViewById(R.id.btnPostComment);

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
        LifecycleOwner owner = getViewLifecycleOwner();
        bookInfoViewModel.getAllComments(bookInfo.getId()).observe(owner, cmts -> {
            Collections.reverse(cmts);
            commentAdapter.setItems(cmts);
            progress.hide();
        });

        bookInfoViewModel.getCategoryById(bookInfo.getCategoryId()).observe(owner, cate -> {
            txtCategory.setText(cate.getName());
        });

        postCommentObserver = comment -> {
            if (comment != null) {
                commentAdapter.addItem(comment);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView
                        .getLayoutManager();
                layoutManager.scrollToPositionWithOffset(0, 0);
            }
        };
        btnPostComment.setOnClickListener(v -> {

            if (!edtComment.getText().toString().isEmpty()){
                hideSoftKeyboard(getActivity(), v);
                CommentCreateDTO cmt = new CommentCreateDTO(edtComment.getText().toString(), bookInfo.getId(), MainActivity.username);
                edtComment.setText("");
                //bookInfoViewModel.postComment(cmt).removeObservers(owner);
                bookInfoViewModel.postComment(cmt).observe(owner, postCommentObserver);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void hideSoftKeyboard (Activity activity, View view)
    {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }
}