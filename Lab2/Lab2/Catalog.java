package Lab2;

// Абстрактный List нужен чтобы возвращать его, так как в интерфейсе не важно, какой именно лист мы используем
import java.util.List;
// ArrayList нужен для реального хранения данных
import java.util.ArrayList;
// Из Collections используется метод Collections.unmodifiableList(<List>), возвращаемый лист, который нельзя изменить
import java.util.Collections;

/* Класс каталога. Содержит лист исполнителей. Имеет методы:
	Конструктор по умолчанию;
	Получение массива названий исполнителей;
	Получение неизменяемого листа исполнителей;
	Добавление исполнителя;
	Добавление альбома данному исполнителю (проброс метода из исполнителя);
	Добавление песни данному исполнителю в данный альбом (проброс метода из альбома);
	Преобразование к строке с выводом всех исполниетелей.
Чего здесь нет, но могло бы быть:
	Удаление исполнителя;
	Удаление альбома у исполнителя (проброс);
	Удаление песни в альбоме у исполнителя (проброс);
	Получение альбомов/названий альбомов у исполнителя по названию (проброс)
	Получение песен/названий песен из альбома у исполнителя по названиям (проброс)
*/
public class Catalog {
	private ArrayList<Artist> artists_; // Лист исполнителей
	// Конструктор, выделяющий память под лист
	public Catalog() {
		artists_ = new ArrayList<Artist>();
	}
	// Получение названий исполнителей дял последующих вызовов getArtist(artistName)
	public String[] getArtistsNames() {
		String[] ans = new String[artists_.size()];
		for (int i = 0; i < artists_.size(); ++i)
			ans[i] = artists_.get(i).getName();
		return ans;
	}
	// Получение неизменяемого листа артистов
	public List<Artist> getArtists() {
		return Collections.unmodifiableList(artists_);
	}
	// Получение артиста по названию
	public Artist getArtist(String artistName) {
		for (Artist artist: artists_)
			if (artist.getName().equals(artistName))
				return artist;
		return null;
	}
	// Добавление артиста
	public void addArtist(Artist artist) {
		for (int i = 0; i < artists_.size(); ++i)
			if (artists_.get(i).getName().equals(artist.getName()))
				return;
		artists_.add(artist);
	}
	// Добавление альбома артисту по названию. Если артист не найден, ничего не происходит
	public void addAlbum(String artistName, Album album) {
		for (int i = 0; i < artists_.size(); ++i)
			if ((artists_.get(i)).getName().equals(artistName)) {
				artists_.get(i).addAlbum(album);
				return;
			}
	}
	// Добавление песни в альбом данного артиста по их названиям. Если артист не найден, ничего не происходит
	public void addSong(String artistName, String album, Song song) {
		for (int i = 0; i < artists_.size(); ++i)
			if (artists_.get(i).getName().equals(artistName)) {
				artists_.get(i).addSong(album, song);
				return;
			}
	}
	// Приведение к строке, каталог выводится как список исполнителей через перенос строки
	public String toString() {
		String ans = "";
		for (int i = 0; i < artists_.size(); ++i)
			ans += artists_.get(i) + "\n";
		return ans;
	}
}
