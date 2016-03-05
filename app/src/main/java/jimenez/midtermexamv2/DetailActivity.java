package jimenez.midtermexamv2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import jimenez.midtermexamv2.apis.BookApi;
import jimenez.midtermexamv2.models.Book;

public class DetailActivity extends AppCompatActivity {

    private EditText txtTitle;
    private EditText txtGenre;
    private EditText txtAuthor;
    private CheckBox cbisRead;
    private Boolean isEdit;
    private List<Book> LBook = new ArrayList<>();
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Bundle intent = getIntent().getExtras();
        if (intent == null) {
            throw new RuntimeException("MovieDetailsActivity is expecting an int extra passed by Intent");
        }
        position = intent.getInt("Position", -1);
        if (position == -1) {
            throw new IllegalArgumentException("position passed is invalid.");
        }

        isEdit=intent.getBoolean("isEdit");

        txtTitle=(EditText)findViewById(R.id.txtTitle);
        txtGenre=(EditText)findViewById(R.id.txtGenre);
        txtAuthor=(EditText)findViewById(R.id.txtAuthor);
        cbisRead=(CheckBox)findViewById(R.id.cbisRead);

        if(isEdit==false)
        {
            txtTitle.setEnabled(false);
            txtAuthor.setEnabled(false);
            txtGenre.setEnabled(false);
            cbisRead.setEnabled(false);

        }

        FetchWeatherTask tsk= new FetchWeatherTask(this);
        tsk.execute();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);


        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }



    public class FetchWeatherTask extends AsyncTask<String, Void, ArrayList<Book>> {


        private ProgressDialog pdia;
        private Context ctx;

        public FetchWeatherTask(Context context) {
            this.ctx = context ;

        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pdia = new ProgressDialog(ctx);
            pdia.setMessage("Getting Book Data ...");
            pdia.setCancelable(false);
            pdia.show();
        }


        @Override
        protected ArrayList<Book> doInBackground(String... params) {

            return BookApi.getBook("http://joseniandroid.herokuapp.com/api/books", "GET");
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            super.onPostExecute(books);
pdia.dismiss();
            Book book= books.get(position);
            txtTitle.setText(book.getTitle());
            txtGenre.setText(book.getGenre());
            txtAuthor.setText(book.getAuthor());
            if(book.isRead()==true)
            {
                cbisRead.setChecked(true);
            }

        }

    }

}
