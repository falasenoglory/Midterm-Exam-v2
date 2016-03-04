package jimenez.midtermexamv2.fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jimenez.midtermexamv2.R;
import jimenez.midtermexamv2.adapter.BookAdapter;
import jimenez.midtermexamv2.apis.BookApi;
import jimenez.midtermexamv2.models.Book;

/**
 * A placeholder fragment containing a {@link ListView}.
 */
public class ListViewFragment extends Fragment implements AdapterView.OnItemClickListener {
    private FloatingActionButton fb;
    private ListView mListView;
    private TextView mTvEmpty;

    private List<Book> LBook = new ArrayList<>();
    private BookAdapter adapter;
    private ProgressBar progressBar;

    public static ListViewFragment newInstance() {
        return new ListViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FetchWeatherTask ft= new FetchWeatherTask(getContext());
        ft.execute();

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_listview, container, false);
    }


    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState){
        // find all the views
        mListView = (ListView) view.findViewById(R.id.listView);
        mTvEmpty = (TextView) view.findViewById(android.R.id.empty);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mTvEmpty.setText("Fetching data from cloud...");

        fb=(FloatingActionButton) view.findViewById(R.id.fab);
        fb.hide();
        // create a new instance of adapter
        adapter = new BookAdapter(getActivity(),
                R.layout.list_item, LBook);

        // set the adapter
        mListView.setAdapter(adapter);

        // set item click listener
        mListView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick (AdapterView < ? > parent, View view,int position, long id){

    }


    public class FetchWeatherTask extends AsyncTask<String, Void, ArrayList<Book>> {


        private Context ctx;

        public FetchWeatherTask(Context context) {
            this.ctx = context ;

        }

        @Override
        protected ArrayList<Book> doInBackground(String... params) {

            return BookApi.getBook("http://joseniandroid.herokuapp.com/api/books", "GET");
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            super.onPostExecute(books);
            adapter.addAll(books);
            progressBar.setVisibility(View.GONE);
            mTvEmpty.setVisibility(View.GONE);

            fb.show();
            }

        }



    }



