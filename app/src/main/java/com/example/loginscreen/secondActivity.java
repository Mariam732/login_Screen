package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class secondActivity extends AppCompatActivity {
    TextView nameTv , emailTv , passwordTv , usernameTv , repeatPasswordTv;
    EditText nameEt , emailEt , passwordEt , usernameEt, repeatPasswordEt;
    Button signUp, signIn;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inflate the views
        setContentView(R.layout.activity_second);
        nameTv = findViewById(R.id.full_name_tv);
        emailTv = findViewById(R.id.email_tv);
        passwordTv = findViewById(R.id.password_tv);
        usernameTv = findViewById(R.id.username_tv);
        repeatPasswordTv = findViewById(R.id.repeat_password_tv);
        nameEt = findViewById(R.id.name_et);
        emailEt = findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);
        repeatPasswordEt = findViewById(R.id.repeat_password_et);
        usernameEt = findViewById(R.id.username_et);
        signUp = findViewById(R.id.login_btn);
        signIn = findViewById(R.id.sign_in_btn);

   signUp.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           validateEmail();
           validatePassword();
           validateUsername();
           validateRepeatPassword();
           if (!validateEmail() | !validateUsername() | !validatePassword()| !validateRepeatPassword()) {
               return;
           }

           String input = "Email: " + emailEt.getText().toString();
           input += "\n";
           input += "Username: " + usernameEt.getText().toString();
           input += "\n";
           input += "Password: " + passwordEt.getText().toString();
           Toast.makeText(secondActivity.this, input, Toast.LENGTH_SHORT).show();
           
       }
   });

    }
    
    private  boolean validateEmail() {
        String inputEmail = emailEt.getText().toString();
        if (inputEmail.isEmpty()) {
            emailEt.setError("field cant be empty");
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()){
            emailEt.setError("please enter availd email address");
            return false;
        }
        else {
            emailEt.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String passwordInput = passwordEt.getText().toString().trim();
         counter=0;
        if (passwordInput.isEmpty()) {
            passwordEt.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            passwordEt.setError("Password too weak");
            return false;
        } else {
            passwordEt.setError(null);
            return true;
        }

    }
    private boolean validateUsername() {
        String usernameInput = usernameEt.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            usernameEt.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            usernameEt.setError("Username too long");
            return false;
        } else {
            usernameEt.setError(null);
            return true;
        }
    }
    private boolean validateRepeatPassword() {
        String passwordInput = repeatPasswordEt.toString().trim();

        if (passwordInput.isEmpty()) {
            repeatPasswordEt.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            repeatPasswordEt.setError("Password too weak");
            return false;
        } else {
            repeatPasswordEt.setError(null);
            return true;
        }
    }
   
}