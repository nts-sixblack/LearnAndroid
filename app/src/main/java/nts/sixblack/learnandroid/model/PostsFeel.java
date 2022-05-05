package nts.sixblack.learnandroid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsFeel {
    private long postsFeelId;
    private boolean feel;
    private long postsId;
    //    private Date dateCreate;
    private String dateCreate;

}
