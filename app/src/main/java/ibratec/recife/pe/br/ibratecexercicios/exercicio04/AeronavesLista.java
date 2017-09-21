package ibratec.recife.pe.br.ibratecexercicios.exercicio04;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import ibratec.recife.pe.br.ibratecexercicios.R;

public class AeronavesLista extends AppCompatActivity {

    private static final int INCLUIR_AERONAVE = 1;

    //private ArrayList<Aeronave> listaAeronaves;
    //private ArrayAdapter<Aeronave> listaAeronavesAdapter;

    //private Aeronave aeronaveSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aeronaves_lista);

        FloatingActionButton button1 = (FloatingActionButton) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AeronavesLista.this, AeronavesCadastro.class);
                startActivityForResult(intent, INCLUIR_AERONAVE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == INCLUIR_AERONAVE) {

            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                //Exercicio02_Estudante estudante = (Exercicio02_Estudante) data.getSerializableExtra(Exercicio02.VAR_ESTUDANTE);
                //listEstudantes.add(estudante);
                //this.listEstudantesAdapter.notifyDataSetChanged();
                Toast toast = Toast.makeText(getApplicationContext(), "SALVAR", Toast.LENGTH_SHORT);
                toast.show();

            } else if (resultCode == RESULT_CANCELED) {

                Toast toast = Toast.makeText(getApplicationContext(), "CANCELAR", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
