**  Практическая работа 3. Отчёт по выполнению**

В данной практической работе с темой "Намерения в андроид разработке" будут изучены передача, обмен и получение данных с помощью Intent и нюансы использования фрагментов в различных приложениях.

**  Модуль IntentApp **

Созданы 2 activity. В первой активности получаем системное время.Далее передаётся время из одной активности в другую и отображается
во второй activity в «textView» следующая строка: «КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ ЧИСЛО, а текущее время ВРЕМЯ».

![image](https://github.com/user-attachments/assets/3e0b232b-db99-48ff-b50a-a11cd61a4670)

![image](https://github.com/user-attachments/assets/2ae06294-55ae-4b38-bafd-7a119ef50eae)


**  Модуль Sharer **

Для отправки различных сообщений типа электронного письма, SMS, MMS
и т.д. применяется действие «ACTION_SEND». Метод «setType», требуется для
установки типа MIME для передаваемых данных, хранящихся в параметре
намерения «extras» с ключами EXTRA_TEXT или EXTRA_STREAM, в зависимости
от типа.

![image](https://github.com/user-attachments/assets/bd63182c-6715-4fa5-8ca6-1f82110b665f)


**  Модуль FavoriteBook **

Создано приложение с двумя экранами. Основное предназначение приложения
заключается в отображении на экране названия любимой книги разработчика
и пользователя приложением с использованием двух активностей.
Компоненты первого экрана:
- поле отображения «TextView» имеет несколько состояний: начальное
значение «Тут появится название вашей любимой книги и любимая цитата из нее!»,
«Название Вашей любимой книги: КНИГА. Цитата: Цитата»;
- кнопка «Button» с текстом «Открыть экран ввода данных» предназначена
для открытия второй активности.
Компоненты второго экрана:
- поле отображения «TextView» – «Любимая книга разработчика»;
- поле отображения «TextView» – «Цитата из книги»;
- поле ввода «EditText», со значением свойства «Hint»: «Введите название
Вашей любимой книги»;

![image](https://github.com/user-attachments/assets/e366e748-bc13-4430-b0a9-3b3fc46e5cab)

![image](https://github.com/user-attachments/assets/650f8302-1136-4fc9-bdb2-ed59d14848af)

![image](https://github.com/user-attachments/assets/c4173407-4adc-48ea-bd24-903d30be1d96)


**  Модуль SystemIntentsApp **

В методе «onClickCall» используется конструктор «Intent (String action)».
В методе «onClickOpenBrowser» создается «Intent» и на вход принимается
«action» и «data». Используется стандартный системный «action» – ACTION_VIEW.
В качестве «data» подаётся объект Uri, созданный из веб-ссылки:
«http://developer.android.com».
В методе «onClickMaps» используется конструктор «Intent» с установленным
«action» – ACTION_VIEW, а в качестве «data» создаётся Uri из пары координат –
«55.749479,37.613944». Данный «Intent» означает намерение отобразить карту
с указанными координатами в центре экрана.

![image](https://github.com/user-attachments/assets/1cc43243-5cb7-4ae6-b0ed-be9031f45628)

![image](https://github.com/user-attachments/assets/6af52fd8-ebc0-4546-9f43-02ed37dc2737) ![image](https://github.com/user-attachments/assets/4c65d584-c6ea-45da-a50e-c79b9e22f084) ![image](https://github.com/user-attachments/assets/e0244759-58bd-425f-966f-540b6d47c7a5)


**  Модуль SimpleFragmentApp **

Требуется создать приложение на основе «Fragment», учитывающее
изменение ориентации экрана.

![image](https://github.com/user-attachments/assets/fbff7381-bc87-464e-b2fd-b471167121da)

![image](https://github.com/user-attachments/assets/90a1887d-e0dd-442d-8bf4-ceb112129959)

![image](https://github.com/user-attachments/assets/bbda577f-519a-4242-beca-28b3efdb76d7)


**  Приложение MireaProject **

Добавлены в сайд-меню кнопки с информацией и браузером 

![image](https://github.com/user-attachments/assets/85e00508-51dc-497a-9151-2d70604a58dd)

В информации добавлен скроллвью с текстом и картинкой

![image](https://github.com/user-attachments/assets/e84b4d25-d03e-4914-b735-973a8d2c1adc)

Была ошибка кэша при попытке открытия браузера, поэтому в манифесте добавлено разрешение пользователя на доступ к интернету 

![image](https://github.com/user-attachments/assets/aedba6af-3842-4f1a-ab94-2af988b25a50)


Итоги

В ходе выполнения практической работы изучены передача, обмен и получение данных с помощью Intent и нюансы использования фрагментов в различных приложениях.

Выполнила Жемайтис Валерия
Группа БСБО-09-22
