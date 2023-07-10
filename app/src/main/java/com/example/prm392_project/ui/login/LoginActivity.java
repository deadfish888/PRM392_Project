package com.example.prm392_project.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prm392_project.MainApplication;
import com.example.prm392_project.R;
import com.example.prm392_project.data.Result;
import com.example.prm392_project.data.model.UserLoggedIn;
import com.example.prm392_project.ui.MainActivity;
import com.example.prm392_project.databinding.ActivityLoginBinding;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    public static final String USERNAME_KEY = "username_key";
    public static final String TOKEN = "token";
    public static final String TIME = "time";

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    String username, token;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        // getting the data which is stored in shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        username = sharedpreferences.getString(USERNAME_KEY, null);
        token = sharedpreferences.getString(TOKEN, null);
        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                    Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainActivity);
                    setResult(Activity.RESULT_OK);
                    finish();
                }
                //Complete and destroy login activity once successful
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString()).observe(LoginActivity.this, resul -> {
                        Result<UserLoggedIn> result;
                        if (resul instanceof Result.Success) {
                            UserLoggedIn data = ((Result.Success<UserLoggedIn>) resul).getData();
                            // Login successful
                            result =new Result.Success<>(data);

                            MainApplication.TOKEN = data.getToken();
                            MainApplication.setUpManager();

                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(USERNAME_KEY, usernameEditText.getText().toString());
                            editor.putString(TOKEN, data.getToken());
                            editor.putLong(TIME, new Date().getTime());

                            // to save our data with key and value.
                            editor.apply();
                        } else {
                            // Login error
                            result = new Result.Error(new IOException("Login failed"));
                        }
                        if (result instanceof Result.Success) {
                            UserLoggedIn data = ((Result.Success<UserLoggedIn>) result).getData();
                            loginViewModel.setLoginResult(new LoginResult(data));
                        } else {
                            loginViewModel.setLoginResult(new LoginResult(R.string.login_failed));
                        }
                    });
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString()).observe(LoginActivity.this, resul -> {
                            Result<UserLoggedIn> result;
                            if (resul instanceof Result.Success) {
                                UserLoggedIn data = ((Result.Success<UserLoggedIn>) resul).getData();
                                // Login successful
                                result =new Result.Success<>(data);

                                MainApplication.TOKEN = data.getToken();
                                MainApplication.setUpManager();

                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString(USERNAME_KEY, usernameEditText.getText().toString());
                                editor.putString(TOKEN, data.getToken());
                                editor.putLong(TIME, new Date().getTime());
                                // to save our data with key and value.
                                editor.apply();
                            } else {
                                // Login error
                                result = new Result.Error(new IOException("Login failed"));
                            }
                            if (result instanceof Result.Success) {
                                UserLoggedIn data = ((Result.Success<UserLoggedIn>) result).getData();
                                loginViewModel.setLoginResult(new LoginResult(data));
                            } else {
                                loginViewModel.setLoginResult(new LoginResult(R.string.login_failed));
                            }
                        });
            }
        });
    }

    private void updateUiWithUser(UserLoggedIn model) {
        String welcome = getString(R.string.welcome) + " "+ model.getUsername();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (username != null && token != null) {
            Date setTime = new Date(sharedpreferences.getLong(TIME,0));
            Date now = new Date();
            long diff = TimeUnit.MINUTES.convert(now.getTime() - setTime.getTime(), TimeUnit.MILLISECONDS);

            if ( diff> 180) {
                return;
            }
            MainApplication.TOKEN = token;
            MainApplication.setUpManager();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
    }
}