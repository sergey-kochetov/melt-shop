package ru.com.melt.info.entity;

import org.hibernate.validator.constraints.SafeHtml;
import ru.com.melt.info.annotation.EnableFormErrorConvertation;
import ru.com.melt.info.annotation.constraints.EnglishLanguage;
import ru.com.melt.info.annotation.constraints.FirstFieldLessThanSecond;
import ru.com.melt.info.util.DataUtil;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "education")
@FirstFieldLessThanSecond(first = "beginYear", second = "finishYear")
@EnableFormErrorConvertation(formName = "educationForm", fieldReference = "finishYear", validationAnnotationClass = FirstFieldLessThanSecond.class)
public class Education extends AbstractEntity<Long> implements Serializable, ProfileEntity, Comparable<Education> {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "EDUCATION_ID_GENERATOR", sequenceName = "EDUCATION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EDUCATION_ID_GENERATOR")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, length = 255)
    @SafeHtml
    @EnglishLanguage(withSpechSymbols = false)
    private String faculty;

    @Column(nullable = false, length = 100)
    @SafeHtml
    @EnglishLanguage(withSpechSymbols = false)
    private String summary;

    @Column(nullable = false, length = 2147483647)
    @SafeHtml
    @EnglishLanguage(withSpechSymbols = false)
    private String university;

    @Column(name = "begin_year", nullable = false)
    private Integer beginYear;

    @Column(name = "finish_year")
    private Integer finishYear;

    // bi-directional many-to-one association to Profile
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile", nullable = false)
    private Profile profile;

    public Education() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFaculty() {
        return this.faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Integer getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(Integer beginYear) {
        this.beginYear = beginYear;
    }

    public Integer getFinishYear() {
        return finishYear;
    }

    public void setFinishYear(Integer finishYear) {
        this.finishYear = finishYear;
    }

    @Transient
    public boolean isFinish() {
        return finishYear != null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((beginYear == null) ? 0 : beginYear.hashCode());
        result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
        result = prime * result + ((finishYear == null) ? 0 : finishYear.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((summary == null) ? 0 : summary.hashCode());
        result = prime * result + ((university == null) ? 0 : university.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof Education))
            return false;
        Education other = (Education) obj;
        if (beginYear == null) {
            if (other.beginYear != null)
                return false;
        } else if (!beginYear.equals(other.beginYear))
            return false;
        if (faculty == null) {
            if (other.faculty != null)
                return false;
        } else if (!faculty.equals(other.faculty))
            return false;
        if (finishYear == null) {
            if (other.finishYear != null)
                return false;
        } else if (!finishYear.equals(other.finishYear))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (summary == null) {
            if (other.summary != null)
                return false;
        } else if (!summary.equals(other.summary))
            return false;
        if (university == null) {
            if (other.university != null)
                return false;
        } else if (!university.equals(other.university))
            return false;
        return true;
    }

    @Override
    public int compareTo(Education o) {
        int res = DataUtil.compareByFields(o.getFinishYear(), getFinishYear(), true);
        if (res == 0) {
            return DataUtil.compareByFields(o.getBeginYear(), getBeginYear(), true);
        } else {
            return res;
        }
    }
}