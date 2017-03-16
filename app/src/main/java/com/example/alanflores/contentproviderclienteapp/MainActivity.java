package com.example.alanflores.contentproviderclienteapp;

import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    Button button;
    TextView textView;
    CursorLoader cursorLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.nutton_solicitar);
        textView = (TextView) findViewById(R.id.text_nombres);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportLoaderManager().initLoader(1, null, MainActivity.this);
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        cursorLoader = new CursorLoader(this, Uri.parse("content://com.example.alanflores.contentproviderapp.noProblemCOnPAquete/cte"),null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        StringBuilder stringBuilder = new StringBuilder();
        while(!data.isAfterLast()){
            stringBuilder.append("\n" + data.getString(data.getColumnIndex("id")) + " - " + data.getString(data.getColumnIndex("name")));
            data.moveToNext();
        }
        textView.setText(stringBuilder);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
