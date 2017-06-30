package my.angelika.com.mymusicdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.angelika.com.mymusicdatabase.db.IDataProvider;
import my.angelika.com.mymusicdatabase.db.SharedPreferencesDataProvider;
import my.angelika.com.mymusicdatabase.model.ProviderType;

public class AddMusicActivity extends AppCompatActivity {

		@BindView(R.id.editTitle)
		protected EditText editTitle;
		@BindView(R.id.editBand)
		protected EditText editBand;
		@BindView(R.id.editAlbum)
		protected EditText editAlbum;
		@BindView(R.id.editRelease)
		protected EditText editRelease;
		@BindView(R.id.editDuration)
		protected EditText editDuration;
		@BindView(R.id.editListenTimes)
		protected EditText editListenTimes;
		@BindView(R.id.checkListened)
		protected CheckBox checkListened;
		private IDataProvider provider;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_add_music);
				ButterKnife.bind(this);

				if (getIntent().hasExtra("PROVIDER_TYPE")) {
						String providerType = getIntent().getStringExtra("PROVIDER_TYPE");
						ProviderType type = ProviderType.valueOf(providerType);

						if (type == ProviderType.SHARED_PREFERENCES) {
								provider = new SharedPreferencesDataProvider(this);
						}
				}

		}
}
