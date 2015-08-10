package ec.edu.espoch.bsc.controladores;

import ec.edu.espoch.bsc.entidades.CTipoUsuario;
import ec.edu.espoch.bsc.entidades.CUsuario;
import ec.edu.espoch.bsc.modelo.MLogin;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import recursos.Util;

public class ControladorLogin implements Serializable {

    private static final long serialVersionUID = -2152389656664659476L;
    private CUsuario objUsuario;
    private boolean logeado = false;

    public boolean estaLogeado() {
        return logeado;
    }

    public ControladorLogin() {
        this.objUsuario = new CUsuario();
    }

    public CUsuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(CUsuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    @PostConstruct
    public void reinit() {
        CTipoUsuario objTipo = new CTipoUsuario();
        this.objUsuario.setObjTipoUsuario(objTipo);
    }

    public void login(ActionEvent actionEvent) {
        try {
            RequestContext context = RequestContext.getCurrentInstance();
            FacesMessage msg = null;
            if (MLogin.loginUsuario(objUsuario)!=null) {
                this.objUsuario=MLogin.loginUsuario(objUsuario);
                logeado = true;
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", objUsuario.getAlias());
            } else {
                logeado = false;
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                        "Credenciales no válidas");
            }
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.addCallbackParam("estaLogeado", logeado);
            if (logeado) {
                redireccionarPaginas(context);
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    
    private void redireccionarPaginas(RequestContext context){
        switch(this.objUsuario.getObjTipoUsuario().getDescripcion()){
            case "Administrador":
                context.addCallbackParam("view", "Administrador/usuario/ListUser.xhtml");
                break;
            default:
                context.addCallbackParam("view", "UsuarioNormal/user.xhtml");
        }
    }

    public void logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
        logeado = false;
    }
}
