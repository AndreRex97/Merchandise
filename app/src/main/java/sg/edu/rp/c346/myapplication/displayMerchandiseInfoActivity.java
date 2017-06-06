package sg.edu.rp.c346.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

@SuppressLint("NewApi")
public class displayMerchandiseInfoActivity extends Activity {

    private String id;
    Button btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_merchandise_info);

        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDetailsButtonClicked(view);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRecordButtonClicked(view);
            }
        });
    }

    protected void onStart(){
        super.onStart();
        Intent intent = getIntent();
        id = intent.getStringExtra("com.example.MAIN_MESSAGE");
        HttpRequest request= new HttpRequest("http://10.0.2.2/C302_P07/getItemById.php?Id=" + id);
        request.setMethod("GET");
        request.execute();
        try{
            String jsonString = request.getResponse();
            JSONObject jsonObj = new JSONObject(jsonString);
            // TODO 01: Set values in the EditText fields
            EditText etItemName = (EditText)findViewById(R.id.etItemName);
            EditText etQuantity = (EditText)findViewById(R.id.etQuantity);
            EditText etPrice = (EditText)findViewById(R.id.etPrice);

            etItemName.setText(jsonObj.getString("item_name"));
            etQuantity.setText(jsonObj.getString("quantity"));
            etPrice.setText(jsonObj.getString("price"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateDetailsButtonClicked(View view){

        EditText etItemName = (EditText)findViewById(R.id.etItemName);
        EditText etQuantity = (EditText)findViewById(R.id.etQuantity);
        EditText etPrice = (EditText)findViewById(R.id.etPrice);


        //TODO 03: Send the HttpRequest to updateContact.php
        HttpRequest request = new HttpRequest("http://10.0.2.2/C302_P07/updateItemById.php");
        request.setMethod("POST");
        request.addData("id", id);
        request.addData("item_name", etItemName.getText().toString());
        request.addData("quantity", etQuantity.getText().toString());
        request.addData("price", etPrice.getText().toString());
        request.execute();
        Toast.makeText(displayMerchandiseInfoActivity.this, "Perform TODO task 3", Toast.LENGTH_SHORT).show();

        try{
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRecordButtonClicked(View view){
        //TODO 04: Send HttpRequest to removeContact.php
        HttpRequest request = new HttpRequest("http://10.0.2.2/C302_P07/deleteItem.php");
        request.setMethod("POST");
        request.addData("Id", id);
        request.execute();
        Toast.makeText(displayMerchandiseInfoActivity.this, "Perform TODO task 4", Toast.LENGTH_SHORT).show();

        try{
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
