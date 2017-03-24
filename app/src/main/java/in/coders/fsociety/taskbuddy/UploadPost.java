package in.coders.fsociety.taskbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UploadPost extends AppCompatActivity {

    EditText title, description, credit, tag;
    Button upload;
    RadioGroup rg;
    String post_type = "T";

    String Title, Description, Credit, Tag;

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

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload_post();
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.public_post) {
                    post_type = "T";
                } else {
                    post_type = "F";
                }
            }
        });
    }
    public void upload_post(){
        Title = title.getText().toString();
        Description = description.toString();
        Credit = credit.getText().toString();
        Tag = tag.getText().toString();

        if(Title.isEmpty() || Description.isEmpty() || Credit.isEmpty() || Tag.isEmpty())
        {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

    }

}
