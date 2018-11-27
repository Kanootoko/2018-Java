package Utils;

/*
Вспомогательный класс, имеет только метод удаления пробелов побокам от строки. Примеры:
"    abcd" -> "abcd"
" abcd "   -> "abcd"
"abcd   "  -> "abcd"
"abcd"     -> "abcd"
" ab cd "  -> "ab cd"
*/
public class Utils {
	public static String deleteSpaces(String str) {
		int start = 0, end = str.length() - 1; // Начало и конец изначально на нуле и на последнем символе
		while (str.charAt(start) == ' ') // Пока на старте находится пробел, увеличиваем старт
			++start;
		while (str.charAt(end) == ' ')   // Пока на конце находится пробел, уменьшаем конец
			--end;
		if (end < start) // Если они пересеклись, строка содержала только пробелы и при их удалении получается пустая строка
			return "";
		return str.substring(start, end + 1); // Иначе, возвращаем подстроку от начала до конца включительно
	}
}
