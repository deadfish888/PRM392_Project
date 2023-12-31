package com.example.prm392_project.ui.book.info;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prm392_project.MainApplication;
import com.example.prm392_project.R;
import com.example.prm392_project.data.DTO.Comment.CommentCreateDTO;
import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.model.Comment;
import com.example.prm392_project.databinding.FragmentBookInfoBinding;
import com.example.prm392_project.ui.MainActivity;
import com.example.prm392_project.ui.common.OnItemClickListener;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookInfoFragment extends Fragment {
    private FragmentBookInfoBinding binding;
    private View root;
    private CommentAdapter commentAdapter;
    private BookInfoViewModel bookInfoViewModel;
    private List<Comment> currentComments;
    ContentLoadingProgressBar progress;
    private RecyclerView recyclerView;
    private Book bookInfo;
    Timer timer;
    LifecycleOwner owner;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        bookInfoViewModel = new ViewModelProvider(this, new BookInfoViewModelFactory()).get(BookInfoViewModel.class);

        binding = FragmentBookInfoBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        owner = getViewLifecycleOwner();
        Bundle args = getArguments();
        if (args != null) {
            String data = args.getString("bookInfo");
            bookInfo = new Gson().fromJson(data, Book.class);
        }
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(bookInfo != null ? bookInfo.getTitle() : "");

        setUpItemView();
        setUpBookInfo();
        OnBtnViewPDFClicked();
        setUpCommentsSection();

        return root;
    }

    private void setUpItemView() {
        progress = root.findViewById(R.id.info_progress);
        recyclerView = root.findViewById(R.id.comment_recycler_view);
    }

    @SuppressLint("SetTextI18n")
    private void setUpBookInfo() {
        TextView txtBookTitle = root.findViewById(R.id.txtBookTitle);
        TextView txtAuthor = root.findViewById(R.id.txtAuthor);
        TextView txtCategory = root.findViewById(R.id.txtCategory);
        txtBookTitle.setText(bookInfo.getTitle());
        txtAuthor.setText("Author: " + bookInfo.getAuthor());
        bookInfoViewModel.getCategoryById(bookInfo.getCategoryId()).observe(owner, cate -> txtCategory.setText(cate.getName()));
    }

    private void setUpCommentsSection() {
        this.setUpAdapter();
        bookInfoViewModel.getAllComments(bookInfo.getId()).observe(owner, comments -> {
            currentComments = comments;
            Collections.reverse(comments);
            commentAdapter.setItems(comments);
            progress.hide();
            this.updateMessages();
        });
        this.OnBtnPostClicked();
    }

    private void setUpAdapter() {
        OnItemClickListener onItemClickListener = (view, cmt) -> {
        };
        commentAdapter = new CommentAdapter(binding.getRoot().getContext(), onItemClickListener);
        recyclerView.setAdapter(commentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        progress.show();
    }


    private void OnBtnViewPDFClicked() {
        Button btnViewPDF = root.findViewById(R.id.btnViewPDF);
        btnViewPDF.setOnClickListener(v -> {
            Intent webIntent = new Intent(getContext(), PDFViewActivity.class);
            webIntent.putExtra("pdf_url", bookInfo.getContent());
            webIntent.putExtra("book_title", bookInfo.getTitle());
            startActivity(webIntent);
        });
    }

    private void OnBtnPostClicked() {
        EditText edtComment = root.findViewById(R.id.edtComment);
        Button btnPostComment = root.findViewById(R.id.btnPostComment);
        Observer<Comment> postCommentObserver = comment -> {
            if (comment == null) {
                return;
            }
            recyclerView.scrollToPosition(0);
        };

        btnPostComment.setOnClickListener(v -> {
            if (edtComment.getText().toString().trim().isEmpty()) {
                return;
            }
            hideSoftKeyboard(getActivity(), v);
            CommentCreateDTO cmt = new CommentCreateDTO(edtComment.getText().toString(), bookInfo.getId(), MainActivity.username);
            edtComment.setText("");
            bookInfoViewModel.postComment(cmt).observe(owner, postCommentObserver);
        });
    }

    void updateMessages() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                MainApplication.commentApiManager.getAllCommentsByBookId(bookInfo.getId(), new Callback<List<Comment>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Comment>> call, @NonNull Response<List<Comment>> response) {
                        if (!response.isSuccessful()) {
                            return;
                        }
                        List<Comment> newComments = response.body();
                        int currentCommentCount = currentComments.size();
                        if (newComments == null || currentCommentCount > newComments.size()) {
                            return;
                        }
                        currentComments.addAll(newComments.subList(currentCommentCount, newComments.size()));
                        Collections.reverse(currentComments);
                        commentAdapter.notifyItemRangeInserted(0, newComments.size() - currentCommentCount);
                        recyclerView.scrollToPosition(0);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Comment>> call, @NonNull Throwable t) {

                    }
                });
            }
        };
        // Schedule the TimerTask to run every X milliseconds
        long interval = 1000; // 5 seconds (adjust this value as needed)
        timer.scheduleAtFixedRate(timerTask, 0, interval);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void hideSoftKeyboard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }
}