package fr.formation.sqlitedepts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
    public void btnNouveau(View v){
        clearIhm();
    }

    public void btnSearch(View v) {
        try {
            String no = txtSearch.getText().toString();
            Departement d = new Departement(this);
            d.select(no);
            deptToIhm(d);
        } catch (Departement.DbException ex) {
            clearIhm();
            affToast(this, "Erreur base de données : " + ex.getMessage());
        } catch (Exception ex) {
            affToast(this, ex.getMessage());
        }
    }
    public void btnSave(View v){
        Departement d = new Departement(this);
        try {
            d.select(txtNoDept.getText().toString());
            ihmToDept(d);
            d.update();
            affToast(this, "Département mis à jour");
        }
        catch(Departement.DbDeptNotFoundException ex) {
            ihmToDept(d);
            d.insert();
            affToast(this, "Département ajouté");
        }
        catch (Exception ex){
            affToast(this, ex.getMessage());
        }
    }
    public void btnDelete(View v){
        Runnable task = new Runnable()   {
            @Override
            public void run() {
                try {
                    Departement d = new Departement(MainActivity.this);
                    d.select(txtNoDept.getText().toString());
                    d.delete();
                    clearIhm();
                    affToast(MainActivity.this, "Département supprimé");
                } catch (Departement.DbException ex) {
                    affToast(MainActivity.this, "Erreur base de données : " + ex.getMessage());
                } catch (Exception ex) {
                    affToast(MainActivity.this, ex.getMessage());
                }
            }
        };
        runIfConfirm(this, "Confirmez-vous ?", task);
    }

    private void clearIhm() {
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
    private void deptToIhm(Departement d){
        txtNoDept.setText(d.getNoDept());
        txtNoRegion.setText(String.valueOf(d.getNoRegion()));
        txtNom.setText(d.getNom());
        txtNomStd.setText(d.getNomStd());
        txtSurface.setText(String.valueOf(d.getSurface()));
        txtChefLieu.setText(d.getChefLieu());
        txtDateCreation.setText(d.getDateCreation());
        txtUrlWiki.setText(d.getUrlWiki());
    }
    private void ihmToDept(Departement d){
        d.setNoDept(txtNoDept.getText().toString());
        d.setNoRegion(Integer.parseInt(txtNoRegion.getText().toString()));
        d.setNom(txtNom.getText().toString());
        d.setNomStd(txtNomStd.getText().toString());
        d.setSurface(Integer.parseInt(txtSurface.getText().toString()));
        d.setChefLieu(txtChefLieu.getText().toString());
        d.setDateCreation(txtDateCreation.getText().toString());
        d.setUrlWiki(txtUrlWiki.getText().toString());
    }
}

