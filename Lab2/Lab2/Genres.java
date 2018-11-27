package Lab2;

// ArrayList нужен для хранения данных
import java.util.ArrayList;

/* Класс жанров, используется для более удобного хранения жанров, можно сказать, что является оберткой вокруг листа жанров.
Содержит лист жанров, имеет методы:
	Конструктор по умолчанию;
	Получение массива названий жанров;
	Получение жанра по названию;
	Добавление жанра;
	Вывод всех жанров;
	Преобразование к строке.
Чего здесь нет, но могло бы быть:
	Получение неизменяемого листа жанров.
*/
public class Genres {
	ArrayList<Genre> genres_;
	// Конструктор по умолчанию, выделяет память
	public Genres() {
		genres_ = new ArrayList<Genre>();
	}
	// Получение массива названий жанров
	public String[] getGenres() {
		String[] ans = new String[genres_.size()];
		for (int i = 0; i < genres_.size(); ++i)
			ans[i] = (genres_.get(i)).getName();
		return ans;
	}
	// Получение жанра по названию
	public Genre get(String genreName) {
		for (Genre g: genres_)
			if ((g.getName()).equals(genreName))
				return g;
		return null;
	}
	// Добавление жанра в массив
	public void add(Genre genre) {
		genres_.add(genre);
	}
	// Приведене к строке (у каждого жанра будут выводиться еще и наджанры, не уверен, что это оправдано.
	public String toString() {
		String ans = "["; // Список будет начинаться с квадратной скобки
		for (int i = 0; i < genres_.size(); ++i)
			ans += genres_.get(i) + (i < genres_.size() - 1 ? ", " : "]"); // Перечисление будет через запятую, после последнего идет квадратная скобка
		if (genres_.size() == 0)
			ans += "]";  // Если жанров нет (список пуст), то сразу после открывающей скобки будет закрывающая.
		return ans;
	}
}
