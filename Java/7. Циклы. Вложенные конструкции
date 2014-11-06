test1
=====

Java
import java.util.Calendar;
import java.util.Scanner;

public class cycles_two {
	private static Scanner input = new java.util.Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Текст для вывода в консоль
		String value_text, text_info_cyclel;
		// Параметры
		text_info_cyclel = "No solution";

		// //////////////////////////// task 1 ////////////////////////////
		// Загадать случайно 100 целых чисел в диапазоне от -100 до 100.
		// Вычислить процент положительных чисел, процент отрицательных чисел и
		// процент нулей. Вычислить процент чётных чисел и процент нечётных.

		// Выводим шапку № задачи
		textInfo("1", "Шапка");
		// Решение
		byte b_positive_number = 0, b_negative_number = 0, b_even_number = 0;
		byte b_not_even_number = 0, b_zero_number = 0, b_number_one = 0;
		text_info_cyclel = "";
		//
		for (int i = 0; i < 100; i++) {
			b_number_one = (byte) (Math_random(-100, 100));// получаем случайное
															// число

			// определяем четное или не четное
			if (b_number_one % 2 == 0) {
				b_even_number++;// четное
			} else {
				b_not_even_number++;// не четное
			}
			// определяем положительное или отрицательное или ноль
			if (b_number_one > 0) {
				b_positive_number++;// положительное
			} else if (b_number_one == 0) {
				b_zero_number++;// ноль
			} else {
				b_negative_number++;// отрицательное
			}
		}

		text_info_cyclel = text_info_cyclel + "The % from -100 to 100 \n"
				+ "positive = " + b_positive_number + "\n" + "negative = "
				+ b_negative_number + "\n" + "even = " + b_even_number + "\n"
				+ "not even = " + b_not_even_number + "\n" + "zero = "
				+ b_zero_number + "\n";
		// Выводим результат
		textInfo(text_info_cyclel, "Подвал");

		// //////////////////////////// task 2 ////////////////////////////
		// Вывести на экран таблицу умножения (таблицу Пифагора).

		// Выводим шапку № задачи
		textInfo("2", "Шапка");
		// решение
		byte a = 0;
		byte b = 1;
		byte z = 0;

		for (byte j = 22; j < 110; j++) {

			a = (byte) (j % 10); // остаток от деления

			if (a == 0) {
				System.out.println();// двигаем каретку
				b++;
			}
			if (a < 2 || a > 9) {// фильтр сколько выводим столбцов
				continue;
			}
			z = (byte) (a * b);// расчет
			System.out.print(a + " x " + b + " = " + z + " \t");

		}

		System.out.print("\n");

		// //////////////////////////// task 3 ////////////////////////////
		// Ежемесячная стипендия студента составляет N гривен, а расходы на
		// проживание превышают стипендию и составляют M гривен в месяц. Рост
		// цен ежемесячно увеличивает расходы на 3%. Составьте программу расчёта
		// суммы денег, которую необходимо единовременно попросить у родителей,
		// чтобы можно было прожить учебный год (10 месяцев), используя только
		// эти деньги и стипендию.

		// Выводим шапку № задачи
		textInfo("3", "Шапка");

		// Вводим данные
		value_text = "Enter monthly scholarship student...";
		int i_scholarship = nextInt(value_text);// ежемесячная стипендия
												// студента
		value_text = "Enter living expenses...";
		int i_staying = nextInt(value_text);// расходы на проживание

		// решение
		// проверка
		if (i_staying > i_scholarship) {// проверяем расходы должны быть больше
										// чем стипендия

			int i_staying_sum = 0;// сумма стипендии за 10 месяцев
			// расчет цен которые ежемесячно увеличивают расходы на 3%
			for (byte j = 0; j < 10; j++) {
				// 3%
				i_staying = i_staying + (i_staying * 3 / 100);
				// сумма
				i_staying_sum = i_staying_sum + i_staying;
			}
			// вычисляем сумму денег, которую необходимо единовременно попросить
			// у родителей на 10 мес
			int i_sum_10 = i_staying_sum - i_scholarship * 10;

			text_info_cyclel = "Cуммы денег, которую необходимо единовременно попросить у родителей чтобы можно было прожить учебный год (10 месяцев) = "
					+ i_sum_10;

		} else {
			text_info_cyclel = "err: Мы не в той стране живем, где может быть наоборот.";
		}
		// Выводим результат
		textInfo(text_info_cyclel, "Подвал");

