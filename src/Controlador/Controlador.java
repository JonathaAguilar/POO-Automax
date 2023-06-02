package controlador;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import modelo.Cotizacion;
import Vista.dlgCotizacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Controlador implements ActionListener{
    private Cotizacion cot;
    private dlgCotizacion vista;
    
    public Controlador(Cotizacion cot, dlgCotizacion vista){
        
        this.cot = cot;
        this.vista = vista;
        
        vista.btnCancelar.addActionListener(this);
        vista.btnCerrar.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);
        
        vista.btnNuevo.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.btnMostrar.addActionListener(this);
    }
    private void iniciarVista(){
        vista.setTitle("COTIZACION");
        vista.setSize(500, 500);
        vista.setVisible(true);
    }
    public void limpiar(){
        vista.txtFolio.setText("");
        vista.txtPagoI.setText("");
        vista.txtDescripcion.setText("");
        vista.txtPrecio.setText("");
        vista.txtCalcularPaI.setText("");
        vista.txtCalcularPaM.setText("");
        vista.txtCalcularTotal.setText("");
        vista.cmbPlazo.setSelectedIndex(0);
    }
    @Override
    public void actionPerformed(ActionEvent e) { 
        if(e.getSource()==vista.btnNuevo){
            vista.txtFolio.setEnabled(true);
            vista.btnGuardar.setEnabled(true);
            vista.btnCancelar.setEnabled(true);
            vista.btnMostrar.setEnabled(true);
            vista.txtPagoI.setEnabled(true);
            vista.txtDescripcion.setEnabled(true);
            vista.txtPrecio.setEnabled(true); 
            vista.btnLimpiar.setEnabled(true);
            vista.btnNuevo.setEnabled(false);
            vista.cmbPlazo.setEnabled(true);
        }
        if(e.getSource()==vista.btnLimpiar){
            limpiar();
        }
        if(e.getSource()==vista.btnCerrar){
            int option=JOptionPane.showConfirmDialog(vista,"¿Deseas salir?",
            "Decide", JOptionPane.YES_NO_OPTION);
             if(option==JOptionPane.YES_NO_OPTION){
                 vista.dispose();
                 System.exit(0);
             }
        }
        if(e.getSource()==vista.btnCancelar){
            vista.txtFolio.setEnabled(false);
            vista.btnGuardar.setEnabled(false);
            vista.btnCancelar.setEnabled(false);
            vista.btnMostrar.setEnabled(false);
            vista.txtPagoI.setEnabled(false);
            vista.txtDescripcion.setEnabled(false);
            vista.txtPrecio.setEnabled(false); 
            vista.btnLimpiar.setEnabled(false);
            vista.btnNuevo.setEnabled(true);
            vista.cmbPlazo.setEnabled(true);
            limpiar();
        }
        if(e.getSource()==vista.btnGuardar){             
             cot.setFO(vista.txtFolio.getText());
             cot.setDesc(vista.txtDescripcion.getText());
             try{
                
                cot.setPorcentajePagoIni(Float.parseFloat(vista.txtPagoI.getText()));
                cot.setPre(Float.parseFloat(vista.txtPrecio.getText()));
                cot.setPlazo(Integer.parseInt(vista.cmbPlazo.getSelectedItem().toString()));
                JOptionPane.showMessageDialog(vista,"Guardado con exito");
                limpiar();
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(vista, "Surgio el siguiente error: "+ex.getMessage());
            }
            catch(Exception ex2){
                 JOptionPane.showMessageDialog(vista, "Surgio el siguiente error: "+ex2.getMessage());
            }
            
        }
        if(e.getSource()==vista.btnMostrar){
            
            vista.txtFolio.setText(cot.getFolio());
            vista.txtDescripcion.setText(cot.getDesc());
            vista.cmbPlazo.setSelectedItem(cot.getPlazo());
            vista.txtPagoI.setText(Float.toString(cot.getPorPagoI()));
            vista.txtPrecio.setText(Float.toString(cot.getPrecio()));
            vista.txtCalcularPaI.setText(Float.toString(cot.calcularPagoI()));
            vista.txtCalcularTotal.setText(Float.toString(cot.calcularTotalF()));
            vista.txtCalcularPaM.setText(Float.toString(cot.calcularPagoM()));
        }
    }

    public static void main(String[] args) {
        Cotizacion cot=new Cotizacion();
        dlgCotizacion vista = new dlgCotizacion(new JFrame(),true);
        Controlador contra = new Controlador(cot,vista);  
        contra.iniciarVista();
    }
}
