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
import com.group.mysa.R;

/**
 * @author jesusnieto
 */
public class SignInActivity extends AppCompatActivity {

    private Button signIn;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signIn = (Button) findViewById(R.id.sign_in_button);
        email = (EditText) findViewById(R.id.sign_in_email_textField);
        password = (EditText) findViewById(R.id.sign_in_password_textField);
        mAuth = FirebaseAuth.getInstance();  //creates instance to connect to database auth

        logInHandle(signIn);

    }

    /**
     * Sign in action when the user clicks on sign in button.
     * Calls sign in function when button is clicked.
     * @param button
     */
    private void logInHandle(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!email.getText().toString().equals("") && !password.getText().toString().equals("")){
                    signIn(email.getText().toString(), password.getText().toString());
                }

            }
        });

    }

    /**
     * Function signs in the user if the email is in the database
     * @param email
     * @param password
     */
    private void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            System.out.println("------------------------------------Logged in works");
                            // switch view
                        }else {
                            System.out.println("------------------------------------error");
                            // prompt some error sign
                        }
                    }
                });
    }



}
