**  Практическая работа 5. Отчёт по выполнению**

В ходе практической работы будет изучено использование аппаратных показателей смартфонов, а именно сенсоров и датчиков (компаса и акселлеромента), а также камеры и микрофона. Изучен механизм запроса разрешений на пользование определёнными приложениями.

**  Модуль App**

Свободный layout основанный на относительных связях с parent

Добавлен компонент «ListView» необходимый для отображения списка. В файле «MainActivity» реализована логика получения списка всех существующих датчиков и отображения их в компоненте с соответствующими названиями.

![image](https://github.com/user-attachments/assets/5400f2f2-b8de-4bda-b7b3-eb1e733d9df2)

**  Модуль Accelerometer**

В файле разметки добавлено 3 текстовых поля для отображения трёх различных значений. В файле «MainActivity» при помощи интерфейса «SensorEventListener» реализована логика отображения текущих показаний акселерометра по трём осям (X, Y, Z) и логика обновления данных при изменении положения мобильного устройства.

![image](https://github.com/user-attachments/assets/63326f1d-1dd8-4ad5-96c9-9233d3068cc5)

**  Модуль Camera**

В файле разметки добавлено поле imageView, при нажатии на которое открывается камера. В папке res в папке xml создан файл paths.xml для сохранения полученного изображения в галерее. В манифест файле прописано разрешение на использование камеры. В «MainActivity» реализована логика запуска приложения "камера" при нажатии на соответствующий компонент, создание новой фотографии и сохранения фотографии во внутренней памяти.

![image](https://github.com/user-attachments/assets/13829d20-9b3c-44df-b478-e693ca975840) ![image](https://github.com/user-attachments/assets/711a6656-e1a8-49a0-805b-c2b572a98de0)
![image](https://github.com/user-attachments/assets/a4a7fb31-e603-4796-9e4c-ce116b66a557) ![image](https://github.com/user-attachments/assets/51b8269a-1589-46c5-8ec6-5412985a5390)

**  Модуль Audiorecord**

В файле activity_main.xml добавлено две кнопки для записи и воспроизведения аудио. В манифестe добавлено разрешение на использование микрофона. В файле MainActivity в классе «MediaRecorder» реализована логика записи аудиофайла и воспроизведения записанного файла по нажатию на соответствующие кнопки, также реализован учет состояния нажатия кнопок для предотвращения одновременного запуска процессов.

![image](https://github.com/user-attachments/assets/53f5979e-55d5-4523-9c7a-8e81905474c2) ![image](https://github.com/user-attachments/assets/466af420-0751-42ac-bfab-87c40f0d0053)
![image](https://github.com/user-attachments/assets/60135940-896d-4677-8aa4-e0bc8cffdc06) ![image](https://github.com/user-attachments/assets/95ff01d3-e489-4038-9f94-f258f6ca0f1e)
![image](https://github.com/user-attachments/assets/3b3b526c-6f59-4ede-aa5e-8d4357b6f622) ![image](https://github.com/user-attachments/assets/5d4a06c3-6459-46ef-9b7b-e8715cce3d2e)





**  Контрольное задание добавление новых экранов в MireaProject **

В приложение были добавлены новые фрагменты SensorFragment, CameraFragment и MicrophoneFragment. Фрагмент камеры использован для создания записи с фото. Фрагменты также реализуют механизм для запроса разрешений на использование соответствующих приложений.

![image](https://github.com/user-attachments/assets/4c20c36d-9dc4-4baa-bf0e-9b2e47b6dd96) ![image](https://github.com/user-attachments/assets/01c6f951-39e4-4a72-8f7f-f69349e0a859)

![image](https://github.com/user-attachments/assets/c609c377-81ad-496b-8cc3-ccac62357870) ![image](https://github.com/user-attachments/assets/e5170bfb-2983-440d-842e-13a4b7d0e546)
![image](https://github.com/user-attachments/assets/ab06567a-b87e-492d-8109-2dccdb14bfd4) ![image](https://github.com/user-attachments/assets/30c233fb-1260-42a7-9d48-32b50b1d6df8)

![image](https://github.com/user-attachments/assets/47d4659a-8d88-4818-b895-6d98bc2f767c) ![image](https://github.com/user-attachments/assets/9925e88a-0b5b-46e1-9f0a-8e011a990287)




Итоги

В ходе выполнения практической работы изучено использование аппаратных показателей смартфонов, а именно сенсоров и датчиков (компаса и акселлеромента), а также камеры и микрофона. Изучен механизм запроса разрешений на пользование определёнными приложениями. Дополнен МирэаПроект. Работа выполнена в полном объёме.

Выполнила Жемайтис Валерия
Номер по списку 9
Группа БСБО-09-22
