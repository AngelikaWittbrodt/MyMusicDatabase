package my.angelika.com.mymusicdatabase.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by angelika on 28.06.17.
 */

public class Music {

		private static final String SEPARATOR = ";";
		private int id = -1;
		private String title;
		private String band;
		private String album;
		private Date releaseDate;
		private int duration;
		private int listenedTimes;
		private boolean listened;

		public Music() {
		}

		public Music(int id, String title, String band, String album,
		             Date releaseDate, int duration, int listenedTimes,
		             boolean listened) {
				this.id = id;
				this.title = title;
				this.band = band;
				this.album = album;
				this.releaseDate = releaseDate;
				this.duration = duration;
				this.listenedTimes = listenedTimes;
				this.listened = listened;
		}

		public Music(String unparsedString) throws ParseException {
				String[] splits = unparsedString.split(SEPARATOR);

				//splits[0] = title
				this.title = splits[0];

				//splits[1] = id
				this.id = Integer.parseInt(splits[1]);

				//splits[2] = band
				this.band = splits[2];

				//splits[3] = album
				this.album = splits[3];

				//splits[4] = releaseDate
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				this.releaseDate = sdf.parse(splits[4]);

				//splits[5] = duration
				this.duration = Integer.parseInt(splits[5]);

				//splits[6] = listenedTimes
				this.listenedTimes = Integer.parseInt(splits[6]);

				//splits[7] = listened
				this.listened = Boolean.parseBoolean(splits[7]);

		}

		@Override
		public String toString() {
				return "Music { " +
								"id = " + id +
								", title = '" + title + '\'' +
								", band = '" + band + '\'' +
								", album = '" + album + '\'' +
								", releaseDate = " + releaseDate +
								", duration= " + duration +
								", listenedTimes= " + listenedTimes +
								", listened= " + listened +
								'}';
		}

		public String toDatabaseFormat() {
				StringBuilder builder = new StringBuilder();

				builder.append(title);

				builder.append(SEPARATOR);
				builder.append(id);

				builder.append(SEPARATOR);
				builder.append(band);

				builder.append(SEPARATOR);
				builder.append(album);

				builder.append(SEPARATOR);
				builder.append(releaseDate);

				builder.append(SEPARATOR);
				builder.append(duration);

				builder.append(SEPARATOR);
				builder.append(listenedTimes);

				builder.append(SEPARATOR);
				builder.append(listened);

				return builder.toString();
		}

		public int getId() {
				return id;
		}

		public void setId(int id) {
				this.id = id;
		}

		public String getTitle() {
				return title;
		}

		public void setTitle(String title) {
				this.title = title;
		}

		public String getBand() {
				return band;
		}

		public void setBand(String band) {
				this.band = band;
		}

		public String getAlbum() {
				return album;
		}

		public void setAlbum(String album) {
				this.album = album;
		}

		public Date getReleaseDate() {
				return releaseDate;
		}

		public void setReleaseDate(Date releaseDate) {
				this.releaseDate = releaseDate;
		}

		public int getDuration() {
				return duration;
		}

		public void setDuration(int duration) {
				this.duration = duration;
		}

		public int getListenedTimes() {
				return listenedTimes;
		}

		public void setListenedTimes(int listenedTimes) {
				this.listenedTimes = listenedTimes;
		}

		public boolean isListened() {
				return listened;
		}

		public void setListened(boolean listened) {
				this.listened = listened;
		}
}
