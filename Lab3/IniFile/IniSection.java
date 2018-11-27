package IniFile;

import java.util.Map;
import java.util.HashMap;
//import java.util.Set;
//import java.util.HashSet;

import java.util.zip.DataFormatException; // Не самое лучшее решение

import FileStructs.Section;
import Utils.Utils;


/*
Секция ini файла, содержит название секции и HashMap<название свойства, значение>. Имеет методы:
	Конструктор от названия секции,
	Конструктор от названия и строки для парсинга,
	Получение названия секции,
	Функция парсинга от строки,
	Получение свойства в данном типе по названию,
	Получение свойства строкой по названию,
	Получение списка свойств в секции,
	Получение количества свойств в секции,
	Очистка секции,
	Добавление свойства,
	Удаление свойства.
*/
public class IniSection implements Section {
	private String name_; // Название секции
	private HashMap<String, String> properties_; // Свойства, хранятся строками
	// Конструктор от названия, создает пустую секцию
	public IniSection(String name) throws DataFormatException {
		if (name.contains(" ")) // Название секции не может содержать пробелы
			throw new DataFormatException(String.format("Section name cannot contain spaces in name: \"%s\"", name));
		name_ = name;
		properties_ = new HashMap<String, String>();
	}
	// Конструктор от названия и строки для парсинга. Вызывает конструктор от имени и парсер
	public IniSection(String name, String sectionToParse) throws DataFormatException {
		this(name);
		parse(sectionToParse);
	}
	// Геттер названия
	public String getName() {
		return name_;
	}
	// Метод парсинга
	public void parse(String sectionToParse) throws DataFormatException {
		if (!sectionToParse.endsWith("\n")) // Если строка не заканчивается переносом, добавляем его (проще парсить)
			sectionToParse += "\n";
		int pos = 0; // Позиция в строке для парсинга, находится в начале строки
		while (true) {
			int posNext = sectionToParse.indexOf("\n", pos); // Ищем следующую строку
			if (posNext == -1) // Если ее нет, останавливаем парсинг (одна строка в конце всегда есть)
				break;
			String line = sectionToParse.substring(pos, posNext); // Вырезаем текущую строку
			// Если линия пуста или начинается с комментария при отбрасывании пробелов, то игнорируем ее
			if (line.isEmpty() || Utils.deleteSpaces(line).charAt(0) == ';') {
				pos = posNext + 1; // + 1 чтобы оказаться не на переносе строки, а дальше
				continue; // Переходим к обработке следующей строки
			}

			// Сложная конструкция: разбиваем линию на массив строк по любым разделяющим символам (пробел, таб, ..?), потом
			//   склеиваем эти линии обратно через один пробел: мы избавились от лишних пробелов и от пробелов в начале и конце
			line = String.join(" ", line.split("\\s+"));
			String[] tmp;
			if (line.contains(";")) // Если в строке есть символ комментария, сначала берем подстроку до него, а потом
				tmp = line.substring(0, line.indexOf(';')).split("="); // разделяем строку по знаку "равно".
			else
				tmp = line.split("="); // Иначе - просто разделяем
			if (tmp.length != 2) // Если получилось не две строки (больше одного "равно" или он отсутствует), то кидаем ошибку
				throw new DataFormatException(String.format("Error while reading ini file at label \"%s\"", name_));
			if (Utils.deleteSpaces(tmp[0]).contains(" ")) // Если в названии свойства (слева от "равно", tmp[0]) есть пробел, ошибка
				throw new DataFormatException(String.format(
				          "Error while reading ini file at label \"%s\", parameter \"%s\" has space(s) in name", tmp[0], name_));
			properties_.put(Utils.deleteSpaces(tmp[0]).split("\\s+")[0], 
			                Utils.deleteSpaces(tmp[1])); // Если все хорошо, кидаем в мап свойств
			pos = posNext; // Переходим к следующей строке этой секции
		}
	}
	// Получение свойства в виде данного класса. Класс должен иметь конструктор от строки и чтобы данная строка приводилась к нему.
	// Тип передается с помощью Class<T>, вызов как getProperty("name", Double.class), вернет Double
	public <T> T getProperty(String propertyName, Class<T> clss) throws IndexOutOfBoundsException, InstantiationException {
		if (!properties_.containsKey(propertyName)) // Если отсутвует свойство с таким именем, ошибка
			throw new IndexOutOfBoundsException(String.format("No \"%s\" entry found in \"%s\" label", propertyName, name_));
		try {
			// Попытка привести к нужному типу. Из clss достается конструктор от строки. А потом он применяется на prop.get()
			return clss.getDeclaredConstructor(String.class).newInstance(properties_.get(propertyName));
		} catch (Exception ex) { // Конструктора от строки может не быть, либо привести не получится - это много экспшенов
			throw new InstantiationException(String.format("Cannot cast \"%s\" in %s", properties_.get(propertyName), clss.getName()));
		}
	}
	// Получение свойства как строки
	public String getProperty(String propertyName) {
		if (!properties_.containsKey(propertyName)) // Если нет свойства с таким названием, то ошибка
			throw new IndexOutOfBoundsException(String.format("No \"%s\" entry found in \"%s\" label", propertyName, name_));
		return properties_.get(propertyName); // Иначе возвращаем
	}
	// Получение списка названий свойств в секции
	public String[] keys() {
		String[] ans = new String[properties_.size()];
		int i = 0;
		for (String propName: properties_.keySet())
			ans[i++] = propName;
		return ans;
	}
	// Получение количества свойств в секции
	public int size() {
		return properties_.size();
	}
	// Очистка секции
	public void clear() {
		properties_ = new HashMap<String, String>();
	}
	// Добавление свойства
	public void addProperty(String propertyName, String value) throws DataFormatException {
		if (propertyName.contains(" ")) // Имя не должно содержать пробел, ошибка
			throw new DataFormatException(String.format("Property cannot contain spaces in name: \"%s\"", propertyName));
		properties_.put(propertyName, value);
	}
	// Удаление свойсва по названию
	public void delProperty(String propertyName) {
		properties_.remove(propertyName);
	}
}
