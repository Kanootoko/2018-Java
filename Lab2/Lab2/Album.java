package Lab2;

// Абстрактный List нужен чтобы возвращать его, так как в интерфейсе не важно, какой именно лист мы используем
import java.util.List;
// ArrayList нужен для реального хранения данных
import java.util.ArrayList;
// Из Collections используется метод Collections.unmodifiableList(<List>), возвращаемый лист, который нельзя изменить
import java.util.Collections;

/* Класс альбома, содержит лист песен и год выпуска, имеет методы:
	Конструктор от названия альбома и года выпуска;
	Получение имени альбома;
	Получение года выпуска альбома;
	Получение массива строк - названий песен в альбоме;
	Получение неизменяемого листа структур песен;
	Получение песни по названию;
	Получение жанра альбома (жанр в данном случае хранится в артисте, это метод-проброс параметра);
	Получение исполнителя, выпустившего альбом;
	Получение имени исполнителя, выпустившего альбом (метод-проброс имени от артиста);
	Добавление пести в альбом;
	Преобразование к строке с выводом всех песен.
Чего здесь нет, но могло бы быть:
	Удаление песни из альбома.
*/
public class Album {
	private String name_;             // Имя альбома
	protected int year_;              // Год выпуска
	protected ArrayList<Song> songs_; // Лист песен
	/*default*/ Artist artist_;       // Исполнитель изначально не задан, он задается когда альбом добавляется к исполнителю
	// Конструктор от названия и года выпуска
	public Album(String name, int year) {
		name_ = name;
		year_ = year;
		songs_ = new ArrayList<Song>();
	}
	// Геттер названия
	public String getName() {
		return name_;
	}
	// Геттер года
	public int getYear() {
		return year_;
	}
	// Геттер жанра с пробросом от исполнителя
	public Genre getGenre() {
		if (artist_ == null)
			return null;
		return artist_.getGenre();
	}
	// Получение массива названий песен для последующих вызовов getSong(songName)
	public String[] getSongsNames() {
		String[] ans = new String[songs_.size()];
		for (int i = 0; i < songs_.size(); ++i)
			ans[i] = (songs_.get(i)).getName();
		return ans;
	}
	// Получение листа песен, он будет неизменяемым
	public List<Song> getSongs() {
		return Collections.unmodifiableList(songs_);
	}
	// Получение одной песни. Вполне возможно, что она будет изменяемой, как-то я зыбал про это
	public Song getSong(String songName) {
		for (Song s: songs_)
			if (s.getName().equals(songName))
				return s;
		return null;
	}
	// Геттер исполнителя Вполне возможно, он будет изменяемым
	public Artist getArtist() {
		return artist_;
	}
	// Получение названия исполнителя, при отсутствии такового будет возвращена строка "Неизвестный исполнитель"
	public String getArtistName() {
		if (artist_ == null)
			return "Unknown artist";
		return artist_.getName();	
	}
	// Добавление песни в альбом, если такая песня уже есть (по названию), то добавления не произойдет
	public void addSong(Song song) {
		for (Song s: songs_)
			if (s.getName().equals(song.getName()))
				return;
		if (song.album_ == null) // private
			song.album_ = this;
		songs_.add(song);
	}
	// Преобразование к строке
	public String toString() {
		// При выводе любого альбом будет выведено: "<Название>, <год выпуска> year. <количество песен> song(s)"
		String ans = name_ + ", " + year_ + " year. " + songs_.size() + " song(s)";
		// И есои песен больше нуля, пойдет их перечисление через запятую, после последней будет точка
		if (songs_.size() > 0) {
			ans += ": ";
			for (int i = 0; i < songs_.size(); ++i) {
				ans += songs_.get(i);
				if (i < songs_.size() - 1)
					ans += ", ";
				else
					ans += ".";
			}
		} else
			ans += '.'; // если песен нет, после "0 song(s)" будет точка.
		return ans;
	}
}
