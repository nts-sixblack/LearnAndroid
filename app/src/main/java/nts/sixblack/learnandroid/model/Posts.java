package nts.sixblack.learnandroid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posts implements Serializable {
//    private int postsId;
    private String caption;
//    private int totalFeel;
//    private int totalComment;
//    private String dateCreate;
    private String postsUser;
    private String postsImage;
}
