package com.example.jeesan_2.testingpro.Concepts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jeesan_2.testingpro.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KAMAL on 11/8/2017.
 */

public class WriteFile extends AppCompatActivity {

    @BindView(R.id.edt)
    EditText edt;

    @BindView(R.id.save)
    Button save;

    @BindView(R.id.next)
    Button next;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writefile);
        ButterKnife.bind(this);
    }

    public void saveMethod(View v) {
        String text = edt.getText().toString();
        if (text != null && !text.isEmpty()) {
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = openFileOutput("SampleFile.txt", MODE_PRIVATE);
                fileOutputStream.write(text.getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Toast.makeText(this, "File saved successfully.", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Fill text", Toast.LENGTH_SHORT).show();
    }

    public void nextMethod(View v) {
        Intent intent = new Intent(this,ReadFile.class);
        startActivity(intent);
    }
}
