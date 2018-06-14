package com.group.mysa.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.group.mysa.R;

import org.w3c.dom.Text;

/**
 * @author jesusnieto
 */
public class SignInActivity extends AppCompatActivity {

    private Button signIn;
    private Button signIn_signUp;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;
    private TextView signInErrorMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signIn = (Button) findViewById(R.id.sign_in_button);
        signIn_signUp = (Button) findViewById(R.id.sign_in_signUp_button);
        email = (EditText) findViewById(R.id.sign_in_email_textField);
        password = (EditText) findViewById(R.id.sign_in_password_textField);
        signInErrorMessage = (TextView) findViewById(R.id.sign_up_error_message);
        mAuth = FirebaseAuth.getInstance();  //creates instance to connect to database auth

        logInHandle(signIn);
        changeToSignUpView(signIn_signUp);

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
                            Intent startIntent = new Intent(SignInActivity.this, MainAppHomeActivity.class);
                            startActivity(startIntent);
                            finish();

                            // switch view
                        }else {
                            System.out.println("INCORRECT EMAIL AND PASSWORD!");
                            signInErrorMessage.setVisibility(1);
                        }
                    }
                });
    }


    /**
     * Functions takes user to sign up view to create an account.
     * @param button
     */
    private void changeToSignUpView(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("------------Sign Up button clicked");
                Intent startIntent = new Intent(SignInActivity.this, SignUp.class);
                startActivity(startIntent);

            }
        });

    }



}
