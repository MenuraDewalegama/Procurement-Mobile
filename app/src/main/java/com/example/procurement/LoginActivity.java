package com.example.procurement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username_input, password_input;
    Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_input = findViewById(R.id.username_input);
        password_input = findViewById(R.id.password_input);

        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (username_input.getText().toString().equals("") && password_input.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "Please fill user name and password field", Toast.LENGTH_SHORT).show();
                }
                else {
                    String userName = username_input.getText().toString();
                    String password = password_input.getText().toString();

                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    intent.putExtra("userName", userName);
                    intent.putExtra("password", password);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