		// //////////////////////////// task 4 ////////////////////////////
		// Показать на экране прямоугольник размером MxN, состоящий из
		// звёздочек. Затем сделать то же самое, но чтоб фигура внутри была
		// пустая (остаётся только рамка).

		// Выводим шапку № задачи
		textInfo("4", "Шапка");

		// Вводим данные
		value_text = "Enter the size of the rectangle M...";
		int M = nextInt(value_text);// вводим размер
		value_text = "Enter the size of the rectangle N...";
		int N = nextInt(value_text);// вводим размер

		// Заполненный
		for (int y = 0; y <= M; y++) {

			for (int x = 0; x <= N; x++) {
				if (x <= N) {
					System.out.print("*");
				}
			}
			System.out.print("\n");
		}

		// Каркас
		for (int y = 0; y <= M; y++) {

			for (int x = 0; x <= N; x++) {

				if (x == 0 && y <= M || y == 0 && x <= N || y <= M && x == N
						|| y == M && x <= N) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}

		// //////////////////////////// task 5 ////////////////////////////
		// Показать на экране равнобедренный треугольник (пользователь вводит
		// высоту). Затем сделать то же самое, но чтоб фигура внутри была пустая
		// (остаётся только контур).

		// Вводим данные
		value_text = "Enter type the height of the triangle...";
		byte height = nextByte(value_text);// вводим размер

		// Стороны треугольника
		a = 0;
		b = (byte) (height - 1);
		byte c = (byte) (height + 1);

		// Заполненный треугольник
		for (byte y = 0; y <= height; y++) {
			for (byte x = 0; x <= height * 2; x++) {
				if (y == a && x > b && x < c) {
					System.out.print("*");

				} else {
					System.out.print(" ");
				}
			}
			a++;
			b--;
			c++;
			System.out.print("\n");
		}

		// Стороны треугольника
		a = 0;
		b = height;
		c = height;
		// каркас треугольник
		for (byte y = 0; y <= height; y++) {
			for (byte x = 0; x <= height * 2; x++) {

				if (y == a && x == b || y == a && x == c || y == height
						&& x <= height * 2) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			a++;
			b--;
			c++;
			System.out.print("\n");
		}
		// //////////////////////////// task 6 ////////////////////////////
		// Написать программу, которая выводят на экран ромб (длины
		// диагоналей ромба одинаковы по значению, но могут быть равны любому
		// числу). Затем сделать то же самое, но чтоб фигура внутри была пустая
		// (остаётся только контур).

		// Вводим данные
		value_text = "Enter type the height of the triangle...";
		height = nextByte(value_text);// вводим размер
		byte height_2 = (byte) (height / 2);

		// Заполненный
		// Верхня сторона
		for (byte y = 0; y <= height_2; y++) {

			for (byte x = 0; x <= height; x++) {

				if (x <= height_2 + y && x >= height_2 - y) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}

		// Нижняя сторона
		for (byte y = height_2; y < height; y++) {

			for (byte x = 0; x < height; x++) {
				if (x <= height_2 + height - y - 1 && x >= y - height_2 + 1) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}

			System.out.print("\n");
		}

		// Карскас
		// Верхня сторона
		for (byte y = 0; y <= height_2; y++) {

			for (byte x = 0; x <= height; x++) {

				if (x == height_2 + y || x == height_2 - y) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
		// Нижняя сторона
		for (byte y = height_2; y < height; y++) {

			for (byte x = 0; x < height; x++) {
				if (x == height_2 + height - y - 1 || x == y - height_2 + 1) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}

			System.out.print("\n");
		}
		// //////////////////////////// task 7 ////////////////////////////
		// Определить, является ли введённое число любой разрядности палиндромом
		// (например, 1234321 – палиндром, 12345 – не палиндром).

		// Выводим шапку № задачи
		textInfo("7", "Шапка");

		// Вводим данные
		value_text = "Enter the number of any capacity...";
		int i_capacity = nextInt(value_text);// вводим число

		String str_duplicate_one = "" + i_capacity;
		String str_let_1 = "";
		String str_let_2 = "";
		text_info_cyclel = "";
		int y = digit_num(i_capacity) - 1;// получаем разряд числа

		for (byte x = 0; x < y; x++) {

			str_let_2 = "" + str_duplicate_one.charAt(x);// слево число
			str_let_1 = "" + str_duplicate_one.charAt(y--);// справа число
			// проверка
			if (!str_let_2.equals(str_let_1)) {
				text_info_cyclel = "The number is not a palindrome.";
				break;
			} else {
				text_info_cyclel = "The number is a palindrome.";
			}
		}

		// Выводим результат
		textInfo(text_info_cyclel, "Подвал");

		// //////////////////////////// task 8 ////////////////////////////
		// Осуществить циклический сдвиг влево введённого числа на N разрядов
		// (например, при сдвиге числа 12345 влево на 3 разряда получится число
		// 45123).

		// Выводим шапку № задачи
		textInfo("8", "Шапка");
		// Вводим данные
		value_text = "Enter the number...";
		int i_number_next = nextInt(value_text);// вводим число
		value_text = "Enter how much to shift the category of number...";
		int i_number_shift_next = nextInt(value_text);// вводим число

		String st_tem = "";
		text_info_cyclel = "";
		int i_number = 0;

		for (byte x = 0; x < i_number_shift_next - 1; x++) {

			i_number = i_number_next % 10;// получаем остаток
			st_tem = i_number + st_tem;// перевернули сразу
			i_number_next = i_number_next / 10;// уменьшаем число

		}
		text_info_cyclel = st_tem + i_number_next;
		// Выводим результат
		textInfo(text_info_cyclel, "Подвал");

		// //////////////////////////// task 9 ////////////////////////////
		// Вывести на экран каркас параллелепипеда размерностью AxBxC.
		// Выводим шапку № задачи

		textInfo("9", "Шапка");

		// Вводим данные
		value_text = "Enter the length of the side A...";
		int A = nextInt(value_text);// вводим число
		value_text = "Enter the length of the side B...";
		int B = nextInt(value_text);// вводим число
		value_text = "Enter the length of the side C...";
		int C = nextInt(value_text);// вводим число

		int A_C = A - C;
		int B_C = B - C;

		for (y = 0; y <= A; y++) {

			for (int x = 0; x <= B; x++) {

				if (y >= C && x == 0 || y == C && x <= B_C || y >= C
						&& x == B_C || y == A && x <= B_C) {// первый квадрат

					System.out.print("*");
				} else if (y <= A_C && x == C || y == 0 && x >= C || y <= A_C
						&& x == B || y == A_C && x >= C) { // второй квадрат
					System.out.print("*");

					// /////// Строна С начало // //////////////////////
				} else if (y == A - x && x == A - y && y >= A_C) {
					System.out.print("*");
				} else if (y == A - x - A_C && x == A - y - A_C) {

					System.out.print("*");
				} else if (y == B - x + A_C && x == B - y + A_C) {
					System.out.print("*");

				} else if (y == B - x && x == B - y && y <= A_C) {
					System.out.print("*");
					// /////// Строна С конец // //////////////////////

				} else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}

		// //////////////////////////// task 10 ////////////////////////////
		// Написать программу, которая выводит на экран все простые числа в
		// диапазоне от 2 до 10.000.000.
		textInfo("10", "Шапка");

		// Решение
		int number_N_d = 0;
		text_info_cyclel = "The prime numbers...\n";
		// перебираем все числа от 2 до 10.000.000
		for (int i = 1; i < 10000000; i++) {
			number_N_d = 0;
			for (int j = 1; j <= i; j++) {
				if (i % j == 0) {
					number_N_d++;
				}
			}
			if (number_N_d <= 2)
				text_info_cyclel = text_info_cyclel + i + "\n";

		}
		// Выводим результат
		textInfo(text_info_cyclel, "Подвал");

		// //////////////////////////// task 11 ///////////////////////////
		// Показать на экране все числа Армстронга в диапазоне от 0 до
		// 10.000.000.
		textInfo("11", "Шапка");
		byte number_digit = 0;// разряд числа
		int sum_armstrong = 0;// число Армстронга
		text_info_cyclel = "The number Armstrong...\n";

		for (int i = 0; i <= 10000000; i++) {
			// Определяем разряд
			number_digit = digit_num(i);
			sum_armstrong = 0;// число Армстронга обнуляем
			i_number_next = i;// копируем в другую переменную

			for (int j = 0; j < number_digit; j++) {

				i_number = i_number_next % 10;// получаем остаток
				sum_armstrong = sum_armstrong
						+ (int) (Math.pow(i_number, number_digit));// число
																	// Армстронга
				i_number_next = i_number_next / 10;// уменьшаем число
			}

			text_info_cyclel = text_info_cyclel + sum_armstrong + "\n";
		}

		// Выводим результат
		textInfo(text_info_cyclel, "Подвал");

		// //////////////////////////// task 12 ///////////////////////////
		// Показать на экране все числа Армстронга в диапазоне от 0 до
		// 10.000.000.
		textInfo("12", "Шапка");

		int n = 0;
		i_number_next = 0;
		text_info_cyclel = "The perfect numbers...\n";

		for (int i = 0; i <= 10000000; i++) {
			i_number_next = i;// копируем в другую переменную
			for (int j = 1; j < i_number_next; j++) {
				if (i_number_next % j == 0)// смотрим на остаток
					n += j;
			}
			if (n == i)// проврка
				text_info_cyclel = text_info_cyclel + i + "\n";
			n = 0; // обнуляем
		}
		// Выводим результат
		textInfo(text_info_cyclel, "Подвал");

		// //////////////////////////// task 13 ///////////////////////////
		// Показать на экране все числа Фибоначчи в диапазоне от 0 до
		// 10.000.000.
		textInfo("13", "Шапка");

		int a_f = 1, b_f = 0, sum_ab = 0;
		text_info_cyclel = "The Fibonacci numbers...\n";

		for (int i = 0; i <= 10000000; i++) {
			sum_ab = a_f + b_f;
			a_f = b_f;
			b_f = sum_ab;
			text_info_cyclel = text_info_cyclel + sum_ab + "\n";
		}
		// Выводим результат
		textInfo(text_info_cyclel, "Подвал");

		// //////////////////////////// task 14 ///////////////////////////
		// Написать программу, которая генерирует календарь на любой год,
		// указанный с клавиатуры.

		textInfo("14", "Шапка");
		value_text = "Enter the year...";
		short year_number = nextshort(value_text);// вводим год

		// Определяем по году день 6 января его порядковый номер в недели
		Calendar newCal = new java.util.GregorianCalendar(year_number, 0, 6);
		byte day_start = (byte) (newCal.get(Calendar.DAY_OF_WEEK));
		//
		byte date_start;
		// вычисляем день старта в блоке января
		date_start = (byte) (31 - day_start);

		a = 0;// индекс для массива от 0 - 6
		b = 0;// число вхожденрий дат 1
		c = 0;// дата с какой нужно начинать следующий месяц
		byte d = date_start;// дата с какой нужно начинать год
		byte day_28 = 0;// дата высокосного года
		byte f = 0;// дата начало если в нижней строке блока счет дат от 1 до 7

		// определяем в этом году сколько в феврале дней 28 или 29
		day_28 = (byte) (year_number % 4 == 0 ? 28 : 29);

		byte[] m_7day = new byte[7];// хранит 7 последних дат месяца
		// месяца года
		String[] months = { "январь", "февраль", "март", "апрель", "май",
				"июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь",
				"декабрь" };

		for (byte i = 0; i < 12; i++) {

			System.out.print("     < " + months[i] + " >\n");
			System.out.print(" пн вт ср чт пт сб вс\n");
			// System.err.print(" сб вс\n");//не всегда работает

			for (byte j = 1; j <= 42; j++) {

				d++;// счет дат от 1 - 31
				a++;// счет для индекса в массиве
				//
				m_7day[a - 1] = d;// запоминаем последние 7 чисел мясаца
				// считаем сколько первых чисел есть в блоке месяца
				if (d == 1) {
					b++;
				}
				//
				if (b == 2) {// если первых чисел = 2 запоминаем число
								// для следующего месяца
					c = m_7day[0];
					b = 0;
				}
				// запоминаем дату когда в нижней строке блока счет дат от 1 до
				// 7
				if (j == 35) {
					f = d;
				} else if (j == 42 && d == 7) {
					c = (byte) (f - 6);// дата начало если в нижней строке блока
										// счет дат от 1 до 7
				}

				// если число с разрядом 1 тогда два пробела иначе один
				if (digit_num(d) == 1) {
					System.out.print("  " + d);// выводим дату
				} else {
					System.out.print(" " + d);// выводим дату
				}
				// считаем до 7 потом двигаем каретку
				if (j % 7 == 0) {
					System.out.println();
					a = 0;// обнуляем индекс в массиве
				}

				// Вычисляем до какой даты неужно вести счет в блоке месяца

				if (d == day_28 && i == 1 && b == 1 || d == day_28 && i == 2
						&& b == 0) {
					d = 0;
				} else if (d == 30 && i == 3 && b == 1 || d == 30 && i == 4
						&& b == 0) {
					d = 0;
				} else if (d == 30 && i == 5 && b == 1 || d == 30 && i == 6
						&& b == 0) {
					d = 0;
				} else if (d == 30 && i == 8 && b == 1 || d == 30 && i == 9
						&& b == 0) {
					d = 0;
				} else if (d == 30 && i == 10 && b == 1 || d == 30 && i == 11
						&& b == 0) {
					d = 0;
				} else if (d == 31) {
					d = 0;
				}
			}
			// дата начало следуещего месяца
			d = (byte) (c - 1);
			b = 0;
			System.out.print("\n");
		}

		// //////////////////////////// task 15 ///////////////////////////
		// Вывести лестницу из отрезков определённой длины. Длина (например, 14)
		// и количество ступенек (например, 4) указывается с клавиатуры.
		
		textInfo("15", "Шапка");
		
		value_text = "Enter the length of the stairs...";
		 A = nextInt(value_text);// вводим число
		value_text = "Enter number of steps...";
		 B = nextInt(value_text);// вводим число
    	
    	for (int i = 1; i <= A; ++i)
    	{
    		for (int j = 1; j < i; ++j)
    		{
    			System.out.print(" ");
    		}
    		for (int j = 1; j <= B; ++j)
    		{
    			System.out.print("*");
    		}
    		System.out.println("");

    	}
	}

	// ////////////////////////////////////////////////////////
	// ВСПОМАГАТЕЛЬНЫЕ ФУНКЦИИ И ПРОЦЕДУРЫ
	// ///////////////////////////////////////////////////////

	// Показывает число разрядов в числе
	// numb_value - число у которого надо определить разряд
	// Возвращаем число
	public static byte digit_num(int number_value) {
		byte number_digit = 0;

		// Если число = 0, то автоматом это 1 разряд
		if (number_value >= 0 && number_value < 10) {
			number_digit = 1;
		} else {
			while (number_value > 0) {

				number_value = number_value / 10;// каждое делениена 10 - 1
													// разряд
				// + разряд
				number_digit++;
			}
		}
		return number_digit;
	}

	// Выводим шапку или подвал задачи
	// text_r - режим вывода информации (шапка или подвал...)
	// tesk_number - № задачи

	public static void textInfo(String tesk_number_info, String text_r) {

		String text_info = "";
		switch (text_r) {
		case "Шапка":
			text_info = "Task № " + tesk_number_info + "\n";
			break;
		case "Подвал":
			text_info = "Result:\n" + tesk_number_info + "\n\n";
			break;
		}
		System.out.print(text_info);
	}

	// ////////////////////////////////////////////////////////
	// ВСПОМАГАТЕЛЬНЫЕ ФУНКЦИИ И ПРОЦЕДУРЫ
	// ///////////////////////////////////////////////////////

	// Вычислям случайные чисела в диапазоне от и до
	// number_of - число от
	// number_to - число до
	// Возвращаем число
	public static int Math_random(int number_of, int number_to) {

		int numb_rnd = number_of
				+ (int) (Math.random() * ((number_to - number_of) + 1));
		return numb_rnd;
	}

	// Создаем массив
	// Возвращаем массив
	public static int[] Shared_numbers_on_figures(int number_m) {

		// Создаем массив взависимости от размера параметра
		int[] massif = new int[number_m];

		return massif;
	}

	// Вводим значение в консоль
	// value_text - текст для вывода в консоль
	// Возвращаем число
	public static int nextInt(String value_text) {
		int value_input;

		// Выводим текст на экран
		System.out.println(value_text);
		// Вводим значение
		value_input = input.nextInt();

		return value_input;

	}

	// Вводим значение в консоль
	// value_text - текст для вывода в консоль
	// Возвращаем число
	public static short nextshort(String value_text) {
		short value_input;

		// Выводим текст на экран
		System.out.println(value_text);
		// Вводим значение
		value_input = input.nextShort();

		return value_input;

	}

	// Вводим значение в консоль
	// value_text - текст для вывода в консоль
	// Возвращаем число
	public static byte nextByte(String value_text) {
		byte value_input;

		// Выводим текст на экран
		System.out.println(value_text);
		// Вводим значение
		value_input = input.nextByte();

		return value_input;

	}
}
