package com.example.prm392_project.ui.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.*;
import android.widget.Toast;

import com.example.prm392_project.MainApplication;
import com.example.prm392_project.R;
import com.example.prm392_project.data.DTO.User.UserUpdateDTO;
import com.example.prm392_project.data.model.UserInfo;
import com.example.prm392_project.data.remote.APIManager.UserApiManager;
import com.example.prm392_project.data.repository.UserRepository;
import com.example.prm392_project.databinding.FragmentAccountBinding;
import com.example.prm392_project.databinding.FragmentAdminBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FragmentAccountBinding binding;
    public static SharedPreferences sharedpreferences;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private UserApiManager userApiManager;

    private EditText phoneText;
    private int userId;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Account.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        this.phoneText = view.findViewById(R.id.editTextText);
        SetupData(view);
        SetupEventClickButton(view);
        return view;
    }

    private void SetupData(View view){
        sharedpreferences = getActivity().getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        MainApplication.userApiManager.getUserByUsername(sharedpreferences.getString("username_key", null), new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if(response.isSuccessful()){
                    userId = response.body().getId();
                    TextView userNameText= (TextView)view.findViewById(R.id.textView3);
                    userNameText.setText(response.body().getUsername());
                    EditText userPhoneText = (EditText)view.findViewById(R.id.editTextText);
                    userPhoneText.setText(response.body().getPhoneNumber());
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });


    }

    private void SetupEventClickButton(View view) {
        Button saveButton = view.findViewById(R.id.button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveAccountData();
            }
        });
    }

    private void SaveAccountData(){

        MainApplication.userApiManager.UpdateUserPhone(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(),"Save account successfully",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Toast.makeText(getContext(),"Save account successfully",Toast.LENGTH_LONG).show();
            }
        },new UserUpdateDTO(userId,this.phoneText.getText().toString()));


    }
}