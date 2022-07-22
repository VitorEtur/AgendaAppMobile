package com.example.crudsimples.contatoblock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

import com.example.crudsimples.R;
import com.example.crudsimples.contatoimportante.ContatoImportante;
import com.example.crudsimples.contatoimportante.ContatoImportanteActivity;
import com.example.crudsimples.contatoimportante.ListarContatosImportantesActivity;
import com.example.crudsimples.contatonormal.ListarContatosActivity;

import java.util.ArrayList;
import java.util.List;
/*Classe q ira fazer parte do Listview na parte de listar usuarios*/
public class ListarContatosBloqueadosActivity extends AppCompatActivity {

    Button btnorma, btimportant;
/*Atributos*/
    private ListView listViewblock;
    private ContatoBlockDAO bkdao;
    private List<ContatoBlock> contatoBlocks;
    private List<ContatoBlock> contatoBlocksFiltrados = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contatos_bloqueados);

        btnorma = findViewById(R.id.button_nav_norma); /*LIGANDO OS BOTÕES AOS BOTOES DO LAYOUT*/
        btimportant = findViewById(R.id.button_nav_importan);

        btimportant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaimpor=new Intent(getApplicationContext(), ListarContatosImportantesActivity.class);
                startActivity(telaimpor);
                finish();
            }
        });

        btnorma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telanorma=new Intent(getApplicationContext(), ListarContatosActivity.class);
                startActivity(telanorma);
                finish(); /*método intent para chamar uma nova janela ao abrir*/
            }
        });

        listViewblock = findViewById(R.id.lista_contatosblock);
        bkdao = new ContatoBlockDAO(this);
        contatoBlocks = bkdao.obterTodosBlocks();
        contatoBlocksFiltrados.addAll(contatoBlocks);
        ArrayAdapter<ContatoBlock> adaptador = new ArrayAdapter<ContatoBlock>(this, android.R.layout.simple_list_item_1, contatoBlocksFiltrados);
        listViewblock.setAdapter(adaptador);
        registerForContextMenu(listViewblock); /*Método da Activity*/

    }

    public boolean onCreateOptionsMenu(Menu menublock) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal_block, menublock);

        SearchView sv = (SearchView) menublock.findItem(R.id.app_bar_search_block).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraContatoBlock(s);
                return false;
            }
        });

        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto_block, menu);

    }

    public void procuraContatoBlock(String nome) { /*método de procurar contato bloqueado*/

        contatoBlocksFiltrados.clear();
        for (ContatoBlock a : contatoBlocks) {
            if (a.getNomeblock().toLowerCase().contains(nome.toLowerCase())) {
                contatoBlocksFiltrados.add(a);
            }
        }

        listViewblock.invalidateViews();

    }

    public void excluirblock(MenuItem item) { /*método de excluir contato bloqueado*/
        AdapterView.AdapterContextMenuInfo menuInfo3 = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final ContatoBlock contatoBlockExcluir = contatoBlocksFiltrados.get(menuInfo3.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir esse contato bloqueado?")
                .setNegativeButton("NÃO",null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        contatoBlocksFiltrados.remove(contatoBlockExcluir);
                        contatoBlocks.remove(contatoBlockExcluir);
                        bkdao.excluirblock((contatoBlockExcluir));
                        listViewblock.invalidateViews();
                    }
                }).create();
        dialog.show(); //********* Mostrar dialogo se usuário realmente deseja excluir o contato
    }

    public void cadastrarblock(MenuItem item){ /*Método de cadastrar contato bloqueado*/
        Intent it = new Intent(this, ContatoBlockActivity.class);
        startActivity(it);

    }

    public void atualizarblock(MenuItem item){ /*Metodo de atualizar contato bloqueado*/

        AdapterView.AdapterContextMenuInfo menuInfo3 =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final ContatoBlock contatoBlockAtualizar = contatoBlocks.get(menuInfo3.position);
        Intent it = new Intent(this, ContatoBlockActivity.class);
        it.putExtra("contatoblock", contatoBlockAtualizar);
        startActivity(it);

    }

}