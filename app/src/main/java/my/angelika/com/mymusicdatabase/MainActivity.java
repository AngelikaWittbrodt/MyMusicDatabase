package my.angelika.com.mymusicdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import my.angelika.com.mymusicdatabase.model.ProviderType;

public class MainActivity extends AppCompatActivity {

		@OnClick(R.id.btnAddSharedPref)
		protected void addSharedPreferences() {
				Intent i = new Intent(this, AddMusicActivity.class);
				i.putExtra("PROVIDER_TYPE", ProviderType.SHARED_PREFERENCES.toString());
				startActivity(i);
		}


		@Override
		protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_main);
				ButterKnife.bind(this);
				isStoragePermissionGranted();
		}

		private void isStoragePermissionGranted() {
		}
}
