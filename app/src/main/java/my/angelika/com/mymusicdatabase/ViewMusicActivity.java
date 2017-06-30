package my.angelika.com.mymusicdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.angelika.com.mymusicdatabase.db.IDataProvider;
import my.angelika.com.mymusicdatabase.db.InternalDatabaseDataProvider;
import my.angelika.com.mymusicdatabase.db.SharedPreferencesDataProvider;
import my.angelika.com.mymusicdatabase.model.Music;
import my.angelika.com.mymusicdatabase.model.ProviderType;

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


				if (getIntent().hasExtra("PROVIDER_TYPE")) {
						String providerType = getIntent().getStringExtra("PROVIDER_TYPE");
						ProviderType type = ProviderType.valueOf(providerType);

						// utworzenie providera
						if (type == ProviderType.SHARED_PREFERENCES) {
								provider = new SharedPreferencesDataProvider(this);
						} else if (type == ProviderType.SQLITE) {
								provider = new InternalDatabaseDataProvider(this);
						}
				}
				refreshView();
		}

		public void setProvider(IDataProvider providerToSet) {
				this.provider = providerToSet;
		}

		private void refreshView() {
				listAdapter = new ArrayAdapter<Music>(this,
								android.R.layout.simple_list_item_1,
								provider.getAllMusic());
		}
}
