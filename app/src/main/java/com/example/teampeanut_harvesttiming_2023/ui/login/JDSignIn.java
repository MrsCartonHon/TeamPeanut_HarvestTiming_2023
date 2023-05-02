package com.example.teampeanut_harvesttiming_2023.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teampeanut_harvesttiming_2023.R;
import com.example.teampeanut_harvesttiming_2023.SignUpPage;
import com.example.teampeanut_harvesttiming_2023.StartSelect;
import com.example.teampeanut_harvesttiming_2023.WeatherActivity;
import com.example.teampeanut_harvesttiming_2023.databinding.ActivetyStartselectBinding;
import com.example.teampeanut_harvesttiming_2023.databinding.ActivityJdsignInBinding;
import com.example.teampeanut_harvesttiming_2023.inputdatastart;
import com.google.firebase.auth.FirebaseAuth;

public class JDSignIn extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityJdsignInBinding binding;
    private Button toSignin;
    private Button toWeather;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityJdsignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        toSignin = (Button)findViewById(R.id.signinTOsignup);
        toSignin.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                    startActivity(new Intent(getApplicationContext(),SignUpPage.class));
           /* if you want to finish the first activity then just call
            finish(); */

        }
        });

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
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
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
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondActivityIntent = new Intent(JDSignIn.this, StartSelect.class);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                loadingProgressBar.setVisibility(View.VISIBLE);
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(task->{
                    if(task.isSuccessful())
                    {
                        Log.i("firebase auth", "logged in");
                        startActivity(secondActivityIntent);
                    }
                    else
                    {
                        Log.e("firebase auth", task.getException().toString());
                        Toast.makeText(getApplicationContext(), "Wrong E-mail or password, try again", Toast.LENGTH_SHORT).show();
                    }
                });
            }







        });
    }



    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}