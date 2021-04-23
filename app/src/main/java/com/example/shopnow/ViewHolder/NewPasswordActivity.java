package com.example.shopnow.ViewHolder;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.shopnow.LoginActivity;
import com.example.shopnow.Prevalent.Prevalent;
import com.example.shopnow.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class NewPasswordActivity extends AppCompatActivity {

    private EditText password1, password2;
    private Button changePasswordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        password1=findViewById(R.id.new_password);
        password2=findViewById(R.id.reenter_password);
        changePasswordBtn=findViewById(R.id.change_password_btn);

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p1,p2;
                p1=password1.getText().toString();
                p2=password2.getText().toString();

                if(p1.isEmpty())
                {
                    password1.setError("Password is required");
                    password1.requestFocus();
                    return;
                }

                if(! p1.equals(p2))
                {
                    password2.setError("Passwords do not match");
                    password2.requestFocus();
                    return;
                }
                else
                {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
                    HashMap<String, Object> userMap = new HashMap<>();
                    userMap. put("password", p1);
                    ref.child(Prevalent.currentOnlineUser.getPhone()).updateChildren(userMap);

                    Toast.makeText(NewPasswordActivity.this, "Password updated successfully.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NewPasswordActivity.this, LoginActivity.class));
                }
            }
        });

    }
}
