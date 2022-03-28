package nts.sixblack.learnandroid;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import nts.sixblack.learnandroid.api.RetrofitClient;
import nts.sixblack.learnandroid.api.TokenResponse;
import nts.sixblack.learnandroid.form.PostsForm;
import nts.sixblack.learnandroid.model.ResponObject;
import nts.sixblack.learnandroid.ultil.RealPathUltil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;

public class UploadImageActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    private Button btnFindPosts, btnNewPosts, btnSelectImage;
    private EditText edtUserId, edtCaption;
    private String token = "Bearer "+ TokenResponse.accessToken;
    private ImageView imgChoose;
    private Uri mUri;
    private ProgressDialog progressDialog;

    private ActivityResultLauncher<Intent> mActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if (data == null){
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imgChoose.setImageBitmap(bitmap);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        btnFindPosts = (Button) findViewById(R.id.btnFindPosts);
        btnNewPosts = (Button) findViewById(R.id.btnNewPosts);
        btnSelectImage = (Button) findViewById(R.id.btnChooseImage);
        imgChoose = (ImageView) findViewById(R.id.imgChoose);
        edtUserId = (EditText) findViewById(R.id.edtUserId);
        edtCaption = (EditText) findViewById(R.id.edtCaption);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Waiting");

        btnFindPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find();
            }
        });

        btnNewPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });

        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

    }

    private void find(){
        Call<ResponObject> call = RetrofitClient.getInstance().getMyAPI().findPosts( token,"4");
        call.enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                ResponObject responObject = response.body();
                if (responObject==null){
                    Toast.makeText(getApplicationContext(), "Fail call", Toast.LENGTH_LONG).show();

                } else {
                    System.out.println(responObject.getMessage());

                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void selectImage(){
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            openGalary();
            return;
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openGalary();
        } else {
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, MY_REQUEST_CODE);
        }
    }

    private void openGalary() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivity.launch(Intent.createChooser(intent, "Choose Image"));
    }

    private void upload(){
        if (mUri != null){
            progressDialog.show();

            String userId = edtUserId.getText().toString().trim();
            String caption = edtCaption.getText().toString().trim();

            RequestBody requestBodyUserId  = RequestBody.create(MediaType.parse("multipart/form-data"), userId);
            RequestBody requestBodyCaption  = RequestBody.create(MediaType.parse("multipart/form-data"), caption);

            String strPath = RealPathUltil.getRealPath(this, mUri);
            Log.e("Path", strPath);

            File file = new File(strPath);
            RequestBody requestBodyImage = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            MultipartBody.Part multiPartImage = MultipartBody.Part.createFormData(PostsForm.KEY_IMAGE, file.getName(), requestBodyImage);
//            MultipartBody.Part multiPartImage = MultipartBody.Part.create(requestBodyImage);

            Call<ResponObject> call = RetrofitClient.getInstance().getMyAPI().newPosts(token, requestBodyCaption, multiPartImage, requestBodyUserId);
            call.enqueue(new Callback<ResponObject>() {
                @Override
                public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "New Posts Success", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<ResponObject> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    Log.e("error", t.getMessage());
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGalary();
            }
        }
    }
}