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

    public static ArrayList<String> validacoes(Usuario usuario, String operacao) {
        ArrayList<String> mensagens = new ArrayList<>();

        switch (operacao) {
            case "cadastro":
                if (usuario != null) {
                    if (usuario.getNome().trim().equals("")) {
                        mensagens.add("O campo Nome é obrigatório.");
                    }

//                    if (usuario.getEmail().trim().equals("")) {
//                        mensagens.add("O campo E-mail é obrigatório.");
//                    } else {
//                        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
//                        if (!pattern.matcher(usuario.getEmail()).matches()) {
//                            mensagens.add("Formato de e-mail inválido..");
//                        }
//                    }
                } else {
                    mensagens.add("Ocorreu um erro ao realizar operação.");
                }
        }
        return mensagens;
    }
}
