package ibratec.recife.pe.br.ibratecexercicios.exercicio04;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ibratec.recife.pe.br.ibratecexercicios.R;

public class AeronavesCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aeronaves_cadastro);

        //Botão Cancelar
        Button btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                setResult(Activity.RESULT_CANCELED, intent);
                finish();
            }
        });


        //Botão Salvar
        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });


    }
}
