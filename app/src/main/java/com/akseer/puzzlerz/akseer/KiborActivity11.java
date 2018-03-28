package com.akseer.puzzlerz.akseer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KiborActivity11 extends AppCompatActivity {




    private String classtag= BrokerActivity19.class.getSimpleName();  //return tenor of underlying class
    private ListView listViewKibor;
    private ListView listViewLibor;
    private ListView listViewPKRV;
    private ProgressDialog progress;
    //private String url="https://raw.githubusercontent.com/iCodersLab/JSON-Parsing-in-Android-using-Android-Studio/master/index.html"; //passing url
    private String url="http://videostreet.pk/tejori/tjApi/getCategoryData"; //passing url
    ArrayList<HashMap<String,String>> listTab1;
    ArrayList<HashMap<String,String>> listTab2; //arraylist to save key value pair from json
    ArrayList<HashMap<String,String>> listTab3; //arraylist to save key value pair from json
    ArrayList<HashMap<String,String>> listTab4; //arraylist to save key value pair from json
    //arraylist to save key value pair from json
    private   TabHost host;


    private TextView mTextMessage;





    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.News:
                    startActivity(new Intent(KiborActivity11.this, NewsActivity4.class));finish();
                    return true;
                case R.id.Market:

                    startActivity(new Intent(KiborActivity11.this, KiborActivity11.class));finish();

                    return true;

                case R.id.Akseer:
                    startActivity(new Intent(KiborActivity11.this, AkseerResearchActivity15.class));finish();

                    return true;

                case R.id.Broker:

                    startActivity(new Intent(KiborActivity11.this, BrokerActivity19.class));finish();

                    return true;
            }
            return false;
        }

    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Disclaimer:
                startActivity(new Intent(KiborActivity11.this, DisclaimerActivity16.class));
                return true;
        /*    case R.id.PerformanceReviews:
                startActivity(new Intent(KiborActivity11.this, PR18Activity.class));
                return true;
            case R.id.additiondeletion:
                startActivity(new Intent(KiborActivity11.this, AdditionDeletion17.class));
                return true;*/
            case R.id.logout:
                startActivity(new Intent(KiborActivity11.this, LoginActivity1.class));
                finishAffinity();
                finish();

                return true;
            case R.id.Share:
//                startActivity(new Intent(NewsInsideActivity5.this, AdditionDeletion17.class));
                return true;
            case R.id.aboutus:
