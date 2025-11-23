package utils;

public enum HeaderMenuItem {
    LOGIN("//a[text()='LOGIN']"),
    ADD("//a[text()='ADD']"),
    HOME("//a[text()='HOME']"),
    ABOUT("//a[text()='ABOUT']"),
    CONTACTS("//a[text()='CONTACTS']"),
    SIGN_OUT("//button[text()='Sign Out']")
    ;
    private final String locator;
    HeaderMenuItem(String locator){
        this.locator = locator;
    }
    public String getLocator(){
        return locator;
    }
}