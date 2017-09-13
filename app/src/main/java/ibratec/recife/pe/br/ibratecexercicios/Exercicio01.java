package ibratec.recife.pe.br.ibratecexercicios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.view.View.*;

public class Exercicio01 extends AppCompatActivity {

    private EditText editToastValue;
    private Button buttonToast;
    private Button buttonEstudante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio01);

        //Recuperando EditText editToastValue
        editToastValue  = (EditText) findViewById(R.id.editText);

        //Recuperando Button buttonToast
        buttonToast = (Button) findViewById(R.id.button1);
        buttonToast.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                   Toast toast = Toast.makeText(getApplicationContext(), editToastValue.getText(), Toast.LENGTH_SHORT);
                   toast.show();
               }
        });

        //Recuperando Button buttonEstudante
        buttonEstudante = (Button) findViewById(R.id.button2);
        buttonEstudante.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Exercicio01.this, Exercicio01_Estudante.class);
                    startActivity(intent);
                }
        });
    }
}
