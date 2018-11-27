package Lab2;

// Абстрактный List нужен чтобы возвращать его, так как в интерфейсе не важно, какой именно лист мы используем
import java.util.List;
// ArrayList нужен для реального хранения данных
import java.util.ArrayList;
// Из Collections используется метод Collections.unmodifiableList(<List>), возвращаемый лист, который нельзя изменить
import java.util.Collections;

/* Класс исполнителя. Содержит название, жанр и лист альбомов, имеет методы:
	Конструктор от названия и жанра;
	Получение имени;
	Получение жанра;
	Получение массива названий альбомов;
	Получение неизменяемого листа альбомов;
	Получение альбома по его имени;
	Добавление альбома;
	Добавление песни в альбом по имени (проброс метода от альбома);
	Преобразование к строке с выводом всех альбомов.
Чего здесь нет, но могло бы быть:
	Удаление альбомов;
	Удаление песен из альбома по названию (проброс);
	Получение песен/названий песен из альбома по названию (проброс).
*/
public class Artist {
	private String name_;             // Назвение исполнителя
	private Genre genre_;             // Жанр исполнителя (можно было бы присвоить жанр альбомам или песням, но это самый простой вариант
	private ArrayList<Album> albums_; // Лист альбомов исполнителя
	// Конструктор от названия и жанра
	public Artist(String name, Genre genre) {
		name_ = name;
		genre_ = genre;
		albums_ = new ArrayList<Album>();
	}
	// Геттер имени
	public String getName() {
		return name_;
	}
	// Геттер жанра
	public Genre getGenre() {
		return genre_;
	}
	// Получение массива названий альбомов для последуюих вызовов getAlbum(albumName)
	public String[] getAlbumsNames() {
		String[] ans = new String[albums_.size()];
		for (int i = 0; i < albums_.size(); ++i)
			ans[i] = (albums_.get(i)).getName();
		return ans;
	}
	// Получение неизменяемого листа альбомов
	public List<Album> getAlbums() {
		return Collections.unmodifiableList(albums_);
	}
	// Получение альбома по названию
	public Album getAlbum(String albumName) {
		for (Album a: albums_)
			if (a.getName().equals(albumName))
				return a;
		return null;
	}
	// Добавление альбома. Если такой альбом уже есть (по названию), добавления не происходит.
	// При добавлении альбому присваивается артист
	public void addAlbum(Album album) {
		for (Album a: albums_)
			if (a.getName().equals(album.getName()))
				return;
		if (album.artist_ == null) // private
			album.artist_ = this;
		albums_.add(album);
	}
	// Добавление песни а альбом с данным именем, проброс метода. Если альбом с не найдется, ничего не произойдет
	public void addSong(String albumName, Song song) {
		for (Album a: albums_)
			if (a.getName().equals(albumName)) {
				a.addSong(song);
				return;
			}
	}
	// Перобразование к строке
	public String toString() {
		// Любой исполнитель будет выведен как "<Имя исполнителя>, genre: <жанр{[(наджанр...)]}>. <Количество альбомов> album(s)"
		String ans = name_ + ", genre: " + genre_ + ". " + albums_.size() + " album(s)";
		// Если альбомов больше нуля, пойдет их перечисление через перенос строки
		if (albums_.size() > 0) {
			ans += ":\n";
			for (int i = 0; i < albums_.size(); ++i)
				ans += albums_.get(i) + "\n";
		} else
			ans += "."; // Если альбомов нет, после "0 album(s)" будет точка.
		return ans;
	}
}
