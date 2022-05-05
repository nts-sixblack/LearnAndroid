package nts.sixblack.learnandroid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsUser {
    private long postsUserId;
    private long userId;
    private String name;
    private String image;
    //    private Date dateCreate;
    private String dateCreate;
}