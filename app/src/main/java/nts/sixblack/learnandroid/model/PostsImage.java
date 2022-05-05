package nts.sixblack.learnandroid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsImage {
    private long postsImageId;
    private String image;
    //    private Date dateCreate;
    private String dateCreate;
}