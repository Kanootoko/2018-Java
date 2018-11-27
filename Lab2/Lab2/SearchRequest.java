package Lab2;

/* Класс поискового запроса. Содержит название жанра, год выхода альбома, минимальную и максимальную длину песни. Имеет методы:
	Конструктор по умолчанию;
	Установка года выпуска альбома;
	Установка жанра исполнителя;
	Установка минимальной длины песни через количество секунд;
	Установка максимальной длины песни через количество секунд;
	    --- при этом, в перечисленные сеттеры можно передать null, чтобы сбросить данный параметр поиска
	Установка минимальной длины песни через (минуты, секунды);
	Установка максимальной длины песни через (минуты, секунды);
	Проверка на то, что данный жанр подходит под запрос;
	Проверка на то, что данный год подходит под запрос;
	Проверка на то, что данная песня подходит по длине;
	Преобразование к строке.
Что стоило бы изменить:
	Добавить поиск по названиям исполнителя, альбома и песни;
	Проверки переименовать под большее число параметров:
		вместо isGenreOkay - isArtistOkay
		вместо isYearOkay - isAlbumOkay
		вместо isLengthOkay - isSongOkay
		, а также изменить функционал, так что в них на проверку передаются не жанр/год/длина, а исполнитель/альбом/песня;
   	Добавить флаг, что мы ищем не "данный жанр или поджанры", а "именно данный жанр";
	Добавить в запрос указание на то, что именно мы ищем (песни, альбомы или исполнителей), чтобы избавиться от двух лишних функций поиска.
*/
public class SearchRequest {
	public String genreName;                   // название жанра артиста для поиска
	public Integer year, minLength, maxLength; // год
	// Пустой поисковый запрос выведет все песни/альбомы/исполнителей в каталоге
	public SearchRequest() {
		genreName = null;
		year = null;
		minLength = null;
		maxLength = null;
	}
	// Сеттеры...
	public SearchRequest setYear(Integer year_) {
		year = year_;
		return this;
	}
	public SearchRequest setGenre(String genreName) {
		this.genreName = genreName;
		return this;
	}
	public SearchRequest setMinLength(Integer length) {
		minLength = length;
		return this;
	}
	public SearchRequest setMaxLength(Integer length) {
		maxLength = length;
		return this;
	}
	public SearchRequest setMinLength(int lengthMinutes, int lengthSeconds) {
		minLength = lengthMinutes * 60 + lengthSeconds;
		return this;
	}
	public SearchRequest setMaxLength(int lengthMinutes, int lengthSeconds) {
		maxLength = lengthMinutes * 60 + lengthSeconds;
		return this;
	}
	// Проверки...
	public boolean genreOkay(Genre g) {
		if (genreName == null || g.includedIn(genreName))
			return true;
		return false;
	}
	public boolean yearOkay(int year) {
		if (this.year == null || this.year.equals(year))
			return true;
		return false;
	}
	public boolean lengthOkay(int length) {
		if ((maxLength == null || maxLength.compareTo(length) >= 0) &&
		    (minLength == null || minLength.compareTo(length) <= 0))
		    return true;
		return false;
	}
	// Приведение к строке
	@Override
	public String toString() {
		if (genreName == null && year == null && minLength == null && maxLength == null)
			return "Empty search request";
		String ans = "Searches request parameters: ";
		boolean wasFirst = false;
		if (genreName != null)
			ans += " genre " + genreName;
		if (year != null) {
			ans += (wasFirst ? ", " : " ") + "year " + year;
			wasFirst = true;
		}
		if (minLength != null) {
			ans += (wasFirst ? ", " : " ") + "length >= " + String.format("%02d:%02d", minLength / 60, minLength % 60);
			wasFirst = true;
		}
		if (maxLength != null) {
			ans += (wasFirst ? ", " : " ") + "length <= " + String.format("%02d:%02d", maxLength / 60, maxLength % 60);
			wasFirst = true;
		}
		return ans;
	}
}
