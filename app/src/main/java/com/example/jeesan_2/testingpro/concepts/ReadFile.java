package com.example.jeesan_2.testingpro.concepts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jeesan_2.testingpro.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KAMAL on 11/9/2017.
 */

public class ReadFile extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readfile);
        ButterKnife.bind(this);
    }

    public void loadMethod(View v) {
        FileInputStream fileInputStream = null;
        StringBuffer buff=new StringBuffer();
        try {
            fileInputStream = openFileInput("SampleFile.txt");
            int read = -1;
            while ((read = fileInputStream.read()) != -1) {
                buff.append((char)read);
            }
            tv.setText(buff.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
