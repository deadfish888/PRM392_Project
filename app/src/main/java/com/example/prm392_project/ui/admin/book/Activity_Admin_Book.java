package com.example.prm392_project.ui.admin.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_project.MainApplication;
import com.example.prm392_project.R;
import com.example.prm392_project.data.DTO.Book.BookCreateDTO;
import com.example.prm392_project.data.DTO.Book.BookUpdateDTO;
import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Admin_Book extends AppCompatActivity {

    private EditText txtId;
    private EditText txtTitle;
    private EditText txtAuthor;
    private EditText txtContent;
    private int categoryId;

    private Spinner spnCategory;
    private List<Category> categories;

    private ListView lvBook;
    private ArrayAdapter<Book> lvBookAdapter;

    private Callback<List<Book>> loadBooksCallback;
    private Callback<Book> updateBookCallback;
    private Callback<Void> deleteBookCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_book);

        this.findViews();
        this.setupViews();
    }

    private void findViews() {
        this.txtId = this.findViewById(R.id.activity_admin_book_txt_id);
        this.txtTitle = this.findViewById(R.id.activity_admin_book_txt_title);
        this.txtAuthor = this.findViewById(R.id.activity_admin_book_txt_author);
        this.txtContent = this.findViewById(R.id.activity_admin_book_txt_content);
        this.spnCategory = this.findViewById(R.id.activity_admin_book_spn_category);
        this.lvBook = this.findViewById(R.id.activity_admin_book_lv_book);
    }

    private void setupViews() {
        this.findViewById(R.id.activity_admin_book_btn_create).setOnClickListener(this::onClickCreate);
        this.findViewById(R.id.activity_admin_book_btn_update).setOnClickListener(this::onClickUpdate);
        this.findViewById(R.id.activity_admin_book_btn_delete).setOnClickListener(this::onClickDelete);
        this.findViewById(R.id.activity_admin_book_btn_search).setOnClickListener(this::onClickSearch);

        this.loadBooksCallback = new LoadBooksCallback();
        this.updateBookCallback = new UpdateBookCallback();
        this.deleteBookCallback = new DeleteBookCallback();

        MainApplication.categoryApiManager.GetAllCategories(new LoadCategoriesCallback());
    }

    private void onClickCreate(View _ignored) {
        Book book = this.getBook();
        MainApplication.bookApiManager.PostBook(BookCreateDTO.fromBook(book), this.updateBookCallback);
    }

    private void onClickUpdate(View _ignored) {
        Book book = this.getBook();
        MainApplication.bookApiManager.PutBook(book.getId(), BookUpdateDTO.fromBook(book), this.updateBookCallback);
    }

    private void onClickDelete(View _ignored) {
        Book book = this.getBook();
        MainApplication.bookApiManager.DeleteBook(book.getId(), this.deleteBookCallback);
    }

    private void onClickSearch(View _ignored) {

    }

    private Book getBook() {
        String id = this.txtId.getText().toString();
        if (id.isEmpty()) id = "0";
        return new Book(
                Integer.parseInt(id),
                this.txtTitle.getText().toString(),
                this.txtAuthor.getText().toString(),
                this.txtContent.getText().toString(),
                this.categoryId
        );
    }

    private int getCategoryIndex(Integer id) {
        if (id == null) return -1;
        for (int i = 0; i < this.categories.size(); ++i) {
            if (this.categories.get(i).getId() == id) return i;
        }
        return -1;
    }

    private void setCategory(Book book) {
        int categoryIndex = Activity_Admin_Book.this.getCategoryIndex(book.getCategoryId());
        if (categoryIndex == -1) return;
        book.setCategory(Activity_Admin_Book.this.categories.get(categoryIndex));
    }

    private class SpnCategoryOnItemSelected implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Category category = (Category) parent.getSelectedItem();
            Activity_Admin_Book.this.categoryId = category.getId();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Activity_Admin_Book.this.categoryId = 0;
        }
    }

    private class LoadBooksCallback implements Callback<List<Book>> {

        @Override
        public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
            if (!response.isSuccessful()) return;
            response.body().forEach(Activity_Admin_Book.this::setCategory);
            Activity_Admin_Book.this.lvBookAdapter.clear();
            Activity_Admin_Book.this.lvBookAdapter.addAll(response.body());
        }

        @Override
        public void onFailure(Call<List<Book>> call, Throwable t) {
        }
    }

    private class UpdateBookCallback implements Callback<Book> {

        @Override
        public void onResponse(Call<Book> call, Response<Book> response) {
            if (!response.isSuccessful()) return;
            MainApplication.bookApiManager.GetBooks(Activity_Admin_Book.this.loadBooksCallback);
        }

        @Override
        public void onFailure(Call<Book> call, Throwable t) {
        }
    }

    private class DeleteBookCallback implements Callback<Void> {

        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if (!response.isSuccessful()) return;
            MainApplication.bookApiManager.GetBooks(Activity_Admin_Book.this.loadBooksCallback);
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
        }
    }

    private class LoadCategoriesCallback implements Callback<List<Category>> {

        @Override
        public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
            if (!response.isSuccessful()) return;
            Activity_Admin_Book.this.categories = response.body();
            Activity_Admin_Book.this.spnCategory.setAdapter(new ArrayAdapter<>(
                    Activity_Admin_Book.this.getBaseContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    Activity_Admin_Book.this.categories)
            );
            Activity_Admin_Book.this.spnCategory.setOnItemSelectedListener(new SpnCategoryOnItemSelected());

            Activity_Admin_Book.this.lvBookAdapter = new LvBookAdapter();
            Activity_Admin_Book.this.lvBook.setAdapter(Activity_Admin_Book.this.lvBookAdapter);
            MainApplication.bookApiManager.GetBooks(Activity_Admin_Book.this.loadBooksCallback);
        }

        @Override
        public void onFailure(Call<List<Category>> call, Throwable t) {
        }
    }

    private class LvBookAdapter extends ArrayAdapter<Book> {

        public LvBookAdapter() {
            super(Activity_Admin_Book.this.getBaseContext(), R.layout.activity_admin_book_item_book);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_book_item_book, parent, false);
            }

            Book book = this.getItem(position);

            ((TextView) convertView.findViewById(R.id.activity_admin_book_lb_id)).setText(String.valueOf(book.getId()));
            ((TextView) convertView.findViewById(R.id.activity_admin_book_lb_title)).setText(book.getTitle());
            ((TextView) convertView.findViewById(R.id.activity_admin_book_lb_author)).setText(book.getAuthor());
            ((TextView) convertView.findViewById(R.id.activity_admin_book_lb_content)).setText(book.getContent());
            ((TextView) convertView.findViewById(R.id.activity_admin_book_lb_category_name)).setText(book.getCategory().getName());

            convertView.setOnClickListener((_ignored) -> {
                Activity_Admin_Book.this.txtId.setText(String.valueOf(book.getId()));
                Activity_Admin_Book.this.txtTitle.setText(book.getTitle());
                Activity_Admin_Book.this.txtAuthor.setText(book.getAuthor());
                Activity_Admin_Book.this.txtContent.setText(book.getContent());
                Activity_Admin_Book.this.spnCategory.setSelection(Activity_Admin_Book.this.getCategoryIndex(book.getCategoryId()));
            });

            return convertView;
        }
    }
}
