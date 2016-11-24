package br.edu.devrybrasil.getarp.bean;

import br.edu.devrybrasil.getarp.bo.UsuarioBO;
import br.edu.devrybrasil.getarp.model.Usuario;
import com.sun.faces.facelets.tag.jstl.core.ForEachHandler;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "UsuarioController")
@RequestScoped
public class UsuarioBean {

    private Usuario usuario = new Usuario();
    private ArrayList<Usuario> usuarios = UsuarioBO.obterTodos();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String inserirUsuario() {
        //if (validacoes("cadastro")) {

            if (UsuarioBO.inserirUsuario(this.usuario)) {
                this.usuario = new Usuario();
                this.usuarios = UsuarioBO.obterTodos();
                return "listarUsuarios";
            } else {
                return "cadastrarUsuario";
            }
        //} else {
        //    return null;
        //}
    }

    public String excluirUsuario(Long id) {
        if (UsuarioBO.excluirUsuario(id)) {
            this.usuario = new Usuario();
            this.usuarios = UsuarioBO.obterTodos();
            return "usuario";
        } else {
            return null;
        }
    }

    public String alterarUsuario(Long id) {
        this.usuario = UsuarioBO.obterPorId(id);
        if (this.usuario != null) {
            return "alterarUsuario";
        } else {
            return null;
        }
    }

    public String alterarUsuarioSalvar() {

        if (UsuarioBO.alterarUsuario(this.usuario)) {
            this.usuario = new Usuario();
            this.usuarios = UsuarioBO.obterTodos();
            return "listarUsuarios";
        } else {
            return null;
        }

    }

    public boolean validacoes(String operacao) {
        ArrayList<String> mensagens = UsuarioBO.validacoes(this.usuario, operacao);
        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (mensagens != null && mensagens.size() > 0) {
            for (String m : mensagens) {
                facesContext.addMessage(null, new FacesMessage(m));
            }
            return false;
        } else {
            return true;
        }
    }
}
