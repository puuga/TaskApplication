package com.example.siwaweswongcharoen.taskapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MyActivity extends Activity {

    private TextView tvResult;
    private EditText etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        bindWidget();
    }

    private void bindWidget() {
        tvResult = (TextView) findViewById(R.id.tvResult);
        etNumber = (EditText) findViewById(R.id.etNumber);
    }

    public void runTask(View view) {
        if (!etNumber.getText().toString().equals("")) {
            new LongOperation().execute(Integer.parseInt(etNumber.getText().toString()));
        }
    }

    private class LongOperation extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            for (int i = 0; i < params[0]; i++) {
                try {
                    publishProgress(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "complete";
        }

        @Override
        protected void onPostExecute(String result) {
            tvResult.setText(result);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            tvResult.setText(values[0].toString());
        }
    }
}
