package com.example.prm392_project.ui.admin.adcategory;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.prm392_project.MainApplication;
import com.example.prm392_project.R;
import com.example.prm392_project.data.model.Category;
import com.example.prm392_project.databinding.AdmCateBinding;
import com.example.prm392_project.databinding.FragmentCategoryBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adCateFrag extends Fragment {

    public adCateFrag(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ad_cate, container, false);
        ListView lvCategory=view.findViewById(R.id.lv_cate_admin);
         List<Category> listCate = new ArrayList<>();
        CategoryAdapter cateAdap=new CategoryAdapter(getActivity().getApplicationContext(),listCate);
        lvCategory.setAdapter(cateAdap);
        MainApplication.categoryApiManager.GetAllCategories(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                listCate.addAll(response.body());
                cateAdap.setListCategory(listCate);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        Button btnAdd=view.findViewById(R.id.btnADD);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpFragment showPopUp=new PopUpFragment();
                showPopUp.show(requireActivity().getSupportFragmentManager(),"ShowPopUp");
            }
        });
    }
}