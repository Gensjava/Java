test1
=====

Java
// //////////////////////////// task 15 ////////////////////////////
		// Написать программу, позволяющую переводить любое число из десятичной
		// системы счисления в 2, 8 и 16-ричную систему.
		int i_number_next, i_sys_next, i_numbre;
		String st_tem = "";

		// Выводим шапку № задачи
		textInfo("15", "Шапка");// Данные
		// Выбираем число
		value_text = "Enter the number ...";
		i_number_next = nextInt(value_text);
		// Выбираем систему счисления
		value_text = "Enter the system uscislenia 2/8/16...";
		i_sys_next = nextInt(value_text);

		// Решение
		if (i_sys_next == 2 || i_sys_next == 8 || i_sys_next == 16) {
			while (i_number_next != 0) {

				i_numbre = i_number_next % i_sys_next;// получаем остаток
				// если 16 -ти система счисления
				if (i_sys_next == 16 && i_numbre >= 10) {
					st_tem = uscislenia_16(i_numbre) + st_tem;// перевернули
																// сразу
				} else {
					st_tem = i_numbre + st_tem;// перевернули сразу
				}
				// st_tem = i_numbre + st_tem;// перевернули сразу
				i_number_next = i_number_next / i_sys_next;// уменьшаем число
			}
			text_info_cyclel = st_tem;
		} else {
			text_info_cyclel = "err: command";
		}

		// Выводим результат
		textInfo(text_info_cyclel, "Подвал");
