package com.anchorcms.cms.model.assist;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016/11/24
 * @Desc 评分记录
 */
@Entity
@Table(name = "c_score_record")
public class CmsScoreRecord implements Serializable{
    private static final long serialVersionUID = 2481332438630552254L;
    private int scoreRecordId;
    private int scoreItemId;
    private int contentId;
    private int scoreCount;

    @Id
    @Column(name = "score_record_id")
    public int getScoreRecordId() {
        return scoreRecordId;
    }

    public void setScoreRecordId(int scoreRecordId) {
        this.scoreRecordId = scoreRecordId;
    }

    @Basic
    @Column(name = "score_item_id")
    public int getScoreItemId() {
        return scoreItemId;
    }

    public void setScoreItemId(int scoreItemId) {
        this.scoreItemId = scoreItemId;
    }

    @Basic
    @Column(name = "content_id")
    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    @Basic
    @Column(name = "score_count")
    public int getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(int scoreCount) {
        this.scoreCount = scoreCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsScoreRecord that = (CmsScoreRecord) o;

        if (scoreRecordId != that.scoreRecordId) return false;
        if (scoreItemId != that.scoreItemId) return false;
        if (contentId != that.contentId) return false;
        if (scoreCount != that.scoreCount) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = scoreRecordId;
        result = 31 * result + scoreItemId;
        result = 31 * result + contentId;
        result = 31 * result + scoreCount;
        return result;
    }
}
