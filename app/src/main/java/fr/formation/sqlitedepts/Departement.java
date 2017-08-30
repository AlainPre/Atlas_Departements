package fr.formation.sqlitedepts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import static fr.formation.sqlitedepts.Tools.*;

public class Departement {

    private Context ctxt;
    private SQLiteDatabase db;

    private final String TABLE_NAME = "Departements";

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
        Cursor curs = db.query(false, TABLE_NAME, cols, where, null, "", "", "", "");
        if(!curs.moveToFirst()) {
            throw new DbDeptNotFoundException();
        }
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
        String where = "no_dept ='" + noDept + "'";
        db.delete(TABLE_NAME, where, null);
    }
    public void insert() {
        ContentValues values = iniValues();
        db.insert(TABLE_NAME, null, values);
    }
    public void update(){
        ContentValues values = iniValues();
        String where = "no_dept ='" + noDept + "'";
        db.update(TABLE_NAME, values, where, null);
    }

    private ContentValues iniValues(){
        ContentValues values = new ContentValues();
        values.put("no_dept", noDept);
        values.put("no_region", noRegion);
        values.put("nom", nom);
        values.put("nom_std", nomStd);
        values.put("surface", surface);
        values.put("date_creation", dateCreation);
        values.put("chef_lieu", chefLieu);
        values.put("url_wiki", urlWiki);
        return values;
    }

    public class DbException extends Exception {
        public DbException(String msg) {
            super(msg);
        }
        public DbException(Context ctxt, int stringId) {
            super(ctxt.getString(stringId));
        }
    }
    public class DbDeptNotFoundException extends Exception {
        public DbDeptNotFoundException() {
            super("Département non trouvé.");
        }
    }
}
