import IniFile.*;

public class Main {
	public static void main(String[] args) throws Exception {
		// Создаем ini Файл
		IniFile ini = new IniFile();
		// Изначально, считывание будет производиться из "test.ini"
		String input = "test.ini";
		// Но если у нас есть консольные параметры, то первый из них станет названием файла для ввода
		if (args.length > 0)
			input = args[0];

		ini.readFile(input);     // Парсим файл
		System.out.println(ini); // Выводим результат парсинга
		IniSection values = ini.getSection("values"); // Работаем с секцией values (есть в "test.ini")
		String[] vals = new String[]{"lol", "int", "double", "doubleRus", "str"}; // Посмотрим, что будет когда будут запрашиваться эти штуки
		for (String s: vals) { // Бежим строкой s по массиву выше. "lol" отсутствует в секции
			// Выводим, что сейчас пытаемся сделать: взяь один из элементов как строку (всегда возможно, если элемент есть - "lol" выдаст ошибку)
			System.out.print(String.format("Getting %12s as String: ", s));
			try {
				System.out.print(values.getProperty(s, String.class)); // Берем как строку
			} catch(Exception ex) {
				System.out.print("(Error)"); // Если что-то пошло не так, ошибка. Только "lol".
			}
			System.out.print(", as Int: "); // Будем брать как Int
			try {
				System.out.print(values.getProperty(s, Integer.class)); // Берем как Int
			} catch(Exception ex) {
				System.out.print("(Error)"); // Ошибка будет для double, doubleRus, str
			}
			System.out.print(", as Double: "); // Будем брать как Double
			try {
				System.out.print(values.getProperty(s, Double.class)); // Берем как Double
			} catch(Exception ex) {
				System.out.print("(Error)"); // Ошибка будет на doubleRus, str
			}
			System.out.println(".");
		}
		// Далее - дополнительный функционал, к парсеру напрямую не относящийся
		for (String sectionName: ini.keys()) { // Будем бежать по названиям секций.
			IniSection section = ini.getSection(sectionName); // Получаем данную секцию
			if (section.size() == 0) // Если в ней не было ни одного свойства, удаляем ее.
				ini.delSection(sectionName);
		}
		IniSection tmp = ini.addSection("Ole4kaKiso4ka"); // Добавляем секцию
		tmp.addProperty("kotik", "sdoh");                 // И свойства к ней
		tmp.addProperty("kotik", "jiv");
		tmp = ini.addSection("Lizo4kaZai4ka");
		tmp.addProperty("Zai4ka", "jiv");
		ini.writeFile("Output.ini");                      // Записываем в файл
	}
}
