package com.example.a47directorydb;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    EditText mEditTextWord;
    EditText miEditTextDefinition;
    DictionaryDatabase mDB;
    ListView mListView;
    Button mSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linearLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mEditTextWord = findViewById(R.id.editTextWord);
        miEditTextDefinition = findViewById(R.id.editTextDefinition);
        mDB = new DictionaryDatabase(this);
        mListView = findViewById(R.id.listView);
        mSave = findViewById(R.id.buttonAddUpdate);

        mSave.setOnClickListener(v -> saveRecord());
    }

    private void saveRecord() {
        String word = mEditTextWord.getText().toString();
        String definition = miEditTextDefinition.getText().toString();
        mDB.saveRecord(word, definition);
    }

    private void updateWordList(){
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this, android.R.layout.simple_list_item_1,
                mDB.getWordList(), new String[]{DictionaryDatabase.KEY_WORD},
                new int[]{android.R.id.text1}, 0
        );
    }
}
