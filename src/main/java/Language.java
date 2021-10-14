public class Language {
    private String countryCode;
    private String language;
    private boolean isOfficial;

    public String getCountryCode() {
        return countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isOfficial() {
        return isOfficial;
    }

    public Language convertToLanguage(String[] text){
        this.countryCode = text[0];
        this.language = text[1];
        if(text[2].contains("TRUE")) {
            this.isOfficial = true;
        }else{
            this.isOfficial = false;
        }
        return this;
    }
}
