package by.shyrei.rentshop.utils;

public interface Messages {
	public static final String SPACE = "===================================================\n";
	public static final String SELECT_USER = "Выберите пользователя:\n";
	public static final String WORK_WITH_CLIENT = "1. Для работы с клиентами\n";
	public static final String WORK_WITH_ACCOUNT = "2. Операции со счетами\n";
	public static final String LOAD = "3. Загрузить\n";
	public static final String SAVE = "4. Сохранить\n";
	public static final String EXIT_FROM_PROGRAM = "0. Выход из программы\n";
	
	public static final String RETURN_MAIN = "0. Вернуться в главное меню\n";

	public static final String CREATE_CLIENT = "1. Создать клиента\n";
	public static final String VIEW_LIST_OF_CLIENT = "2. Показать список клиентов\n";
	public static final String DELETE_CLIENT = "3. Удалить клиента\n";
	public static final String FIND_CLIENT = "4. Найти клиента\n";

	public static final String ADD_ACCOUNT = "1. Добавить счет к клиенту\n";
	public static final String VIEW_LIST_OF_ACCOUNT = "2. Показать список счетов клиента\n";
	public static final String VIEW_LIST_CLIENT = "3. Показать список клиентов\n";
	public static final String FIND_ACCOUNT = "4. Найти счет\n";
	public static final String DELETE_ACCOUNT = "5. Удалить счет\n";
	public static final String BALANCE_PLUS = "6. Положительный баланс\n";
	public static final String BALANCE_MINUS = "7. Отрицательный баланс\n";

	public static final String ENTER_FHONE_NUMBER = "Введите свой телефон\n";
	public static final String ENTER_ADRESS = "Введите свой адрес\n";
	public static final String ENTER_ACCOUNT_NUMBER = "Введите номер счета\n";
	public static final String ENTER_ACCOUNT_BALANCE = "Введите сумму денег, которую вы хотите положить на счет.\n";
	public static final String CLIENT_LIST_EMPTY = "Список клиентов пуст\n";
	public static final String ACCOUNT_IS_EXIST = "Такой счет уже существует.\n";
	public static final String ACCOUNT_IS_NOT_EXIST = "Такой счет не существует.\n";
	public static final String NF_EXCEPTION = "Неккоректные символы ввода. Повторите";
	public static final String FILE_NOT_FOUND = "Фалй не найден. Повторите";
	public static final String QUESTION_DELETE_CLIENT = "Вы действительно хотите удалить клиента? (да / нет?)\n";
	public static final String ENTER_FILE_NAME = "Введите имя файла.\n";
	public static final String FILE_SAVE = "Файл сохранен.\n";
	public static final String FILE_LOAD = "Фалй загружен.\n";

	public static final String CLIENT_REGISTERED = "Вы зарегистрированы в банковской системе как:\n";
	public static final String ENTER_NAME_CLIENT = "Введите наименование организации\n";
	public static final String DELETE_CLIENT_MESSAGE = "Клиент удален из базы\n";
	public static final String CLIENT_IS_EXIST = "Такой клиент уже существует. Он принадлежит организации:\n";
	public static final String CLIENT_NOT_EXIST = "Такого клиента не существует. Повторите...\n";
	public static final String NEED_CREATE_ACCOUNT = "\nНеобходимо создать счет.\n";
	public static final String IO_EXCEPTION = "Ошибка ввода/вывода.\n";

	public static final String YES = "да";
	public static final String NO = "нет";
	public static final String INCORRECT_INPUT = "Неправильный ввод, повторите свой выбор:\n";
	public static final String ACCOUNT_LIST_EMPTY = "Список счетов пуст\n";
	
	public static final String NO_CLIENTS = "Нету ни одного клиента.. создайте клиента";

}