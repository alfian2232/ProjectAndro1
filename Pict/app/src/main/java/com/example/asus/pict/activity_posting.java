package com.example.asus.pict;
//
//import android.Manifest;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.provider.MediaStore;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
//import android.util.Base64;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.asus.pict.Request.RequestHandler;
//
//import net.gotev.uploadservice.MultipartUploadRequest;
//import net.gotev.uploadservice.UploadNotificationConfig;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.UUID;
//
//public class activity_posting extends AppCompatActivity
//    implements View.OnClickListener{
//
//        private static final String UPLOAD_URL = "https://bogelardi.000webhostapp.com/api_kuliah/insert_informasi.php";
//        private static final int IMAGE_REQUEST_CODE = 3;
//        private static final int STORAGE_PERMISSION_CODE = 123;
//        private ImageView imageView;
//        private EditText etCaption;
//        private TextView tvPath;
//        private FloatingActionButton btnUpload;
//        private Bitmap bitmap;
//        private Uri filePath;
//        String id;
//        Toolbar toolbar;
//        ImageButton idBack;
//        SharedPreferences sharedPreferences;
//        private ByteArrayOutputStream byteArrayOutputStream;
//        private byte[] byteArray;
//        private String ConvertImage;
//
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_posting);
//
//            imageView = (ImageView)findViewById(R.id.id_image);
//            etCaption = (EditText)findViewById(R.id.etCaption);
//            tvPath    = (TextView)findViewById(R.id.path);
//            btnUpload = (FloatingActionButton) findViewById(R.id.btnUpload);
//
//            requestStoragePermission();
//
//            imageView.setOnClickListener(this);
//            btnUpload.setOnClickListener(this);
//
////            toolbar = findViewById(R.id.toolbar_posting);
////            setSupportActionBar(toolbar);
////            getSupportActionBar().setDisplayShowTitleEnabled(true);
//
//            sharedPreferences = getSharedPreferences("data_user",Context.MODE_PRIVATE);
//            id = sharedPreferences.getString("id",null);
//
//            idBack = (ImageButton) findViewById(R.id.btn_kembali);
//            idBack.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    activity_posting.this.finish();
//                }
//            });
//
//        }
//
//    @Override
//        public void onClick(View view) {
//            if(view == imageView){
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Complete action using"), IMAGE_REQUEST_CODE);
//            }else if(view == btnUpload){
////                uploadMultipart();
//                uploadPostingan();
//            }
//        }
//
//    private void uploadPostingan() {
//        final String getJudul = etCaption.getText().toString().trim();
//
//        if(getJudul.isEmpty()){
//            etCaption.setError("Harap isi judul informasi");
//            Toast.makeText(activity_posting.this, "Harap isi judul informasi", Toast.LENGTH_SHORT).show();
//        }  else{
//            if (bitmap != null) {
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
//                byteArray = byteArrayOutputStream.toByteArray();
//                ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
//
//                class Upload extends AsyncTask<Void, Void, String> {
//
//                    ProgressDialog loading;
//
//                    @Override
//                    protected void onPreExecute() {
//                        super.onPreExecute();
//                        loading = ProgressDialog.show(activity_posting.this, "Sedang Diproses...", "Tunggu...", false, false);
//                    }
//
//                    @Override
//                    protected void onPostExecute(String s) {
//                        super.onPostExecute(s);
//                        loading.dismiss();
//                        Toast.makeText(activity_posting.this, s, Toast.LENGTH_LONG).show();
//                        finish();
//                    }
//
//                    @Override
//                    protected String doInBackground(Void... v) {
//                        HashMap<String, String> params = new HashMap<>();
//
//                        params.put("id_user", id);
//                        params.put("judul_informasi", getJudul);
//                        params.put("ImageName", tvPath.getText().toString());
//                        params.put("foto_informasi", ConvertImage);
//
//                        RequestHandler rh = new RequestHandler();
//                        String res = rh.sendPostRequest(UPLOAD_URL, params);
//                        return res;
//                    }
//                }
//
//                Upload ae = new Upload();
//                ae.execute();
//            } else {
//                Toast.makeText(activity_posting.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//
//    @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//                filePath = data.getData();
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                    tvPath.setText("Path: ". concat(getPath(filePath)));
//                    imageView.setImageBitmap(bitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        public void uploadMultipart() {
//            String caption = etCaption.getText().toString().trim();
//
//            //getting the actual path of the image
//            String path = getPath(filePath);
//
//            //Uploading code
//            try {
//                String uploadId = UUID.randomUUID().toString();
////                Log.d("debug","OnResponse"+ id);
//
//                //Creating a multi part request
//                new MultipartUploadRequest(this, uploadId, UPLOAD_URL)
//                        .addFileToUpload(path, "image") //Adding file
//                        .addParameter("caption", caption) //Adding text parameter to the request
//                      // .addParameter("id_users", id) //Adding text parameter to the request
//                        .setNotificationConfig(new UploadNotificationConfig())
//                        .setMaxRetries(2)
//                        .startUpload(); //Starting the upload
////                        Toast.makeText(this, "Berhasil Terupload", Toast.LENGTH_SHORT).show();
////                        startActivity(new Intent(this, MainActivity.class));
//            } catch (Exception exc) {
//                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        public String getPath(Uri uri) {
//            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//            cursor.moveToFirst();
//            String document_id = cursor.getString(0);
//            document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
//            cursor.close();
//
//            cursor = getContentResolver().query(
//                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                    null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
//            cursor.moveToFirst();
//            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            cursor.close();
//
//            return path;
//        }
//
//        private void requestStoragePermission() {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
//                return;
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                //If the user has denied the permission previously your code will come to this block
//                //Here you can explain why you need this permission
//                //Explain here why you need this permission
//            }
//            //And finally ask for the permission
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
//        }
//
//        //This method will be called when the user will tap on allow or deny
//        @Override
//        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//            //Checking the request code of our request
//            if (requestCode == STORAGE_PERMISSION_CODE) {
//
//                //If permission is granted
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    //Displaying a toast
//                    Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
//                } else {
//                    //Displaying another toast if permission is not granted
//                    Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
//                }
//            }
//        }
//
//
//
//
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////            MenuInflater inflater = getMenuInflater();
////            inflater.inflate(R.menu.menu_posting, menu);
////            return super.onCreateOptionsMenu(menu);
////    }
//
//    }
//
//package xtrch.com.prostheticgo2.Activity;

