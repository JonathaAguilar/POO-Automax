 package modelo;

public class Cotizacion {
    String folio, des;
    float precio, porPago;
    int pz; 
    
    public Cotizacion(){ 

    }
    public Cotizacion(String fo, String descripcion, float precio, float porPago, int plazo){
        this.folio=fo;
        this.des=descripcion;
        this.precio=precio;
        this.porPago=porPago;
        this.pz=plazo;        
    }
    public Cotizacion(Cotizacion cot){
        this.folio=cot.folio;
        this.des=cot.des;
        this.precio=cot.precio;
        this.porPago=cot.porPago;
        this.pz=cot.pz;
    }
    public void setFO(String fol){
        this.folio=fol;
    }
    public void setDesc(String des){
        this.des=des;
    }
    public void setPre(float pre){
        this.precio=pre;
    }
    public void setPorcentajePagoIni(float pagoI){
        this.porPago=pagoI;
    }
    public void setPlazo(int pla){
        this.pz=pla;
    } 
    public String getFolio(){
        return folio;
    }
    public String getDesc(){
        return des;
    }
    public float getPrecio(){
        return precio;
    }
    public float getPorPagoI(){
        return porPago;
    }
    public int getPlazo(){
        return pz;
    }
    public float calcularPagoI(){
        float totalP=0;
        totalP=(this.precio/100)*this.porPago;
        return totalP;
    }
    public float calcularTotalF(){
        float totalF=0;
        totalF=this.precio-calcularPagoI();
        return totalF;
    }
    public float calcularPagoM(){
        float totalM=0;
        totalM=calcularTotalF()/this.pz;
        return totalM;
    }
}
