package com.example.promul_firebase_carlosmilenaquesada;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.promul_firebase_carlosmilenaquesada.clases.Producto;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NuevoProductoActivity extends AppCompatActivity {

    EditText edt_nuevo_idproducto;
    EditText edt_nuevo_nombre;
    EditText edt_nuevo_cantidadp;
    EditText edt_nuevo_preciop;
private FirebaseDatabase database;
private DatabaseReference dbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);
        edt_nuevo_idproducto = (EditText) findViewById(R.id.edt_nuevo_idproducto);
        edt_nuevo_nombre = (EditText) findViewById(R.id.edt_nuevo_nombre);
        edt_nuevo_cantidadp = (EditText) findViewById(R.id.edt_nuevo_cantidadp);
        edt_nuevo_preciop = (EditText) findViewById(R.id.edt_nuevo_preciop);
        database = FirebaseDatabase.getInstance();
        dbr = database.getReference();
    }

    public void insertarProducto(View view) {
        String identificador = String.valueOf(edt_nuevo_idproducto.getText());
        String nombre = String.valueOf(edt_nuevo_nombre.getText());
        int cantidad = Integer.valueOf(String.valueOf(edt_nuevo_cantidadp.getText()));
        double precio = Double.valueOf(String.valueOf(edt_nuevo_preciop.getText()));
        Producto p1 = new Producto(identificador,nombre,cantidad,precio);
        dbr.child("productos").child(p1.getIdProducto()).setValue(p1);
    }
}