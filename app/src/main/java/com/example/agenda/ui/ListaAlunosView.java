package com.example.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        this.dao = new AlunoDAO();
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                    remove(alunoEscolhido);
                })
                .setPositiveButton("Não", null)
                .show();
    }

    public void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    private void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    public void configuraAdapter(ListView listaDeALunos) {
        listaDeALunos.setAdapter(adapter);
    }
}
