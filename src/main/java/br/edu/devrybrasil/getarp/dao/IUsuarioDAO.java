package br.edu.devrybrasil.getarp.dao;

import br.edu.devrybrasil.getarp.model.Usuario;
import java.util.ArrayList;

public interface IUsuarioDAO {

    public boolean inserir(Usuario usuario);

    public boolean excluir(Long id);

    public Usuario obterPorId(Long id);

    public ArrayList<Usuario> obterTodos();

    public boolean alterar(Usuario usuario);

    public Usuario obterPorNomeEEmail(String nome, String email);

}
