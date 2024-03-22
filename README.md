# Sprint 4
Финальный проект 4 спринта курса обучения Автоматизатор тестирования на Java
# Запуск
# Chrome
```bash
mvn test
```
# Firefox
Если mvn test падает с 500 ошибкой, и не находит бинарник, то нужно запустить с параметром 
-Dwebdriver.firefox.bin={PATH_TO_FIREFOX}
```bash
mvn -Dbrowser=firefox -Dwebdriver.firefox.bin={PATH_TO_FIREFOX} test
```
# Стек

java 11
maven 3.9.6
junit 4.13.2
selenium 4.18.1
webdrivermanage 5.7.0