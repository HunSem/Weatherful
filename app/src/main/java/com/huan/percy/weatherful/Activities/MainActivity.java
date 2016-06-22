package com.huan.percy.weatherful.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.huan.percy.weatherful.Model.WeatherInfo;
import com.huan.percy.weatherful.R;
import com.thinkpage.lib.api.TPCity;
import com.thinkpage.lib.api.TPListeners;
import com.thinkpage.lib.api.TPWeatherManager;
import com.thinkpage.lib.api.TPWeatherNow;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String KEY = "2txwvjv6b0knz6gi";
    private String ID = "U8F257FF5F";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UIThread().start();
                Snackbar.make(view, "更新了哟~", Snackbar.LENGTH_LONG)
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


    Handler handler = new Handler()
    {
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x123)
            {
                ImageView now_photo = (ImageView) findViewById(R.id.now_photo);
                TextView now_desc = (TextView) findViewById(R.id.now_desc);
                TextView now_location = (TextView) findViewById(R.id.now_location);
                TextView now_temp = (TextView) findViewById(R.id.now_temp);
                TextView now_tip = (TextView) findViewById(R.id.now_tip);

                SharedPreferences pref = getSharedPreferences("weatherData", MODE_PRIVATE);
                now_photo.setImageResource(pref.getInt("imageID", R.drawable.sunny));
                now_desc.setText(pref.getString("desc", "N/A"));
                now_location.setText(pref.getString("location", "未知"));
                now_temp.setText(pref.getString("temp", "N/A"));
                now_tip.setText("!!!");


            }
        }
    };

    class UIThread extends Thread
    {
        @Override
        public void run() {

            try {
                queryWeatherInfo();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x123);
        }
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
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
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
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void queryWeatherInfo(){
        final TPWeatherManager weatherManager = TPWeatherManager.sharedWeatherManager();
        //使用心知天气官网获取的key和用户id初始化WeatherManager
        weatherManager.initWithKeyAndUserId(KEY,ID);
        // 获取北京当前天气，使用简体中文、摄氏度
        weatherManager.getWeatherNow(TPCity.cityWithAutoRecognization()
                , TPWeatherManager.TPWeatherReportLanguage.kSimplifiedChinese
                , TPWeatherManager.TPTemperatureUnit.kCelsius
                , new TPListeners.TPWeatherNowListener() {
                    @Override
                    public void onTPWeatherNowAvailable(TPWeatherNow weatherNow, String errorInfo) {
                        if(weatherNow != null){
                            //weatherNow 就是返回的当前天气信息。
                            SharedPreferences.Editor editor = getSharedPreferences("weatherData",
                                    MODE_PRIVATE).edit();

                            editor.putInt("imageID", imageMatch(weatherNow.code));
                            editor.putString("location", "大连");
                            editor.putString("temp", weatherNow.temperature+"");
                            editor.putString("code", weatherNow.code);
                            editor.putString("desc", weatherNow.text);

                            Calendar c = Calendar.getInstance();
                            editor.putString("date", c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH));
                            editor.apply();
                        } else{
                            Log.d("TAG", errorInfo); //错误信息
                        }
                    }
                });
    }

    public int imageMatch(String code){
        int weatherCode = Integer.parseInt(code);
        switch (weatherCode) {
            case 0:
            case 2:
                return R.drawable.sunny;
            case 1:
            case 3:
                return R.drawable.clear_night;
            case 4:
            case 5:
            case 7:
            case 9:
                return R.drawable.sun_cloudy;
            case 6:
            case 8:
                return R.drawable.cloudy_night;
            default:
                return R.drawable.sunny;
        }
    }

}
