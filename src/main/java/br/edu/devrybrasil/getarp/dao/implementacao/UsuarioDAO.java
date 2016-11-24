package br.edu.devrybrasil.getarp.dao.implementacao;

import br.edu.devrybrasil.getarp.dao.IUsuarioDAO;
import br.edu.devrybrasil.getarp.model.Usuario;
import br.edu.devrybrasil.getarp.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO implements IUsuarioDAO {

    private static final String INSERIR = "insert into usuario (nome, email) values (?,?)";
    private static final String EXCLUIR = "delete from usuario where idusuario = ?";
    private static final String OBTERTODOS = "select idusuario, nome, email from usuario order by nome, email";
    private static final String OBTERPORID = "select idusuario, nome, email from usuario where idusuario = ?";
    private static final String ALTERAR = "update usuario set nome = ?, email = ? where idusuario = ?";


    private Connection conexao;

    @Override
    public boolean inserir(Usuario usuario) {
        conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(INSERIR);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.executeUpdate();
            ps.close();
            conexao.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir usuário. Exception: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Usuario> obterTodos() {
        conexao = ConnectionFactory.getConnection();
        ArrayList<Usuario> usuarios = null;
        Usuario usuario = null;

        try {
            PreparedStatement ps = conexao.prepareStatement(OBTERTODOS);

            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(Long.parseLong(resultado.getString("idusuario")));
                usuario.setNome(resultado.getString("nome"));
                usuario.setEmail(resultado.getString("email"));

                if (usuarios == null) {
                    usuarios = new ArrayList<>();
                }

                usuarios.add(usuario);
            }

            ps.close();
            conexao.close();
            return usuarios;

        } catch (SQLException ex) {
            System.out.println("Erro ao obter usuários. Exception: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean excluir(Long id) {
        conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(EXCLUIR);
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
            conexao.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir usuário. Exception: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public Usuario obterPorId(Long id) {
        conexao = ConnectionFactory.getConnection();
        Usuario usuario = null;

        try {
            PreparedStatement ps = conexao.prepareStatement(OBTERPORID);
            ps.setLong(1, id);
            ResultSet resultado = ps.executeQuery();

            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(Long.parseLong(resultado.getString("idusuario")));
                usuario.setNome(resultado.getString("nome"));
                usuario.setEmail(resultado.getString("email"));
            }

            ps.close();
            conexao.close();
            return usuario;

        } catch (SQLException ex) {
            System.out.println("Erro ao obter usuário. Exception: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean alterar(Usuario usuario) {
        conexao = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conexao.prepareStatement(ALTERAR);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setLong(3, usuario.getId());
            ps.executeUpdate();
            ps.close();
            conexao.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao alterar usuário. Exception: " + ex.getMessage());
            return false;
        }
    }

}
