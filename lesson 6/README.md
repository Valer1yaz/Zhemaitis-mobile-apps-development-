**  Практическая работа 6. Отчёт по выполнению**

В ходе практической работы будет изучено хранение данных в OS Android: хранение через специальный файл настроек с использованием класса «SharedPreferences», хранение файлов во внутреннем и внешнем хранилище, работа с базой данных SQLite.

**  Модуль App **

добавлены поля ввода текста и кнопка. Сделано сохранение введённых данных через getSharedPreferences и их загрузка в поля при запуске приложения. Через «Device Explorer» просмотрен файл настроек, в котором новый файл с полученными данными. Сделан скриншот экрана со значениями файла, скриншот сохранен в папке «raw».

![image](https://github.com/user-attachments/assets/1287c492-eb13-44dc-bba2-589513f69494) ![image](https://github.com/user-attachments/assets/26a3898e-d3e3-4928-8174-d11b40b8094f)

![image](https://github.com/user-attachments/assets/c40eced4-d764-488e-a7a5-0636f8ac3fa2)

![image](https://github.com/user-attachments/assets/c823a80b-ffc4-4bfa-a132-89ee954f7d3c)


**  Модуль SecureSharedPreferences **

В файле разметки добавлено поле ImageView, текстовое поле и кнопка для сохранения данных. В «gradle»-файл модуля добавлены новые зависимости. В файле «MainActivity» реализован механизм создания мастер-ключа и шифрования данных AES256. Сделан скриншот экрана со значениями полученного файла, скриншот сохранен в папке «raw».

![image](https://github.com/user-attachments/assets/bb06ba57-f5bc-4984-a863-1e00e801910b) 

![image](https://github.com/user-attachments/assets/0bffe206-37d2-40fa-8e65-179ab5abe78d)

![image](https://github.com/user-attachments/assets/c9359322-f329-4e60-822d-9b02f9e8e983)


**  Модуль InternalFileStorage **

В файле разметки добавлено поле для ввода текста и кнопка. В «MainActivity» реализована логика записи данных, введённых пользоватлем во внутреннее хранилище. Полученный файл сохранен в папке «raw».

![image](https://github.com/user-attachments/assets/862917e3-8c15-4908-82ba-6a87d6578166) ![image](https://github.com/user-attachments/assets/f189d7ef-dfa2-4d31-baef-f5d04eab2a25)

![image](https://github.com/user-attachments/assets/629a50a4-cd61-44de-9e1b-a668a1f31dfa)


**  Модуль Notebook **

В файле разметки activity_main.xml добавлено два поля для ввода текста (названия файла и цитата) и две кнопки для сохранения и загрузки сохранённых данных. В файле MainActivity реализована логика записи полученных данных в общедоступную директорию Documents с перезаписью существующих файлов. Создано два файла с цитатами известных личностей. Полученный файл сохранен в папке «raw».

![image](https://github.com/user-attachments/assets/8e7433cd-dc1b-4b1f-a2d3-5f09b2e74ef4) ![image](https://github.com/user-attachments/assets/2329f8ce-4465-4fe4-aefe-2e3a76b238ad)
![image](https://github.com/user-attachments/assets/86d965a2-efd8-4100-9635-af7cd0080d80) ![image](https://github.com/user-attachments/assets/b770825d-f0c1-425e-8ff4-1f48016da74a)

**  Модуль EmployeeDB **

В файле разметки activity_main.xml было добавлено текстовое поле и компонент ListView для отображения списка данных из БД. Созданы дополнительные файлы: файл Superhero.java, реализующий класс-сущность для создания таблицы, файл SuperheroDAO.java, реализующий интерфейс DAO с методами CRUD, файл AppDatabase.java, реализующий класс базы данных. Создан класс Application для инициализации БД. В файле MainActivity реализована логика создания БД через созданный Application, заполнение тестовыми данными и вывод полученных данных в соответствующий компонент (список).

![image](https://github.com/user-attachments/assets/e217df18-b2c8-47f9-804b-a0c77ce83c69) ![image](https://github.com/user-attachments/assets/4ac57902-cf64-450f-87d7-4766370ae1d0)

![image](https://github.com/user-attachments/assets/90254329-991a-4227-940a-a78cf24016b4)

![image](https://github.com/user-attachments/assets/901cb8a5-1465-4e4c-912b-93f016077588) ![image](https://github.com/user-attachments/assets/90e6afbc-e9db-484f-87c7-c0f1b848d6a4)


**  Контрольное задание MireaProject **

В приложение было добавлено:
Фрагмент ProfileFragment. В файл разметки добавлено несколько полей ввода текста (имя, возраст, группа и номер по списку) и кнопка сохранения. Добавлена логика сохранения введённых данных в файл настроек.
Фрагмент WorkingWithFilesFragment. В файле разметки добавлена кнопка «Floating Action Button» и текстовое поле для отображения всех сохранённых файлов. 
Фрагмент FileDialogFragment. Сделан вывод окна с полями для ввода текста и кнопками сохранить и отменить, которое появляется после нажатия на круглую  кнопку действия на основном экране. Добавлена логика простой шифрования и сохранения данных в локальном хранилище.



Итоги

В ходе выполнения практической работы изучено . Дополнен МирэаПроект. Работа выполнена в полном объёме.

Выполнила Жемайтис Валерия
Номер по списку 9
Группа БСБО-09-22
