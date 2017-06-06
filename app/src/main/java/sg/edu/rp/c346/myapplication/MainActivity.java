package sg.edu.rp.c346.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    ListView lvMerchandise;
    ArrayAdapter aaMerchandise;
    ArrayList<Merchandise> MerchandiseList = new ArrayList<Merchandise>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onResume(){
        super.onResume();
        MerchandiseList.clear();
        // Check if there is network access
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            HttpRequest request = new HttpRequest("http://10.0.2.2/C302_P07/getItems.php");
            request.setMethod("GET");
            request.execute();
            try{
                String jsonString = request.getResponse();
                System.out.println(">>" + jsonString);
                JSONArray jsonArray = new JSONArray(jsonString);

                // Populate the arraylist personList
                for(int i=0 ; i < jsonArray.length(); i++){
                    JSONObject jObj = jsonArray.getJSONObject(i);
                    Merchandise merchandise = new Merchandise();
                    merchandise.setId(jObj.getInt("id"));
                    merchandise.setItemName(jObj.getString("item_name"));
                    merchandise.setPrice(Double.parseDouble(jObj.getString("price")));
                    merchandise.setQuantity(Integer.parseInt(jObj.getString("quantity")));
                    MerchandiseList.add(merchandise);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            MerchandiseAdapter arrayAdapter = new MerchandiseAdapter(this, R.layout.row, MerchandiseList);
            lvMerchandise = (ListView) findViewById(R.id.lvMerchandise);
            lvMerchandise.setAdapter(arrayAdapter);
//            lvMerchandise.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View arg1, int arg2, long arg3) {
//
//                    Merchandise merchandise = (Merchandise) parent.getItemAtPosition(arg2);
//
//                    intent = new Intent(getApplicationContext(), DisplayUserInfoActivity.class);
//                    intent.putExtra("com.example.MAIN_MESSAGE", Integer.toString(person.getId()));
//                    startActivity(intent);
//                }
//            });
        } else {
            // AlertBox
//            showAlert();
        }
    }
}
