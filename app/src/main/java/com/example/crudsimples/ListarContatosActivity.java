package com.example.crudsimples;
//********* Importando biblioetcas que iremos utilizar
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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
//*************** Classe para listar os contatos salvos
public class ListarContatosActivity extends AppCompatActivity {

    //*************** ATRIBUTOS
    private ListView listView;
    private ContatoDAO contatoDAO;
    private List<Contato> contatos;
    private List<Contato> contatosFiltrados = new ArrayList<>();

    //****** Implementando exibir ID ao clicar parte 1
    public ArrayList<Integer> arrayIds;


    //******

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();

        setContentView(R.layout.activity_listar_contatos);

        listView = findViewById(R.id.lista_contatos);
        contatoDAO = new ContatoDAO(this);
        contatos = contatoDAO.obterTodos();
        contatosFiltrados.addAll(contatos);
        //ArrayAdapter<Contato> adaptador = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatosFiltrados);
        ContatoAdapter adaptador = new ContatoAdapter(this, contatosFiltrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);

//************Implementando o Exibir ID ao clicar parte 2
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                excluirr(i);

            }
        });


        //************************************

    }
    //********* Implementando o menu que contém os 2 itens de pesquisar e adicionar novo contato
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraContato(s);
                return false;
            }
        });

        return true;
    }
    //********* Menu de atualizar contato e excluir ao segurar o botão
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
////////////*** Delete parte 3
        arrayIds = new ArrayList<>();
                ////////////////*****
    }
//********* Método procurar contato
    public void procuraContato(String nome) {

        contatosFiltrados.clear();
        for (Contato a : contatos) {
            if (a.getNome().toLowerCase().contains(nome.toLowerCase())) {
                contatosFiltrados.add(a);
            }
        }

        listView.invalidateViews();

    }
    //********* Método excluir contato
    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Contato contatoExcluir = contatosFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir esse contato?")
                .setNegativeButton("NÃO",null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        contatosFiltrados.remove(contatoExcluir);
                        contatos.remove(contatoExcluir);
                        contatoDAO.excluir((contatoExcluir));
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show(); //********* Mostrar dialogo se usuário realmente deseja excluir o contato
    }
    //********* Método cadastrar contato
    public void cadastrar(MenuItem item) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
    //********* Método atualizar o cadastro do contato
    public void atualizar(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Contato contatoAtualizar = contatosFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("contato", contatoAtualizar);
        startActivity(it);

    }

    @Override
    public void onResume() {
        super.onResume();
        contatos = contatoDAO.obterTodos();
        contatosFiltrados.clear();
        contatosFiltrados.addAll(contatos);
        listView.invalidateViews();

    }

    //***********Exibir ID ao clicar parte 3

    public void excluirr(Integer i){

        Toast.makeText(this,"ID: " + i.toString(), Toast.LENGTH_SHORT).show();
    }

    //************

    

}