package Lab2;

// Абстрактный List нужен чтобы возвращать его, так как в интерфейсе не важно, какой именно лист мы используем
import java.util.List;
// ArrayList нужен для реального хранения данных
import java.util.ArrayList;
// Из Collections используется метод Collections.unmodifiableList(<List>), возвращаемый лист, который нельзя изменить
import java.util.Collections;
// Абстрактный Set используется для передачи множества жанров наружу, так как в интерфейсе не важно, какой именно Set мы используем
import java.util.Set;
// HashSet используется для реального хранения в процессе
import java.util.HashSet;

/* Класс плейлиста, похож на альбом. Содержит название и лист песен. Имеет методы:
	Конструктор от названия;
	Получение названия;
	Получения массива названий песен;
	Получение неизменяемого листа песен;
	Получение множества жанров;
	Получение массива названий исполнителей;
	Преобразование к строке с выводом всех песен.
Чего здесь нет, но могло бы быть:
	Удаление песни из плейлиста;
	Получение списка жанров нужно было бы сделать массивом жанров (не их названий), либо наоборот список исполнителей передавать обратно как Set;
	Получение песни по названию (затерялось где-то)
*/
public class Playlist {
	private String name_;           // Название плейлиста
	private ArrayList<Song> songs_; // Лист песен
	// Конструктор от названия
	public Playlist(String name) {
		name_ = name;
		songs_ = new ArrayList<Song>();
	}
	// Геттер названия
	public String getName() {
		return name_;
	}
	// Получение массива названий песен для последующего вызова getSong(songName) (отсутствует)
	public String[] getSongsNames() {
		String[] ans = new String[songs_.size()];
		for (int i = 0; i < songs_.size(); ++i)
			ans[i] = (songs_.get(i)).getName();
		return ans;
	}
	// Получение неизменяемого листа песен
	public List<Song> getSongs() {
		return Collections.unmodifiableList(songs_);
	}
	// Добавление песни, в отличие от альбома, тут песне не присваивается альбом
	public void addSong(Song song) {
		songs_.add(song);
	}
	// Получение множества жанров песен в плейлисте
	public Set<Genre> getGenres() {
		HashSet<Genre> ans = new HashSet<Genre>();
		for (Song song: songs_)
			ans.add(song.getGenre());
		return ans;
	}
	// Получение массива названий исполнителей песен в плейлисте
	public String[] getArtistsNames() {
		HashSet<String> ansSet = new HashSet<String>();
		for (Song song: songs_)
			ansSet.add(song.getName());
		String[] ans = new String[ansSet.size()];
		int i = 0;
		for (String s: ansSet)
			ans[i++] = s;
		return ans;
	}
	// Приведение к строке, песни выводятся как "<Название исполнителя> - <Название альбома> - <Песня>"
	public String toString() {
		String ans = name_ + " playlist, " + songs_.size() + " song(s)";
		if (songs_.size() > 0) {
			ans += ":\n";
			for (int i = 0; i < songs_.size(); ++i) {
				ans += songs_.get(i).getArtist().getName() + " - " + songs_.get(i).getAlbum().getName() + " - " + songs_.get(i);
				if (i < songs_.size() - 1)
					ans += ",\n";
				else
					ans += ".\n";
			}
		} else
			ans += ".";
		return ans;
	}
}
