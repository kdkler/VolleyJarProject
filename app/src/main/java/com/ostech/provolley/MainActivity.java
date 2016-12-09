package com.ostech.provolley;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView tvID;
    static ProgressBar Ppbar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



tvID=(TextView)findViewById(R.id.tvID);
        Ppbar1=(ProgressBar)findViewById(R.id.pbar1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }



    public String getStringReq(String url)
    {
        StringRequest strReq=new StringRequest(Request.Method.POST, "", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        return "";
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //第一个
            Ppbar1.setVisibility(View.VISIBLE);
            String url="http://www.selftech.cn/api/timeline/TimeLineEntity";
            String tag="selfstrget";
            VolleyRequest.RequestStringGet(this,url,tag,new VolleyIntfcString(this, VolleyIntfcString._listener, VolleyIntfcString._errListener){
                @Override
                public void onMySuccess(String result) {
                    Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
                    tvID.setText(result);
                    Ppbar1.setVisibility(View.GONE);
                }

                @Override
                public void onMyError(VolleyError error) {
                    Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    tvID.setText(error.getMessage());
                    Ppbar1.setVisibility(View.GONE);
                }

            });



        } else if (id == R.id.nav_gallery) {
            //第二个
            Ppbar1.setVisibility(View.VISIBLE);
            String url = "http://www.selftech.cn/api/AppUser/AppUserAdd";
            String tag = "selfstrpost";
            Map<String, String> params = new HashMap<String, String>();
            params.put("RegKey","1070334591");
            params.put("UserName","淇园散人");
            params.put("Signature","原谅我一生爱自由");
            params.put("RegFrom","other");
            params.put("RegName","text");
            params.put("UserGender","1");
            VolleyRequest.RequestStringPost(this, url, tag, params, new VolleyIntfcString(this, VolleyIntfcString._listener, VolleyIntfcString._errListener) {
                @Override
                public void onMySuccess(String result) {
                    tvID.setText("添加成功,获取到的用户id是"+result);
                    Ppbar1.setVisibility(View.GONE);

                }

                @Override
                public void onMyError(VolleyError error) {
                    tvID.setText("添加失败,原因是"+error.getMessage());
                    Ppbar1.setVisibility(View.GONE);
                }
            });



        } else {
            if (id == R.id.nav_slideshow) {
                //第三个
                String url="http://www.selftech.cn/api/timeline/TimeLineEntity";
                String tag="selfobjget";
                VolleyRequest.RequestJsonObjGet(this,url,tag,new VolleyIntfcObject(this, VolleyIntfcObject._listenerObject, VolleyIntfcObject._errListener){


                    @Override
                    public void onMySuccessObject(JSONObject object) {
                        Toast.makeText(MainActivity.this,object.toString(),Toast.LENGTH_LONG).show();
                        tvID.setText(object.toString());
                        Ppbar1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                        tvID.setText(error.getMessage());
                        Ppbar1.setVisibility(View.GONE);
                    }

                });

            } else if (id == R.id.nav_manage) {
                //第四个
                Ppbar1.setVisibility(View.VISIBLE);
                String url = "http://www.selftech.cn/api/AppUser/AppUserAdd";
                //聚合数据账号 126 sing
                url="http://v.juhe.cn/weather/index";
                String tag = "selfobjpost";
                Map<String, String> params = new HashMap<String, String>();
//                params.put("RegKey","1070334592");
//                params.put("UserName","淇园散人2");
//                params.put("Signature","原谅我一生爱自由2");
//                params.put("RegFrom","other");
//                params.put("RegName","text");
//                params.put("UserGender","1");
                //cityname=beijing&dtype=&format=&key=ee1fa46de7b0af15d2e7761cc3fdb2b0
                params.put("cityname","beijing");
                params.put("key","ee1fa46de7b0af15d2e7761cc3fdb2b0");

                JSONObject jojbect=new JSONObject(params);
                VolleyRequest.RequestJsonObjPost(this, url, tag, jojbect, new VolleyIntfcObject(this, VolleyIntfcObject._listenerObject, VolleyIntfcString._errListener) {
                    @Override
                    public void onMySuccessObject(JSONObject object) {
                        //经过测试,发现调用的api 返回的值必须是一个json对象,如果返回的是字符串,这个方法会报错,提示返回的值不能convert成jsonObject类型
                        //这个方法需要注意了.
                        tvID.setText("添加成功,获取到的用户id是"+object.toString());
                        Ppbar1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        tvID.setText("添加失败,原因是"+error.getMessage());
                        Ppbar1.setVisibility(View.GONE);
                    }
                });


            } else if (id == R.id.nav_share) {
                //第五个
                String url="http://www.selftech.cn/api/timeline/timelinelist";
                String tag="selfarrget";
                VolleyRequest.RequestJsonArrayGet(this,url,tag,new VolleyIntfcArray(this, VolleyIntfcArray._listenerArrar, VolleyIntfcArray._errListener){

                    @Override
                    public void onMySuccessArry(JSONArray arrar) {
                        Toast.makeText(MainActivity.this,arrar.toString(),Toast.LENGTH_LONG).show();
                        tvID.setText("获取成功");
                        Ppbar1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onMyError(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                        tvID.setText(error.getMessage());
                        Ppbar1.setVisibility(View.GONE);
                    }

                });

            } else if (id == R.id.nav_send) {

            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
