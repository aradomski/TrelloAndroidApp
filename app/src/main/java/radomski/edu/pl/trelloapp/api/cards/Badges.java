
package radomski.edu.pl.trelloapp.api.cards;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

public class Badges {

    private Integer votes;
    private Boolean viewingMemberVoted;
    private Boolean subscribed;
    private String fogbugz;
    private Integer checkItems;
    private Integer checkItemsChecked;
    private Integer comments;
    private Integer attachments;
    private Boolean description;
    private Object due;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The votes
     */
    public Integer getVotes() {
        return votes;
    }

    /**
     * 
     * @param votes
     *     The votes
     */
    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    /**
     * 
     * @return
     *     The viewingMemberVoted
     */
    public Boolean getViewingMemberVoted() {
        return viewingMemberVoted;
    }

    /**
     * 
     * @param viewingMemberVoted
     *     The viewingMemberVoted
     */
    public void setViewingMemberVoted(Boolean viewingMemberVoted) {
        this.viewingMemberVoted = viewingMemberVoted;
    }

    /**
     * 
     * @return
     *     The subscribed
     */
    public Boolean getSubscribed() {
        return subscribed;
    }

    /**
     * 
     * @param subscribed
     *     The subscribed
     */
    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    /**
     * 
     * @return
     *     The fogbugz
     */
    public String getFogbugz() {
        return fogbugz;
    }

    /**
     * 
     * @param fogbugz
     *     The fogbugz
     */
    public void setFogbugz(String fogbugz) {
        this.fogbugz = fogbugz;
    }

    /**
     * 
     * @return
     *     The checkItems
     */
    public Integer getCheckItems() {
        return checkItems;
    }

    /**
     * 
     * @param checkItems
     *     The checkItems
     */
    public void setCheckItems(Integer checkItems) {
        this.checkItems = checkItems;
    }

    /**
     * 
     * @return
     *     The checkItemsChecked
     */
    public Integer getCheckItemsChecked() {
        return checkItemsChecked;
    }

    /**
     * 
     * @param checkItemsChecked
     *     The checkItemsChecked
     */
    public void setCheckItemsChecked(Integer checkItemsChecked) {
        this.checkItemsChecked = checkItemsChecked;
    }

    /**
     * 
     * @return
     *     The comments
     */
    public Integer getComments() {
        return comments;
    }

    /**
     * 
     * @param comments
     *     The comments
     */
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    /**
     * 
     * @return
     *     The attachments
     */
    public Integer getAttachments() {
        return attachments;
    }

    /**
     * 
     * @param attachments
     *     The attachments
     */
    public void setAttachments(Integer attachments) {
        this.attachments = attachments;
    }

    /**
     * 
     * @return
     *     The description
     */
    public Boolean getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(Boolean description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The due
     */
    public Object getDue() {
        return due;
    }

    /**
     * 
     * @param due
     *     The due
     */
    public void setDue(Object due) {
        this.due = due;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
