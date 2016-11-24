package com.anchorcms.cms.model.assist;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016/11/24
 * @Desc 评分项
 */
@Entity
@Table(name = "c_score_item")
public class CmsScoreItem implements Serializable{
    private static final long serialVersionUID = -7284836054656726782L;
    private int scoreItemId;
    private int scoreGroupId;
    private String name;
    private int score;
    private String imagePath;
    private int priority;

    @Id
    @Column(name = "score_item_id")
    public int getScoreItemId() {
        return scoreItemId;
    }

    public void setScoreItemId(int scoreItemId) {
        this.scoreItemId = scoreItemId;
    }

    @Basic
    @Column(name = "score_group_id")
    public int getScoreGroupId() {
        return scoreGroupId;
    }

    public void setScoreGroupId(int scoreGroupId) {
        this.scoreGroupId = scoreGroupId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Basic
    @Column(name = "image_path")
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Basic
    @Column(name = "priority")
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsScoreItem that = (CmsScoreItem) o;

        if (scoreItemId != that.scoreItemId) return false;
        if (scoreGroupId != that.scoreGroupId) return false;
        if (score != that.score) return false;
        if (priority != that.priority) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (imagePath != null ? !imagePath.equals(that.imagePath) : that.imagePath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = scoreItemId;
        result = 31 * result + scoreGroupId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + score;
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + priority;
        return result;
    }
    private CmsScoreGroup group;
    private Set<CmsScoreRecord> records;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "score_item_id")
    public Set<CmsScoreRecord> getRecords() {
        return records;
    }

    public void setRecords(Set<CmsScoreRecord> records) {
        this.records = records;
    }

    @ManyToOne
    @JoinColumn(name = "score_group_id",insertable = false,updatable = false)
    public CmsScoreGroup getGroup() {
        return group;
    }

    public void setGroup(CmsScoreGroup group) {
        this.group = group;
    }
}
