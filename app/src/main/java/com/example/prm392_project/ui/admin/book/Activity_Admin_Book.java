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

import com.example.prm392_project.R;
import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.model.Category;

import java.util.ArrayList;
import java.util.List;

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

        this.categories = new ArrayList<>();
        this.categories.add(new Category(1, "A"));
        this.categories.add(new Category(2, "B"));
        this.categories.add(new Category(3, "C"));
        this.categories.add(new Category(4, "D"));
        this.categories.add(new Category(5, "E"));
        this.spnCategory.setAdapter(new ArrayAdapter<>(this.getBaseContext(), android.R.layout.simple_spinner_dropdown_item, this.categories));
        this.spnCategory.setOnItemSelectedListener(new SpnCategoryOnItemSelected());

        this.lvBookAdapter = new LvBookAdapter();
        this.lvBookAdapter.add(new Book()
                .id(1)
                .title("asdfadsfasdfasdf")
                .author("Asdfasdfjkhasdflhkjasd")
                .content("asdfjlkhadfslhfdashjkdfaslhfdajlhkdfashjhjdkas")
                .categoryId(1)
                .category(new Category(1, "A"))
        );
        this.lvBookAdapter.add(new Book().id(2).categoryId(2).category(new Category(2, "B")));
        this.lvBook.setAdapter(this.lvBookAdapter);
    }

    private void onClickCreate(View _ignored) {

    }

    private void onClickUpdate(View _ignored) {

    }

    private void onClickDelete(View _ignored) {

    }

    private void onClickSearch(View _ignored) {

    }

    private Book getBook() {
        return new Book(
                Integer.parseInt(this.txtId.getText().toString()),
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
