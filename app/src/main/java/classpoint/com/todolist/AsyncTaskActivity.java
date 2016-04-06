package classpoint.com.todolist;

import android.os.AsyncTask;

/**
 * Created by Shlomo on 06/04/2016.
 */
public class AsyncTaskActivity extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... params) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result) {
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }
}

