package FileStructs;

import java.util.zip.DataFormatException; // Не самое лучшее решение
import java.io.FileNotFoundException; 
import java.io.IOException;

/*
Интерфейс для файла - любой файл, содержащий секции, в которых есть секции, в которых - свойства
Имеет методы:
	Конструктор по умолчанию,
	Конструктор от названия файла,
	Получение секции по ее названию,
	Чтение файла из файла на диске,
	Парсинг файла из строки,
	Получение списка названий секций,
	Получение количества секций в файле,
	Очистка файла,
	Добавление пустой новой секции по названию,
	Добавление секции (Замена секции),
	Удаление секции,
	Запись в файл.
*/
public interface File {
	//public File();
	//public File(String fileName) throws FileNotFoundException, DataFormatException, IOException;
	public Section getSection(String sectionName);
	public void readFile(String fileName) throws FileNotFoundException, DataFormatException, IOException;
	public void parse(String FileToParse) throws DataFormatException;
	public String[] keys();
	public int size();
	public void clear();
	public Section addSection(String sectionName) throws DataFormatException;
	//public Section addSection(Section section);
	public void delSection(String sectionName);
	public void writeFile(String fileName) throws IOException;
}
