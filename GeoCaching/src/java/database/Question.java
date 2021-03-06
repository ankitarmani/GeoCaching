/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Beatriz
 */
@Entity
@Table(name = "question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.findByDigitPosition",
        query = "SELECT q FROM Question q WHERE q.questionPK.digitPosition ="
        + " :digitPosition"),
    @NamedQuery(name = "Question.findByQuestion",
        query = "SELECT q FROM Question q WHERE q.question = :question"),
    @NamedQuery(name = "Question.findByWaypointIdwaypoint",
        query = "SELECT q FROM Question q WHERE "
        + "q.questionPK.waypointIdwaypoint = :waypointIdwaypoint") })
public class Question implements Serializable {
    /**
     * Max characters of question.
     */
    private static final int MAX_QUESTION = 500;
    /**
     * UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Question primary key.
     */
    @EmbeddedId
    private QuestionPK questionPK;
    /**
     * Question text.
     */
    @Size(max = MAX_QUESTION)
    @Column(name = "question")
    private String question;
    /**
     * Waypoint of the question.
     */
    @JoinColumn(name = "waypoint_idwaypoint",
            referencedColumnName = "idwaypoint",
            insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Waypoint waypoint;

    /**
     * Empty constructor.
     */
    public Question() {
    }

    /**
     * Set question primary key.
     * @param questionPK1 key.
     */
    public Question(final QuestionPK questionPK1) {
        this.questionPK = questionPK1;
    }

    /**
     * Constructor.
     * @param digitPosition position.
     * @param waypointIdwaypoint waypoint id.
     */
    public Question(final int digitPosition, final int waypointIdwaypoint) {
        this.questionPK = new QuestionPK(digitPosition, waypointIdwaypoint);
    }

    /**
     * Get question primary key.
     * @return key.
     */
    public final QuestionPK getQuestionPK() {
        return questionPK;
    }

    /**
     * Set Question Primary Key.
     * @param questionPK1 key.
     */
    public final void setQuestionPK(final QuestionPK questionPK1) {
        this.questionPK = questionPK1;
    }

    /**
     * Get question.
     * @return question.
     */
    public final String getQuestion() {
        return question;
    }

    /**
     * Set question.
     * @param question1 question text.
     */
    public final void setQuestion(final String question1) {
        this.question = question1;
    }

    /**
     * Get waypoint.
     * @return waypoint.
     */
    public final Waypoint getWaypoint() {
        return waypoint;
    }

    /**
     * Set waypoint to question.
     * @param waypoint1 waypoint.
     */
    public final void setWaypoint(final Waypoint waypoint1) {
        this.waypoint = waypoint1;
    }

    /**
     * HashCode.
     * @return HashCode.
     */
    @Override
    public final int hashCode() {
        int hash = 0;
        if (questionPK != null) {
            hash += questionPK.hashCode();
        } else {
            hash += 0;
        }
        return hash;
    }

    /**
     * Equals.
     * @param object .
     * @return equal.
     */
    @Override
    public final boolean equals(final Object object) {
        // Warning: this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.questionPK == null && other.questionPK != null)
                || (this.questionPK != null
                && !this.questionPK.equals(other.questionPK))) {
            return false;
        }
        return true;
    }

    /**
     * To string.
     * @return string.
     */
    @Override
    public final String toString() {
        return "database.Question[ questionPK=" + questionPK + " ]";
    }

}
