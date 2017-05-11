Для запуска приложения - используйте класс "RentShopRuner" в пакете package "by.shyrei.rentshop.runer"
Для изменения максимального кол-ва товара для аренды клиентом - поменяйте константу "MAX_NUMBER_OF_ORDERS" в классе "Operator" в пакете "by.shyrei.rentshop.services"
Инициализация магазина происходит при помощи считывания данных из XML файла - shop.xml.
Для изменения пути к файлу инициализации магазина "shop.xml" - поменйте константу "PARSER_FILE_NAME" в классе "XmlDAOImpl" в пакете "by.shyrei.rentshop.dao.impl"
Также вы можете загрузить данные из файла "rent.txt" (сохранен при помощи сериализации объектов). 

To run the application - use the "RentShopRuner" class in the package "by.shyrei.rentshop.runer"
To change the maximum number of goods for rent by the client - change the constant "MAX_NUMBER_OF_ORDERS" in the class "Operator" in the package "by.shyrei.rentshop.services"
The store is initialized by reading the data from the XML file - shop.xml.
To change the path to the store initialization file "shop.xml" - change the constant "PARSER_FILE_NAME" in the class "XmlDAOImpl" in the package "by.shyrei.rentshop.dao.impl"
Also you can download data from the file "rent.txt" (saved using object serialization).