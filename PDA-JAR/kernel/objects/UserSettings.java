package com.infowave.pda.kernel.objects;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 16.03.2008
 * Time: 15:23:34
 * Paccage : com.infowave.pda.kernel.objects
 */
public class UserSettings {

    private String link_color;
    private String background_color;
    private String text_color;
    private String selection_color;
    private int items_on_page=4;

    private boolean show_hints;
    private boolean email_service;
    private boolean contact_service;
    private boolean event_service;
    private boolean note_service;
    private boolean favorites_service;
    private boolean task_service;
    private boolean wds_service;
    private boolean gds_service;
    private boolean bookmarks_service;
    

    public WeatherService getWeather_service() {
        return weather_service;
    }

    public void setWeather_service(WeatherService weather_service) {
        this.weather_service = weather_service;
    }

    public int getItems_on_page() {
        return items_on_page;
    }

    public void setItems_on_page(int items_on_page) {
        this.items_on_page = items_on_page;
    }

    private WeatherService weather_service;

    public UserSettings() {
        weather_service = new WeatherService();
    }

    public String getProtocolURL() {
        return  "hostXML==/pdaProtocol/request/services/getServiceListRequest.jsp;"+
                (email_service?"emailXML==/pdaProtocol/request/services/getMiniEmailList.jsp;":"")+
                (task_service?"taskXML==/pdaProtocol/request/services/getMiniTaskList.jsp;":"")+
                (event_service?"eventXML==/pdaProtocol/request/services/getMiniEventList.jsp;":"")+
                (note_service?"noteXML==/pdaProtocol/request/services/getMiniNoteList.jsp;":"")+
                (favorites_service?"favoritesXML==/pdaProtocol/request/favorites/getFavoritesList.jsp;":"")+
                (bookmarks_service?"bookmarkXML==/pdaProtocol/request/storage/getBookmarks.jsp;":"");
    }

    public String getLink_color() {
        return link_color;
    }

    public void setLink_color(String link_color) {
        this.link_color = link_color;
    }

    public String getBackground_color() {
        return background_color;
    }

    public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }

    public String getText_color() {
        return text_color;
    }

    public void setText_color(String text_color) {
        this.text_color = text_color;
    }

    public String getSelection_color() {
        return selection_color;
    }

    public void setSelection_color(String selection_color) {
        this.selection_color = selection_color;
    }

    public boolean isEmail_service() {
        return email_service;
    }

    public void setEmail_service(boolean email_service) {
        this.email_service = email_service;
    }

    public boolean isContact_service() {
        return contact_service;
    }

    public void setContact_service(boolean contact_service) {
        this.contact_service = contact_service;
    }

    public boolean isEvent_service() {
        return event_service;
    }

    public void setEvent_service(boolean event_service) {
        this.event_service = event_service;
    }

    public boolean isNote_service() {
        return note_service;
    }

    public void setNote_service(boolean note_service) {
        this.note_service = note_service;
    }

    public boolean isTask_service() {
        return task_service;
    }

    public void setTask_service(boolean task_service) {
        this.task_service = task_service;
    }

    public boolean isWds_service() {
        return wds_service;
    }

    public void setWds_service(boolean wds_service) {
        this.wds_service = wds_service;
    }

    public boolean isGds_service() {
        return gds_service;
    }

    public void setGds_service(boolean gds_service) {
        this.gds_service = gds_service;
    }

    public boolean isBookmarks_service() {
        return bookmarks_service;
    }

    public void setBookmarks_service(boolean bookmarks_service) {
        this.bookmarks_service = bookmarks_service;
    }

    public boolean isFavorites_service() {
        return favorites_service;
    }

    public void setFavorites_service(boolean favorites_service) {
        this.favorites_service = favorites_service;
    }

    public boolean isShow_hints() {
        return show_hints;
    }

    public void setShow_hints(boolean show_hints) {
        this.show_hints = show_hints;
    }
}
