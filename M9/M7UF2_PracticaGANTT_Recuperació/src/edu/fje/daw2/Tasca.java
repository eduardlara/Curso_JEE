package edu.fje.daw2;

import java.io.Serializable;
import java.util.Date;

/**
 * JavaBean Tasca
 * Model de dades
 */
public class Tasca implements Serializable {
    private Date dini;
    private Date dfin;
    private long data_ini;
    private long data_fin;
    private int desc;
    private String name;

    public Tasca(String name, int desc, Date dini, Date dfin ) {
        this.dini = dini;
        this.dfin = dfin;
        this.data_ini = dini.getTime();
        this.data_fin = dfin.getTime();
        this.desc = desc;
        this.name = name;
    }
    public Tasca(String name, int desc, long dini, long dfin ) {
        this.data_ini = dini;
        this.data_fin = dfin;
        this.dini = new Date(dini);
        this.dfin = new Date(dfin);
        this.desc = desc;
        this.name = name;
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

    @Override
    public String toString() {
        return "Tasca{" +
                    "Data inici: '" + data_ini + '\'' +
                    ", Data final: " + data_fin + '\'' +
                    ", % Completado: " + desc +
                '}';
    }

}
