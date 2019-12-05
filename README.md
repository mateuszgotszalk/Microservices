# Microservice
Projekt Mikroserwisu

I Uruchomienie kontenerów:
  1. Uruchomić terminal w folderze z projektami i plikami docker. 
  
  2. Włączyć maszyne docker za pomocą polecenia:
      docker-machine start default
    Następnie: 
      docker-machine env
      
  3. Po uruchomienu maszyny można uruchomić skrypt budujący kontenery:
      docker-compose up
      Aplikacja buduje się około 15-20 min
      
  4. Otwierami kolejny terminal i sprawdzamy ip maszyny np.
      docker-machine ip
      
  5. Po uruchomieniu maszyny można wykonywać zapytania na adresach:
      (ip maszyny):3301/getCredits 
      (ip maszyny):3301/createCredits
      Dane wejściowe i wyjściowe są w formacie JSON.
      
  6. Przykładowe wywołania:
  
    (ip maszyny):3301/createCredits :

  	{
	"firstName": "Maja2",
	"surName": "Smerf2",
	"pesel": "9204055122",
	"productName": "DomJednorodzinny5",
	"productValue": 560000,
	"creditName": "NaDomJednorodzinny5"
  	}
  
  Odpowiedź: 18
  
      (ip maszyny):3301/getCredits :
      
      {
        "id": 8,
        "creditName": "Mercedes",
        "firstName": "Mateusz",
        "surName": "Gotszalk",
        "pesel": "9511194232",
        "productName": "Samochod",
        "productValue": 120000
    },
    {
        "id": 9,
        "creditName": "NoweMieszakanie",
        "firstName": "Aleksandra",
        "surName": "Kalityn",
        "pesel": "9802114232",
        "productName": "Mieszaknie",
        "productValue": 350000
    },
    {
        "id": 10,
        "creditName": "NaAparat",
        "firstName": "Bartosz",
        "surName": "Zajewski",
        "pesel": "940101909091",
        "productName": "Aparat",
        "productValue": 19000
    },
    {
        "id": 11,
        "creditName": "NaCiezarowke31",
        "firstName": "Andrzej",
        "surName": "Nowak",
        "pesel": "7832413",
        "productName": "Ciezarowka31",
        "productValue": 55000
    },
    {
        "id": 12,
        "creditName": "Test987",
        "firstName": "Marcin",
        "surName": "Dawid",
        "pesel": "97011598974",
        "productName": "Samochod987",
        "productValue": 40000
    },
    {
        "id": 18,
        "creditName": "NaDomJednorodzinny5",
        "firstName": "Maja2",
        "surName": "Smerf2",
        "pesel": "9204055122",
        "productName": "DomJednorodzinny5",
        "productValue": 560000
    }
