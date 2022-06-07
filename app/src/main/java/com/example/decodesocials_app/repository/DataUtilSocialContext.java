package com.example.decodesocials_app.repository;

public class DataUtilSocialContext {

    public static String[] questionsForSocialContexts = {
            "Vad har hänt?",
            "Hur kändes det?",
            "Varför tror du att det hände?",
            "Kan det finnas andra anledningar till att det hände?"
    };
    public static String[] subjects = {
            "HÄNDELSE: ",
            "KÄNSLOR: ",
            "VARFÖR: ",
            "ORSAKER: "
    };
    public static String[][] alternatives = {
            {"Någon sa något till mig", "Någon gjorde något mot mig", "Någon lyssnade inte på mig", "Någon tittade på mig så att det inte kändes bra"},
            {"Jag blev ledsen/nedstämd", "Jag blev arg", "Jag fick skamkänslor", "Jag reagerade inte som jag ville"},
            {"Jag tror att personen ogillar mig", "Jag tror att personen ignorerade mig", "Jag tror att personen är arg över något/på mig", "Jag tror att personen försökte säga något till mig"},
            {"Personen kan ha varit stressad/haft bråttom", "Personen kan ha varit hungrig eller sovit dåligt", "Personen kan bara ha sett ut så i sitt ansiktsuttryck", "Ja, det finns säkert andra anledningar"}
    };
}

