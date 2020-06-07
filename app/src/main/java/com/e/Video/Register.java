package com.e.Video;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    EditText edittextname, editTextmail, editTextpass, editTextnum;
    Button login;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        edittextname = findViewById(R.id.name);
        editTextmail = findViewById(R.id.emailid);
        editTextpass = findViewById(R.id.Passsword);
        editTextnum = findViewById(R.id.Mobile_number);
        login = findViewById(R.id.login_btn);
        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataValidity();
            }
        });
    }



    private void checkDataValidity() {
        if (validate()) {

            registerUser(editTextmail.getText().toString().trim(), editTextpass.getText().toString().trim());
        }

    }


    private boolean validate() {

        boolean flag = true;
        String password =  "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[$@$!%*#?&])[A-Za-z\\\\d$@$!%*#?&]{8,}$";
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (edittextname.getText().toString().trim().isEmpty()) {
            edittextname.setError("Write your name");
            Toast.makeText(this, " Please enter name", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        else if (editTextmail.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            editTextmail.setError(" Enter Email id ");
            flag = false;

        }
        else if (editTextpass.getText().toString().isEmpty()){
            editTextpass.setError("please enter password");
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
            flag=false;

        }
        else if (editTextnum.getText().toString().trim().isEmpty()) {
            editTextnum.setError("Enter mobile number");
            Toast.makeText(this, "Please enter Mobile number", Toast.LENGTH_SHORT).show();
            flag = false;

        }
        return flag;
    }

    private static boolean isValidPassword(final String Password){
        Pattern pattern;
        Matcher matcher;
        final String password =  "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[$@$!%*#?&])[A-Za-z\\\\d$@$!%*#?&]{8,}$";
        pattern = Pattern.compile(password);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }
    private void registerUser(String email, String password) {

        auth.createUserWithEmailAndPassword( email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "succesfully register", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_LONG).show();
                        }


                    }


                });
    }

}
