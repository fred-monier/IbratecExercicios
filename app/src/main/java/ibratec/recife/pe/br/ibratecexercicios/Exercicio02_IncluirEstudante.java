package ibratec.recife.pe.br.ibratecexercicios;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Exercicio02_IncluirEstudante extends AppCompatActivity {

    private EditText editNomeEstudante;
    private EditText editTelefoneEstudante;
    private EditText editEnderecoEstudante;
    private EditText editSiteEstudante;
    private EditText editNotaEstudante;

    private Button buttonConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio02__incluir_estudante);

        editNomeEstudante  = (EditText) findViewById(R.id.editNomeEstudante);

        buttonConfirmar = (Button) findViewById(R.id.buttonConfirmar);
        buttonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editNomeEstudante.getText() != null && !editNomeEstudante.getText().toString().equals("")) {

                    editTelefoneEstudante  = (EditText) findViewById(R.id.editTelefoneEstudante);
                    editEnderecoEstudante  = (EditText) findViewById(R.id.editEnderecoEstudante);
                    editSiteEstudante  = (EditText) findViewById(R.id.editSiteEstudante);
                    editNotaEstudante  = (EditText) findViewById(R.id.editNotaEstudante);

                    Exercicio02_Estudante estudante = new Exercicio02_Estudante();
                    estudante.setNome(editNomeEstudante.getText().toString());
                    estudante.setTelefone(editTelefoneEstudante.getText().toString());
                    estudante.setEndereco(editEnderecoEstudante.getText().toString());
                    estudante.setSite(editSiteEstudante.getText().toString());
                    estudante.setNota(editNotaEstudante.getText().toString());

                    Intent devolve = getIntent();
                    devolve.putExtra(Exercicio02.VAR_ESTUDANTE, estudante);
                    setResult(Activity.RESULT_OK, devolve);
                    finish();

                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Digite um nome não em branco", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }
}
