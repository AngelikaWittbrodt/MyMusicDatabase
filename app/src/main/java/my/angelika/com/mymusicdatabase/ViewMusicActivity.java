package my.angelika.com.mymusicdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.angelika.com.mymusicdatabase.db.IDataProvider;
import my.angelika.com.mymusicdatabase.model.Music;

public class ViewMusicActivity extends AppCompatActivity {

		@BindView(R.id.musicList)
		protected ListView musicList;

		ArrayAdapter<Music> listAdapter;
		IDataProvider provider;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_view_music);
				ButterKnife.bind(this);

				refreshView();
		}

		private void refreshView() {
				listAdapter = new ArrayAdapter<Music>(this,
								android.R.layout.simple_list_item_1,
								provider.getAllMusic());
		}
}
