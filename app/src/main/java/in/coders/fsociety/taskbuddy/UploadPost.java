package in.coders.fsociety.taskbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cloudinary.android.Utils;
import com.google.gson.annotations.SerializedName;

import in.coders.fsociety.taskbuddy.Utils.SharedPref;
import in.coders.fsociety.taskbuddy.Utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadPost extends AppCompatActivity {

    EditText title, description, credit, tag;
    Button upload;
    RadioGroup rg;
    String post_type = "T";

    ProgressBar pb;

    String Title = "", Description = "", Tag = "", Picurl="testing", authorid="testing", circlesid="testing";
    int Credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_post);

        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        credit = (EditText) findViewById(R.id.credit);
        tag = (EditText) findViewById(R.id.tag);
        rg = (RadioGroup) findViewById(R.id.post_type);
        upload = (Button) findViewById(R.id.upload);
        pb = (ProgressBar)findViewById(R.id.progress_bar);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Title = title.getText().toString();
                Description = description.toString();
                Credit = Integer.parseInt("00"+credit.getText().toString());
                Tag = tag.getText().toString();

                if(Title.equals("")  || Description.equals("") || Tag.equals("") || Credit==0)
                {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                upload_post();
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.public_post) {
                    post_type = "True";
                } else {
                    post_type = "False";
                }
            }
        });
    }
    public void upload_post(){

        pb.setVisibility(View.VISIBLE);
        Call<Response1> userSentResponseCall= Util.getRetrofitService().sendPostData(Title, Description, Credit, Tag, "",post_type,
                authorid, circlesid);
        userSentResponseCall.enqueue(new Callback<Response1>() {
            @Override
            public void onResponse(Call<Response1> call, Response<Response1> response) {
                Response1 r=response.body();

                pb.setVisibility(View.GONE);

                Log.v("Response", r+"");

                if(r!=null&&response.isSuccess()){

                    Toast.makeText(getApplicationContext(), r.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Some error occured",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response1> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Check Internet connection",Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.GONE);
            }
        });
    }

    public  class  Response1{
        @SerializedName("message")
        private String message;

        @SerializedName("success")
        private String success;

        public Response1(String message, String success) {
            this.message = message;
            this.success= success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }
    }

}
