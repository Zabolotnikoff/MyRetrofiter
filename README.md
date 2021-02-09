Focus Start

Итоговое задание.
Необходимо реализовать упрощённое приложение - личный кабинет заёмщика.
Заёмщик вводит логин/пароль и авторизуется на сервене, либо имеет возможность на нём зарегистрироваться.
После регистрации он имеет возможность выбрать что ему нужно сделать дальше. Клиент может оформить новую кредитную заявку, 
параметры которой должны соответствовать параметрам загруженным с сервера (срок, ставка - равны, сумма - не более). После 
оформления заявки должна быть показана детальная информация по ней, в т.ч. статус (на рассмотрении, согласовано, отказ)
Так же должна быть возможность посмотреть на список всех поданных ранее заявок, и при желании, детализацию по каждой из них.
Требований к архитектуре особых не было, однако просили не скидывать всё в кучу, и постараться сделать хоть какое-то её подобие, 
как минимум давать нормальные имена переменным, константам, классам, интерфейсам, модулям и пр. При повороте экрана приложение не должно лагать.
Под это дело был создан учебный сервер который обрабатывал запросы, хранил регистрационны данные, и данные о кредитных заявках.
Была дана ссылка на его API. Авторизация на сервере происходит с использованием бессрочного токена. 
Ограничений на логин и пароль не было. Формат возвращаемых сервером данных - Json.
Так же предоставлены модели данных используемые при запросах и ответах сервера (оформлены дата-классами).

Моя реализация предполагала использование MVVM. На фичи не разбивал (хотя, лучше было-бы разбить, но всё равно не успел доделать).
Все запросы к серверу оформлены в соответствии с предложенным API (сервер уже снесли, поэтому проверить сейчас их работоспособность уже не получится).
Завершить проект я не успел - не сделал процедуру оформления заявки на кредит. Список заявок на кредит и их детализация работала корректно.