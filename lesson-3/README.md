    Напишите HTTPServer принимающий методом POST 2 параметра login и password по протоколу HTTP.

    Если передан login равный слову java, то возвращать в ответ "Hello Java". 
    В противном случае бросать исключение InvalidLogin. Исключение InvalidLogin наследовать от RuntimeException.

    В качестве клиента для проверки используйте HTTPClient и асинхронный вызов.