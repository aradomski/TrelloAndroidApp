package radomski.edu.pl.trelloapp.api.boards;

/**
 * Created by adam on 7/6/15.
 */
public class TroelloList {

    private String id;
    private String name;
    private Boolean closed;
    private String idBoard;
    private Integer pos;
    private Object subscribed;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The closed
     */
    public Boolean getClosed() {
        return closed;
    }

    /**
     * @param closed The closed
     */
    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    /**
     * @return The idBoard
     */
    public String getIdBoard() {
        return idBoard;
    }

    /**
     * @param idBoard The idBoard
     */
    public void setIdBoard(String idBoard) {
        this.idBoard = idBoard;
    }

    /**
     * @return The pos
     */
    public Integer getPos() {
        return pos;
    }

    /**
     * @param pos The pos
     */
    public void setPos(Integer pos) {
        this.pos = pos;
    }

    /**
     * @return The subscribed
     */
    public Object getSubscribed() {
        return subscribed;
    }

    /**
     * @param subscribed The subscribed
     */
    public void setSubscribed(Object subscribed) {
        this.subscribed = subscribed;
    }


}
