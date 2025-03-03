package com.example.anybreak;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.OutputStream;
import java.net.Socket;

public class helpsupport extends AppCompatActivity {

    private EditText editTextMessage;
    private Button buttonSend;
    private TextView textViewResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_helpsupport);


        editTextMessage = findViewById(R.id.editTextMessege);
        buttonSend = findViewById(R.id.buttonSend);
        textViewResponse = findViewById(R.id.textViewResponce);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editTextMessage.getText().toString();
                if (!message.isEmpty()) {

                    new SendMessageTask().execute(message);
                } else {
                    Toast.makeText(helpsupport.this, "Введите сообщение!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class SendMessageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String message = params[0];
            String serverResponse = "";

            try {

                Socket socket = new Socket("192.168.1.23", 8888);
                OutputStream outputStream = socket.getOutputStream();


                outputStream.write(message.getBytes());
                outputStream.flush();


                outputStream.close();
                socket.close();

                serverResponse = "Сообщение отправлено: " + message;
            } catch (Exception e) {
                serverResponse = "Ошибка: " + e.getMessage();
            }

            return serverResponse;
        }

        @Override
        protected void onPostExecute(String result) {
            // Отображаем результат в TextView
            textViewResponse.setText(result);
        }
    }
}