//                startActivity(new Intent(NewsInsideActivity5.this, AdditionDeletion17.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kibor11);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setSelectedItemId(R.id.Market);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        host = (TabHost)findViewById(R.id.TabHost);
        host.setup();



        listTab1=new ArrayList<>();
        listTab2=new ArrayList<>();
        listTab3=new ArrayList<>();
      //  listTab4=new ArrayList<>();
        listViewKibor= (ListView) findViewById(R.id.list2_Kibor);
        listViewLibor= (ListView) findViewById(R.id.list_Libor);
        listViewPKRV= (ListView) findViewById(R.id.list2_PKRV);//from home screen list view
        new KiborActivity11.getStudents().execute(); // it will execute your AsyncTask

        TabHost.TabSpec spec = host.newTabSpec("KIBOR");
        spec.setContent(R.id.Kibor);
        spec.setIndicator("Kibor");
        host.addTab(spec);


        //Tab 2
        spec = host.newTabSpec("LIBOR");
        spec.setContent(R.id.Libor);
        spec.setIndicator("Libor");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("PKRV");
        spec.setContent(R.id.PKRV);
        spec.setIndicator("PKRV");
        host.addTab(spec);
        final String[] array = getResources().getStringArray(R.array.country_arrays);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setSelection(1);







        //   spin.setAdapter(new ArrayAdapter<String>(MarketsActivity6.this, R.layout.a_layout, array));
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {

                switch (position) {
                    case 0:
                        startActivity(new Intent(KiborActivity11.this, MarketsActivity6.class));
                        break;
                    case 1:
                       // startActivity(new Intent(KiborActivity11.this, KiborActivity11.class));
                        break;
                    case 2:
                        startActivity(new Intent(KiborActivity11.this, ForexRatesActivity14.class));
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



























    }


    public class getStudents extends AsyncTask<Void,Void,Void> {
        protected void onPreExecute(){
            super.onPreExecute(); //it will use pre defined preExecute method in async task
            progress=new ProgressDialog(KiborActivity11.this);
            progress.setMessage("Loading data..."); // show what you want in the progress dialog
            progress.setCancelable(false); //progress dialog is not cancellable here
            progress.show();





        }
        protected Void doInBackground(Void...arg0){

            Map<String, String> headers = new HashMap<>();
            headers.put("X-TJ-APIKEY", "876564123");
            headers.put("Content-Type","application/x-www-form-urlencoded");
            String result="";
            try {
                result= HttpRequestSomething2.makePostRequest(url,headers);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("Result",result);
            //HTTP_Handler hh = new HTTP_Handler(); // object of HTTP_Handler
            // String jString = hh.makeHTTPCall(url); //calling makeHTTPCall method and string its response in a string
            Log.e(classtag, "Response from URL: " + result);
            if (result != null) try {
                JSONObject jObj = new JSONObject(result);
                JSONObject categoryObject = jObj.getJSONObject("data");
                JSONObject categoryObject1 = categoryObject.getJSONObject("money-market");
                //JSONArray categoryObject2 = categoryObject1.getJSONArray("data");
                //for (int j = 0; j < categoryObject2.length(); j++) {

                    JSONObject kiborObject = categoryObject1.getJSONObject("kibor");
                JSONObject liborObject = categoryObject1.getJSONObject("libor");
                JSONObject pkrvObject = categoryObject1.getJSONObject("pkrv");
                Log.e(classtag, "Response from kibor: " + kiborObject);
                Log.e(classtag, "Response from libor: " + liborObject);
                Log.e(classtag, "Response from pkrv: " + pkrvObject);
                    // Log.e(classtag, "Response 2 from URL: " + categoryObject2);
                    //our json data starts with the object
                    JSONArray resultArray = kiborObject.getJSONArray("data"); //fetch array from studentsinfo object

                    for (int i = 0; i < resultArray.length(); i++) {

                        JSONObject resultElement = resultArray.getJSONObject(i); //get object from i index
                        String tenor = resultElement.getString("tenor");   //save string from variable 'id' to string
                        String bid = resultElement.getString("bid");
                        String offer = resultElement.getString("offer");
                        String frequency = resultElement.getString("frequency");
                        if(frequency.equals("w"))
                            frequency = " Week";
                        else if(frequency.equals("m"))
                            frequency = " Month";
                        else if(frequency.equals("y"))
                            frequency = " Year";
                        else if(frequency.equals("d"))
                            frequency = " Day";
                        else if(frequency.equals("on"))
                        {
                            frequency = "Today";
                            tenor ="";

                        }

                        HashMap<String, String> resultRow = new HashMap<>(); //create a hash map to set key and value pair

                        resultRow.put("tenor", tenor); //hash map will save key and its value
                        resultRow.put("bid", bid);
                        resultRow.put("offer", offer);
                        resultRow.put("frequency", frequency);
                        listTab1.add(resultRow); //now save all of the key value pairs to arraylist

                    }
                        resultArray = liborObject.getJSONArray("data"); //fetch array from studentsinfo object

                        for (int i = 0; i < resultArray.length(); i++) {

                            JSONObject resultElement = resultArray.getJSONObject(i); //get object from i index
                            String tenor = resultElement.getString("tenor");   //save string from variable 'id' to string
                         //   String bid = resultElement.getString("bid");
                            String rate = resultElement.getString("rate");
                            String frequency = resultElement.getString("frequency");
                            if(frequency.equals("w"))
                                frequency = " Week";
                            else if(frequency.equals("m"))
                                frequency = " Month";
                            else if(frequency.equals("y"))
                                frequency = " Year";
                            else if(frequency.equals("d"))
                                frequency = " Day";
                            else if(frequency.equals("on"))
                            {
                                frequency = "Today";
                                tenor ="";

                            }

                            HashMap<String, String> resultRow = new HashMap<>(); //create a hash map to set key and value pair

                            resultRow.put("tenor", tenor); //hash map will save key and its value
                           // resultRow.put("bid", bid);
                            resultRow.put("rate", rate);
                            resultRow.put("frequency", frequency);
                            listTab2.add(resultRow); //now save all of the key value pairs to arraylist


                        }

                resultArray = pkrvObject.getJSONArray("data"); //fetch array from studentsinfo object

                for (int i = 0; i < resultArray.length(); i++) {

                    JSONObject resultElement = resultArray.getJSONObject(i); //get object from i index
                    String tenor = resultElement.getString("tenor");   //save string from variable 'id' to string
                    String bid = resultElement.getString("mid_rate");
                    String change = resultElement.getString("change");
                    String frequency = resultElement.getString("frequency");
                    if(frequency.equals("w"))
                        frequency = " Week";
                    else if(frequency.equals("m"))
                        frequency = " Month";
                    else if(frequency.equals("y"))
                        frequency = " Year";
                    else if(frequency.equals("d"))
                        frequency = " Day";
                    else if(frequency.equals("on"))
                    {
                        frequency = "Today";
                        tenor ="";

                    }

                    HashMap<String, String> resultRow = new HashMap<>(); //create a hash map to set key and value pair

                    resultRow.put("tenor", tenor); //hash map will save key and its value
                    resultRow.put("bid", bid);
                    resultRow.put("change", change);
                    resultRow.put("frequency", frequency);
                    listTab3.add(resultRow); //now save all of the key value pairs to arraylist


                }

            } catch (final JSONException e) {
                Log.e(classtag, "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show(); //show if you catch any exception with data
                    }
                });
            }
            else {
                Log.e(classtag, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {Toast.makeText(getApplicationContext(),
                            "Couldn't get json from server. Check internet connection!",
                            Toast.LENGTH_LONG).show();//show if you are unable to connect with the internet or if jString is null
                    }
                });
            }
            return null;
        }






        protected void onPostExecute(Void Result){

            super.onPostExecute(Result);
            if(progress.isShowing()){
                progress.dismiss();
            }
            host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

                public void onTabChanged(String tabId) {
                    //.setCurrentItem(Integer.parseInt(tabId));
                   /* Toast.makeText(getApplicationContext(),
                            tabId,
                            Toast.LENGTH_LONG)
                            .show();*/

                    if(tabId == "PKRV")
                    {
                        ListAdapter adapter=new SimpleAdapter(


                                KiborActivity11.this,
                                listTab3,
                                R.layout.bucket_list4,
                                new String[]{"tenor","bid","change","frequency"},
                                new int[]{R.id.textViewDays,R.id.bid,R.id.offer ,R.id.textViewMonth});
                        listViewPKRV.setAdapter(adapter);
                    }

                    else if(tabId == "LIBOR")
                    {
                        ListAdapter adapter=new SimpleAdapter(


                                KiborActivity11.this,
                                listTab2,
                                R.layout.bucket_list4,
                                new String[]{"tenor","rate","frequency"},
                                new int[]{R.id.textViewDays,R.id.offer ,R.id.textViewMonth});
                        listViewLibor.setAdapter(adapter);
                    }

                    else
                    {
                        ListAdapter adapter=new SimpleAdapter(


                                KiborActivity11.this,
                                listTab1,
                                R.layout.bucket_list4,
                                new String[]{"tenor","bid","offer","frequency"},
                                new int[]{R.id.textViewDays,R.id.bid,R.id.offer ,R.id.textViewMonth});
                        listViewKibor.setAdapter(adapter);
                    }
                    }

            });
                 ListAdapter adapter=new SimpleAdapter(


                                KiborActivity11.this,
                                listTab1,
                                R.layout.bucket_list4,
                                new String[]{"tenor","bid","offer","frequency"},
                                new int[]{R.id.textViewDays,R.id.bid,R.id.offer ,R.id.textViewMonth});
                        listViewKibor.setAdapter(adapter);
                    }

                  /*  else if(position == 1)
                    {
                        ListAdapter adapter=new SimpleAdapter(


                                KiborActivity11.this,
                                listTab2,
                                R.layout.bucket_list3,
                                new String[]{"tenor","bid","offer","frequency","todays_change","imapact"},
                                new int[]{R.id.list_id,R.id.list_title,R.id.list_description ,R.id.list_category_tenor,R.id.list_post_frequencytime,R.id.list_author});
                        lv.setAdapter(adapter);
                    }

                    else if(position == 2)
                    {
                        ListAdapter adapter=new SimpleAdapter(


                                KiborActivity11.this,
                                listTab3,
                                R.layout.bucket_list3,
                                new String[]{"tenor","bid","offer","frequency","todays_change","imapact"},
                                new int[]{R.id.list_id,R.id.list_title,R.id.list_description ,R.id.list_category_tenor,R.id.list_post_frequencytime,R.id.list_author});
                        lv.setAdapter(adapter);
                    }

                    if(position == 3)
                    {
                        ListAdapter adapter=new SimpleAdapter(


                                KiborActivity11.this,
                                listTab4,
                                R.layout.bucket_list3,
                                new String[]{"tenor","bid","offer","frequency","todays_change","imapact"},
                                new int[]{R.id.list_id,R.id.list_title,R.id.list_description ,R.id.list_category_tenor,R.id.list_post_frequencytime,R.id.list_author});
                        lv.setAdapter(adapter);
                    } }*/


//
//            ListAdapter adapter=new SimpleAdapter(
//
//
//                    KiborActivity11.this,
//                    listTab1,
//                    R.layout.bucket_list4,
//                    new String[]{"tenor","bid","offer","frequency"},
//                    new int[]{R.id.textViewDays,R.id.bid,R.id.offer ,R.id.textViewMonth});
//            lv.setAdapter(adapter);







//            SimpleAdapter (Context context,                   //
//                    List<? extends Map<String, ?>> data,      //
//            int resource,                                     //
//            String[] from,                                    //
//            int[] to)                                         //
//this will pass your json values to the bucket_list xml and whatever it is stored of key 'tenor' will be
// displayed to text view list_tenor
        }
    }


//A}
