package my.angelika.com.mymusicdatabase.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

import my.angelika.com.mymusicdatabase.model.Music;

/**
 * Created by angelika on 30.06.17.
 */

public class InternalDatabaseDataProvider extends SQLiteOpenHelper implements IDataProvider {
		private static final String DB_NAME = "music.db";
		private static final int DB_VERSION = 1;

		private static final String DB_TABLE_NAME = "music";

		private static final String COLUMN_ID = "id";
		private static final String COLUMN_NAME = "name";
		private static final String COLUMN_DURATION = "duration";

		public InternalDatabaseDataProvider(Context context) {
				super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
				db.execSQL("CREATE TABLE " + DB_TABLE_NAME + " ( " +
								COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
								COLUMN_NAME + " VARCHAR, " +
								COLUMN_DURATION + " INTEGER); "
				);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
				onCreate(db);
		}

		private void insertRecordIntoDatabase(Music music) {
				SQLiteDatabase db = getWritableDatabase();
				ContentValues cv = new ContentValues();
				cv.put(COLUMN_ID, music.getId());
				cv.put(COLUMN_NAME, music.getTitle());
				cv.put(COLUMN_DURATION, music.getDuration());

				db.insert(DB_TABLE_NAME, null, cv);
		}

		@Override
		public List<Music> getAllMusic() {
				List<Music> list = new LinkedList<>();

				SQLiteDatabase db = getReadableDatabase();
				Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE_NAME, null);

				for (int i = 0; i < cursor.getCount(); i++) {
						cursor.moveToNext();

						// pobieram 3 wartosci
						int id = cursor.getInt(0);
						String title = cursor.getString(1);
						int duration = cursor.getInt(2);

						// tworze obiekt, ustawiam 3 wartości
						Music newMusic = new Music();
						newMusic.setId(id);
						newMusic.setTitle(title);
						newMusic.setDuration(duration);

						// dodaje do listy
						list.add(newMusic);
				}

				return list;
		}

		@Override
		public void saveAllMusic(List<Music> list) {
				for (Music m : list) {
						if (m.getId() == -1) {
								insertRecordIntoDatabase(m);
						}
				}
		}
}
