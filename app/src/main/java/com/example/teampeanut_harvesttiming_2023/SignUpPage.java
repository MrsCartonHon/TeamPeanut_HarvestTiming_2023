package com.example.teampeanut_harvesttiming_2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teampeanut_harvesttiming_2023.ui.login.JDSignIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class SignUpPage extends AppCompatActivity {
    private Button backLogin;
    Button btn2_signup;
    EditText user_name, pass_word, confirm_pass, firstName;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        user_name=findViewById(R.id.emailInput);
        pass_word=findViewById(R.id.passwordInput);
        confirm_pass = findViewById(R.id.confrimPassword);
        btn2_signup=findViewById(R.id.signUp);
        firstName = findViewById(R.id.firstNameInput);
        mAuth= FirebaseAuth.getInstance();

        btn2_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user_name.getText().toString().trim();
                String password= pass_word.getText().toString().trim();
                String confirm = confirm_pass.getText().toString().trim();
                String name = firstName.getText().toString().trim();
                if(email.isEmpty())
                {
                    user_name.setError("Email is empty");
                    user_name.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    user_name.setError("Enter the valid email address");
                    user_name.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    pass_word.setError("Enter the password");
                    pass_word.requestFocus();
                    return;
                }
                if(password.length()<6)
                {
                    pass_word.setError("Length of the password should be more than 6");
                    pass_word.requestFocus();
                    return;
                }
                if(!confirm.equals(password))
                {
                    pass_word.setError("Passwords are not the same");
                    confirm_pass.setError("Passwords are not the same");
                    pass_word.requestFocus();
                    confirm_pass.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(SignUpPage.this,"You are successfully Registered", Toast.LENGTH_SHORT).show();
                            Intent toLoadingIntent = new Intent(SignUpPage.this, StartSelect.class);

                            startActivity(toLoadingIntent);

                        }
                        else
                        {
                            Toast.makeText(SignUpPage.this,"You are not Registered! Try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });


        backLogin = (Button)findViewById(R.id.backToLogin);
        backLogin.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), JDSignIn.class));
           /* if you want to finish the first activity then just call
            finish(); */

            }
        });
    }
}