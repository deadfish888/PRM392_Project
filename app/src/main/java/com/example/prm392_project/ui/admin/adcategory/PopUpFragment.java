package com.example.prm392_project.ui.admin.adcategory;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prm392_project.MainApplication;
import com.example.prm392_project.R;
import com.example.prm392_project.data.DTO.Category.CategoryRequestDTO;
import com.example.prm392_project.data.model.Category;
import com.example.prm392_project.ui.admin.AdminFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopUpFragment extends DialogFragment {


    public PopUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pop_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnSubmit = view.findViewById(R.id.btnSubmit);
        EditText edtName = view.findViewById(R.id.edtName);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edtName.getText().toString().trim();
                if (content.isEmpty()) {
                    return;
                }
                MainApplication.categoryApiManager.PostCategory(new CategoryRequestDTO(content),
                        new Callback<Category>() {
                            @Override
                            public void onResponse(Call<Category> call, Response<Category> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(view.getContext(), "New Category added!", Toast.LENGTH_SHORT).show();
                                    NavHostFragment.findNavController(PopUpFragment.this).navigate(R.id.action_adCateFrag_self);
                                    PopUpFragment.this.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<Category> call, Throwable t) {

                            }
                        });
            }
        });
    }
}