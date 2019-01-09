package com.maugarciaf.basiccrudjava;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper myDb;
    EditText txtId, txtName, txtSurname, txtEmail;
    TextView txtReadData;
    Button btnClick;
    Button btnReadData;
    Button btnUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb  = new DataBaseHelper(this);
        txtId    =  (EditText) findViewById(R.id.id_txt);
        txtName     = (EditText)findViewById(R.id.id_name) ;
        txtSurname  = (EditText)findViewById(R.id.id_Surname);
        txtEmail    = (EditText)findViewById(R.id.id_email);
        btnClick    = (Button)findViewById(R.id.id_insert);
        btnReadData = (Button)findViewById(R.id.id_read);
        btnUpdate = (Button)findViewById(R.id.id_update);
        btnDelete = (Button)findViewById(R.id.id_delete);
        txtReadData =(TextView)findViewById(R.id.txt);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe();
            }
        });

        btnReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReadData();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });
    }

    // Insertar Datos
    private void ClickMe() {
        String name = txtName.getText().toString();
        String surname = txtSurname.getText().toString();
        String email = txtEmail.getText().toString();
        //verificar  si los campos se dejan vacios
        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(surname)||TextUtils.isEmpty(email) ){
            txtName.setError("The item  cannot be empty");
            txtSurname.setError("The item  cannot be empty");
            txtEmail.setError("The item  cannot be empty");
            Toast.makeText(this, "Data Insertion Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
            myDb.insertData(name,surname,email);
        }
    }

    // Leer datos insertados
    private void ReadData() {
        Cursor res = myDb.getAlldata();
        StringBuffer stringBuffer = new StringBuffer();
        if (res !=null && res.getCount()>0){
            while(res.moveToNext()){
                stringBuffer.append("ID: " + res.getString(0)+ "\n");
                stringBuffer.append("Name: " + res.getString(1)+ "\n");
                stringBuffer.append("Surname: " + res.getString(2)+ "\n");
                stringBuffer.append("Email: " + res.getString(3)+ "\n\n");
            }
            txtReadData.setText(stringBuffer.toString());
            Toast.makeText(this, "Data Retrieved Successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No Data to Retrieved", Toast.LENGTH_SHORT).show();
        }
    }

    // Actualizar data
    private void UpdateData() {
        String id = txtId.getText().toString();
        String name = txtName.getText().toString();
        String surname = txtSurname.getText().toString();
        String email = txtEmail.getText().toString();
        Boolean update = myDb.updateData(id,name,surname,email);
        if (update == true){
            Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data is not Updated", Toast.LENGTH_SHORT).show();
        }
    }

    // Delete Data
    public void deleteData(){
        String id = txtId.getText().toString();
        int delete = myDb.deleteData(id);
        Toast.makeText(this, delete+":Rows Affected", Toast.LENGTH_SHORT).show();
    }

}
