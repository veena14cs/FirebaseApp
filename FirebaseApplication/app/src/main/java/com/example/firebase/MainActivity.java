package com.example.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
   public static DatabaseReference myRef;
    Button addBtn,updateBtn,uploadImage;
    TextView textView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
         myRef = database.getReference();

//        myRef.setValue("Hello, World!");

        intViews();
        initListener();
//        readFromDatabase();

    }

         private void writeNewUser(String userId, String username, String title,String body,String imageUrl) {
            Post user = new Post(userId,username, title,body,imageUrl);

            myRef.child("posts").setValue(user);

             ValueEventListener postListener = new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {
                     // Get Post object and use the values to update the UI

                     Post post = dataSnapshot.child("posts").getValue(Post.class);
                     textView.setText(post.author+" "+post.title);
                     Toast.makeText(MainActivity.this,post.author,Toast.LENGTH_SHORT).show();

                     // ...
                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {
                     // Getting Post failed, log a message
                     Log.w("post", "loadPost:onCancelled", databaseError.toException());
                     // ...
                 }
             };
             myRef.addValueEventListener(postListener);
        }
//    private void UpdateUser(String userId, String username, String title,String body) {
//        // Create new post at /user-posts/$userid/$postid and at
//        // /posts/$postid simultaneously
//        String key = myRef.child("posts").push().getKey();
//        Post post = new Post(userId, username, title, body);
//        Map<String, Object> postValues = post.toMap();
//
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/posts/" + key, postValues);
//        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);
//
//        myRef.updateChildren(childUpdates);
//
//    }



    private void initListener() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myRef.setValue(editText.getText().toString());
//
//                readFromDatabase();

                writeNewUser("2","abc","xyz","jhgfdjh","");
                Toast.makeText(MainActivity.this,"Added.",Toast.LENGTH_SHORT).show();

            }
        });
//        updateBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UpdateUser("3","ewr","rtt","vbbb");
//                Toast.makeText(MainActivity.this,"updated.",Toast.LENGTH_SHORT).show();
//            }
//        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Upload_RetriveImage_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void intViews() {
        addBtn = findViewById(R.id.addBtn);
        updateBtn = findViewById(R.id.updateBtn);
        textView = findViewById(R.id.label);
        editText = findViewById(R.id.edit);
        uploadImage = findViewById(R.id.updloadImage);
    }

    private void readFromDatabase() {
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                textView.setText(value);
                Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
                Log.d("TAG", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainActivity.this,"Failed to read value.",Toast.LENGTH_SHORT).show();

                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }
}
