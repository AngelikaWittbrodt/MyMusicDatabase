package my.angelika.com.mymusicdatabase.model;

/**
 * Created by angelika on 28.06.17.
 */

public enum ProviderType {
		SHARED_PREFERENCES(1),
		EXTERNAL_STORAGE(2),
		INTERNAL_STORAGE(3),
		SQLITE(4);

		int value;

		ProviderType(int i) {
				value = i;
		}
}
