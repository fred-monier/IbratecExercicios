package ibratec.recife.pe.br.ibratecexercicios.exercicio04;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import ibratec.recife.pe.br.ibratecexercicios.R;

public class AeronavesCadastro extends AppCompatActivity {

    private static final String CRUZEIRO = "Velocidade de Cruzeiro:";

    private Spinner spFabricante;
    private TextView txtVwVelCruzeiro;
    private SeekBar seeBarVelCruzeiro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aeronaves_cadastro);

        //Inicializando componentes
        spFabricante = (Spinner) findViewById(R.id.spFabricante);
        txtVwVelCruzeiro = (TextView) findViewById(R.id.txtVwVelCruzeiro);
        seeBarVelCruzeiro = (SeekBar) findViewById(R.id.seeBarVelCruzeiro);

        //spFabricante
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, Aeronave.LISTA_FABRICANTE);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spFabricante.setAdapter(spinnerArrayAdapter);

        //seeBarVelCruzeiro
        seeBarVelCruzeiro.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                txtVwVelCruzeiro.setText(CRUZEIRO + " " + progressChangedValue + " km/h");
            }
        });

        //txtVwVelCruzeiro
        txtVwVelCruzeiro.setText(CRUZEIRO + " " + seeBarVelCruzeiro.getProgress() + " km/h");

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
