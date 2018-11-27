package FileStructs;

import java.util.zip.DataFormatException; // Не самое лучшее решение

/*
Интерфейс секции для файла. Имеет методы:
	Получение имени секции,
	Парсинг секции из строки,
	Получение свойства данного класса по названию,
	Получение свойства в виде строки по названию,
	Получение списка названий свойств,
	Получение количества свойств в секции,
	Очистка секции,
	Добавление свойства со значением,
	Удаление свойства.
*/
public interface Section {
	//public Section(String name);
	//public Section(String name, String sectionToParse) throws DataFormatException;
	public String getName();
	public void parse(String sectionToParse) throws DataFormatException;
	public <T> T getProperty(String propertyName, Class<T> clss) throws IndexOutOfBoundsException, InstantiationException;
	public String getProperty(String propertyName);
	public String[] keys();
	public int size();
	public void clear();
	public void addProperty(String propertyName, String value) throws DataFormatException;
	public void delProperty(String propertyName);
}
