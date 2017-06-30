package my.angelika.com.mymusicdatabase.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import my.angelika.com.mymusicdatabase.model.Music;

/**
 * Created by angelika on 28.06.17.
 */

public class SharedPreferencesDataProvider implements IDataProvider {
		private static final String PREFERENCES_PATH = "prefs_file";
		private static final String KEY_MUSIC = "music_key";
		private SharedPreferences sharedPreferences;

		public SharedPreferencesDataProvider(Context context) {
				sharedPreferences = context.getSharedPreferences(PREFERENCES_PATH, Context.MODE_PRIVATE);

		}


		@Override
		public List<Music> getAllMusic() {
				//Initialize empty list
				List<Music> returnedMusicList = new ArrayList<>();
				//Download Set of Strings from Shared Preferences
				Set<String> musicSet = sharedPreferences.getStringSet(KEY_MUSIC, new HashSet<String>());

				for (String music : musicSet) {
						try {
								returnedMusicList.add(new Music(music));
						} catch (ParseException e) {
								Log.e(getClass().getName(), "Couldn't parse data form: "
												+ music);
						}
				}

				return returnedMusicList;
		}

		@Override
		public void saveAllMusic(List<Music> list) {
				SharedPreferences.Editor editor = sharedPreferences.edit();
				Set<String> collectionOfMusic = new HashSet<>();

				for (Music music : list) {
						collectionOfMusic.add(music.toDatabaseFormat());
				}

				editor.putStringSet(KEY_MUSIC, collectionOfMusic);
				editor.commit();
		}
}
