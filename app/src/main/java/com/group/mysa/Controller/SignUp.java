package com.group.mysa.Controller;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group.mysa.Database.Database;
import com.group.mysa.R;

public class SignUp extends AppCompatActivity {
    private Button createAccount;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        createAccount = (Button) findViewById(R.id.sign_up_createAccount);
        firstName = (EditText) findViewById(R.id.sign_up_firstName);
        lastName = (EditText) findViewById(R.id.sign_up_lastName);
        email = (EditText) findViewById(R.id.sign_up_emailTextField);
        password = (EditText) findViewById(R.id.sign_up_passwordTextField);
        confirmPassword = (EditText) findViewById(R.id.sign_up_confirmPasswordTextField);
        mAuth = FirebaseAuth.getInstance();

        signUpHandle(createAccount);



    }


    /**
     *
     * @param button
     */
    private void signUpHandle(Button button){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!firstName.getText().toString().equals("") &&
                        !lastName.getText().toString().equals("") &&
                        !email.getText().toString().equals("") &&
                        !password.getText().toString().equals("") &&
                        !confirmPassword.getText().toString().equals("")){

                    if(password.getText().toString().equals(confirmPassword.getText().toString())){
                        createAccount();
                    }else{
                        System.out.println("-------------Passwords do not match");

                    }

                }else{
                    System.out.println("-------------one of the fields was empty");
                }

            }
        });


    }


    private void createAccount(){
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    System.out.println("-----------------------Succesful");
                    currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String uID = currentUser.getUid();
                    Database.storeUserInfoInDatabase(email.getText().toString(), password.getText().toString(), confirmPassword.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), uID);

                    //goes to the main app view here

                }else {
                    System.out.println("-----------------------User exists already");
                    //prompts an error message
                }

            }
        });


    }


}
