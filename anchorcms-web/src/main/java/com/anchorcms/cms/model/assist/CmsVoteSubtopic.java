package com.anchorcms.cms.model.assist;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-9
 * @Desc CMS投票子标题
 */
@Entity
@Table(name = "c_vote_subtopic")
public class CmsVoteSubTopic implements Serializable{
    private static final long serialVersionUID = -773240023180890812L;
    private int subtopicId;
    private String title;
    private int votetopicId;
    private int subtopicType;
    private Integer priority;

    @Id
    @Column(name = "subtopic_id")
    public int getSubtopicId() {
        return subtopicId;
    }

    public void setSubtopicId(int subtopicId) {
        this.subtopicId = subtopicId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "votetopic_id")
    public int getVotetopicId() {
        return votetopicId;
    }

    public void setVotetopicId(int votetopicId) {
        this.votetopicId = votetopicId;
    }

    @Basic
    @Column(name = "subtopic_type")
    public int getSubtopicType() {
        return subtopicType;
    }

    public void setSubtopicType(int subtopicType) {
        this.subtopicType = subtopicType;
    }

    @Basic
    @Column(name = "priority")
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsVoteSubTopic that = (CmsVoteSubTopic) o;

        if (subtopicId != that.subtopicId) return false;
        if (votetopicId != that.votetopicId) return false;
        if (subtopicType != that.subtopicType) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (priority != null ? !priority.equals(that.priority) : that.priority != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subtopicId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + votetopicId;
        result = 31 * result + subtopicType;
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        return result;
    }

    private CmsVoteTopic voteTopic;

    private Set<CmsVoteItem> voteItems;
    @OneToMany
    @JoinColumn(name = "subtopic_id",insertable = false,updatable = false)
    public Set<CmsVoteItem> getVoteItems() {
        return voteItems;
    }

    public void setVoteItems(Set<CmsVoteItem> voteItems) {
        this.voteItems = voteItems;
    }
    @ManyToOne
    @JoinColumn(name = "votetopic_id",insertable = false,updatable = false)
    public CmsVoteTopic getVoteTopic() {
        return voteTopic;
    }

    public void setVoteTopic(CmsVoteTopic voteTopic) {
        this.voteTopic = voteTopic;
    }
}
