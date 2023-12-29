package com.mjc.school.repository.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "findAllNewsByTagIds", query = """
                SELECT n FROM News n
                LEFT JOIN n.tags t
                WHERE t.id IN (:ids)
                GROUP BY n
                HAVING COUNT(n) >= :numberTags
                """),
        @NamedQuery(name = "findAllNewsByTagNames", query = """
                SELECT n FROM News n
                LEFT JOIN n.tags t
                WHERE t.name IN (:names)
                GROUP BY n
                HAVING COUNT(n) >= :numberTags
                  """),
})
public class News implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author")
    private Author author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "news_tags",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    public News(String title,
                String content,
                Author author,
                List<Tag> tags) {
        this.title = title;
        this.content = content;
        this.createDate = LocalDateTime.now();
        this.lastUpdateDate = LocalDateTime.now();
        this.author = author;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}