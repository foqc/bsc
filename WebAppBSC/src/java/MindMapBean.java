/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.UUID;
import javax.faces.bean.ManagedBean;
 
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;
 
@ManagedBean
public class MindMapBean implements Serializable {
     
    private MindmapNode root;
    private MindmapNode partesInteresadas;
    private MindmapNode sociedad;
    private MindmapNode procesosInternos;
    private MindmapNode recursosHumanos;    
    private MindmapNode financiera;    
     
    private MindmapNode selectedNode;
     
    public MindMapBean() {
        /*  PERSPECTIVA PARTES INTERESADAS  */
        partesInteresadas=new DefaultMindmapNode("Desarrollo de la cultura.", "Desarrollo de la cultura.", "7F087D", true);
        MindmapNode mpPi = new DefaultMindmapNode("Potenciar la investigación.", "Potenciar la investigación.", "7F087D", false);
        MindmapNode mpPi1 = new DefaultMindmapNode("Acreditar la carrera.", "Acreditar la carrera.", "7F087D", false);
        MindmapNode mpPi2 = new DefaultMindmapNode("Evaluar impacto social.", "Evaluar impacto social.", "7F087D", false);
        partesInteresadas.addNode(mpPi);
        partesInteresadas.addNode(mpPi1);
        partesInteresadas.addNode(mpPi2);
        
        
        
        /*  PERSPECTIVA SOCIEDAD  */
        sociedad=new DefaultMindmapNode("Aumentar satisfacción empresarial.", "Aumentar satisfacción empresarial.", "7F0808", true);
        MindmapNode mpS=new DefaultMindmapNode("Aumentar las satisfacción de los estudiantes y la sociedad.", "Aumentar las satisfacción de los estudiantes y la sociedad.", "7F0808", true);
        partesInteresadas.addNode(mpS);
        
        /*  PERSPECTIVA PROCESOS INTERNOS  */
        procesosInternos=new DefaultMindmapNode("Fortalecer proceso de aprendizaje y enseñanza.", "Fortalecer proceso de aprendizaje y enseñanza.", "F2CB03", true);
        MindmapNode mpPN1=new DefaultMindmapNode("Promover la excelencia académica.", "Promover la excelencia académica.", "F2CB03", true);
        MindmapNode mpPN2=new DefaultMindmapNode("Implementar el SGC.", "Implementar el SGC.", "F2CB03", true);
        MindmapNode mpPN3=new DefaultMindmapNode("Potenciar la investigación y el desarrollo.", "Potenciar la investigación y el desarrollo.", "F2CB03", true);
        
        /*  PERSPECTIVA PROCESOS RECURSOS HUMANOS  */
        recursosHumanos=new DefaultMindmapNode("Fortalecer y mejorar las capacidades docentes.", "Fortalecer y mejorar las capacidades docentes.", "39AADB", true);
        MindmapNode mpRH1=new DefaultMindmapNode("Promover cooperación entre organizaciones productivas y académicas.", "Promover cooperación entre organizaciones productivas y académicas.", "39AADB", true);
        MindmapNode mpRH2=new DefaultMindmapNode("Mejorar clima laboral.", "Mejorar clima laboral.", "39AADB", true);
        
        /*  PERSPECTIVA PROCESOS FINANCIERO */
        financiera=new DefaultMindmapNode("Optimizar los recursos económicos financiero.", "Optimizar los recursos económicos financiero.", "FF5700", true);
        MindmapNode mpF1=new DefaultMindmapNode("Obtener financiamiento de los convenios.", "Obtener financiamiento de los convenios.", "FF5700", true);
    }
 
    public MindmapNode getRoot() {
        return root;
    }

    public MindmapNode getPartesInteresadas() {
        return partesInteresadas;
    }

    public MindmapNode getSociedad() {
        return sociedad;
    }

    public MindmapNode getProcesosInternos() {
        return procesosInternos;
    }

    public MindmapNode getRecursosHumanos() {
        return recursosHumanos;
    }

    public MindmapNode getFinanciera() {
        return financiera;
    }
 
    public MindmapNode getSelectedNode() {
        return selectedNode;
    }
    public void setSelectedNode(MindmapNode selectedNode) {
        this.selectedNode = selectedNode;
    }
 
    public void onNodeSelect(SelectEvent event) {
       
    }
     
    public void onNodeDblselect(SelectEvent event) {
        this.selectedNode = (MindmapNode) event.getObject();        
    }
}