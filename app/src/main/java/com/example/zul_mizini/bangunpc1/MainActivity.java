package com.example.zul_mizini.bangunpc1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zul_mizini.bangunpc1.adapter.RecyclerMainAdapter;
import com.example.zul_mizini.bangunpc1.adapter.RecyclerViewAdapterHasil;
import com.example.zul_mizini.bangunpc1.helper.DetailBarangPilih;
import com.example.zul_mizini.bangunpc1.helper.DetailbarangpilihModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,GoogleApiClient.OnConnectionFailedListener {

    //firebase
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference2;
    private FirebaseUser user;
    private static final int RC_SIGN_IN = 9001;
    GoogleApiClient googleApiClient;

    private TextView tv_email;
    private RelativeLayout konten_gaAdaData;
    private RelativeLayout konten_belumLogin;
    private Button btn_daftar;
    private Button btn_masuk;
    private Button btn_logout;
    private RecyclerView rv_konten;
    private ScrollView sv_daftar;
    private ScrollView sv_login;
    private EditText et_email_daftar;
    private EditText et_email_login;
    private EditText et_password_daftar1;
    private EditText et_password_login;
    private EditText et_password_daftar2;
    private Button btn_bataldaftar;
    private Button btn_batalLogin;
    private Button btn_daftarHide;
    private Button btn_loginHide;
    private InterstitialAd mInterstitialAd;
    private SignInButton signInButton;

    private Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_email = (TextView) findViewById(R.id.tv_email);
        konten_gaAdaData = (RelativeLayout) findViewById(R.id.konten_gaAdaData);
        konten_belumLogin = (RelativeLayout) findViewById(R.id.konten_belumLogin);
        btn_daftar = (Button) findViewById(R.id.btn_daftar);
        btn_masuk = (Button) findViewById(R.id.btn_masuk);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        rv_konten = (RecyclerView) findViewById(R.id.rv_kontenx);
        sv_daftar = (ScrollView) findViewById(R.id.sv_daftar);
        sv_login = (ScrollView) findViewById(R.id.sv_login);
        et_email_daftar = (EditText) findViewById(R.id.et_email_daftar);
        et_email_login = (EditText) findViewById(R.id.et_email_login);
        et_password_daftar1 = (EditText) findViewById(R.id.et_password_daftar1);
        et_password_login = (EditText) findViewById(R.id.et_password_login);
        et_password_daftar2 = (EditText) findViewById(R.id.et_password_daftar2);
        btn_bataldaftar = (Button) findViewById(R.id.btn_bataldaftar);
        btn_batalLogin = (Button) findViewById(R.id.btn_batalLogin);
        btn_daftarHide = (Button) findViewById(R.id.btn_daftarHide);
        btn_loginHide = (Button) findViewById(R.id.btn_loginHide);
        signInButton = (SignInButton) findViewById(R.id.btn_sign_google);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        AdManager adManager = new AdManager(this,"ca-app-pub-3940256099942544/1033173712");
        adManager.createAd();


        //firebase
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        if (user!=null) {
            databaseReference = FirebaseDatabase.getInstance()
                    .getReference()
                    .child("riwayat")
                    .child(user.getUid());
        }

        cekLogin();

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv_konten.setVisibility(View.GONE);
                konten_gaAdaData.setVisibility(View.GONE);
                konten_belumLogin.setVisibility(View.GONE);
                btn_logout.setVisibility(View.GONE);
                sv_daftar.setVisibility(View.VISIBLE);
                sv_login.setVisibility(View.GONE);
            }
        });

        btn_bataldaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv_konten.setVisibility(View.GONE);
                konten_gaAdaData.setVisibility(View.GONE);
                konten_belumLogin.setVisibility(View.VISIBLE);
                btn_logout.setVisibility(View.GONE);
                sv_daftar.setVisibility(View.GONE);
                sv_login.setVisibility(View.GONE);
            }
        });

        btn_daftarHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesDaftar();
            }
        });

        btn_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv_konten.setVisibility(View.GONE);
                konten_gaAdaData.setVisibility(View.GONE);
                konten_belumLogin.setVisibility(View.GONE);
                btn_logout.setVisibility(View.GONE);
                sv_daftar.setVisibility(View.GONE);
                sv_login.setVisibility(View.VISIBLE);
            }
        });

        btn_batalLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv_konten.setVisibility(View.GONE);
                konten_gaAdaData.setVisibility(View.GONE);
                konten_belumLogin.setVisibility(View.VISIBLE);
                btn_logout.setVisibility(View.GONE);
                sv_daftar.setVisibility(View.GONE);
                sv_login.setVisibility(View.GONE);
            }
        });

        btn_loginHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesLogin();
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                make_toast("Berhasil Logout");
                rv_konten.setVisibility(View.GONE);
                konten_gaAdaData.setVisibility(View.GONE);
                konten_belumLogin.setVisibility(View.VISIBLE);
                btn_logout.setVisibility(View.GONE);
                sv_daftar.setVisibility(View.GONE);
                sv_login.setVisibility(View.GONE);
                tv_email.setText("Silahkan Login Terlebih Dahulu");
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent i = new Intent(MainActivity.this, Activity_pilihprocessor.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        databaseReference2 =FirebaseDatabase.getInstance().getReference();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("308666999125-i6rt1nelcn78ph6ei7a4lofl0gavg6f3.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                .enableAutoManage(MainActivity.this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    private void signIn() {
        Intent signin = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signin,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
               // Toast.makeText(MainActivity.this,"ini"+account.getIdToken(),Toast.LENGTH_LONG).show();
                authWithGoogle(account);
            }
        }
    }

    private void authWithGoogle(final GoogleSignInAccount account) {
        final AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"berhasil",Toast.LENGTH_LONG).show();
                    databaseReference2.child("USER")

                            .push()
                            .child(account.getId().toString())
                            .child("nama")
                            .setValue(account.getDisplayName())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    }).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                        }
                    });
                }else {
                    Toast.makeText(MainActivity.this,"gagal",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void cekLogin() {
        if (firebaseAuth.getCurrentUser() == null) {
            rv_konten.setVisibility(View.GONE);
            konten_gaAdaData.setVisibility(View.GONE);
            konten_belumLogin.setVisibility(View.VISIBLE);
            btn_logout.setVisibility(View.GONE);
            sv_daftar.setVisibility(View.GONE);
            sv_login.setVisibility(View.GONE);
            tv_email.setText("Silahkan Login Terlebih Dahulu");
        } else {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.exists()) {
                        rv_konten.setVisibility(View.GONE);
                        konten_gaAdaData.setVisibility(View.VISIBLE);
                        konten_belumLogin.setVisibility(View.GONE);
                        btn_logout.setVisibility(View.GONE);
                        sv_daftar.setVisibility(View.GONE);
                        sv_login.setVisibility(View.GONE);
                        tv_email.setText(firebaseAuth.getCurrentUser().getEmail());
                    } else {
                        rv_konten.setVisibility(View.VISIBLE);
                        konten_gaAdaData.setVisibility(View.GONE);
                        konten_belumLogin.setVisibility(View.GONE);
                        btn_logout.setVisibility(View.GONE);
                        sv_daftar.setVisibility(View.GONE);
                        sv_login.setVisibility(View.GONE);
                        tv_email.setText(firebaseAuth.getCurrentUser().getEmail());

                        ArrayList<DetailbarangpilihModel> list = new ArrayList<>();
                        list.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            //TODO : gawe listview nang kene
                            DetailbarangpilihModel data = new DetailbarangpilihModel();
                            data.setId_barang(ds.getKey());
                            data.setNama_barang(ds.child("nama_riwayat").getValue(String.class));
                            list.add(data);
                        }
                        RecyclerMainAdapter adapter = new RecyclerMainAdapter(MainActivity.this, list);
                        rv_konten.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        adapter.notifyDataSetChanged();
                        rv_konten.setAdapter(adapter);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
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
        if (id == R.id.action_Logout) {
            firebaseAuth.signOut();
            make_toast("Berhasil Logout!");
            rv_konten.setVisibility(View.GONE);
            konten_gaAdaData.setVisibility(View.GONE);
            konten_belumLogin.setVisibility(View.VISIBLE);
            btn_logout.setVisibility(View.GONE);
            sv_daftar.setVisibility(View.GONE);
            sv_login.setVisibility(View.GONE);
            tv_email.setText("Silahkan Login Terlebih Dahulu");
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.newsinfo) {
            Intent i = new Intent(MainActivity.this, newsinfo.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.tutorialmerakit) {
            Intent i = new Intent(MainActivity.this, tutorrakitpc.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
            startActivity(i);

        } else if (id == R.id.tutorialinstalos) {
            Intent i = new Intent(MainActivity.this, tutor_instalos.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
            startActivity(i);

        } else if (id == R.id.tipstrik) {

            Intent tt = new Intent(MainActivity.this, tipsandtrick2.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
            startActivity(tt);

        } else if (id == R.id.Penyelmasalah) {

            Intent pm = new Intent(MainActivity.this, pmasalah.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
            startActivity(pm);

        } else if (id == R.id.pojokgamer) {

            Intent i = new Intent(MainActivity.this, paketpc.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
            startActivity(i);


        } else if (id == R.id.tentangapk) {

            Intent i = new Intent(MainActivity.this, about.class); //MainActivity adalah aktivity awal ,praktikum1Activity activity yang akan di tuju
            startActivity(i);


        } else if (id == R.id.exit) {

            finish();
            System.exit(0);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void make_toast(String pesan) {
        Toast.makeText(context, pesan, Toast.LENGTH_LONG).show();
    }

    private void prosesDaftar() {

        if ((TextUtils.isEmpty(et_email_daftar.getText().toString()))
                || (TextUtils.isEmpty(et_password_daftar1.getText().toString()))
                || (TextUtils.isEmpty(et_password_daftar2.getText().toString()))) {
            final AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(context);
            }
            builder.setTitle("Peringatan!")
                    .setMessage("Masih Ada data yang belum diisi !")
                    .setPositiveButton("OK", null)
                    .show();
        } else if (!et_password_daftar1.getText().toString()
                .equals(et_password_daftar2.getText().toString())) {

            final AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(context);
            }
            builder.setTitle("Peringatan!")
                    .setMessage("Konfirmasi Password Salah !")
                    .setPositiveButton("OK", null)
                    .show();
//            et_password_daftar2.setError("Konfirmasi Password Salah");
        } else {
            final ProgressDialog progressDialog = ProgressDialog.show(context,
                    "Tunggu Beberapa Saat",
                    "Proses Pendaftaran ...",
                    true);

            firebaseAuth.createUserWithEmailAndPassword(et_email_daftar.getText().toString().trim(),
                    et_password_daftar1.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                et_email_daftar.setText("");
                                et_password_daftar1.setText("");
                                et_password_daftar2.setText("");
                                make_toast("Pendaftaran berhasil, Silahkan Masuk untuk menggunakan fitur ini!");
//                                cekLogin();


                                rv_konten.setVisibility(View.GONE);
                                konten_gaAdaData.setVisibility(View.GONE);
                                konten_belumLogin.setVisibility(View.GONE);
                                btn_logout.setVisibility(View.GONE);
                                sv_daftar.setVisibility(View.GONE);
                                sv_login.setVisibility(View.VISIBLE);

                            } else {
                                make_toast(task.getException().getMessage());
                            }
                        }
                    });
        }
    }

    private void prosesLogin() {
        if ((TextUtils.isEmpty(et_email_login.getText().toString()))
                || (TextUtils.isEmpty(et_password_login.getText().toString()))) {
            final AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(context);
            }

            builder.setTitle("Peringatan!")
                    .setMessage("username atau password masih Kosong!")
                    .setPositiveButton("OK", null)
                    .show();
        } else {
            final ProgressDialog progressDialog = ProgressDialog.show(context,
                    "Tunggu Beberapa Saat",
                    "Proses Login ...",
                    true);

            firebaseAuth.signInWithEmailAndPassword(et_email_login.getText().toString(),
                    et_password_login.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                et_email_login.setText("");
                                et_password_login.setText("");
                                make_toast("Login berhasil, Tunggu Sebentar");
                            } else {
                                make_toast(task.getException().getMessage());
                            }
                            cekLogin();
                            startActivity(new Intent(MainActivity.this, MainActivity.class));
                            finish();
                        }
                    });
        }
    }

    private void showDialog(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();

        builder.setPositiveButton("OK", null)
                .setNegativeButton("Batal", null)
                .setMessage(s)
                .create()
                .show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
