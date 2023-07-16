package com.example.prm392_project.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.R;
import com.example.prm392_project.ui.common.OnItemClickListener;
import com.example.prm392_project.data.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {
    @NonNull
    private final Context context;
    private List<Book> books;
    private List<Book> filteredData;
    private final OnItemClickListener onBookClickListener;
    public BooksAdapter(@NonNull Context context, OnItemClickListener onBookClickListener) {
        this.context = context;
        this.onBookClickListener = onBookClickListener;
    }
    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(context).inflate(R.layout.book_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.setBookItem(filteredData.get(position));
    }
    @Override
    public int getItemCount() {
        return filteredData == null ? 0 : filteredData.size();
    }
    public void setBooks(List<Book> books) {
        this.books = books;
        this.filteredData = books;
        this.notifyDataSetChanged();
    }

    public void getFilter(String query) {
        filteredData.clear();
        if (query.isEmpty()) {
            filteredData.addAll(books);
        } else {
            query = query.toLowerCase();
            for (Book book : books) {
                if (book.getTitle().toLowerCase().contains(query)) {
                    filteredData.add(book);
                }
            }
        }
        notifyDataSetChanged();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        private final TextView book_title;
        private final TextView book_author;
        private final View bookItem;
        BookViewHolder(View bookItem) {
            super(bookItem);
            book_title = bookItem.findViewById(R.id.book_title);
            book_author = bookItem.findViewById(R.id.book_author);
            this.bookItem = bookItem;
        }
        private void setBookItem(Book book){
            book_title.setText(book.getTitle());
            book_author.setText(book.getAuthor());
            bookItem.setOnClickListener(view -> onBookClickListener.onItemClicked(view, book));
        }
    }
}
