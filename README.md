Groep A
Klas 4

Eric Bull  
Burton Braam  
Wouter Swijnenburg  
Lucas Clavel  
Wessel Horsthuis

Scrumwise:  
https://www.scrumwise.com/scrum/#/people/project/2022-h-1-se-4a/id-175522-22381-148

Beoordeling:  
https://blackboard.hhs.nl/bbcswebdav/pid-3367108-dt-content-rid-33036864_2/courses/H-SE-S2-AOOP-2021/SE-S2%20-%20Project%202%20-%20Beoordelingsformulier%202122.pdf

One-Drive map:  
https://dehaagsehogeschool-my.sharepoint.com/:f:/g/personal/21093830_student_hhs_nl/EpiuZjWZwh9PrfppsXl0KWYBLZo_CawjPmvOkQ1xmDGHbg?e=SKByBj

Coverage command:  
mvn jacoco:prepare-agent test install jacoco:report

Puntensysteem excel document:
https://dehaagsehogeschool-my.sharepoint.com/:x:/r/personal/21093830_student_hhs_nl/Documents/PROJ2/H-1-SE%204A%20-%20Uitstoot%20Tabel.xlsx?d=we395177a3f804f67b41da75d29d8cf67&csf=1&web=1&e=NvRUB4

Laatst aangepast op 16-5-2022 15:36
Link ziet er gek uit maar werkt wel. Github verandert de visuals van sommige lettercombinaties


legacy code:
        new User("Testnaam Eric", "Eric", "Bull").getPoint().setPoints(2000);
        new User("Testnaam Burton", "Burton","Braam").getPoint().setPoints(20);
        new User("Testnaam Damnn...Daniël", "Wessel", "Horsthuis");
        new User("Testnaam Wouter", "jyn", "test").getPoint().setPoints(2500);
        new User("Testnaam Lucas", "Lucas", "Clavel").getPoint().setPoints(1500);
        new Transportmiddel("Benzine auto", 147, 50);       //147 g/km
        new Transportmiddel("Diesel auto", 179, 75);        //179 g/km
        new Transportmiddel("Elektrische auto", 87, 25);    //87 g/km
        new Transportmiddel("Openbaar Vervoer", 50, 10);    //50 g/km
        new Transportmiddel("Fiets/Lopen", 5, 0);           //5 g/km

        voorbeeld inlog
        User user = new User("Main man");
        user.setReis(0, new Reis("Thuis", Transportmiddel.getTransportmiddelen().get(1), 25));
        user.setReis(1, new Reis("Werk", Transportmiddel.getTransportmiddelen().get(3), 50));
        user.setReis(2, new Reis("School", Transportmiddel.getTransportmiddelen().get(4), 75));
        user.setReis(3, new Reis(null, null, null));
        user.setReis(4, new Reis(null, null, null));