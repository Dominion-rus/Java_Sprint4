# Chrome
```bash
mvn test
```

# Firefox
если mvn test падает с 500 ошибкой, и не находит бинарник, то нужно запустить с параметром 
-Dwebdriver.firefox.bin={PATH_TO_FIREFOX}
```bash
mvn -Dbrowser=firefox -Dwebdriver.firefox.bin={PATH_TO_FIREFOX} test
```
