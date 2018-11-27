package Lab2;

// Абстрактный List нужен чтобы возвращать его, так как в интерфейсе не важно, какой именно лист мы используем
import java.util.List;
// ArrayList нужен для реального хранения данных
import java.util.ArrayList;
// Из Collections используется метод Collections.unmodifiableList(<List>), возвращаемый лист, который нельзя изменить
import java.util.Collections;

/* Класс поискового движка. Его было сказано вынести в отдельный класс для возможности последующего улучшения.
Например, тут можно было бы устраивать какое-либо кэширование результатов запросов.
Содержит каталог, над которым производится поиск. Имеет методы:
	Поиск песен по заданному поисковому запросу;
	Поиск альбомов по заданному поисковому запросу;
	Поиск исполнителей по заданному поисковому запросу.
Чего здесь нет, но могло бы быть:
	Поиск одной функцией, зависящий от самого поискового запроса
*/
public class SearchEngine {
	private Catalog catalog_; // Каталог, на котором осуществляется поиск
	// Конструктор от каталога
	public SearchEngine(Catalog catalog) {
		catalog_ = catalog;
	}
	// Поиск песен с заданными параметрами
	public List<Song> searchSongs(SearchRequest sr) {
		// Для начала, стоит уточнить, что is<Something>Okay - методы поискового запроса, проверяющие на то, что это <Something> подходит.
		// Если в запросе не задано искать по <Somtething>, то любой подходит.\
		// Например, если минимальная и максимальная длина не заданы, то любая песня подойдет для sr.isLengthOkay(song)
    	ArrayList<Song> ans = new ArrayList<Song>();     // Изначально лист пуст
		for (Artist artist: catalog_.getArtists()) {     // Бежим по исполнителям в каталоге
			if (!sr.genreOkay(artist.getGenre()))        // Если жанр не подходит (а жанр лежит у исполнителя),
				continue;                                //   то переходим к следующему исполнителю
			for (Album album: artist.getAlbums()) {      // Если же жанр подошел, то бежим по альбомам исполнителя
				if (!sr.yearOkay(album.getYear()))       // Если год выпуска альбома не подходит,
					continue;                            //   то идем к следующему альбому
				for (Song song: album.getSongs())        // Если же год подошел, то бежим по песням из альбома
					if (sr.lengthOkay(song.getLength())) // Если длина подходит,
						ans.add(song);                   //   то добавляем песню к ответу - она подходит по всем параметрам
			}
		}
		return ans;
    }
    // Поиск альбомов по параметрам. Если хотя бы одна песня альбома подходит, он заносится в ответ
    public List<Album> searchAlbums(SearchRequest sr) {
		ArrayList<Album> ans = new ArrayList<Album>();
		for (Artist artist: catalog_.getArtists()) {
			if (!sr.genreOkay(artist.getGenre()))
				continue;
			for (Album album: artist.getAlbums()) {
				if (!sr.yearOkay(album.getYear()))
					continue;
				for (Song song: album.getSongs())
					if (sr.lengthOkay(song.getLength())) {
						ans.add(album);                  // Альбом добавляется в ответ
						break;                           // И цикл по песням альбома останавливается, так как это больше не нужно
					}
			}
		}
		return ans;
    }
    public List<Artist> searchArtists(SearchRequest sr) {
   		ArrayList<Artist> ans = new ArrayList<Artist>();
		for (Artist artist: catalog_.getArtists()) {
			if (!sr.genreOkay(artist.getGenre()))
				continue;
			for (Album album: artist.getAlbums()) {
				boolean br = true;
				if (!sr.yearOkay(album.getYear()))
					continue;
				for (Song song: album.getSongs())
					if (sr.lengthOkay(song.getLength())) {
						ans.add(artist);                 // Исполнитель добавляется в ответ
						br = true;                       // Нужно совершить две остановки, чтобы перейти к следующему исполнителю, флаг = 1
						break;                           // Останавливаем бег по песням альбома
					}
				if (br)
					break;                               // Если флаг, то останавливаем бег по альбомам исполнителя - он уже в ответе
			}
		}
		return ans;
	}
}
