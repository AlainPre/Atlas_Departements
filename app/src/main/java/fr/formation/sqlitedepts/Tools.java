package fr.formation.sqlitedepts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class Tools {

    public static void affToast(Context ctxt, String msg) {
        Toast.makeText(ctxt, msg, Toast.LENGTH_LONG).show();
    }
/*
    public static boolean askConfirm(Context ctxt, String msg) {
        final Boolean result;
        AlertDialog.Builder dlg = new AlertDialog.Builder(ctxt);
        dlg.setTitle("");
        dlg.setMessage(msg);
        dlg.setIcon(android.R.drawable.ic_dialog_alert);
        dlg.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //result = true;
            }
        });
        dlg.setNegativeButton(android.R.string.no, null);
        dlg.show();
        return true;
    }
    */
}
