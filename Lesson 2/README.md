**  Практическая работа 2. Отчёт по выполнению**

Во второй практической работе были изучены инструменты отладки приложений, жизненный цикл activity, процесс создания и вызова activity, а также работа с диалоговыми окнами.

** Модуль ActivityLifecycle **

Была создана страничка со строкой для редактирования текста, к которой дописаны сообщения, которые будут сообщать в лог происходящие процессы

![image](https://github.com/user-attachments/assets/392b2e1c-d459-4816-9335-ccc4aa3e83b4)

Состояние logcat после запуска приложения, изменения текста, выхода в меню и возвращения в приложение:

![image](https://github.com/user-attachments/assets/b7f67070-7cc8-4257-accb-e9cc86a88981)

* Будет ли вызван метод «onCreate» после нажатия на кнопку «Home» и возврата в приложение?
  * Нет, метод не сработает, потому что активность не закрывается. При повторном открытии приложения выполнятся onRestart(), onStart() и onResume().
* Изменится ли значение поля «EditText» после нажатия на кнопку «Home» и возврата в приложение?
  * Нет, при сворачивании приложения вызывается только onPause(), поэтому при возврате значение поля останется прежним.
* Изменится ли значение поля «EditText» после нажатия на кнопку «Back» и возврата в приложение?
  * Да, сохранение работает, так как в ActivityMain переопределён onSaveInstanceState(). Без этого активность пересоздалась бы, и значение UI-элемента сбросилось.

** Модуль MultiActivity **

Было добавлено поле для ввода ФИО и кнопка, при нажатии на которую введенный текст из первой активности копируется во viewtext во второй активности 

![image](https://github.com/user-attachments/assets/49793536-fdd5-4831-9dd6-c8345d5291a9)

![image](https://github.com/user-attachments/assets/13984327-d0c5-4248-9993-af27c7ae124f)

В логе можно наблюдать переключение активностей

![image](https://github.com/user-attachments/assets/faa8956c-8902-473f-b4ee-efbdea3e2e41)

** Модуль IntentFilter **

В разметку добавлены «activity_main.xml» кнопка и обработчик нажатия для вызова веб-браузера

![image](https://github.com/user-attachments/assets/5a80eb99-db48-4ae7-9295-da3ce265cf25)

При нажатии на первую кнопку открывается браузер на указанной странице

![image](https://github.com/user-attachments/assets/bb6b96cd-2e27-4f18-9d35-eed7296cde2f)

При нажатии на кнопку "Мои ФИО" открывается окно для отправки указанных данных в другие приложения

![image](https://github.com/user-attachments/assets/f649d47a-7d69-465e-874f-bdd2fefe559b)

** Модуль ToastApp **

Обработчик для подсчёта количества введенных символов с выводом во всплывающей подсказке

![image](https://github.com/user-attachments/assets/47506177-6cd0-488f-93b7-f08b8c7f267d) ![image](https://github.com/user-attachments/assets/a5db58b9-3cce-4eac-94b8-7bcf38d13658)


** Модуль NotificationApp **

Обработчик для отправки\получения уведомления в шторке

![image](https://github.com/user-attachments/assets/11fbf9ee-30d8-476e-bfdc-db412b0c599f)

![image](https://github.com/user-attachments/assets/9ac77362-c297-4cf7-a5b6-8d2425d595b0)

![image](https://github.com/user-attachments/assets/1d8c8eba-8044-495d-a490-d0b21c3c2fcd) ![image](https://github.com/user-attachments/assets/9d81ffec-e418-4f2c-a454-689fe84a7d94)


** Модуль Dialog **

Самый большой модуль практики
Добавлены кнопки для вызова диалогов

![image](https://github.com/user-attachments/assets/9e1f23e9-683c-4694-b714-1ef292fce046) !

В первой кнопке по инструкции из практики сделан диалог "Дороги к успеху"

![image](https://github.com/user-attachments/assets/96cf5aee-a365-46ad-bf9b-2d10cc34f678)
 [image](https://github.com/user-attachments/assets/310d97a8-8977-43d3-958f-3df6c82762bb) ![image](https://github.com/user-attachments/assets/34ecd7bb-cb61-4b78-9723-289f9167ce37)

По второй кнопке вызывается диалог выбора времени с помощью TimePickerDialog

![image](https://github.com/user-attachments/assets/1ac3d18b-ed9c-42c2-98cd-6dcd2f707250) ![image](https://github.com/user-attachments/assets/9f3950c2-3fde-4a8a-8376-5fad0d6baf4f)

На третьей кнопке вызывается диалог выбора даты сделанный с помощью Snackbar

![image](https://github.com/user-attachments/assets/1215a0ac-f637-4b62-ae3c-a4cfa6effb50) ![image](https://github.com/user-attachments/assets/3e322268-53fe-4426-89bd-5713255a9961)

По последней кнопке вызывается уведомление "прогресса\загрузки" ProgressDialog

![image](https://github.com/user-attachments/assets/e504eac9-37b4-4424-b7cf-e8f5f6c53b45)

** Итоги **

В ходе выполнения практической работы были изучены инструменты отладки приложений, жизненный цикл activity, процесс создания и вызова activity, а также работа с диалоговыми окнами.

Выполнила Жемайтис Валерия
Группа БСБО-09-22
