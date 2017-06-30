package my.angelika.com.mymusicdatabase;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

		@OnClick(R.id.btnAddInternalDB)
		protected void addInternalDB() {
				Intent i = new Intent(this, AddMusicActivity.class);
				i.putExtra("PROVIDER_TYPE", ProviderType.SQLITE.toString());
				startActivity(i);
		}

		@OnClick(R.id.btnViewSharedPref)
		protected void viewSharedPreferences() {
				Intent i = new Intent(this, ViewMusicActivity.class);
				i.putExtra("PROVIDER_TYPE", ProviderType.SHARED_PREFERENCES.toString());
				startActivity(i);
		}

		@OnClick(R.id.btnViewInternalDB)
		protected void viewInternalDB() {
				Intent i = new Intent(this, ViewMusicActivity.class);
				i.putExtra("PROVIDER_TYPE", ProviderType.SQLITE.toString());
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
				if (Build.VERSION.SDK_INT >= 23) {
						if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
										== PackageManager.PERMISSION_GRANTED) {
								Log.d(getClass().getName(), "Permission already granted");
						} else {
								Log.d(getClass().getName(), "Requesting permission");
								ActivityCompat.requestPermissions(this,
												new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
												1);
						}
				} else {
						Log.d(getClass().getName(), "Permission already granted");
				}
		}

}
