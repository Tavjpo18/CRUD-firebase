package com.example.api_crud_mock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnClick, btEdit, btDelete;
    TextView tvDisplay;
    EditText Mssv;
    EditText Name;
    EditText Email;
    Button btAdd;
    int index;
    ListView lvUsers;
    UserAdapter adt;
    ArrayList<User> arrayList;

    String url = "https://5fceceb03e19cc00167c6327.mockapi.io/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvUsers =  findViewById(R.id.lvUsers);
        arrayList = new ArrayList<>();

        btnClick = (Button) findViewById(R.id.btnClick);
        btAdd = findViewById(R.id.btAdd);
        btEdit = findViewById(R.id.btEdit);
        btDelete = findViewById(R.id.btDel);

        Mssv = findViewById(R.id.txtMssv);
        Name = findViewById(R.id.txtName);
        Email = findViewById(R.id.txtEmail);

        adt = new UserAdapter(this, R.layout.items, arrayList);
        lvUsers.setAdapter(adt);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetArrayJson(url);
                lvUsers.removeAllViewsInLayout();
                arrayList.clear();
            }
        });
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostApi(url);
//                lvUsers.removeAllViewsInLayout();
//                arrayList.clear();
                Toast.makeText(MainActivity.this, "Đã Thêm", Toast.LENGTH_SHORT).show();
                GetArrayJson(url);
            }
        });
        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = arrayList.get(i);
                index = Integer.parseInt(user.getId());

            }
        });
        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PutApi(url,index);
                lvUsers.removeAllViewsInLayout();
                arrayList.clear();
                GetArrayJson(url);
                Toast.makeText(MainActivity.this, "Đã sữa!", Toast.LENGTH_SHORT).show();
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteApi(url,index);
                lvUsers.removeAllViewsInLayout();
                arrayList.clear();
                GetArrayJson(url);
                Toast.makeText(MainActivity.this, "Đã xóa!", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void GetArrayJson(final String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject object = (JSONObject) response.get(i);
                        String txtid = object.getString("id").toString();
                        String txtMssv = object.getString("MSSV").toString();
                        String txtName = object.getString("NAME").toString();
                        String txtEmail = object.getString("EMAIL").toString();
                        User user = new User(txtid, txtMssv, txtName, txtEmail);
                        arrayList.add(user);
                        tvDisplay.setText(arrayList.get(i).getMSSV());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by get Json Array!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
    private void PostApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                String txMssv=  Mssv.getText().toString();
                String txName = Name.getText().toString();
                String txEmail = Email.getText().toString();
                params.put("MSSV", txMssv);
                params.put("NAME", txName);
                params.put("EMAIL", txEmail);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void PutApi(String url, int i){
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT,
                url + '/' + i, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                String txMssv=  Mssv.getText().toString();
                String txName = Name.getText().toString();
                String txEmail = Email.getText().toString();
                params.put("MSSV", txMssv);
                params.put("NAME", txName);
                params.put("EMAIL", txEmail);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void DeleteApi(String url, int i){
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' + i, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
