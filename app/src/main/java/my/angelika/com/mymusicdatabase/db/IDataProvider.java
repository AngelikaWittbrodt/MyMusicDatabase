package my.angelika.com.mymusicdatabase.db;

import java.util.List;

import my.angelika.com.mymusicdatabase.model.Music;

/**
 * Created by angelika on 28.06.17.
 */

public interface IDataProvider {
		List<Music> getAllMusic();
		void saveAllMusic(List<Music> list);
}
