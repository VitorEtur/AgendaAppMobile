package com.example.crudsimples.contatoimportante;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudsimples.R;
import com.example.crudsimples.contatoblock.ListarContatosBloqueadosActivity;
import com.example.crudsimples.contatonormal.ListarContatosActivity;

import java.util.ArrayList;
import java.util.List;

public class ListarContatosImportantesActivity extends AppCompatActivity {

     Button botaonormaltela, botaoimportantetela;
    private ListView listViewImportante;
    private ContatoImportanteDAO ctdao;
    private List<ContatoImportante> contatosimportantes;
    private List<ContatoImportante> contatosimportantesFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contatos_importantes);

        botaonormaltela = findViewById(R.id.button_nav_norma);
        botaoimportantetela = findViewById(R.id.button_nav_importan);

        botaoimportantetela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telabloq=new Intent(getApplicationContext(), ListarContatosBloqueadosActivity.class);
                startActivity(telabloq);
                finish();
            }
        });

        botaonormaltela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telanormal=new Intent(getApplicationContext(), ListarContatosActivity.class);
                startActivity(telanormal);
                finish();
            }
        });

        listViewImportante = findViewById(R.id.lista_contatosblock);
        ctdao = new ContatoImportanteDAO(this);
        contatosimportantes = ctdao.obterTodosImportantes();
        contatosimportantesFiltrados.addAll(contatosimportantes);
        ArrayAdapter<ContatoImportante> adaptador = new ArrayAdapter<ContatoImportante>(this, android.R.layout.simple_list_item_1, contatosimportantesFiltrados);
        listViewImportante.setAdapter(adaptador);
        registerForContextMenu(listViewImportante);
    }

    public boolean onCreateOptionsMenu(Menu menuimportante) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal_importante, menuimportante);

        SearchView sv = (SearchView) menuimportante.findItem(R.id.app_bar_search_block).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraContatoImportante(s);
                return false;
            }
        });

        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto_importante, menu);

    }

    public void procuraContatoImportante(String nome) {

        contatosimportantesFiltrados.clear();
        for (ContatoImportante a : contatosimportantes) {
            if (a.getNomeimportante().toLowerCase().contains(nome.toLowerCase())) {
                contatosimportantesFiltrados.add(a);
            }
        }

        listViewImportante.invalidateViews();

    }

    public void excluirimportante(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo2 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final ContatoImportante contatoImportanteExcluir = contatosimportantesFiltrados.get(menuInfo2.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir esse contato importante?")
                .setNegativeButton("NÃO",null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        contatosimportantesFiltrados.remove(contatoImportanteExcluir);
                        contatosimportantes.remove(contatoImportanteExcluir);
                        ctdao.excluirimportante((contatoImportanteExcluir));
                        listViewImportante.invalidateViews();
                    }
                }).create();
        dialog.show(); //********* Mostrar dialogo se usuário realmente deseja excluir o contato
    }

    public void cadastrarimportante(MenuItem item){
        Intent it = new Intent(this, ContatoImportanteActivity.class);
        startActivity(it);
    }

    public void atualizarimportante(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo2 =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final ContatoImportante contatoImportanteAtualizar = contatosimportantes.get(menuInfo2.position);
        Intent it = new Intent(this, ContatoImportanteActivity.class);
        it.putExtra("contatoimportante", contatoImportanteAtualizar);
        startActivity(it);

    }

}