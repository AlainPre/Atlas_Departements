package fr.formation.sqlitedepts;

import android.content.Context;
import android.widget.Toast;

public class Tools {

    public static void affToast(Context ctxt, String msg) {
        Toast.makeText(ctxt, msg, Toast.LENGTH_LONG).show();
    }

}
