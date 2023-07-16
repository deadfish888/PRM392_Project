package com.example.prm392_project.ui.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.prm392_project.R;
import com.example.prm392_project.databinding.FragmentAdminBinding;
import com.example.prm392_project.databinding.FragmentHomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentAdminBinding binding;

    public AdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminFragment newInstance(String param1, String param2) {
        AdminFragment fragment = new AdminFragment();
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
        // Inflate the layout for this fragment
        binding = FragmentAdminBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        SetupEventClickButton(view);
        return view;
    }

    private void SetupEventClickButton(View view) {
        Button bookButton = view.findViewById(R.id.BookManager);
        Button categoryButton = view.findViewById(R.id.CartegoryManager);
        Button userButton = view.findViewById(R.id.UserManager);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickBookButton();
            }
        });

        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickCategoryButton();
            }
        });

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickUserButton();
            }
        });
    }

    private void OnClickBookButton() {
        Log.d("LogDm", "clickOnBook");
    }

    private void OnClickCategoryButton() {
        NavHostFragment.findNavController(AdminFragment.this).navigate(R.id.action_nav_admin_to_adCateFrag);
    }

    private void OnClickUserButton() {
        Log.d("LogDm", "clickOnUser");
    }

}