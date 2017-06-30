package my.angelika.com.mymusicdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import my.angelika.com.mymusicdatabase.db.IDataProvider;
import my.angelika.com.mymusicdatabase.db.SharedPreferencesDataProvider;
import my.angelika.com.mymusicdatabase.model.Music;
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

		@OnClick(R.id.buttonSave)
		protected void createMusic() {
				Music newMusic = new Music();

				newMusic.setTitle(editTitle.getText().toString());
				newMusic.setBand(editBand.getText().toString());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				try {
						newMusic.setReleaseDate(sdf.parse(editRelease.getText().toString()));
				} catch (ParseException e) {
						Toast.makeText(this, "Wrong date format! Use yyyy/MM/dd.", Toast.LENGTH_SHORT).show();
						return;
				}
				newMusic.setAlbum(editAlbum.getText().toString());
				newMusic.setDuration(Integer.parseInt(editDuration.getText().toString()));
				newMusic.setListenedTimes(Integer.parseInt(editListenTimes.getText().toString()));
				newMusic.setListened(checkListened.isChecked());

				List<Music> music = provider.getAllMusic();

				music.add(newMusic);

				provider.saveAllMusic(music);
				finish();


		}
}