//import android.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import xtrch.com.prostheticgo2.R;
//import xtrch.com.prostheticgo2.Request.Konfigurasi;
//import xtrch.com.prostheticgo2.Request.RequestHandler;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.asus.pict.Request.RequestHandler;
import com.example.asus.pict.apihelper.BaseApiService;
import com.example.asus.pict.apihelper.UtilsApi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_posting extends AppCompatActivity {
    ImageView pilihFoto;
    EditText etCaption;
    EditText etHarga;
    FloatingActionButton btnUpload;

    private static final String UPLOAD_URL = "https://vegetarianmarket.000webhostapp.com/api_kuliah/insert_informasi.php";
    private File f;
    String id;
    private Bitmap imageUri;
    SharedPreferences sharedPreferences;
    private Uri contentUri;
        private static final int PICK_IMAGE = 100;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] byteArray;
    private String ConvertImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);
        sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id",null);

        setFindView();
        setPermission();
        setOnClick();
    }

    private void setPermission() {
        byteArrayOutputStream = new ByteArrayOutputStream();

        if (imageUri != null) {
            imageUri.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
            ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        } else {
            Toast.makeText(activity_posting.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
        }
    }

    private void setOnClick() {
        pilihFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String getJudul = etCaption.getText().toString().trim();
                final String getHarga = etHarga.getText().toString().trim();
                if(getJudul.isEmpty() || getHarga.isEmpty()){
                    Toast.makeText(activity_posting.this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
                } else{
                    if (imageUri != null) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        imageUri.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byteArray = byteArrayOutputStream .toByteArray();
                        ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);

                        class Upload extends AsyncTask<Void, Void, String> {

                            ProgressDialog loading;

                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                                loading = ProgressDialog.show(activity_posting.this, "Sedang Diproses...", "Tunggu...", false, false);
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                super.onPostExecute(s);
                                loading.dismiss();
                                Toast.makeText(activity_posting.this, s, Toast.LENGTH_LONG).show();

                                startActivityForResult(new Intent(activity_posting.this,MainActivity.class),14);
//                                finish();
                            }

                            @Override
                            protected String doInBackground(Void... v) {
                                HashMap<String, String> params = new HashMap<>();

                                params.put("id_users", id);
                                params.put("judul_informasi", getJudul);
                                params.put("ImageName", f.getName());
                                params.put("foto_informasi", ConvertImage);
                                params.put("harga", getHarga);

                                RequestHandler rh = new RequestHandler();
                                String res = rh.sendPostRequest(UPLOAD_URL, params);
                                return res;
                            }
                        }

                        Upload ae = new Upload();
                        ae.execute();
                    } else {
                        Toast.makeText(activity_posting.this, "Lampirkan Foto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity_posting.this.managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    private void uploadPostingan() {
    }

    private void setFindView() {
        pilihFoto = findViewById(R.id.id_image);
        etCaption = findViewById(R.id.etCaption);
        etHarga = findViewById(R.id.etHarga);
        btnUpload = findViewById(R.id.btnUpload);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {

            if (data != null) {
                contentUri = data.getData();

                try {
                    imageUri = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(activity_posting.this).getContentResolver(), contentUri);
                    String selectedPath = getPath(contentUri);
                    pilihFoto.setImageBitmap(imageUri);
                    pilihFoto.setVisibility(View.VISIBLE);
                    f = new File(selectedPath);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(activity_posting.this, "Failed!", Toast.LENGTH_SHORT).show();
                }

            }
        }

    }
}
//    private void hapusData(String delete, int id) {
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Deleting...");
//        progressDialog.show();
//
//
//        apiInterface = UtilsApi.getAPIService().create(BaseApiService.class);
//
//        Call<ListPosting> call = apiInterface.deletePet(this.id);
//
//        call.enqueue(new Callback<ListPosting>() {
//            @Override
//            public void onResponse(Call<ListPosting> call, Response<ListPosting> response) {
//
//                progressDialog.dismiss();
//
//                Log.i(Activity_posting_user.class.getSimpleName(), response.toString());
//
//                String value = response.body().getId();
//
//                if (value.equals("1")){
//                    Toast.makeText(Activity_posting_user.this, value, Toast.LENGTH_SHORT).show();
//                    finish();
//                } else {
//                    Toast.makeText(Activity_posting_user.this, value, Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ListPosting> call, Throwable t) {
//                progressDialog.dismiss();
//                Toast.makeText(Activity_posting_user.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }