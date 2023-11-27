package com.example.promul_firebase_carlosmilenaquesada;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.promul_firebase_carlosmilenaquesada.clases.Producto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRefproductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        //creamos una conexión a la base de datos firebase
        database = FirebaseDatabase.getInstance();
        //podemos asignarle una ruta, para conectar internamente con el árbol
        DatabaseReference dbr = database.getReference();//("alumnos");
        //si en lugar de alumnos no ponemos nada, se va a la raíz
        //para crear un nodo hijo (se actualzia automáticamente en tipo real en la bd)
        //dbr.child("hola mundo").setValue("Eso es un hola mundo");

        //el .child() es para movernos entre niveles de nodos.

        //vamos a insertar un producto identificado como p1, dentro de productos
        Producto p1 = new Producto("p1", "balón", 5, 20.3);
        dbr.child("productos").child(p1.getIdProducto()).setValue(p1);

        //vamos a crear un segundo registro con identificador aleatorio, usamos el push
        //Producto p2 = new Producto("p2", "manzanas", 25, 2.3);
        //dbr.child("productos").push().setValue(p2);

        //vamos ahora a borrar un registro.
        //dbr.child("productos").child(p1.getIdProducto()).removeValue();
        //otra forma de borrar
        dbr.child("productos").child(p1.getIdProducto()).setValue(null);
        */


        /*VAMOS A LEER DATOS DE LA BD Y VAMOS A AÑADIRLO A UN RECICLERVIEW, PARA QUE LO MUESTRE DE FROMA ASÍNCRONA
         * CON A TRAVÉS DE UN ADAPTER*/
        //1º DEBEMOS POSICIONARNOS EN EL NODO A LEER
        //2º LE AÑADIMOS UN LISTENER, PARA ESTAR A LA ESCUCHA DE LOS CAMBIOS.

        //El siguiente ejemplo es dado por el maestro


        // LEER UN ALUMNO CON CLAVE "clave2"
        // Read from the database alumno key -> "clave2"

        //1ºcreamos la instancia a la base de datos
        database = FirebaseDatabase.getInstance();
        //2ºcreamos una referencia al node
        myRefproductos = database.getReference("productos");
        //3º añadimos el listener
        /*myRefproductos.child("asdf").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                Producto value = dataSnapshot.getValue(Producto.class);
                if (value != null) {
                    System.out.println(value.toString());
                    Log.i("firebase1", value.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("firebase1", String.valueOf(error.toException()));
            }
        });*/

        //AHora igual pero con un conjunto de productos


        myRefproductos.child("asdf").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<String> keys = new ArrayList<String>();
                List<Producto> productos = new ArrayList<Producto>();
                for (DataSnapshot keynode : snapshot.getChildren()) {
                    keys.add(keynode.getKey());
                    productos.add(keynode.getValue(Producto.class));
                }
                for (String k : keys) {
                    System.out.println(k);
                    Log.i("firebase1", "clave leida " + k);
                }
                for (Producto a : productos) {
                    System.out.println(a.toString());
                    Log.i("firebase1", "producto leido " + a.toString());
                }

                if (value != null) {
                    System.out.println(value.toString());
                    Log.i("firebase1", value.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("firebase1", String.valueOf(error.toException()));
            }
        });
    }


}
/*
        //--------------------------------------------------------
       // LEER TODOS LOS productos
        DatabaseReference myRefproductos = database.getReference("productos");
        myRefproductos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> keys = new ArrayList<String>();
                List<Producto> productos = new ArrayList<Producto>();
                for (DataSnapshot keynode : snapshot.getChildren()) {
                    keys.add(keynode.getKey());
                    productos.add(keynode.getValue(Producto.class));
                }
                for (String k : keys) {
                    System.out.println(k);
                    Log.i("firebase1", "clave leida " + k);
                }
                for (Producto a : productos) {
                    System.out.println(a.toString());
                    Log.i("firebase1", "producto leido " + a.toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        productos = new ArrayList<Producto>();

        //-----------------------------------------------------------
        adapter = new ListaProductosAdapter(this,productos);
        rv_productos.setAdapter(adapter);
        myRefProductos = database.getReference("productos");
        myRefProductos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // adapter.getProductos().clear();
                ArrayList<Producto> productos = new ArrayList<Producto>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Producto a = (Producto) dataSnapshot.getValue(Producto.class);
                    productos.add(a);
                }
                adapter.setProductos(productos);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "firebase1", String.valueOf(error.toException()));
            }
        });

        //-------------------------------------------------------------
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            rv_productos.setLayoutManager(new GridLayoutManager(this,2));
        } else {
            // In portrait
            rv_productos.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    //---------------------------------------------------------------------------------
    public void buscarProductos(View view) {

        String texto = String.valueOf(edt_buscar_productos.getText());
        myRefProductos = database.getReference("productos");
        myRefProductos.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //   ArrayList<String> keys1 = new ArrayList<String>();
                ArrayList<Producto> productos = new ArrayList<Producto>();
                for (DataSnapshot keynode : snapshot.getChildren()) {
                    //        keys1.add(keynode.getKey());
                    Producto a = keynode.getValue(Producto.class);
                    if (a.getNombre().contains(texto)) {
                        productos.add(keynode.getValue(Producto.class));
                    }
                }
                adapter.setProductos(productos);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("firebase1", String.valueOf(error.toException()));
            }
        });

    }

    public void insertarProductos(View view) {
        Intent intent = new Intent(this, NuevoProductoActivity.class);
        startActivity(intent);

    }




 */

