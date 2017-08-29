package fr.formation.sqlitedepts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Departement {

    private Context ctxt;
    private SQLiteDatabase db;

    public Departement(Context ctxt) {
        this.ctxt = ctxt;
        DbInit dbInit = DbInit.getInstance(ctxt);
        db = dbInit.getWritableDatabase();
    }
    private String noDept;
    public void setNoDept(String value) {noDept = value;}
    public String getNoDept() {return noDept;}

    private int noRegion;
    public void setNoRegion(int value) {noRegion = value;}
    public int getNoRegion() {return noRegion;}

    private String nom;
    public void setNom(String value) {nom= value;}
    public String getNom() {return nom;}

    private String nomStd;
    public void setNomStd(String value) {nomStd= value;}
    public String getNomStd() {return nomStd;}

    private int surface;
    public int getSurface() {return surface;}
    public void setSurface(int surface) {this.surface = surface;}

    private String dateCreation;
    public void setDateCreation(String value) {dateCreation= value;}
    public String getDateCreation() {return dateCreation;}

    private String chefLieu;
    public void setChefLieu(String value) {chefLieu= value;}
    public String getChefLieu() {return chefLieu;}

    private String urlWiki;
    public void setUrlWiki(String value) {urlWiki = value;}
    public String getUrlWiki() {return urlWiki;}

    public void select(String no) throws Exception{
        String[] cols = {"no_dept", "no_region", "nom", "nom_std", "surface", "date_creation", "chef_lieu", "url_wiki"};
        String where = "no_dept ='" + no + "'";
        Cursor curs = db.query(false,"departements", cols, where, null, "", "", "", "");
        if(curs.moveToFirst()) {
            setNoDept(curs.getString(0));
            setNoRegion(curs.getInt(1));
            setNom(curs.getString(2));
            setNomStd(curs.getString(3));
            setSurface(curs.getInt(4));
            setDateCreation(curs.getString(5));
            setChefLieu(curs.getString(6));
            setUrlWiki(curs.getString(7));
        }
        else {
            throw new DbException(ctxt, R.string.errDeptNotFound);
        }
    }
    public void delete() {

    }
}
