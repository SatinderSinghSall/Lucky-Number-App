package com.example.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textView_Message = findViewById(R.id.textView_Message);
        TextView textView_Result = findViewById(R.id.textView_Result);
        Button button_ResultShare = findViewById(R.id.button_ResultShare);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");

        int randomNumber = generateRandomNumbers();

        textView_Message.setText(userName + ", your lucky number is:");

        textView_Result.setText(String.valueOf(randomNumber));

        button_ResultShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, userName + "'s lucky number is: " + randomNumber);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });
    }

    public int generateRandomNumbers() {
        Random random = new Random();
        int upperLimit = 1000;
        return random.nextInt(upperLimit);
    }
}
