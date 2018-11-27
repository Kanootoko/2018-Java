package Lab2;

/* Класс песни. Содержит название, длину в секундах и альбом, в котором состоит. Имеет методы:
	Конструктор от названия и длины в секундах;
	Конструктор от названия и длины в (минутах, секундах);
	Получение имени;
	Получение длины в секундах;
	Получение строки длины в виде "мм:сс", если длина больше минуты и "сс", если меньше
	Получение жанра (проброс к исполнителю);
	Получение альбома;
	Получение названия альбома (проброс к альбому);
	Получение исполнителя (проброс к альбому);
	Получение названия исполнителя (проброс к исполнителю);
	Преобразование к строке с выводом названия и длины.
Чего здесь нет, но могло бы быть:
	Получение года альбома (проброс к альбому).
*/
public class Song {
	private String name_;     // Название песни
	private int length_;      // Длина в секундах
	/*default*/ Album album_; // Изначально не задано, меняется, когда песня добавляется в альбом
	// Конструктор от имени и длины в секундах
	public Song(String name, int length) {
		name_ = name;
		length_ = length;
	}
	// Конструктор от имени и длины в (минутах, секундах)
	public Song(String name, int minutes, int seconds) {
		name_ = name;
		length_ = minutes * 60 + seconds;
	}
	// Геттер названия
	public String getName() {
		return name_;
	}
	// Геттер длины
	public int getLength() {
		return length_;
	}
	// Получение строки длины песни
	public String getLengthFormatted() {
		if (length_ >= 60) {
			return String.format("%02d:%02d", (length_ / 60), (length_ % 60));
		} else
			return String.format("%02d", length_);
	}
	// Геттер жанра
	public Genre getGenre() {
		if (album_ == null)
			return null;
		return album_.getGenre();
	}
	// Геттер альбома
	public Album getAlbum() {
		return album_;
	}
	// Получение названия альбома. Если аьбом не задан, то вернется строка "Неизвестный альбом"
	public String getAlbumName() {
		if (album_ == null)
			return "Unknown album";
		else
			return album_.getName();
	}
	// Получение исполнителя
	public Artist getArtist() {
		if (album_ == null)
			return null;
		return album_.getArtist();
	}
	// Получение названия артиста. Если артист не задан, то верентся строка "Неизвестный исполнитель"
	public String getArtistName() {
		if (album_ == null)
			return "Unknown artist";
		return album_.getArtistName();
	}
	// Приведение к строке
	public String toString() {
		return name_ + "(" + getLengthFormatted() + ")";
	}
}
