package ibratec.recife.pe.br.ibratecexercicios;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Exercicio02 extends AppCompatActivity {

    public static final String VAR_ESTUDANTE = "Estudante";

    private static final int INSERIR_ESTUDANTE = 1;
    private static final String LISTA_ESTUDANTES = "ListaDeEstudantes";

    private Button buttonInserirEstudante;

    private ArrayList<Exercicio02_Estudante> listEstudantes;
    private ArrayAdapter<Exercicio02_Estudante> listEstudantesAdapter;

    private Exercicio02_Estudante estudanteSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio02);

        //Inicializando a Lista de Estudantes
        if (savedInstanceState != null) {
            listEstudantes = (ArrayList<Exercicio02_Estudante>) savedInstanceState.getSerializable(LISTA_ESTUDANTES);
        } else {
            listEstudantes = new ArrayList<Exercicio02_Estudante>();
        }

        this.montarLista();

        //Recuperando botão de inserir estudante
        buttonInserirEstudante = (Button) findViewById(R.id.button3);
        buttonInserirEstudante.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(Exercicio02.this, Exercicio02_IncluirEstudante.class);
                 startActivityForResult(intent, INSERIR_ESTUDANTE);
             }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == INSERIR_ESTUDANTE) {

            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                Exercicio02_Estudante estudante = (Exercicio02_Estudante) data.getSerializableExtra(Exercicio02.VAR_ESTUDANTE);
                listEstudantes.add(estudante);
                this.listEstudantesAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(LISTA_ESTUDANTES, listEstudantes);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        this.getMenuInflater().inflate(R.menu.estudante_opcoes, menu);

        MenuItem itemLigar = menu.getItem(0);
        itemLigar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
             @Override
             public boolean onMenuItemClick(MenuItem item) {
                 Intent callIntent = new Intent(Intent.ACTION_CALL);
                 callIntent.setData(Uri.parse("tel:" + estudanteSelecionado.getTelefone()));
                 try {
                     startActivity(callIntent);
                 } catch (Exception e) {
                     Toast toast = Toast.makeText(getApplicationContext(), "Erro ao tentar ligar para estudante", Toast.LENGTH_SHORT);
                     toast.show();
                 }
                 return true;
             }
        });

        MenuItem itemSMS = menu.getItem(1);
        itemSMS.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(estudanteSelecionado.getTelefone(), null, "Olá, estudante!", null, null);
                    Toast.makeText(getApplicationContext(), "Mensagem enviada",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                            Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
                return true;
            }
        });

        MenuItem itemLocalizacao = menu.getItem(2);
        itemLocalizacao.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(estudanteSelecionado.getEndereco()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                return true;
            }
        });

        MenuItem itemSite = menu.getItem(3);
        itemSite.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + estudanteSelecionado.getSite()));
                startActivity(browserIntent);
                return true;
            }
        });

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        estudanteSelecionado = listEstudantesAdapter.getItem(info.position);
    }


    private void montarLista() {
        listEstudantesAdapter =
                new ArrayAdapter<Exercicio02_Estudante>(this, android.R.layout.simple_list_item_1, listEstudantes);
        ListView listEstudantesView = (ListView) findViewById(R.id.listview1);
        listEstudantesView.setAdapter(listEstudantesAdapter);

        registerForContextMenu(listEstudantesView);

    }
}
