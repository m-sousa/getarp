package br.edu.devrybrasil.getarp.bo;

import br.edu.devrybrasil.getarp.dao.IUsuarioDAO;
import br.edu.devrybrasil.getarp.dao.implementacao.UsuarioDAO;
import br.edu.devrybrasil.getarp.model.Usuario;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class UsuarioBO {

    private static IUsuarioDAO usuarioDAO = new UsuarioDAO();

    public static boolean inserirUsuario(Usuario usuario) {
        return usuarioDAO.inserir(usuario);
    }

    public static boolean excluirUsuario(Long id) {
        return usuarioDAO.excluir(id);
    }

    public static ArrayList<Usuario> obterTodos() {
        return usuarioDAO.obterTodos();
    }

    public static Usuario obterPorId(Long id) {
        return usuarioDAO.obterPorId(id);
    }

    public static boolean alterarUsuario(Usuario usuario) {
        return usuarioDAO.alterar(usuario);
    }

    public static ArrayList<String> validacoes(Usuario usuario) {
        ArrayList<String> mensagens = new ArrayList<>();

        // duplicidade
        Usuario usuarioEncontrado = usuarioDAO.obterPorNomeEEmail(usuario.getNome(), usuario.getEmail());

        if (usuarioEncontrado != null) {
            mensagens.add("Usuário com mesmo Nome e E-mail já cadastrados.");
        }

        return mensagens;
    }
}
