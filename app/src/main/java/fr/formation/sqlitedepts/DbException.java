package fr.formation.sqlitedepts;


import android.content.Context;

public class DbException extends Exception {
    public DbException(String msg) {
        super(msg);
    }
    public DbException(Context ctxt, int stringId) {
        super(ctxt.getString(stringId));
    }
}
