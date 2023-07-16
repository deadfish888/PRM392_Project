package com.example.prm392_project.ui.admin.adcategory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.example.prm392_project.MainApplication;
import com.example.prm392_project.R;
import com.example.prm392_project.data.DTO.Category.CategoryRequestDTO;
import com.example.prm392_project.data.model.Category;
import com.example.prm392_project.ui.common.OnItemClickListener;
import com.example.prm392_project.ui.home.HomeFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryAdapter extends BaseAdapter {


    private Context context;
    private List<Category> items;

    public CategoryAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return items==null ? 0: items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adm_cate,parent,false);
        View resultView=inflater.inflate(R.layout.fragment_ad_cate,parent,false);
        EditText edtCate=rowView.findViewById(R.id.edt_ad_name);
        edtCate.setText(items.get(position).getName());
        Button btnEdit=rowView.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName=edtCate.getText().toString();
                MainApplication.categoryApiManager.PutCategory(items.get(position).getId(),
                        new CategoryRequestDTO(newName),
                        new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful()) {
                                    editCate(position, newName);
                                    Toast.makeText(context, "Edit successfully", Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Log.i("Error","Error"+t.getMessage());
                            }
                        });
            }
        });
        Button btnDelete =rowView.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainApplication.categoryApiManager.DeleteCategory(items.get(position).getId(),
                        new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(context,"Delete successfully",Toast.LENGTH_LONG).show();
                                    removeCategory(position);
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
            }
        });


        return rowView;
    }

    public void setListCategory(List<Category> listCate){
        this.items=listCate;
        notifyDataSetChanged();
    }

    public void removeCategory(int position){
        items.remove(position);
        notifyDataSetChanged();
    }

    public void editCate( int position, String name){
        items.get(position).setName(name);
        notifyDataSetChanged();
    }

}
