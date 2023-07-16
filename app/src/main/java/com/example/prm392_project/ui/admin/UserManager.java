package com.example.prm392_project.ui.admin;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prm392_project.R;
import com.example.prm392_project.data.DTO.Auth.RegisterDTO;
import com.example.prm392_project.data.model.UserInfo;

public class UserManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
        UserViewModel userViewModel = new ViewModelProvider(this, new UserViewModelFactory()).get(UserViewModel.class);

        UserAdapter userAdapter = new UserAdapter();
        userViewModel.getUsers().observe(UserManager.this, users -> {
            userAdapter.setListUser(users);
            RecyclerView rec = findViewById(R.id.result);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            rec.setLayoutManager(layoutManager);
            rec.setAdapter(userAdapter);
        });

        Button btnAdd = findViewById(R.id.ADD);
        Button btnUpdate = findViewById(R.id.UPDATE);
        Button btnDelete = findViewById(R.id.DELETE);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // connect to db bla bla
                try{
                    String username = ((EditText)findViewById(R.id.edtName)).getText().toString();
                    String credentialCode = String.valueOf(((EditText)findViewById(R.id.edtCredentialCode)).getText());
                    String phoneNumber = String.valueOf(((EditText)findViewById(R.id.edtPhone)).getText());
                    if (username.isEmpty() || credentialCode.isEmpty() || phoneNumber.isEmpty()){
                        Toast.makeText(getApplicationContext(), "empty parameters", Toast.LENGTH_LONG).show();
                    } else{
                        userViewModel.registerUser(new RegisterDTO(username, "default123" ,phoneNumber, credentialCode));
                        Toast.makeText(getApplicationContext(), "add successfully", Toast.LENGTH_LONG).show();
                        UserAdapter userAdapter = new UserAdapter();
                        userViewModel.getUsers().observe(UserManager.this, users -> {
                            userAdapter.setListUser(users);
                            RecyclerView rec = findViewById(R.id.result);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            rec.setLayoutManager(layoutManager);
                            rec.setAdapter(userAdapter);
                        });
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "empty parameters", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // connect to db bla bla
                try {
                    int id = Integer.parseInt((String.valueOf(((EditText)findViewById(R.id.edtId)).getText())));
                    String username = ((EditText)findViewById(R.id.edtName)).getText().toString();
                    String credentialCode = String.valueOf(((EditText)findViewById(R.id.edtCredentialCode)).getText());
                    String phonenummber = String.valueOf(((EditText)findViewById(R.id.edtPhone)).getText());
                    if (username.isEmpty() || credentialCode.isEmpty() || phonenummber.isEmpty()){
                        Toast.makeText(getApplicationContext(), "empty parameters", Toast.LENGTH_LONG).show();
                    } else{
                        userViewModel.updateUser(new UserInfo(id, username, phonenummber, credentialCode));
                        Toast.makeText(getApplicationContext(), "update successfully", Toast.LENGTH_LONG).show();
                        UserAdapter userAdapter = new UserAdapter();
                        userViewModel.getUsers().observe(UserManager.this, users -> {
                            userAdapter.setListUser(users);
                            RecyclerView rec = findViewById(R.id.result);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            rec.setLayoutManager(layoutManager);
                            rec.setAdapter(userAdapter);
                        });
                    }
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // connect to db bla bla
                try{
                    int id = Integer.parseInt((String.valueOf(((EditText)findViewById(R.id.edtId)).getText())));
                    userViewModel.deleteUser(id);

                    Toast.makeText(getApplicationContext(), "delete successfully", Toast.LENGTH_LONG).show();
                    UserAdapter userAdapter = new UserAdapter();
                    userViewModel.getUsers().observe(UserManager.this, users -> {
                        userAdapter.setListUser(users);
                        RecyclerView rec = findViewById(R.id.result);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        rec.setLayoutManager(layoutManager);
                        rec.setAdapter(userAdapter);
                    });
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}