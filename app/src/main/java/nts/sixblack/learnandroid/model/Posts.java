package nts.sixblack.learnandroid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posts implements Serializable {
    private int postsId;
    private String caption;
    private int totalFeel;
    private int totalComment;
    private String dateCreate;
    private boolean feel;

    private List<PostsUser> postsUserList;
    private List<PostsComment> postsCommentList;
    private List<PostsFeel> postsFeelList;
    private List<PostsImage> postsImageList;
}
