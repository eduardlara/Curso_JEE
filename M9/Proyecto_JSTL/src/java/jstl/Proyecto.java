package edu.fje.daw2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * JavaBean Proyecto
 Model de dades
 */
public class Proyecto implements Serializable {
    private Date dini;
    private Date dfin;
    private long data_ini;
    private long data_fin;
    private int desc;
    private String name;
    private int codi;
    private ArrayList<Tarea> llistaTasques;

    public Proyecto(String name, int codi, int desc, Date dini, Date dfin) {
        this.dini = dini;
        this.dfin = dfin;
        this.data_ini = dini.getTime();
        this.data_fin = dfin.getTime();
        this.desc = desc;
        this.name = name;
        this.codi = codi;
        this.llistaTasques = new ArrayList<Tarea>();
    }
    public Proyecto(String name, int codi, int desc, long dini, long dfin) {
        this.data_ini = dini;
        this.data_fin = dfin;
        this.dini = new Date(dini);
        this.dfin = new Date(dfin);
        this.desc = desc;
        this.name = name;
        this.codi = codi;
        this.llistaTasques = new ArrayList<Tarea>();
    }

    public Date getDini() {
        return dini;
    }

    public void setDini(Date dini) {
        this.dini = dini;
    }

    public Date getDfin() {
        return dfin;
    }

    public void setDfin(Date dfin) {
        this.dfin = dfin;
    }

    public long getData_ini() {
        return data_ini;
    }

    public void setData_ini(long data_ini) {
        this.data_ini = data_ini;
    }

    public long getData_fin() {
        return data_fin;
    }

    public void setData_fin(long data_fin) {
        this.data_fin = data_fin;
    }

    public int getDesc() {
        return desc;
    }

    public void setDesc(int desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public void addTasca(Tarea t){
        llistaTasques.add(t);
    }

    public ArrayList<Tarea> getLlistaTasques() {
        return llistaTasques;
    }

    @Override
    public String toString() {
        return "Projecte{" +
                    "Data inici: '" + data_ini + '\'' +
                    ", Data final: " + data_fin + '\'' +
                    ", % Completado: " + desc +
                '}';
    }
}
