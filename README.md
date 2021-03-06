# Веб-сервис, реализующий логику работы клиентов с банковскими счетами

## Реализованные функции

1.  Выпуск новой карты по счету
2.  Просмотр списка карт
3.  Внесение средств
4.  Просмотр баланса

## Краткое описание работы приложения

При обращении клиента по одному из 4-ех запросов(POST - /cards, GET - /cards/{номер_счета}, PUT - /accounts/{номер_счета}, GET - /accounts/{номер_счета}), приложение отдает результат в виде JSON-файла.

При обращении по запросам POST и PUT в тело запроса необходимо передать номер счета и сумму для пополнения баланса в формате JSON, соответственно.

## Использованные технологии

Приложение написано на Java 8. Для простоты использования в качестве базы данных использована H2. 

База данных состоит из трех таблиц <b>users</b>, <b>accounts</b> и <b>cards</b>. 

Инициализация схемы и предзаполнение таблиц происходит при запуске приложения.

---

### Примеры работы приложения

1.  <b>Выпуск новой карты по счету</b>
    
    Выполняется POST запрос по адресу http://localhost:9099/cards. 
    
    В тело запроса передается строка в JSON - формате `{"accountNumber":  "10100202139087645632"}`
    
    ![Создание новой карты по счету](https://github.com/maximgn/ft_project_bankAPI/blob/master/create_card.png "Создание новой карты по счету")
    
2.  <b>Просмотр списка карт</b>
    
    Выполняется GET запрос по адресу http://localhost:9099/cards/10100202139087645632. 
    
    ![Просмотр списка карт](https://github.com/maximgn/ft_project_bankAPI/blob/master/get_card.png "Просмотр списка карт")

3.  <b>Внесение средств</b>
    
    Выполняется PUT запрос по адресу http://localhost:9099/accounts/10100202139087645632. 
    
    В тело запроса передается строка в JSON - формате `{"sum":  "300"}`
    
    ![Внесение средств по счету](https://github.com/maximgn/ft_project_bankAPI/blob/master/update_balance.png "Внесение средств по счету")

4.  <b>Просмотр баланса</b>
    
    Выполняется GET запрос по адресу http://localhost:9099/accounts/10100202139087645632. 
    
    ![Просмотр баланса по счету](https://github.com/maximgn/ft_project_bankAPI/blob/master/update_balance.png "Просмотр баланса по счету")
