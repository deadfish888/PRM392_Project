package com.example.prm392_project.ui.admin.book;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_project.R;
import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.model.Category;

public class Activity_Admin_Book extends AppCompatActivity {

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
    }

    private void setupViews() {
        this.findViewById(R.id.activity_admin_book_btn_create).setOnClickListener(this::onClickCreate);
        this.findViewById(R.id.activity_admin_book_btn_update).setOnClickListener(this::onClickUpdate);
        this.findViewById(R.id.activity_admin_book_btn_delete).setOnClickListener(this::onClickDelete);
        this.findViewById(R.id.activity_admin_book_btn_search).setOnClickListener(this::onClickSearch);

        Spinner spnCategory = this.findViewById(R.id.activity_admin_book_spn_category);
        ArrayAdapter<Category> adapter = new  ArrayAdapter<>(this.getBaseContext(), android.R.layout.simple_spinner_dropdown_item);
        adapter.add(new Category(1, "A"));
        adapter.add(new Category(2, "B"));
        adapter.add(new Category(3, "C"));
        adapter.add(new Category(4, "D"));
        adapter.add(new Category(5, "E"));
        spnCategory.setAdapter(adapter);
        spnCategory.setOnItemSelectedListener(new SpnCategoryOnItemSelected(this));
    }

    private void onClickCreate(View _ignored) {

    }

    private void onClickUpdate(View _ignored) {

    }

    private void onClickDelete(View _ignored) {

    }

    private void onClickSearch(View _ignored) {

    }

    private class SpnCategoryOnItemSelected implements AdapterView.OnItemSelectedListener {

        private final Activity_Admin_Book container;

        private SpnCategoryOnItemSelected(Activity_Admin_Book container) {
            this.container = container;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Category category = (Category) parent.getSelectedItem();
            this.container.categoryId = category.getId();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            this.container.categoryId = 0;
        }
    }

    private EditText txtId;
    private EditText txtTitle;
    private EditText txtAuthor;
    private EditText txtContent;
    private int categoryId;

    private Book getBook() {
        return new Book(
                Integer.parseInt(this.txtId.getText().toString()),
                this.txtTitle.getText().toString(),
                this.txtAuthor.getText().toString(),
                this.txtContent.getText().toString(),
                this.categoryId
        );
    }
}
