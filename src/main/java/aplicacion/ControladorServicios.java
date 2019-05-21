package aplicacion;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.*;

import core.Principal;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.Alojamiento;
import modelo.Servicio;

public class ControladorServicios implements Initializable {
    
	@FXML
	private AnchorPane paneServicios, main;
	
	@FXML
	private Pane panePrincipal;
	
	@FXML
	private Label titulo;
	
	@FXML
	private JFXCheckBox reservarTodo;
	
    @FXML
    private GridPane gridServicios;
	
	private Alojamiento alojamiento;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	this.alojamiento = Principal.modelo.reserva.getAlojamiento();
    	cargarServicios(alojamiento);
	}
    
    @FXML
    void reservarTodo(ActionEvent event) {
    		
    }
    
    public void cargarServicios(Alojamiento alojamiento) {
    	ArrayList<Servicio> servicios = Principal.modelo.gestorBBDD.obtenerServicios(alojamiento.getCodAlojamiento());
    	for(int i=0;i<servicios.size();i++) {
    		Servicio servicio = servicios.get(i);
    		JFXCheckBox serv = new JFXCheckBox(servicio.getNombre());
    		serv.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.valueOf(servicio.getIcon())));
    		serv.setPrefHeight(30);
    		serv.setOnAction(new EventHandler<ActionEvent>(){
                 @Override
                 public void handle(ActionEvent event) {
                	 if(serv.isSelected()) {
						ArrayList<Servicio> serviciosSeleccionados = Principal.modelo.reserva.getServiciosSeleccionados();
						if (serviciosSeleccionados.size() == 0) {
							Principal.modelo.reserva.addServicio(servicio);
						}
                     } else {
                    	 Principal.modelo.reserva.removeServicio(servicio);
                     }
                 }
            });
    		gridServicios.addRow(i, serv);
    	}
    }

}