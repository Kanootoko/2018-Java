import Lab2.*;

import java.util.ArrayList;

public class Main {
	static final boolean DEBUG = true;
	public static void main(String[] args) {
		Catalog catalog = new Catalog();                                    // Создание пустого каталога
		Genres genres = new Genres();                                       // Создание пустого набора жанров
		genres.add(new Genre("Metall"));                                    // Добавление жанра "Металл"
		genres.add(new Genre("Power Metall", genres.get("Metall")));        // Добавление жанра "Пауэр Металл", являющегося поджанром "Металл", который достается по названию из набора
		genres.add(new Genre("Battle Metall", genres.get("Power Metall"))); // Добавление жанра "Баттл Металл", являющегося поджанром "Пауэр Металл"
		genres.add(new Genre("Rock"));                                      // Добавление жанра "Рок"

		catalog.addArtist(new Artist("System Of A Down", genres.get("Metall")));     // Добавление исполнителя "SOAD", жанра Металл
		catalog.addAlbum("System Of A Down", new Album("Toxicity", 1999));           // Добавление альбома "Toxicity" 1999 года исполнителю "SOAD"
		catalog.addSong("System Of A Down", "Toxicity", new Song("ATWA", 2,20));     // Добавление трех песен исполнителю "SOAD" в альбом "Toxicity"
		catalog.addSong("System Of A Down", "Toxicity", new Song("Psycho", 3, 7));
		catalog.addSong("System Of A Down", "Toxicity", new Song("Aerials", 1, 52));

		catalog.addArtist(new Artist("Powerwolf", genres.get("Power Metall")));      // Добавление исполнителя "Powerwolf", жанра Пауэр Металл (наследуется от Металл)
		catalog.addAlbum("Powerwolf", new Album("Preachers Of The Night", 2013));    // Добавление альбома
		catalog.addSong("Powerwolf", "Preachers Of The Night", new Song("Extratum Et Oratum", 3, 56));
		catalog.addSong("Powerwolf", "Preachers Of The Night", new Song("Last Of The Living Dead", 3, 52));
		catalog.addSong("Powerwolf", "Preachers Of The Night", new Song("Amen & Attack", 3, 55));
		catalog.addAlbum("Powerwolf", new Album("Blessed & Possessed", 2015));       // Еще альбом
		catalog.addSong("Powerwolf", "Blessed & Possessed", new Song("Blessed & Possessed", 4, 42));
		catalog.addSong("Powerwolf", "Blessed & Possessed", new Song("Dead Until Dark", 3, 50));
		catalog.addSong("Powerwolf", "Blessed & Possessed", new Song("Army Of The Night", 3, 21));

		catalog.addArtist(new Artist("Sabaton", genres.get("Battle Metall")));       // Еще исполнитель, жанр Баттл Металл (наследуется от Пауэр Металл)
		catalog.addAlbum("Sabaton", new Album("Heroes", 2013));                      // Альбом
		catalog.addSong("Sabaton", "Heroes", new Song("Night Witches", 3, 2));
		catalog.addSong("Sabaton", "Heroes", new Song("No Bullets Fly", 3, 38));
		catalog.addSong("Sabaton", "Heroes", new Song("Smoking Snakes", 3, 15));

		System.out.println(catalog); // Вывод каталога на экран

		//SearchRequest sr = new SearchRequest().setMinLength(3, 10).setMaxLength(3, 20);
		SearchRequest sr = new SearchRequest().setGenre("Power Metall"); // Задание поискового запроса: ищем Пауэр Металл (и его поджанры)
		SearchEngine se = new SearchEngine(catalog);                     // Задание поискового движка вокруг каталога
		System.out.println(sr + ":");                                    // Вывод запроса на экран
		for (Song song: se.searchSongs(sr))                              // Бежим по песням, которые нам вернул поисковый движок, когда мы сказали найти песни
			System.out.println(song.getArtistName() + " - " + song.getAlbumName() + " - " + song); // Выводим как <Название исполнителя> - <Название альбома> - <Песня>
		System.out.println();

		sr = new SearchRequest().setMinLength(3, 10).setMaxLength(3, 20); // другой поисковый запрос
		// Можно было просто sr.setGenre(null).setMinLength(3, 10).setMaxLength(3, 20);
		System.out.println(sr + ":");
		for (Album album: se.searchAlbums(sr))                            // Поиск по альбомам
			System.out.println(album.getArtistName() + " - " + album.getName());

		System.out.println();
		sr = new SearchRequest().setMinLength(3, 10).setMaxLength(3, 20); // Запрос не поменялся, просто скопировано
		System.out.println(sr + ":");
		for (Artist artist: se.searchArtists(sr))                         // Поиск по исполнителям
			System.out.println(artist.getName());

		Playlist pl = new Playlist("Favorite songs"); // Создание плейлиста и добавление в него пары песен для теста
		pl.addSong(catalog.getArtist("Sabaton").getAlbum("Heroes").getSong("Smoking Snakes"));
		pl.addSong(catalog.getArtist("System Of A Down").getAlbum("Toxicity").getSong("Aerials"));
		System.out.println(pl); // Вывод плейлиста на экран
	}
}
