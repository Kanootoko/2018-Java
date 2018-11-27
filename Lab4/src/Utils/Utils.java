package Utils;

import java.util.ArrayList;

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
	public static int count(String str, char chr) {
		int ans = 0;
		char[] tmp = new char[str.length() + 1];
		str.getChars(0, str.length(), tmp, 0);
		tmp[str.length()] = '\0';
		for (int i = 0; i < tmp.length; ++i)
			if (tmp[i] == chr)
				++ans;
		return ans;
	}	
	public static String[] parseQuotes(String str) {
		ArrayList<String> ans = new ArrayList<String>();
		String[] tmp = str.split("\\s+");
		boolean isOpened = false;
		for (int i = 0; i < tmp.length; ++i) {
			boolean change = false;
			if (count(tmp[i], '\"') % 2 != 0)
				change = true;
			if (isOpened) {
				String o0 = ans.get(ans.size() - 1);
				ans.remove(ans.size() - 1);
				ans.add(String.format("%s %s", o0, tmp[i].replace("\"", "")));
			} else
				ans.add(tmp[i].replace("\"", ""));
			if (change)
				isOpened = !isOpened;
		}
		//System.err.print(String.format("<%s> -> ", str));
		//for (String s: ans)
		//	System.err.print(String.format("%s, ", s));
		//System.err.println();
		tmp = new String[ans.size()];
		ans.toArray(tmp);
		return tmp;
	}
}
