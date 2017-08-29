package fr.formation.sqlitedepts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import static fr.formation.sqlitedepts.Tools.*;

public class MainActivity extends Activity {

    private EditText txtSearch;
    private EditText txtNoDept;
    private EditText txtNoRegion;
    private EditText txtNom;
    private EditText txtNomStd;
    private EditText txtSurface;
    private EditText txtChefLieu;
    private EditText txtDateCreation;
    private EditText txtUrlWiki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSearch = (EditText) findViewById(R.id.txtSearch);
        txtNoDept = (EditText) findViewById(R.id.txtNoDept);
        txtNoRegion = (EditText) findViewById(R.id.txtNoRegion);
        txtNom  = (EditText) findViewById(R.id.txtNom);
        txtNomStd = (EditText) findViewById(R.id.txtNomStd);
        txtSurface = (EditText) findViewById(R.id.txtSurface);
        txtDateCreation = (EditText) findViewById(R.id.txtDateCreation);
        txtChefLieu = (EditText) findViewById(R.id.txtChefLieu);
        txtUrlWiki = (EditText) findViewById(R.id.txtUrlWiki);
    }
    public void btnClear(View v){
        txtSearch.setText("");
        txtNoDept.setText("");
        txtNoRegion.setText("");
        txtNom.setText("");
        txtNomStd.setText("");
        txtSurface.setText("");
        txtDateCreation.setText("");
        txtChefLieu.setText("");
        txtUrlWiki.setText("");
    }

    public void btnSearch(View v) {
        try {
            String no = txtSearch.getText().toString();
            Departement d = new Departement(this);
            d.select(no);
            txtNoDept.setText(d.getNoDept());
            txtNoRegion.setText(String.valueOf(d.getNoRegion()));
            txtNom.setText(d.getNom());
            txtNomStd.setText(d.getNomStd());
            txtSurface.setText(String.valueOf(d.getSurface()));
            txtChefLieu.setText(d.getChefLieu());
            txtDateCreation.setText(d.getDateCreation());
            txtUrlWiki.setText(d.getUrlWiki());
        } catch (DbException ex) {
            affToast(this, "Erreur base de données : " + ex.getMessage());
        } catch (Exception ex) {
            affToast(this, ex.getMessage());
        }
    }
    public void btnSave(View v){


    }
    public void btnDelete(View v){
    }
}

