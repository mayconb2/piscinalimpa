package br.com.unicesumar.piscinalimpa.dto;

public class TrelloCardSugestionForm {

    private String name;
    private String pos = "top";
    private String desc;

    public TrelloCardSugestionForm() {
    }

    public TrelloCardSugestionForm(String name, String pos, String desc) {
        this.name = name;
        this.pos = pos;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
