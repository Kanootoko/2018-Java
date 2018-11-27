package IniFile;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.io.*;

import java.util.zip.DataFormatException; // Не самое лучшее решение

import FileStructs.File;
import Utils.Utils;

/*
Класс ini Файла, выполняет интерфейс Файла. Содержит Мап, обеспечивающий доступ к секции по ее названию.
Имеет методы:
	Конструктор по умолчанию,
	Конструктор от названия файла на диске,
	Чтение файла из файла на диске,
	Парсинг файла из строки,
	Добавление новой пустой секции по названию,
	Добавление секции,
	Удаление секции,
	Получение множества названий секций,
	Очистка файла,
	Запись в файл на диске,
	Преобразование к строке с выводом всех секций.
*/
public class IniFile implements File {
	private HashMap<String, IniSection> sections_;
	// Конструктор по умолчанию, выделяет память под мап
	public IniFile() {
		sections_ = new HashMap<String, IniSection>();
	}
	// Получение секции по названию
	public IniSection getSection(String sectionName) {
		return sections_.get(sectionName);
	}
	// Чтение из файла
	public void readFile(String fileName) throws FileNotFoundException, DataFormatException, IOException {
		BufferedReader file = new BufferedReader(new FileReader(fileName)); // Открываем файл
		// Прочитаем весь файл в одну стоку, чтобы потом вызвать функцию парсинга файла из строки
		StringBuilder sb = new StringBuilder(); // Используем StringBuilder так как собираемся склеивать огромное число строк
		while (true) {
			String line = file.readLine(); // Читаем строку из файла
			if (line == null)              // Если null, значит файл закончился
				break;
			sb.append(line);               // Иначе, добавляем к результату даную строку
			sb.append("\n");               // И перенос строки после нее
		}
		file.close();
		parse(sb.toString());              // Вызываем пансинг от строки, закрыв файл
	}
	// Парсинг из строки, идея в том, чтобы идти по файлу построчно, а секции отсылать в конструктор секции всей подстрокой
	public void parse(String iniToParse) throws DataFormatException {
		int pos = 0; // Текущая позиция (номер символа) в строке, поддерживается на местах после переноса строки
		if (!iniToParse.endsWith("\n")) // Если в конце строкинет переноса строки, то
			iniToParse += "\n";         // Добавляем лишний перенос строки - нужно для простоты условия парсера, можно было обойтись
		while (true) {
			// Следующая позиция будет на переносе строки после текущей позции
			int posNext = iniToParse.indexOf("\n", pos); // string.indexOf(Строка что искать, начиная с какой позиции)
			if (posNext == -1) // Если переносов строки больше нет, то останавшиваем парсинг (последний перенос всегда есть)
				break;
			String line = iniToParse.substring(pos, posNext); // Вырезаем строчку между предыдущем переносом строки и следующем
			// К слову, string.substring(pos1, pos2) вырезает строку, включая pos1, но не включая pos2

			// Если строка пуста, либо пуста до знака комментария, то идем дальше
			if (line.isEmpty() || Utils.deleteSpaces(line).charAt(0) == ';') {
				pos = posNext + 1; // увеличение на 1, чтобы встать за энтер, а не на него
				continue;
			}
			// Если строка начинается с квадатной скобки, то это секция
			if (line.charAt(0) == '[') {
				boolean ok = line.contains("]");
				// Проверяем название секции на корректность. Если нет закрывающей, она точно некорректна, но проверка продолжается
				for (int i = line.indexOf(']') + 1; i < line.length(); ++i)
					if (line.charAt(i) == ';') // Если дошли до комментария, то все окей, дальше не важно, какие идут символы
						break;
					else if (line.charAt(i) != ' ') { // Если же мы встретили что-то кроме пробела, то секция плохая
						ok = false;
						break;
					}
				if (!ok)
					throw new DataFormatException(String.format("Error while reading ini file: label \"%s\" is broken", line));
				String name = line.substring(1, line.indexOf(']')); // Дотстаем название 
				if (name.contains(" ")) // Если в названии есть пробелы, кидаем ошибку
					throw new DataFormatException(String.format("Error while reading ini file: label \"%s\" contain spaces", name));
				// Ищем конец секции - либо начало новой секции, либо конец файла, если секций больше нет
				int posFinish = iniToParse.indexOf("\n[", posNext) != -1 ?
				                iniToParse.indexOf("\n[", posNext) :
				                iniToParse.length();
				// Добавляем секцию, вызывая конструктор секции: вырезаем строку от следующей строки до конца секции
				addSection(new IniSection(name, iniToParse.substring(posNext, posFinish)));
				posNext = posFinish; // Устанавливаем следующую точку на конец текущей секции, она будет на \n
			}
			pos = posNext + 1; // Переходим на следующую строку/секцию, на единицу больше чтобы оказаться на новой строке, а не на \n
		}
	}
	// Добавление пустой секции
	public IniSection addSection(String sectionName) throws DataFormatException {
		sections_.put(sectionName, new IniSection(sectionName));
		return sections_.get(sectionName);
	}
	// Добавление секции
	public IniSection addSection(IniSection section) {
		sections_.put(section.getName(), section);
		return sections_.get(section.getName());
	}
	// Удаление секции по названию
	public void delSection(String sectionName) {
		sections_.remove(sectionName);
	}
	// Получение всех названий секций в файле
	public String[] keys() {
		String[] ans = new String[sections_.size()];
		int i = 0;
		for (String sectName: sections_.keySet())
			ans[i++] = sectName;
		return ans;
	}
	// Колчество секций
	public int size() {
		return sections_.size();
	}
	// Очистка 
	public void clear() {
		sections_ = new HashMap<String, IniSection>();
	}
	// Запись в файл на диске
	public void writeFile(String fileName) throws IOException {
		BufferedWriter file = new BufferedWriter(new FileWriter(fileName));
		int i = 0;
		for (String section: sections_.keySet()) {
			IniSection out = sections_.get(section);
			file.write(String.format("[%s]\n", section));
			for (String prop: out.keys())
				file.write(String.format("%s = %s\n", prop, out.getProperty(prop)));
			if (++i < sections_.size())
				file.write("\n");
		}
		file.close();
	}
	// Приведение к строке
	public String toString() {
		String ans = "[[[\n";
		int i = 0;
		for (String label: sections_.keySet()) {
			ans += label + ": {";
			int j = 0;
			IniSection tmp = sections_.get(label);
			for (String entry: tmp.keys())
				ans += String.format("%s=%s%s", entry, tmp.getProperty(entry), (j++ == tmp.size() - 1 ? "}" : ", "));
			if (tmp.size() == 0)
				ans += "}";
			ans += (i++ == sections_.size() - 1 ? "\n]]]" : ";\n");
		}
		if (sections_.size() == 0)
			ans += "]]]";
		return ans;
	}
}
