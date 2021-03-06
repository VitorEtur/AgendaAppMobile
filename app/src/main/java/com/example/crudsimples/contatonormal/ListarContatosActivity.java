package com.example.crudsimples.contatonormal;
//********* Importando biblioetcas que iremos utilizar
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.crudsimples.MainActivity;
import com.example.crudsimples.R;
import com.example.crudsimples.contatoblock.ListarContatosBloqueadosActivity;
import com.example.crudsimples.contatoimportante.ListarContatosImportantesActivity;

import java.util.ArrayList;
import java.util.List;
//*************** Classe para listar os contatos salvos
public class ListarContatosActivity extends AppCompatActivity {

    Button botaoimportantetela, botaoblocktela;
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

        botaoblocktela=findViewById(R.id.button_nav_importan);
        botaoimportantetela=findViewById(R.id.button_nav_norma);

        botaoimportantetela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaimportante=new Intent(getApplicationContext(), ListarContatosImportantesActivity.class);
                startActivity(telaimportante);
                finish();
            }
        });

        botaoblocktela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telablock  =new Intent(getApplicationContext(), ListarContatosBloqueadosActivity.class);
                startActivity(telablock);
                finish();
            }
        });


        listView = findViewById(R.id.lista_contatosblock);
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
    //********* Implementando o menu que cont??m os 2 itens de pesquisar e adicionar novo contato
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
    //********* Menu de atualizar contato e excluir ao segurar o bot??o
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
////////////*** Delete parte 3
        arrayIds = new ArrayList<>();
                ////////////////*****
    }
//********* M??todo procurar contato
    public void procuraContato(String nome) {

        contatosFiltrados.clear();
        for (Contato a : contatos) {
            if (a.getNome().toLowerCase().contains(nome.toLowerCase())) {
                contatosFiltrados.add(a);
            }
        }

        listView.invalidateViews();

    }
    //********* M??todo excluir contato
    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Contato contatoExcluir = contatosFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Aten????o")
                .setMessage("Realmente deseja excluir esse contato?")
                .setNegativeButton("N??O",null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        contatosFiltrados.remove(contatoExcluir);
                        contatos.remove(contatoExcluir);
                        contatoDAO.excluir((contatoExcluir));
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show(); //********* Mostrar dialogo se usu??rio realmente deseja excluir o contato
    }
    //********* M??todo cadastrar contato
    public void cadastrar(MenuItem item) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);

    }
    //********* M??todo atualizar o cadastro do contato
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